package jp.co.nok.db.entity;

import jp.co.nok.db.entity.BaseEntity;
import org.seasar.doma.Entity;
import org.seasar.doma.jdbc.entity.NamingType;
import org.seasar.doma.Id;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import java.lang.Integer;
import java.time.LocalDate;
import java.lang.String;

/**
 * 営業日マスタ Entity
 *
 * @version 1.0.0
 */
@Entity(naming = NamingType.SNAKE_UPPER_CASE)
public class BusinessCalendarMt extends BaseEntity {

    /** 営業日マスタID */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer seqBusinessCalendarMtId;
    /** 日付 */
    private LocalDate date;
    /** 営業日フラグ */
    private String businessFlg;

    public void setSeqBusinessCalendarMtId(Integer seqBusinessCalendarMtId) {
        this.seqBusinessCalendarMtId = seqBusinessCalendarMtId;
    }

    public Integer getSeqBusinessCalendarMtId() {
        return seqBusinessCalendarMtId;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setBusinessFlg(String businessFlg) {
        this.businessFlg = businessFlg;
    }

    public String getBusinessFlg() {
        return businessFlg;
    }

}