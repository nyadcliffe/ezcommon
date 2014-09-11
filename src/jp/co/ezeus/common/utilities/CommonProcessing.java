package jp.co.ezeus.common.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * <p>
 * 共通処理を行うクラス
 * </p>
 *
 * @author K.Akai
 * @version 1.0
 */
public class CommonProcessing {

    /**
     * <p>
     * ０埋め処理
     * </p>
     * <p>
     * 編集対象文字列に指定桁数まで０埋め処理を行う
     * </p>
     * <p>
     * フラグ値により先頭０埋めか文字列最後尾０埋めを判断する
     * </p>
     *
     * @param str 編集対象文字列
     * @param num 指定桁数
     * @param flg 先頭、最後尾判断用フラグ
     * @return String 編集後文字列
     */
    public static String zeroPading(String str, int num, String flg) {
        // 文字列の長さを取得
        int strLength = str.length();

        StringBuilder sbZero = new StringBuilder();
        // 指定の桁数までゼロの文字列を作成する
        for (int i = 0; i < num - strLength; i++) {

            sbZero.append(UtilDefine.STRING_ZERO);
        }

        StringBuilder sb = null;

        // ０埋めを先頭に行うか、最後尾に行うかの判定
        if (flg.equals(UtilDefine.FRONT_FLG)) {

            sb = new StringBuilder(sbZero);

            sb.append(str);

            // 最後尾の場合
        } else if (flg.equals(UtilDefine.BACK_FLG)) {

            sb = new StringBuilder(str);

            sb.append(sbZero);
        }
        return sb.toString();
    }

    /**
     * <p>
     * 先頭０消し処理
     * </p>
     * <p>
     * 文字列の先頭から０を削除する
     * </p>
     *
     * @param str 編集対象文字列
     * @return String 編集後文字列
     */
    public static String zeroDelete(String str) {

        // ループ用フラグ
        boolean loopFlg = true;

        // 文字列の先頭が０の場合は繰り返しを実施
        while (loopFlg == true) {

            // 文字列の先頭が０であるか判定
            if (str.substring(0, 1).equals(UtilDefine.STRING_ZERO)) {
                str = str.substring(1);
            } else {
                loopFlg = false;
            }
        }
        return str;
    }

    /**
     * <p>
     * スペース埋め処理
     * </p>
     * <p>
     * 編集対象文字列に指定桁数までのスペース埋め処理を行う
     * </p>
     * <p>
     * フラグ値により先頭スペース埋めか文字列最後尾スペース埋めを判断する
     * </p>
     *
     * @param str 編集対象文字列
     * @param num 指定数
     * @param flg 先頭、最後尾判断用フラグ
     * @return String 編集後文字列
     */
    public static String spacePading(String str, int num, String flg) {

        // 文字列の長さを取得
        int strLength = str.length();

        StringBuffer sbSpace = new StringBuffer();

        // 指定数分スペースの連続文字列を作成
        for (int i = 0; i < num - strLength; i++) {
            sbSpace.append(UtilDefine.HARF_SPACE);

        }

        StringBuffer sb = null;

        // スペース埋めを先頭に行うか、最後尾に行うかの判定
        if (flg.equals(UtilDefine.FRONT_FLG)) {
            sb = new StringBuffer(sbSpace);
            sb.append(str);

            // 最後尾の場合
        } else if (flg.equals(UtilDefine.BACK_FLG)) {
            sb = new StringBuffer(str);
            sb.append(sbSpace);

        }
        str = sb.toString();

        return str;
    }

    /**
     * <p>
     * 文字列右スペース削除処理
     * </p>
     * <p>
     * 文字列最後尾のスペースを削除する処理
     * </p>
     *
     * @param str 編集対象文字列
     * @return String 編集後文字列
     */
    public static String rSpaceTrim(String str) {

        // 文字列長
        int length = 0;

        // ループ用フラグ
        boolean loopFlg = true;

        // 文字列の最後尾がスペースの場合は繰り返しを実施
        while (loopFlg == true) {

            // 文字列長を取得
            length = str.length();

            // 文字列の最後尾がスペースであるか判定
            if (str.substring(length - 1).equals(UtilDefine.STRING_HALF_SPACE)) {
                str = str.substring(0, length - 1);
            } else {
                loopFlg = false;
            }
        }
        return str;
    }

    /**
     * <p>
     * 文字列左スペース削除処理
     * </p>
     * <p>
     * 文字列文字までのスペースを削除する処理
     * </p>
     *
     * @param str 編集対象文字列
     * @return String 編集後文字列
     */
    public static String lSpaceTrim(String str) {

        // ループ用フラグ
        boolean loopFlg = true;

        // 文字列の先頭がスペースの場合は繰り返しを実施
        while (loopFlg == true) {

            // 文字列の先頭がスペースであるか判定
            if (str.substring(0, 1).equals(UtilDefine.STRING_HALF_SPACE)) {
                str = str.substring(1);
            } else {
                loopFlg = false;
            }
        }
        return str;
    }

    /**
     * <p>
     * 文字列両端のスペース削除処理
     * </p>
     * <p>
     * 文字列文字までのスペースを削除する処理
     * </p>
     *
     * @param str 編集対象文字列
     * @return String 編集後文字列
     */
    public static String spaceTrim(String str) {

        /* 正規表現で文字列の前後スペースを空文字に変換 */
        return str.trim();
    }

    /**
     * <p>
     * 誕生日から現在の年齢を算出する
     * </p>
     *
     * @param birth 誕生日
     * @return 現在の年齢
     */
    public static String getAge(String birth) throws ParseException {

        // 年齢
        int age = 0;

        // 日付のフォーマット変換
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        // 誕生日の形式を変換
        birth = birth.replace(UtilDefine.SLASH, "");
        birth = birth.replace(UtilDefine.HYPHEN, "");

        // カレンダークラスの生成
        Calendar birthDay = Calendar.getInstance();

        // 誕生日を設定(yyyy/MM/ddの形式も対応)
        birthDay.setTime(sdf.parse(birth));

        // システム日付を取得
        Date nowDate = new Date();
        String dateString = sdf.format(nowDate);

        Calendar today = Calendar.getInstance();
        today.setTime(sdf.parse(dateString));

        // 年数の差を求める
        age = today.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
        birthDay.clear(Calendar.YEAR);
        today.clear(Calendar.YEAR);

        // 誕生日が今日よりも後だった場合、年齢を-１する
        if (birthDay.after(today)) {
            age -= 1;
        }

        return String.valueOf(age);
    }

    /**
     * <p>
     * 月末日付を取得するメソッド
     * </p>
     *
     * @param yyyyMM 指定年月
     * @return 対象年月の月末日
     */
    public static String getEndDate(String yyyyMM) {
        // 年の取得
        int yyyy = Integer.parseInt(yyyyMM.substring(0, 4));
        // 月の習得
        int MM = Integer.parseInt(yyyyMM.substring(4));
        // 日の設定
        int dd = 1;
        // カレンダークラスのインスタンス生成
        Calendar cal = Calendar.getInstance();

        // 年月日を取得
        cal.set(yyyy, MM - 1, dd);
        // 月末日数の習得
        int last = cal.getActualMaximum(Calendar.DATE);

        // 最終日付を戻す
        return String.valueOf(last);

    }

    /**
     * 西暦日付を和暦へ変換
     *
     * @param input 西暦日付
     * @return String
     */
    public static String getFiscalYear(Date input) {
        Locale.setDefault(new Locale(UtilDefine.LOCAL_JA_LOWER, UtilDefine.LOCALE_JA_UPPER, UtilDefine.LOCALE_JA_UPPER));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(input);
        SimpleDateFormat dateFormat = new SimpleDateFormat(UtilDefine.FORMAT_FISCAL_YEAR);
        return dateFormat.format(calendar.getTime());
    }

    /**
     * Stringをjava.sql.Dateに返還する
     *
     * @param strdate
     * @return java.sql.Date
     */
    public static java.sql.Date stringToSQLdate(String strdate) {

        // 日付のフォーマット変換
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // フォーマットを変換する
        sdf.format(strdate);

        // java.sql.Dateに型変換して返却する
        return java.sql.Date.valueOf(strdate);
    }

    /**
     * java.sql.Dateに返還する
     *
     * @param java.sql.Date
     * @return　String
     */
    public static String SQLdateToString(java.sql.Date sqldate) {

        // 日付のフォーマット変換
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        // フォーマットする
        String stndate = sdf.format(sqldate);

        return stndate;
    }
}