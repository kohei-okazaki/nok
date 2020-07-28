package jp.co.nok.business.db.select;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.nok.db.dao.DailyWorkEntryDataDao;
import jp.co.nok.db.entity.BusinessCalendarMt;
import jp.co.nok.db.entity.DailyWorkEntryData;

/**
 * 日別勤怠登録情報検索サービス実装クラス
 *
 * @version 1.0.0
 */
@Service
public class DailyWorkEntryDataSearchServiceImpl
        implements DailyWorkEntryDataSearchService {

    @Autowired
    private DailyWorkEntryDataDao dao;
    @Autowired
    private BusinessCalendarMtSerachService businessCalendarMtSerachService;

    @Override
    public List<DailyWorkEntryData> getMonthList(Integer seqWorkUserMtId,
            LocalDate targetDate) {

        // 処理対象年月より、営業日マスタリストを検索
        List<BusinessCalendarMt> businessCalendarMtList = businessCalendarMtSerachService
                .selectByMonth(targetDate);

        LocalDate begin = businessCalendarMtList.get(0).getDate();
        LocalDate end = businessCalendarMtList.get(businessCalendarMtList.size() - 1)
                .getDate();

        // TODO SQLを改良する
        // DATE_FORMAT(BEGIN, '%Y%m') = /* begin */200810
        List<DailyWorkEntryData> dailyWorkEntryDataList = dao
                .selectBySeqWorkUserMtIdAndBetweenBegin(seqWorkUserMtId, begin, end);

        // TODO
        // dailyWorkEntryDataListとbusinessCalendarMtListをマージする
        return null;
    }

}
