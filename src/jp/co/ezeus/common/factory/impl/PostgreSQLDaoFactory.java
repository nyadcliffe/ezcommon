package jp.co.ezeus.common.factory.impl;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import jp.co.ezeus.common.dao.CommonDao;
import jp.co.ezeus.common.dao.CommonPluralDao;
import jp.co.ezeus.common.dao.impl.CommonDaoImpl;
import jp.co.ezeus.common.dao.impl.CommonPluralDaoImple;
import jp.co.ezeus.common.factory.AbstractDaoFactory;

/**
 * <p>
 * DaoFactoryの実装クラス（PostgreSQL）
 * </p>
 * DAOクラス生成メソッドは"create一意名Dao"メソッドを作成し使用する。<br>
 * DAOクラス作成メソッドは@see {@link AbstractDaoFactory} インターフェースへ記載追加を
 * する。JNDI経由でのDataSource取得にはgetDataSource()を使用する。<br>
 * RDBMS製品を変更する場合は、当クラスと同列のクラスを作成すること。
 *
 * @see AbstractDaoFactory
 * @author H.Abe
 * @version 1.0
 *
 */
public class PostgreSQLDaoFactory implements AbstractDaoFactory {

    /**
     * DataSourceをJNDI経由で取得する際に必要なDataSource名。
     */
    private final String SOURCE_NAME = "java:comp/env/jdbc/postgres";

    /**
     * 共通DAO実装クラスを生成する。（トランザクション管理無）
     *
     * @param dataSource データソース
     * @return CommonDao
     * @author H.Abe
     */
    public CommonDao createCommonDao(DataSource dataSource) throws Exception {
        return new CommonDaoImpl(dataSource);
    }

    /**
     * 共通DAO実装クラスを生成する。（トランザクション管理有）
     *
     * @param dataSource データソース
     * @return CommonDao
     * @author H.Abe
     */
    public CommonPluralDao createCommonPluralDao(DataSource dataSource) throws Exception {
        return new CommonPluralDaoImple(dataSource);
    }

    /**
     * JNDI経由でDataSourceを取得する。
     *
     * @return DataSource
     * @author H.Abe
     */
    public DataSource getDataSource() throws Exception {
        InitialContext context = null;
        DataSource source = null;
        try {
            context = new InitialContext();
            source = (DataSource) context.lookup(SOURCE_NAME);
        } catch (NamingException exception) {
            if (null != context) {
                try {
                    context.close();
                } catch (NamingException closeException) {
                    throw new Exception(closeException);
                }
            }
            throw new Exception(exception);
        }
        return source;
    }
}
