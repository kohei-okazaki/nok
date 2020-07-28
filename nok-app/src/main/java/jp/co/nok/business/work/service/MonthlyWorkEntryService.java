package jp.co.nok.business.work.service;

import java.time.LocalDate;
import java.util.List;

import jp.co.nok.business.work.dto.BusinessCalendarDto;

/**
 * 当月勤怠登録画面サービスインタフェース
 *
 * @version 1.0.0
 */
public interface MonthlyWorkEntryService {

    LocalDate getTargetDate(String year, String month);

    List<BusinessCalendarDto> getBusinessCalendarDtoList(LocalDate targetDate);

}
