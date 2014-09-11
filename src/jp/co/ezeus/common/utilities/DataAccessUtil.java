package jp.co.ezeus.common.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

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
public class DataAccessUtil {

    private static final String PATH_PROPERTIES = "resources/resourceControl.properties";

    /**
     * プロパティファイル読み込み処理を行う。 引数に検索するキーを指定して、対象となる値を取得する。
     *
     * @param key プロパティファイルのキー
     * @return String
     * @throws IOException
     * @author H.Abe
     */
    public static String getProperties(String key) throws IOException {
        InputStream inStream = new FileInputStream(new File(PATH_PROPERTIES));
        Properties properties = new Properties();
        properties.load(inStream);
        String value = properties.getProperty(key);
        return value;
    }

    /**
     * SQLファイル読み込み処理を行う。 引数にプロパティファイル記載のSQL名を設定し、 該当するSQLファイルのパスを取得する。
     * 取得したパスからSQLファイルの内容を読み取り SQL文を作成して返却する。
     *
     * @param sqlKeyName SQLファイル名（プロパティファイルのキー）
     * @return String
     * @throws IOException
     * @author H.Abe
     */
    public static String readSQLFile(String sqlKeyName) throws IOException {
        FileReader file = new FileReader(getProperties(sqlKeyName));
        BufferedReader reader = new BufferedReader(file);
        StringBuilder queryBuilder = new StringBuilder();
        String str = null;
        while ((str = reader.readLine()) != null) {
            str = str.replaceAll(UtilDefine.STRING_TAB, UtilDefine.STRING_HALF_SPACE);
            queryBuilder.append(str);
        }
        file.close();
        reader.close();
        return queryBuilder.toString();
    }

    /**
     * SQL文のバインド変数設定処理を行う。 引数に変換されるSQL文と変換する変数を受け取り、 バインド後のSQL文を返却する。<br>
     * SQL文の変換対象となるのは"?"であり、変換されるSQL文字列中で 出現するindex順と変換する変数リストのindexは合わせる必要がある。<br>
     * 引数がNULL、"?"を含まない、変数リストに格納値が存在しない、 "?"の数と変数リストの要素数が一致しない場合は、 NULLを返却する。
     *
     * @param query SQL文
     * @param binds 変換文字リスト
     * @return String
     * @author H.Abe
     */
    public static String replaceBind(String query, List<String> binds) {
        if (query == null || binds.size() == 0) {
            return null;
        }
        for (int i = 0; i < binds.size(); i++) {
            if (query.indexOf(UtilDefine.STRING_QUESTION) == -1 && i < binds.size()) {
                return null;
            }
            query = query.replaceFirst(UtilDefine.STRING_REGEX_QUESTION, binds.get(i));
        }
        if (query.indexOf(UtilDefine.STRING_QUESTION) > -1) {
            return null;
        }
        return query;
    }
}
