package jp.co.ezeus.common.utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * <p>
 * DBアクセス用リソース管理クラス
 * </p>
 * DBアクセスに関するリソースファイルを管理する。 「resourceControl.properties」の内容からSQLファイル とのマッピングを行う。
 *
 * @author H.Abe
 * @version 1.0
 *
 */
public class DBResourceControl {

    /**
     * singletonのインスタンス
     */
    private static DBResourceControl instance = new DBResourceControl();

    /**
     * プロパティファイルへのパス
     */
    private static final String PATH_PROPERTIES = "properties/resourceControl.properties";

    /**
     * Propertiesファイル
     */
    private Properties properties;

    /**
     * コンストラクタ
     */
    private DBResourceControl() {
        ClassLoader classLoader = getClass().getClassLoader();
        properties = new Properties();
        try {
            properties.load(classLoader.getResourceAsStream(PATH_PROPERTIES));
        } catch (IOException e) {

        }
    }

    /**
     * DataAccessUtilクラスインスタンス取得。
     *
     * @return DataAccessUtil
     */
    public static DBResourceControl getInstance() {
        return instance;
    }

    /**
     * プロパティファイルの値を取得する。 引数に検索するキーを指定して、対象となる値を取得する。
     *
     * @param key プロパティファイルのキー
     * @return String
     * @author H.Abe
     */
    public String getPropertiesValue(String key) {
        return properties.getProperty(key);
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
    public String readSQLFile(String sqlKeyName) throws IOException {
        FileReader file = new FileReader(getPropertiesValue(sqlKeyName));
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
    public String replaceBind(String query, List<String> binds) {
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
