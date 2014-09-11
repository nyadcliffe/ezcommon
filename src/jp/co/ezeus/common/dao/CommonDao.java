package jp.co.ezeus.common.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import jp.co.ezeus.common.dto.BindingDto;

/**
 * <p>
 * 共通DAOインターフェース（トランザクション管理有）
 * </p>
 * 共通DAOを作成する際には当クラスに記載する。
 *
 * @author H.Abe
 * @version 1.0
 *
 */
public interface CommonDao {

    public abstract List<Map<String, Object>> commonSelectByQuery(String query) throws SQLException;

    public abstract List<Map<String, Object>> commonSelectByBindQuery(String query, List<BindingDto> binds) throws SQLException;

    public abstract void commonUpdateByQuery(String query) throws SQLException;

    public abstract void commonUpdateByBindQuery(String query, List<BindingDto> binds) throws SQLException;

    public abstract void commonInsertByQuery(String query) throws SQLException;

    public abstract void commonInsertByBindQuery(String query, List<BindingDto> binds) throws SQLException;

    public abstract void commonDeleteByQuery(String query) throws SQLException;

    public abstract void commonDeleteByBindQuery(String query, List<BindingDto> binds) throws SQLException;

    public abstract ResultSet commonGetResultSetSelectByQuery(String query) throws SQLException;

    public abstract ResultSet commonGetResultSetSelectByBindQuery(String query, List<BindingDto> binds) throws SQLException;
}
