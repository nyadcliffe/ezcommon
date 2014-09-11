package jp.co.ezeus.common.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import jp.co.ezeus.common.dao.CommonDao;
import jp.co.ezeus.common.dto.BindingDto;
import jp.co.ezeus.common.rdbControl.CommonRDBControl;
import jp.co.ezeus.common.rdbControl.impl.CommonRDBControlImpl;

/**
 * <p>
 * 共通DAO実装クラス （トランザクション管理有）
 * </p>
 * SELECT文、DML文（INSERT、UPDATE、DELETE）を実行する 共通データアクセス 処理実装クラス@see
 * {@link CommonRDBControlImpl}を呼び出す。<br>
 * 当クラスを生成の際には、 DaoFactoryインターフェース@see
 * {@link jp.co.ezeus.common.factory.AbstractDaoFactory}を実装するクラスより生成すること。<br>
 * ConnectionのCLOSEを当クラスで行うため、SQLを単発発行の場合に使用する。
 *
 * @see {CommonPluralDaoImple}トランザクション管理無の実装クラス
 *
 * @author H.Abe
 * @version 1.0
 */
public class CommonDaoImpl implements CommonDao {

    private DataSource dataSource;

    private CommonRDBControl rdbControl;

    private Connection connection;

    public CommonDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 共通SELECT文発行処理を呼び出す。（バインド変数無し） 結果セットをListにて返却する。<br>
     * 引数のSQL文はWHERE句に設定するバインド変数を持たないことが前提。
     *
     * @param query SQL文
     * @return List<Map<String, Object>>
     * @throws SQLException
     * @author H.Abe
     */
    public List<Map<String, Object>> commonSelectByQuery(String query) throws SQLException {
        connection = this.dataSource.getConnection();
        rdbControl = new CommonRDBControlImpl(connection);
        List<Map<String, Object>> resultList = null;
        try {
            resultList = rdbControl.executeCommonSelectByQuery(query);
        } catch (SQLException sqlException) {
            throw new SQLException(sqlException);
        } finally {
            rdbControl.close(connection);
        }
        return resultList;
    }

    /**
     * 共通SELECT文発行処理を呼び出す。（バインド変数有り） 結果セットをListにて返却する。<br>
     * 引数のListはバインド変数。
     *
     * @param query SQL文
     * @param binds バインド変数
     * @return List<Map<String, Object>>
     * @throws SQLException
     * @author H.Abe
     */
    public List<Map<String, Object>> commonSelectByBindQuery(String query, List<BindingDto> binds) throws SQLException {
        connection = this.dataSource.getConnection();
        rdbControl = new CommonRDBControlImpl(connection);
        List<Map<String, Object>> resultList = null;
        try {
            resultList = rdbControl.executeCommonSelectByBindQuery(query, binds);
        } catch (SQLException sqlException) {
            throw new SQLException(sqlException);
        } finally {
            rdbControl.close(connection);
        }
        return resultList;
    }

    /**
     * 共通DML文発行処理にてUPDATE文の発行処理を行う。（バインド変数無し）
     * 引数のSQL文はWHERE句に設定するバインド変数を持たないことが前提。
     *
     * @param query SQL文
     * @throws SQLException
     * @author H.Abe
     */
    public void commonUpdateByQuery(String query) throws SQLException {
        connection = this.dataSource.getConnection();
        rdbControl = new CommonRDBControlImpl(connection);
        try {
            int result = rdbControl.executeCommonDMLByQuery(query);
            if (result > 0) {
                connection.commit();
            }
        } catch (SQLException sqlException) {
            connection.rollback();
            throw new SQLException(sqlException);
        } finally {
            rdbControl.close(connection);
        }
    }

    /**
     * 共通DML文発行処理にてUPDATE文の発行処理を行う。（バインド変数有り） 引数のListはバインド変数。
     *
     * @param query SQL文
     * @param binds バインド変数
     * @throws SQLException
     * @author H.Abe
     */
    public void commonUpdateByBindQuery(String query, List<BindingDto> binds) throws SQLException {
        connection = this.dataSource.getConnection();
        rdbControl = new CommonRDBControlImpl(connection);
        try {
            int result = rdbControl.executeCommonDMLByBindQuery(query, binds);
            if (result > 0) {
                connection.commit();
            }
        } catch (SQLException sqlException) {
            connection.rollback();
            throw new SQLException(sqlException);
        } finally {
            rdbControl.close(connection);
        }
    }

    /**
     * 共通DML文発行処理にてINSERT文の発行処理を行う。（バインド変数無し）
     * 引数のSQL文はWHERE句に設定するバインド変数を持たないことが前提。
     *
     * @param query SQL文
     * @throws SQLException
     * @author H.Abe
     */
    public void commonInsertByQuery(String query) throws SQLException {
        connection = this.dataSource.getConnection();
        rdbControl = new CommonRDBControlImpl(connection);
        try {
            int result = rdbControl.executeCommonDMLByQuery(query);
            if (result > 0) {
                connection.commit();
            }
        } catch (SQLException sqlException) {
            connection.rollback();
            throw new SQLException(sqlException);
        } finally {
            rdbControl.close(connection);
        }
    }

    /**
     * 共通DML文発行処理にてINSERT文の発行処理を行う。（バインド変数有り） 引数のListはバインド変数。
     *
     * @param query SQL文
     * @param binds バインド変数
     * @throws SQLException
     * @author H.Abe
     */
    public void commonInsertByBindQuery(String query, List<BindingDto> binds) throws SQLException {
        connection = this.dataSource.getConnection();
        rdbControl = new CommonRDBControlImpl(connection);
        try {
            int result = rdbControl.executeCommonDMLByBindQuery(query, binds);
            if (result > 0) {
                connection.commit();
            }
        } catch (SQLException sqlException) {
            connection.rollback();
            throw new SQLException(sqlException);
        } finally {
            rdbControl.close(connection);
        }
    }

    /**
     * 共通DML文発行処理にてDELETE文の発行処理を行う。（バインド変数無し）
     * 引数のSQL文はWHERE句に設定するバインド変数を持たないことが前提。
     *
     * @param query SQL文
     * @throws SQLException
     * @author H.Abe
     */
    public void commonDeleteByQuery(String query) throws SQLException {
        connection = this.dataSource.getConnection();
        rdbControl = new CommonRDBControlImpl(connection);
        try {
            int result = rdbControl.executeCommonDMLByQuery(query);
            if (result > 0) {
                connection.commit();
            }
        } catch (SQLException sqlException) {
            connection.rollback();
            throw new SQLException(sqlException);
        } finally {
            rdbControl.close(connection);
        }
    }

    /**
     * 共通DML文発行処理にてDELETE文の発行処理を行う。（バインド変数有り） 引数のListはバインド変数。
     *
     * @param query SQL文
     * @param binds バインド変数
     * @throws SQLException
     * @author H.Abe
     */
    public void commonDeleteByBindQuery(String query, List<BindingDto> binds) throws SQLException {
        connection = this.dataSource.getConnection();
        rdbControl = new CommonRDBControlImpl(connection);
        try {
            int result = rdbControl.executeCommonDMLByBindQuery(query, binds);
            if (result > 0) {
                connection.commit();
            }
        } catch (SQLException sqlException) {
            connection.rollback();
            throw new SQLException(sqlException);
        } finally {
            rdbControl.close(connection);
        }
    }

    /**
     * 共通SELECT文発行処理を呼び出す。（バインド変数無し） 結果セットを返却する。<br>
     * 引数のSQL文はWHERE句に設定するバインド変数を持たないことが前提。
     *
     * @param query SQL文
     * @return ResultSet
     * @throws SQLException
     * @author H.Abe
     */
    public ResultSet commonGetResultSetSelectByQuery(String query) throws SQLException {
        connection = this.dataSource.getConnection();
        rdbControl = new CommonRDBControlImpl(connection);
        ResultSet resultSet = null;
        try {
            resultSet = rdbControl.getResultSetBySelectQuery(query);
        } catch (SQLException sqlException) {
            throw new SQLException(sqlException);
        } finally {
            rdbControl.close(connection);
        }
        return resultSet;
    }

    /**
     * 共通SELECT文発行処理を呼び出す。（バインド変数有り） 結果セットを返却する。<br>
     * 引数のListはバインド変数。
     *
     * @param query SQL文
     * @param binds バインド変数
     * @return ResultSet
     * @throws SQLException
     * @author H.Abe
     */
    public ResultSet commonGetResultSetSelectByBindQuery(String query, List<BindingDto> binds) throws SQLException {
        connection = this.dataSource.getConnection();
        rdbControl = new CommonRDBControlImpl(connection);
        ResultSet resultSet = null;
        try {
            resultSet = rdbControl.getResultSetBySelectBindQuery(query, binds);
        } catch (SQLException sqlException) {
            throw new SQLException(sqlException);
        } finally {
            rdbControl.close(connection);
        }
        return resultSet;
    }

}
