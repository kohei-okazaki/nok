package jp.co.nok.dashboard.work.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.nok.business.db.create.RegularWorkMtCreateService;
import jp.co.nok.business.db.select.RegularWorkMtSearchService;
import jp.co.nok.dashboard.work.form.RegularEntryForm;
import jp.co.nok.db.entity.RegularWorkMt;
import jp.co.nok.web.view.AppView;

/**
 * 定時時刻登録画面Controller
 *
 * @version 1.0.0
 */
@Controller
@RequestMapping("/work/regular")
public class RegularEntryController {

    /** ModelMapper */
    @Autowired
    private ModelMapper modelMapper;
    /** 定時情報マスタ検索サービス */
    @Autowired
    private RegularWorkMtSearchService regularWorkMtSearchService;
    /** 定時情報マスタ作成サービス */
    @Autowired
    private RegularWorkMtCreateService regularWorkMtCreateService;

    @ModelAttribute
    public RegularEntryForm regularEntryForm() {
        return new RegularEntryForm();
    }

    /**
     * 定時時刻登録画面
     *
     * @param model
     *            Model
     * @return 定時時刻登録画面View
     */
    @GetMapping("/entry")
    public String entry(Model model) {

        List<RegularWorkMt> mtList = regularWorkMtSearchService.selectAll();
        model.addAttribute("mtList", mtList);

        return AppView.WORK_REGULAR_ENTRY_VIEW.getValue();
    }

    /**
     * 定時時刻登録処理
     *
     * @param model
     *            Model
     * @param form
     *            定時時刻登録画面Form
     * @param result
     *            妥当性チェック結果
     * @return 定時時刻登録画面View
     */
    @PostMapping("/entry")
    public String entry(Model model, @Validated RegularEntryForm form,
            BindingResult result) {

        if (result.hasErrors()) {
            return AppView.WORK_REGULAR_ENTRY_VIEW.getValue();
        }

        RegularWorkMt mt = modelMapper.map(form, RegularWorkMt.class);
        regularWorkMtCreateService.create(mt);

        List<RegularWorkMt> mtList = regularWorkMtSearchService.selectAll();
        model.addAttribute("mtList", mtList);
        model.addAttribute("entrySuccess", "1");
        return AppView.WORK_REGULAR_ENTRY_VIEW.getValue();
    }
}
