package jp.co.nok.business.work.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import jp.co.nok.business.work.dto.BusinessCalendarDto;
import jp.co.nok.common.util.DateUtil;
import jp.co.nok.common.util.DateUtil.DateFormatType;
import jp.co.nok.common.util.StringUtil;

/**
 * 当月勤怠登録画面サービス実装クラス
 *
 * @version 1.0.0
 */
@Service
public class MonthlyWorkEntryServiceImpl implements MonthlyWorkEntryService {

    @Override
    public LocalDate getTargetDate(String year, String month) {

        String targetYear = StringUtil.isEmpty(year)
                ? DateUtil.toString(DateUtil.getSysDate(), DateFormatType.YYYY)
                : year;

        String targetMonth = StringUtil.isEmpty(month)
                ? DateUtil.toString(DateUtil.getSysDate(), DateFormatType.MM)
                : month;

        return LocalDate.of(Integer.parseInt(targetYear),
                Integer.parseInt(targetMonth), 1);
    }

    @Override
    public List<BusinessCalendarDto> getBusinessCalendarDtoList(LocalDate targetDate) {

        // 月初から月末まで取得
        List<LocalDate> dateList = DateUtil.getLocalDateList(targetDate);

        return dateList.stream().map(e -> {

            BusinessCalendarDto calendar = new BusinessCalendarDto();
            calendar.setDay(new BigDecimal(e.getDayOfMonth()));
            calendar.setWeekDay(e.getDayOfWeek().toString().toLowerCase());
            return calendar;

        }).collect(Collectors.toList());
    }

}
