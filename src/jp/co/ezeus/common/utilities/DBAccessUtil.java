package jp.co.ezeus.common.utilities;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jp.co.ezeus.common.dto.BaseDto;

/**
 * <p>
 * 共通DB処理系クラス
 * </p>
 * DBアクセスに関する共通Utilクラス。
 *
 * @author H.Abe
 * @version 1.0
 *
 */
public class DBAccessUtil {

    /**
     * ResultSetの内容をDTOへ移し替える。 引数にResultSetとDTOを設定し、戻り値のListを受け取る。<br>
     * 引数に設定するDTOは @see {jp.co.ezeus.common.dto.BaseDto} を 継承するようにするのが前提。
     * DTO内のフィールド名はDBのカラム名と同一（大文字・小文字は問わない）に する必要がある。
     *
     * @param dto データ格納対象のDTOオブジェクト
     * @param resultSet 結果セット
     * @return List<BaseDto>
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws SecurityException
     * @throws NoSuchMethodException
     */
    public List<BaseDto> getDtoByResultSet(BaseDto dto, ResultSet resultSet) throws ClassNotFoundException, SQLException,
            IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException {
        String dtoName = dto.getClass().getName();
        Class<?> classObj = Class.forName(dtoName);
        Method[] methods = classObj.getDeclaredMethods();
        Map<String, String> methodNames = new HashMap<String, String>();
        for (int i = 0; i < methods.length; i++) {
            String method = methods[i].getName();
            if (method.startsWith(UtilDefine.STRING_SET)) {
                methodNames.put(method.substring(3).toUpperCase(), method);
            }
        }
        int columnCount = 0;
        String columnName = null;
        int columnType = 0;
        List<BaseDto> resultList = new ArrayList<BaseDto>();
        while (resultSet.next()) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            columnCount = metaData.getColumnCount();
            for (int i = 0; i < columnCount; i++) {
                columnName = metaData.getColumnName(i + 1);
                columnType = metaData.getColumnType(i + 1);
                if (java.sql.Types.VARCHAR == columnType) {
                    String columnValue = resultSet.getString(columnName);
                    Class<?>[] argsType = { String.class };
                    Method method = classObj.getDeclaredMethod(methodNames.get(columnName), argsType);
                    method.invoke(dto, columnValue);
                } else if (java.sql.Types.INTEGER == columnType) {
                    int columnValue = resultSet.getInt(columnName);
                    Class<?>[] argsType = { int.class };
                    Method method = classObj.getDeclaredMethod(methodNames.get(columnName), argsType);
                    method.invoke(dto, columnValue);
                } else if (java.sql.Types.CHAR == columnType) {
                    String columnValue = resultSet.getString(columnName);
                    Class<?>[] argsType = { String.class };
                    Method method = classObj.getDeclaredMethod(methodNames.get(columnName), argsType);
                    method.invoke(dto, columnValue);
                } else if (java.sql.Types.DATE == columnType) {
                    Date columnValue = resultSet.getDate(columnName);
                    Class<?>[] argsType = { Date.class };
                    Method method = classObj.getDeclaredMethod(methodNames.get(columnName), argsType);
                    method.invoke(dto, columnValue);
                }
            }
            resultList.add(dto);
        }
        return resultList;
    }
}
