package jp.co.nok.db.entity;

import java.time.LocalDate;

import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.jdbc.entity.NamingType;

import jp.co.nok.common.log.annotation.Mask;
import jp.co.nok.db.annotation.Crypt;

/**
 * ログインユーザ情報 Entity
 *
 * @version 1.0.0
 */
@Entity(naming = NamingType.SNAKE_UPPER_CASE)
public class LoginUserData extends BaseEntity {

    /** ログインID */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer seqLoginId;
    /** メールアドレス */
    @Mask
    @Crypt
    private String mailAddress;
    /** パスワード */
    private String password;
    /** パスワード有効期限 */
    private LocalDate passwordExpire;

    public void setSeqLoginId(Integer seqLoginId) {
        this.seqLoginId = seqLoginId;
    }

    public Integer getSeqLoginId() {
        return seqLoginId;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPasswordExpire(LocalDate passwordExpire) {
        this.passwordExpire = passwordExpire;
    }

    public LocalDate getPasswordExpire() {
        return passwordExpire;
    }

}