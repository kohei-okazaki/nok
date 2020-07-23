package jp.co.nok.business.db.user.dto;

import jp.co.nok.common.log.annotation.Mask;

/**
 * ユーザ情報設定変更画面DTO
 * 
 * @version 1.0.0
 */
public class UserEditDto {

    /** パスワード */
    @Mask
    private String password;
    /** メールアドレス */
    @Mask
    private String mailAddress;

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
