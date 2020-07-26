package jp.co.nok.dashboard.work.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.nok.web.view.AppView;

/**
 * 当月勤怠登録コントローラ
 *
 * @version 1.0.0
 */
@Controller
@RequestMapping("/work/month/")
public class MonthEntryController {

    /**
     * 当月勤怠登録画面表示
     * 
     * @param model
     *            Model
     * @return 当月勤怠登録View
     */
    @GetMapping("entry")
    public String entry(Model model) {

        return AppView.WORK_MONTH_ENTRY_VIEW.getValue();
    }

}
