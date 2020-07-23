package jp.co.nok.business.user.dto;

import jp.co.nok.common.log.annotation.Mask;

/**
 * ユーザ情報設定変更画面DTO
 *
 * @version 1.0.0
 */
public class UserEditDto {

    /** パスワード変更フラグ */
    private Boolean passwordEditFlag;
    /** パスワード */
    @Mask
    private String password;
    /** メールアドレス */
    @Mask
    private String mailAddress;

    public Boolean getPasswordEditFlag() {
        return passwordEditFlag;
    }

    public void setPasswordEditFlag(Boolean passwordEditFlag) {
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
