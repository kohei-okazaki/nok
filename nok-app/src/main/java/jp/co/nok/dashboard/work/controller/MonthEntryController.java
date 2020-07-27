package jp.co.nok.dashboard.work.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.nok.business.db.select.DailyWorkEntryDataService;
import jp.co.nok.business.db.select.WorkUserMtSearchService;
import jp.co.nok.business.work.dto.BusinessCalendarDto;
import jp.co.nok.business.work.service.WorkEntryService;
import jp.co.nok.common.component.SessionComponent;
import jp.co.nok.common.util.DateUtil;
import jp.co.nok.common.util.DateUtil.DateFormatType;
import jp.co.nok.common.util.StringUtil;
import jp.co.nok.dashboard.work.form.MonthEntryForm;
import jp.co.nok.db.entity.WorkUserCompositeMt;
import jp.co.nok.web.view.AppView;

/**
 * 当月勤怠登録コントローラ
 *
 * @version 1.0.0
 */
@Controller
@RequestMapping("/work/month")
public class MonthEntryController {

    @Autowired
    private HttpSession session;
    /** 勤怠登録画面サービス */
    @Autowired
    private WorkEntryService workEntryService;
    /** 日別勤怠登録情報検索サービス */
    @Autowired
    private DailyWorkEntryDataService dailyWorkEntryDataService;
    /** 勤怠ユーザマスタ検索サービス */
    @Autowired
    private WorkUserMtSearchService workUserMtSearchService;

    /**
     * 当月勤怠登録画面表示
     *
     * @param model
     *            Model
     * @param year
     *            指定年
     * @param month
     *            指定月
     * @return 当月勤怠登録View
     */
    @GetMapping("/entry")
    public String entry(Model model,
            @RequestParam(name = "year", required = false) String year,
            @RequestParam(name = "month", required = false) String month) {

        SessionComponent sessionComponent = (SessionComponent) session
                .getAttribute(SessionComponent.KEY);

        Integer seqLoginId = sessionComponent.getLoginAuthDto().getSeqLoginId();
        // ログイン中のユーザに適用される最新の定時情報マスタを取得
        WorkUserCompositeMt regularMt = workUserMtSearchService
                .selectByLoginIdAndMaxWorkUserMtId(seqLoginId);
        model.addAttribute("regularMt", regularMt);

        String targetYear = year;
        String targetMonth = month;

        // 未指定の場合、今月を取得対象とする
        if (StringUtil.isEmpty(targetYear)) {
            targetYear = DateUtil.toString(DateUtil.getSysDate(), DateFormatType.YYYY);
        } else if (StringUtil.isEmpty(targetMonth)) {
            targetMonth = DateUtil.toString(DateUtil.getSysDate(), DateFormatType.MM);
        }
        LocalDate targetDate = LocalDate.of(Integer.parseInt(targetYear),
                Integer.parseInt(targetMonth), 1);

        List<BusinessCalendarDto> calendarList = workEntryService
                .getBusinessCalendarDtoList(targetDate);

        model.addAttribute("thisMonthList", dailyWorkEntryDataService
                .getMonthList(seqLoginId, year, month, calendarList));

        return AppView.WORK_MONTH_ENTRY_VIEW.getValue();
    }

    /**
     * 当月勤怠登録処理
     *
     * @param model
     *            Model
     * @param form
     *            当月勤怠登録Form
     * @param result
     *            validation結果
     * @return 当月勤怠登録View
     */
    @PostMapping("/entry")
    public String entry(Model model, @Validated MonthEntryForm form,
            BindingResult result) {

        if (result.hasErrors()) {
            return AppView.WORK_MONTH_ENTRY_VIEW.getValue();
        }

        return AppView.WORK_MONTH_ENTRY_VIEW.toRedirect();
    }

}
