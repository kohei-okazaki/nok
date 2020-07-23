package jp.co.nok.db.entity;

import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.jdbc.entity.NamingType;

import jp.co.nok.common.log.annotation.Mask;
import jp.co.nok.db.annotation.Crypt;

/**
 * メールユーザ情報 Entity
 *
 * @version 1.0.0
 */
@Entity(naming = NamingType.SNAKE_UPPER_CASE)
public class MailUserData extends BaseEntity {

    /** ユーザメールID */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer seqSerMailId;
    /** ログインID */
    private Integer seqLoginId;
    /** メールアドレス */
    @Mask
    @Crypt
    private String mailAddress;

    public void setSeqSerMailId(Integer seqSerMailId) {
        this.seqSerMailId = seqSerMailId;
    }

    public Integer getSeqSerMailId() {
        return seqSerMailId;
    }

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

}