package jp.co.nok.business.db.select;

import java.time.LocalDate;
import java.util.List;

import jp.co.nok.db.entity.DailyWorkEntryData;

/**
 * 日別勤怠登録情報検索サービスインターフェース
 *
 * @version 1.0.0
 */
public interface DailyWorkEntryDataSearchService {

    List<DailyWorkEntryData> getMonthList(Integer seqWorkUserMtId, LocalDate targetDate);

}
