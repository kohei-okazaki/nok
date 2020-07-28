package jp.co.nok.business.db.select;

import java.time.LocalDate;
import java.util.List;

import jp.co.nok.db.entity.BusinessCalendarMt;

/**
 * 営業日マスタ検索サービスインターフェース
 *
 * @version 1.0.0
 */
public interface BusinessCalendarMtSerachService {

    List<BusinessCalendarMt> selectByMonth(LocalDate date);
}
