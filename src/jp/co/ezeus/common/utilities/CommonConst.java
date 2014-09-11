package jp.co.ezeus.common.utilities;

/**
 * <p>
 * 定数クラス
 * </p>
 *
 * @author S.Yada
 * @version 1.0
 */
public class CommonConst {

    /****************************************
     ****** 共通文字列 ******
     ****************************************/
    /** 半角スペース */
    public static final String HARF_SPACE = " ";

    /** 全角スペース */
    public static final String FULL_SPACE = "　";

    /** ブランク */
    public static final String EMPTY = "";

    /** ハイフン */
    public static final String HYPHEN = "-";

    /** スラッシュ */
    public static final String SLASH = "/";

    /** 0(半角ゼロ) */
    public static final String STRING_ZERO = "0";

    /** 前埋めフラグ */
    public static final String FRONT_FLG = "0";

    /** 後埋めフラグ */
    public static final String BACK_FLG = "1";

    /****************************************
     ****** 正規表現 ******
     ****************************************/
    /** 半角数字の正規表現 */
    public static final String HARF_NUMERIC = "[\\p{Digit}]+";

    /** 半角英字の正規表現 */
    public static final String HARF_ALPHABET = "[\\p{Alpha}]+";

    /** 半角英数字の正規表現 */
    public static final String HARF_ALPHA_NUMERIC = "[\\p{Alnum}]+";

    /** 半角記号の正規表現 */
    public static final String HARF_SYMBOL = "[\\p{Punct}]+";

    /** 半角英数記号の正規表現 */
    public static final String HARF_ALPHA_NUM_SYMBOL = "[\\p{Alnum}\\p{Punct}]+";

    /** 半角カタカナの正規表現 */
    public static final String HARF_KATAKANA = "^[ｱ-ﾝﾞﾟ]+$";

    /** 半角文字の正規表現 */
    public static final String HARF_STRING = "^[ｱ-ﾝﾞﾟ\\p{Alnum}\\p{Punct}]+$";

    /** 電話番号の正規表現 */
    public static final String TEL_NUMBER = "(090|080|070)-\\d{4}-\\d{4}";

    /** メールアドレスの正規表現 */
    public static final String MAIL_ADDRESS = "^([a-zA-Z0-9])+([a-zA-Z0-9\\._-])*@([a-zA-Z0-9_-])+([a-zA-Z0-9\\._-]+)+$";
}
