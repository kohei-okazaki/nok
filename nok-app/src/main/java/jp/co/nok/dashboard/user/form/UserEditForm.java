package jp.co.nok.dashboard.user.form;

import jp.co.nok.common.log.annotation.Mask;

/**
 * ユーザ情報設定変更画面Form
 *
 * @version 1.0.0
 */
public class UserEditForm {

    /** パスワード変更フラグ */
    private String passwordEditFlag;
    /** パスワード */
    @Mask
    private String password;
    /** メールアドレス */
    @Mask
    private String mailAddress;

    public String getPasswordEditFlag() {
        return passwordEditFlag;
    }

    public void setPasswordEditFlag(String passwordEditFlag) {
        this.passwordEditFlag = passwordEditFlag;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

}
