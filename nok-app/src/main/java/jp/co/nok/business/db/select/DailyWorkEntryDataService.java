package jp.co.nok.business.db.select;

import java.util.List;

import jp.co.nok.business.work.dto.BusinessCalendarDto;
import jp.co.nok.db.entity.DailyWorkEntryData;

/**
 * 日別勤怠登録情報検索サービスインターフェース
 *
 * @version 1.0.0
 */
public interface DailyWorkEntryDataService {

    List<DailyWorkEntryData> getMonthList(Integer seqLoginId, String year, String month,
            List<BusinessCalendarDto> calendarList);

}
