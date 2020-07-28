package jp.co.nok.business.db.select;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.nok.common.util.DateUtil;
import jp.co.nok.common.util.DateUtil.DateFormatType;
import jp.co.nok.db.dao.BusinessCalendarMtDao;
import jp.co.nok.db.entity.BusinessCalendarMt;

/**
 * 営業日マスタ検索サービス実装クラス
 *
 * @version 1.0.0
 */
@Service
public class BusinessCalendarMtSerachServiceImpl
        implements BusinessCalendarMtSerachService {

    @Autowired
    private BusinessCalendarMtDao dao;

    @Override
    public List<BusinessCalendarMt> selectByMonth(LocalDate date) {
        return dao
                .selectByMonth(DateUtil.toString(date, DateFormatType.YYYYMM_NOSEP));
    }

}
