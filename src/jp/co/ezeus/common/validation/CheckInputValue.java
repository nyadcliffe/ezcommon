package jp.co.ezeus.common.validation;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jp.co.ezeus.common.utilities.UtilDefine;

/**
 * <p>
 * 共通入力値チェッククラス
 * </p>
 * 共通チェックメソッドを作成する際には、当クラスに記載する。
 *
 * @author S.Yada
 * @version 1.0
 */
public class CheckInputValue {
    /**
     * null、空文字判定メソッド。 判定結果をboolean型にて返却。
     *
     * @param value 入力値
     * @return boolean (OK : true / NG : false)
     * @author S.Yada
     */
    public static boolean isNullCheck(String value) {
        // 渡された入力値のnull、空文字判定を行う
        if (null == value || UtilDefine.EMPTY.equals(value)) {
            return false;
        }
        return true;
    }

    /**
     * 入力桁数判定メソッド(上下限桁数)。 判定結果をboolean型にて返却。
     *
     * @param value 入力値
     * @param max 上限桁数
     * @param min 下限桁数
     * @return boolean (OK : true / NG : false)
     * @author S.Yada
     */
    public static boolean isFiguresCheck(String value, int max, int min) {

        // 渡された入力値桁数の上下限判定を行う
        if (value.length() < min || max < value.length()) {
            return false;
        }
        return true;
    }

    /**
     * 入力桁数判定メソッド(上限桁数)。 判定結果をboolean型にて返却。
     *
     * @param value 入力値
     * @param max 上限桁数
     * @return boolean (OK : true / NG : false)
     * @author S.Yada
     */
    public static boolean isFiguresCheck(String value, int max) {

        // 渡された入力値桁数の上限判定を行う
        if (value.length() < max) {
            return false;
        }
        return true;
    }

    /**
     * 半角数字判定メソッド。 判定結果をboolean型にて返却。<br>
     * ｖalueにはnull値は入れないこと!!
     *
     * @param value 入力値
     * @return boolean (OK : true / NG : false)
     * @author S.Yada
     */
    public static boolean isHarfNumericCheck(String value) {

        Pattern pattern = Pattern.compile(UtilDefine.HARF_NUMERIC);
        Matcher matcher = pattern.matcher(value);

        // 渡された入力値の半角数字の正規表現判定を行う
        if (!matcher.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 半角英字判定メソッド。 判定結果をboolean型にて返却。<br>
     * ｖalueにはnull値は入れないこと!!
     *
     * @param value 入力値
     * @return boolean (OK : true / NG : false)
     * @author S.Yada
     */
    public static boolean isHarfAlphabetCheck(String value) {

        Pattern pattern = Pattern.compile(UtilDefine.HARF_ALPHABET);
        Matcher matcher = pattern.matcher(value);

        // 渡された入力値の半角英字の正規表現判定を行う
        if (!matcher.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 半角英数字判定メソッド。 判定結果をboolean型にて返却。<br>
     * ｖalueにはnull値は入れないこと!!
     *
     * @param value 入力値
     * @return boolean (OK : true / NG : false)
     * @author S.Yada
     */
    public static boolean isHarfAlphaNumericCheck(String value) {

        Pattern pattern = Pattern.compile(UtilDefine.HARF_ALPHA_NUMERIC);
        Matcher matcher = pattern.matcher(value);

        // 渡された入力値の半角英数字の正規表現判定を行う
        if (!matcher.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 半角記号判定メソッド。 判定結果をbooleanにて返却。<br>
     * ｖalueにはnull値は入れないこと!!
     *
     * @param value 入力値
     * @return boolean (OK : true / NG : false)
     * @author S.Yada
     */
    public static boolean isHarfSymbolCheck(String value) {

        Pattern pattern = Pattern.compile(UtilDefine.HARF_SYMBOL);
        Matcher matcher = pattern.matcher(value);

        // 渡された入力値の半角記号の正規表現判定を行う
        if (!matcher.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 半角英数記号判定メソッド。 判定結果をboolean型にて返却。<br>
     * ｖalueにはnull値は入れないこと!!
     *
     * @param value 入力値
     * @return boolean (OK : true / NG : false)
     * @author S.Yada
     */
    public static boolean isHarfAlphaNumSymbolCheck(String value) {

        Pattern pattern = Pattern.compile(UtilDefine.HARF_ALPHA_NUM_SYMBOL);
        Matcher matcher = pattern.matcher(value);

        // 渡された入力値の半角英数記号の正規表現判定を行う
        if (!matcher.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 半角カタカナ判定メソッド。 判定結果をboolean型にて返却。<br>
     * ｖalueにはnull値は入れないこと!!
     *
     * @param value 入力値
     * @return boolean (OK : true / NG : false)
     * @author S.Yada
     */
    public static boolean isHarfKatakanaCheck(String value) {

        Pattern pattern = Pattern.compile(UtilDefine.HARF_KATAKANA);
        Matcher matcher = pattern.matcher(value);

        // 渡された入力値の半角カタカナの正規表現判定を行う
        if (!matcher.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 半角文字判定メソッド。 判定結果をboolean型にて返却。<br>
     * ｖalueにはnull値は入れないこと!!
     *
     * @param value 入力値
     * @return boolean (OK : true / NG : false)
     * @author S.Yada
     */
    public static boolean isHarfStringCheck(String value) {

        Pattern pattern = Pattern.compile(UtilDefine.HARF_STRING);
        Matcher matcher = pattern.matcher(value);

        // 渡された入力値の半角文字の正規表現判定を行う
        if (!matcher.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 携帯電話番号判定メソッド。 判定結果をboolean型にて返却。<br>
     * ｖalueにはnull値は入れないこと!!
     *
     * @param value 入力値
     * @return boolean (OK : true / NG : false)
     * @author K.Akai
     */
    public static boolean isTelNumber(String value) {

        Pattern pattern = Pattern.compile(UtilDefine.TEL_NUMBER);
        Matcher matcher = pattern.matcher(value);

        // 渡された入力値の関表現チェックを行う
        if (!matcher.matches()) {
            return false;
        }
        return true;
    }

    /**
     * メールアドレス判定メソッド。 判定結果をboolean型にて返却。<br>
     * ｖalueにはnull値は入れないこと!!
     *
     * @param value 入力値
     * @return boolean (OK : true / NG : false)
     * @author K.Akai
     */
    public static boolean isMailAddress(String value) {

        Pattern pattern = Pattern.compile(UtilDefine.MAIL_ADDRESS);
        Matcher matcher = pattern.matcher(value);

        // 渡された入力値の関表現チェックを行う
        if (!matcher.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 日付の妥当性をチェックするメソッド
     *
     * @param value
     * @return boolean (OK : true / NG : false)
     * @author K.Akai
     */
    public static boolean isDate(String value) {
        // 6桁の場合
        if (value.length() == 6) {

            // 最後の2桁を数値化
            int month = Integer.parseInt(value.substring(4));

            // 1～12でなければ、エラー
            if (1 <= month && month <= 12) {
                return true;
            } else {
                return false;
            }

        }
        // 8桁の場合
        if (value.length() == 8) {
            // 日付の形の生成
            StringBuilder stb = new StringBuilder();
            stb.append(value.substring(0, 4));
            stb.append(UtilDefine.SLASH);
            stb.append(value.substring(4, 6));
            stb.append(UtilDefine.SLASH);
            stb.append(value.substring(7));
            value = stb.toString();
        }

        // 10桁の場合
        if (value.length() == 10) {
            // 文字列の置換
            value = value.replace(UtilDefine.HYPHEN, UtilDefine.SLASH);
        } else {
            return false;
        }

        // 日付フォーマッタの取得
        DateFormat format = DateFormat.getDateInstance();

        // 日付と時刻解析を厳密に行うかどうかを設定
        format.setLenient(false);

        try {
            // 日付解析
            format.parse(value);

            // 日付が妥当であれば"true"を返却
            return true;

        } catch (ParseException e) {
            // 日付変換失敗時、正しい数値ではない。
            return false;
        }
    }

    /**
     * <p>
     * 日付の大小関係をチェックするメソッド
     * </p>
     *
     * @param fromDate　fromに設定された日付
     * @param toDate toに設定された日付
     * @return boolean (OK : true / NG : false)
     * @author K.Akai
     */
    public static boolean isDateRelation(String fromDate, String toDate) {
        // 取得した日付の数値化
        int from = Integer.parseInt(fromDate);
        int to = Integer.parseInt(toDate);

        // toの日付がfromよりも小さければエラー
        if (to < from) {
            return false;
        }
        return true;
    }
}