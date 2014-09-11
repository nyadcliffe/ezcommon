package jp.co.ezeus.common.rdbControl.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jp.co.ezeus.common.dto.BindingDto;
import jp.co.ezeus.common.rdbControl.CommonRDBControl;
import jp.co.ezeus.common.utilities.UtilDefine;

/**
 * <p>
 * 共通データアクセス処理実装クラス
 * </p>
 * Connectionの取得、SQL発行、結果値格納、 リソースの解放をする共通処理を集約。<br>
 * 使用する際にはコンストラクタにConnectionを引数として当クラス を生成する。
 *
 * @see CommonRDBControl
 * @author H.Abe
 * @version 1.0
 *
 */
public class CommonRDBControlImpl implements CommonRDBControl {

    /**
     * コネクション
     */
    private Connection connection;

    /**
     * コンストラクタ
     *
     * @param connection コネクション
     * @author H.Abe
     */
    public CommonRDBControlImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * 共通SELECT文発行処理を行う。（バインド変数無し） 引数のSELECT文を発行し、結果セットをListにて返却する。<br>
     * 引数のSQL文はWHERE句に設定するバインド変数を持たないことが前提。
     *
     * @param query SQL文
     * @return List<Map<String, Object>>
     * @throws SQLException
     * @author H.Abe
     */
    public List<Map<String, Object>> executeCommonSelectByQuery(String query) throws SQLException {
        return executeCommonSelectByBindQuery(query, null);
    }

    /**
     * 共通SELECT文発行処理を行う。（バインド変数有り） 引数のSELECT文を発行し、結果セットをListにて返却する。<br>
     * 引数のListはバインド変数。
     *
     * @param query SQL文
     * @param binds バインド変数
     * @return List<Map<String, Object>>
     * @throws SQLException
     * @author H.Abe
     */
    public List<Map<String, Object>> executeCommonSelectByBindQuery(String query, List<BindingDto> binds) throws SQLException {
        PreparedStatement preparedStatement = this.connection.prepareStatement(query);
        if (null != binds) {
            for (int i = 0; i < binds.size(); i++) {
                if (UtilDefine.INT_ARGS_TYPE.equals(binds.get(i).getArgsType())) {
                    preparedStatement.setInt(i, binds.get(i).getIntValue());
                } else if (UtilDefine.STRING_ARGS_TYPE.equals(binds.get(i).getArgsType())) {
                    preparedStatement.setString(i, binds.get(i).getStringValue());
                } else if (UtilDefine.CHAR_ARGS_TYPE.equals(binds.get(i).getArgsType())) {
                    preparedStatement.setString(i, binds.get(i).getCharValue());
                } else if (UtilDefine.DATE_ARGS_TYPE.equals(binds.get(i).getArgsType())) {
                    preparedStatement.setDate(i, binds.get(i).getDateValue());
                }
            }
        }
        ResultSet resultSet = preparedStatement.executeQuery();
        int columnCount = 0;
        String columnName = null;
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        while (resultSet.next()) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            columnCount = metaData.getColumnCount();
            Map<String, Object> resultMap = new HashMap<String, Object>();
            for (int i = 0; i < columnCount; i++) {
                columnName = metaData.getColumnName(i + 1);
                resultMap.put(columnName, resultSet.getObject(columnName));
            }
            resultList.add(resultMap);
        }
        close(preparedStatement);
        close(resultSet);
        return resultList;
    }

    /**
     * 共通DML文発行処理を行う。（バインド変数無し） 引数のDML文を発行し、結果セットをint型にて返却する。<br>
     * 引数のSQL文はWHERE句に設定するバインド変数を持たないことが前提。 DML文は（INSERT、UPDATE、DELETE）に対応する。
     *
     * @param query SQL文
     * @return int
     * @throws SQLException
     * @author H.Abe
     */
    public int executeCommonDMLByQuery(String query) throws SQLException {
        return executeCommonDMLByBindQuery(query, null);
    }

    /**
     * 共通DML文発行処理を行う。（バインド変数有り） 引数のDML文を発行し、結果セットをint型にて返却する。<br>
     * 引数のListはバインド変数。 DML文は（INSERT、UPDATE、DELETE）に対応する。
     *
     * @param query SQL文
     * @param binds バインド変数
     * @return int
     * @throws SQLException
     * @author H.Abe
     */
    public int executeCommonDMLByBindQuery(String query, List<BindingDto> binds) throws SQLException {
        this.connection.setAutoCommit(false);
        PreparedStatement preparedStatement = this.connection.prepareStatement(query);
        if (null != binds) {
            for (int i = 0; i < binds.size(); i++) {
                if (UtilDefine.INT_ARGS_TYPE.equals(binds.get(i).getArgsType())) {
                    preparedStatement.setInt(i, binds.get(i).getIntValue());
                } else if (UtilDefine.STRING_ARGS_TYPE.equals(binds.get(i).getArgsType())) {
                    preparedStatement.setString(i, binds.get(i).getStringValue());
                } else if (UtilDefine.CHAR_ARGS_TYPE.equals(binds.get(i).getArgsType())) {
                    preparedStatement.setString(i, binds.get(i).getCharValue());
                } else if (UtilDefine.DATE_ARGS_TYPE.equals(binds.get(i).getArgsType())) {
                    preparedStatement.setDate(i, binds.get(i).getDateValue());
                }
            }
        }
        int result = 0;
        result = preparedStatement.executeUpdate();
        close(preparedStatement);
        return result;
    }

    /**
     * Connectionのリソースを解放する。
     *
     * @param connection コネクション
     * @throws SQLException
     * @author H.Abe
     */
    public void close(Connection connection) throws SQLException {
        if (null != connection) {
            connection.close();
        }
    }

    /**
     * PreparedStatementのリソースを解放する。
     *
     * @param preparedStatement PreparedStatementオブジェクト
     * @throws SQLException
     * @author H.Abe
     */
    public void close(PreparedStatement preparedStatement) throws SQLException {
        if (null != preparedStatement) {
            preparedStatement.close();
        }
    }

    /**
     * ResultSetのリソースを解放する。
     *
     * @param resultSet ResultSetオブジェクト
     * @throws SQLException
     * @author H.Abe
     */
    public void close(ResultSet resultSet) throws SQLException {
        if (null != resultSet) {
            resultSet.close();
        }
    }

    /**
     * 共通SELECT文発行処理を行う。（バインド変数無し） 引数のSELECT文を発行し、結果セットを返却する。<br>
     * 引数のSQL文はWHERE句に設定するバインド変数を持たないことが前提。
     *
     * @param query SQL文
     * @return ResultSet
     * @throws SQLException
     * @author H.Abe
     */
    public ResultSet getResultSetBySelectQuery(String query) throws SQLException {
        return getResultSetBySelectBindQuery(query, null);
    }

    /**
     * 共通SELECT文発行処理を行う。（バインド変数有り） 引数のSELECT文を発行し、結果セットを返却する。<br>
     * 引数のListはバインド変数。
     *
     * @param query SQL文
     * @param binds バインド変数
     * @return ResultSet
     * @throws SQLException
     * @author H.Abe
     */
    public ResultSet getResultSetBySelectBindQuery(String query, List<BindingDto> binds) throws SQLException {
        PreparedStatement preparedStatement = this.connection.prepareStatement(query);
        if (null != binds) {
            for (int i = 0; i < binds.size(); i++) {
                if (UtilDefine.INT_ARGS_TYPE.equals(binds.get(i).getArgsType())) {
                    preparedStatement.setInt(i, binds.get(i).getIntValue());
                } else if (UtilDefine.STRING_ARGS_TYPE.equals(binds.get(i).getArgsType())) {
                    preparedStatement.setString(i, binds.get(i).getStringValue());
                } else if (UtilDefine.CHAR_ARGS_TYPE.equals(binds.get(i).getArgsType())) {
                    preparedStatement.setString(i, binds.get(i).getCharValue());
                } else if (UtilDefine.DATE_ARGS_TYPE.equals(binds.get(i).getArgsType())) {
                    preparedStatement.setDate(i, binds.get(i).getDateValue());
                }
            }
        }
        ResultSet resultSet = preparedStatement.executeQuery();
        close(preparedStatement);
        return resultSet;
    }
}
