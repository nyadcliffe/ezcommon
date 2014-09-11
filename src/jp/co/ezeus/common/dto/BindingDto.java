package jp.co.ezeus.common.dto;

import java.sql.Date;

import jp.co.ezeus.common.utilities.UtilDefine;

/**
 * <p>
 * 共通データアクセス用バインド変数格納クラス
 * </p>
 * バインド変数の値と属性を設定する。
 *
 * @author H.Abe
 * @version 1.0
 *
 */
public class BindingDto {

    /**
     * int型の値
     */
    private int intValue;

    /**
     * String型の値
     */
    private String stringValue;

    /**
     * char型の値
     */
    private String charValue;

    /**
     * Date型の値
     */
    private Date dateValue;

    /**
     * バインド変数の属性
     *
     * @see UtilDefine
     */
    private String argsType;

    /**
     * int型の値取得
     *
     * @return int
     */
    public int getIntValue() {
        return intValue;
    }

    /**
     * String型の値取得
     *
     * @return String
     */
    public String getStringValue() {
        return stringValue;
    }

    /**
     * char型の値取得
     *
     * @return String
     */
    public String getCharValue() {
        return charValue;
    }

    /**
     * Date型の値取得
     *
     * @return Date
     */
    public Date getDateValue() {
        return dateValue;
    }

    /**
     * バインド変数の属性取得
     *
     * @return String
     */
    public String getArgsType() {
        return argsType;
    }

    /**
     * int型の値設定
     *
     * @param intValue
     */
    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    /**
     * String型の値設定
     *
     * @param stringValue
     */
    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    /**
     * char型の値設定
     *
     * @param charValue
     */
    public void setCharValue(String charValue) {
        this.charValue = charValue;
    }

    /**
     * Date型の値設定
     *
     * @param dateValue
     */
    public void setDateValue(Date dateValue) {
        this.dateValue = dateValue;
    }

    /**
     * バインド変数の属性設定
     *
     * @param argsType
     */
    public void setArgsType(String argsType) {
        this.argsType = argsType;
    }
}
