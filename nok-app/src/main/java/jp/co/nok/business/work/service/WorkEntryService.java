package jp.co.nok.business.work.service;

import java.time.LocalDate;
import java.util.List;

import jp.co.nok.business.work.dto.BusinessCalendarDto;

/**
 * 勤怠登録画面サービスインタフェース
 *
 * @version 1.0.0
 */
public interface WorkEntryService {

    List<BusinessCalendarDto> getBusinessCalendarDtoList(LocalDate targetDate);

}
