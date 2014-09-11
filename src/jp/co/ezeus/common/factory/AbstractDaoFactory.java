package jp.co.ezeus.common.factory;

import javax.sql.DataSource;
import jp.co.ezeus.common.dao.CommonDao;
import jp.co.ezeus.common.dao.CommonPluralDao;

/**
 * <p>
 * DaoFactoryインターフェース
 * </p>
 * DAOのFactoryクラスを作成する際には必ず実装する。<br>
 * 当インターフェースを実装するクラスの作成単位はRDBMS製品毎になる。<br>
 * 実装するDAOのFactoryクラスでのDAOクラス生成メソッドは必ず当インターフェース で定義をする。
 *
 * @author H.Abe
 * @version 1.0
 *
 */
public interface AbstractDaoFactory {

    public abstract DataSource getDataSource() throws Exception;

    public abstract CommonDao createCommonDao(DataSource dataSource) throws Exception;

    public abstract CommonPluralDao createCommonPluralDao(DataSource dataSource) throws Exception;
}
