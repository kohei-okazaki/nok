package jp.co.nok.web.view;

import jp.co.nok.common.type.BaseEnum;

/**
 * アプリの画面View定義の列挙<br>
 * 新しくControllerクラスのメソッドの戻り値の文字列が追加された場合、追加する
 *
 * @version 1.0.0
 */
public enum AppView implements BaseEnum {

    /** ログインView:/login/index */
    LOGIN_VIEW("/login/index"),
    /** TOPView:/common/top */
    TOP_VIEW("/common/top"),
    /** ログインユーザ登録View:/login/regist */
    LOGIN_REGIST_VIEW("/login/regist"),
    /** ログインユーザ登録確認View:/login/registconfirm */
    LOGIN_REGIST_CONFIRM_VIEW("/login/registconfirm"),
    /** ログインユーザ登録完了View:/login/registprocess */
    LOGIN_REGIST_PROCESS_VIEW("/login/registprocess"),
    /** ログインユーザ設定変更View:/user/edit */
    USER_EDIT_VIEW("/user/edit"),
    /** ログインユーザ設定変更確認View:/user/editconfirm */
    USER_EDIT_CONFIRM_VIEW("/user/editconfirm"),
    /** ログインユーザ設定変更完了View:/user/editprocess */
    USER_EDIT_PROCESS_VIEW("/user/editprocess");

    /** パス */
    private String value;

    private AppView(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }

}
