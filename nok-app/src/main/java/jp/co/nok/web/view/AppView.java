package jp.co.nok.web.view;

import jp.co.nok.common.type.BaseEnum;

/**
 * アプリの画面View定義の列挙<br>
 * 新しくControllerクラスのメソッドの戻り値の文字列が追加された場合、追加する
 *
 * @version 1.0.0
 */
public enum AppView implements BaseEnum {

    /** ログインView */
    LOGIN_VIEW("/login/index"),
    /** TOPView */
    TOP_VIEW("/common/top"),
    /** ログインユーザ登録View */
    LOGIN_REGIST_VIEW("/login/regist"),
    /** ログインユーザ登録確認View */
    LOGIN_REGIST_CONFIRM_VIEW("/login/registconfirm"),
    /** ログインユーザ登録完了View */
    LOGIN_REGIST_PROCESS_VIEW("/login/registprocess");

    /** パス */
    private String value;

    private AppView(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    public String toRedirect() {
        return "redirect:" + this.value;
    }

    public String toForward() {
        return "forward:" + this.value;
    }

}
