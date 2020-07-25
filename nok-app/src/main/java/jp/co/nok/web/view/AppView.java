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
    LOGIN_VIEW("/login/index", "index"),
    /** TOPView:/common/top */
    TOP_VIEW("/common/top", "top"),
    /** エラー画面のView */
    APP_ERROR_VIEW("/common/error", "error"),
    /** ログインユーザ登録View:/login/regist */
    LOGIN_REGIST_VIEW("/login/regist", "regist"),
    /** ログインユーザ登録確認View:/login/registconfirm */
    LOGIN_REGIST_CONFIRM_VIEW("/login/registconfirm", "registconfirm"),
    /** ログインユーザ登録完了View:/login/registprocess */
    LOGIN_REGIST_PROCESS_VIEW("/login/registprocess", "registprocess"),
    /** ログインユーザ設定変更View:/user/edit */
    USER_EDIT_VIEW("/user/edit", "edit"),
    /** ログインユーザ設定変更確認View:/user/editconfirm */
    USER_EDIT_CONFIRM_VIEW("/user/editconfirm", "editconfirm"),
    /** ログインユーザ設定変更完了View:/user/editprocess */
    USER_EDIT_PROCESS_VIEW("/user/editprocess", "editprocess"),
    /** 定時情報登録画面View:/work/regularentry */
    WORK_REGULAR_ENTRY_VIEW("/work/regularentry", "regularentry"),
    /** 定時情報更新画面View:/work/regularedit */
    WORK_REGULAR_EDIT_VIEW("/work/regularedit", "regularedit"),
    /** ユーザ定時情報登録画面View:/work/regularentry */
    WORK_USER_REGULAR_ENTRY_VIEW("/work/userregularentry", "userregularentry"),
    ;

    /** パス */
    private String value;
    /** リダイレクトパス */
    private String redirectPath;

    private AppView(String value, String redirectPath) {
        this.value = value;
        this.redirectPath = redirectPath;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    public String toRedirect() {
        return "redirect:/" + this.redirectPath;
    }

}
