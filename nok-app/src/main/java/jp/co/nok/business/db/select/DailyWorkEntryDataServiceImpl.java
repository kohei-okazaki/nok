package jp.co.nok.business.db.select;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.nok.business.work.dto.BusinessCalendarDto;
import jp.co.nok.common.util.DateUtil;
import jp.co.nok.common.util.DateUtil.DateFormatType;
import jp.co.nok.common.util.StringUtil;
import jp.co.nok.db.dao.DailyWorkEntryDataDao;
import jp.co.nok.db.entity.DailyWorkEntryData;

/**
 * 日別勤怠登録情報検索サービス実装クラス
 *
 * @version 1.0.0
 */
@Service
public class DailyWorkEntryDataServiceImpl implements DailyWorkEntryDataService {

    @Autowired
    private DailyWorkEntryDataDao dao;

    @Override
    public List<DailyWorkEntryData> getMonthList(Integer seqLoginId, String year,
            String month, List<BusinessCalendarDto> calendarList) {

        String targetYear = year;
        String targetMonth = month;

        // 未指定の場合、今月を取得対象とする
        if (StringUtil.isEmpty(targetYear)) {
            targetYear = DateUtil.toString(DateUtil.getSysDate(), DateFormatType.YYYY);
        } else if (StringUtil.isEmpty(targetMonth)) {
            targetMonth = DateUtil.toString(DateUtil.getSysDate(), DateFormatType.MM);
        }

        String workStartDate = targetYear + "/" + targetMonth;
        String workEndDate = targetYear + "/" + targetMonth;

        return null;
    }

}
