package jp.co.ezeus.common.rdbControl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import jp.co.ezeus.common.dto.BindingDto;

/**
 * <p>
 * 共通データアクセス処理インターフェース
 * </p>
 * データアクセス共通処理を作成する際には当クラスに記載する。
 *
 * @author H.Abe
 * @version 1.0
 *
 */
public interface CommonRDBControl {

    public abstract List<Map<String, Object>> executeCommonSelectByQuery(String query) throws SQLException;

    public abstract List<Map<String, Object>> executeCommonSelectByBindQuery(String query, List<BindingDto> binds) throws SQLException;

    public abstract int executeCommonDMLByQuery(String query) throws SQLException;

    public abstract int executeCommonDMLByBindQuery(String query, List<BindingDto> binds) throws SQLException;

    public abstract void close(Connection connection) throws SQLException;

    public abstract void close(PreparedStatement preparedStatement) throws SQLException;

    public abstract void close(ResultSet resultSet) throws SQLException;

    public abstract ResultSet getResultSetBySelectQuery(String query) throws SQLException;

    public abstract ResultSet getResultSetBySelectBindQuery(String query, List<BindingDto> binds) throws SQLException;
}
