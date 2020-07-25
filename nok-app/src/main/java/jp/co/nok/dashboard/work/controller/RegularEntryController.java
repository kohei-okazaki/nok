package jp.co.nok.dashboard.work.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.nok.business.db.create.RegularWorkMtCreateService;
import jp.co.nok.business.db.select.RegularWorkMtSearchService;
import jp.co.nok.business.db.update.RegularWorkMtUpdateService;
import jp.co.nok.dashboard.work.form.RegularEditForm;
import jp.co.nok.dashboard.work.form.RegularEntryForm;
import jp.co.nok.db.entity.RegularWorkMt;
import jp.co.nok.web.view.AppView;

/**
 * 定時時刻登録/更新画面Controller
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
    /** 定時情報マスタ更新サービス */
    @Autowired
    private RegularWorkMtUpdateService regularWorkMtUpdateService;

    @ModelAttribute
    public RegularEntryForm regularEntryForm() {
        return new RegularEntryForm();
    }

    @ModelAttribute
    public RegularEditForm regularEditForm() {
        return new RegularEditForm();
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
        model.addAttribute("mode", "entry");

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
     *            validationチェック結果
     * @return 定時時刻登録画面View
     */
    @PostMapping("/entry")
    public String entry(Model model, @Validated RegularEntryForm form,
            BindingResult result) {

        model.addAttribute("mode", "entry");

        if (result.hasErrors()) {
            model.addAttribute("mtList", regularWorkMtSearchService.selectAll());
            return AppView.WORK_REGULAR_ENTRY_VIEW.getValue();
        }

        RegularWorkMt mt = modelMapper.map(form, RegularWorkMt.class);
        regularWorkMtCreateService.create(mt);

        List<RegularWorkMt> mtList = regularWorkMtSearchService.selectAll();
        model.addAttribute("mtList", mtList);
        model.addAttribute("entrySuccess", "1");
        return AppView.WORK_REGULAR_ENTRY_VIEW.getValue();
    }

    /**
     * 定時情報更新画面表示
     *
     * @param model
     *            Model
     * @param seqRegularWorkMtId
     *            定時情報マスタID
     * @return 更新画面View
     */
    @GetMapping("/edit")
    public String edit(Model model,
            @RequestParam(name = "id", required = false) Optional<Integer> seqRegularWorkMtId) {

        if (!seqRegularWorkMtId.isPresent()) {
            model.addAttribute("mode", "edit");
            return AppView.WORK_REGULAR_ENTRY_VIEW.getValue();
        }

        RegularWorkMt mt = regularWorkMtSearchService
                .selectById(seqRegularWorkMtId.get());

        List<RegularWorkMt> mtList = regularWorkMtSearchService.selectAll();

        model.addAttribute("mt", mt);
        model.addAttribute("mtList", mtList);
        model.addAttribute("mode", "edit");

        return AppView.WORK_REGULAR_ENTRY_VIEW.getValue();
    }

    /**
     * @param model
     *            Model
     * @param form
     *            定時時刻更新Form
     * @param result
     *            validationチェック結果
     * @return 更新画面View
     */
    @PostMapping("/edit")
    public String edit(Model model, @Validated RegularEditForm form,
            BindingResult result) {

        model.addAttribute("mode", "edit");
        if (result.hasErrors()) {

            model.addAttribute("mt", regularWorkMtSearchService
                    .selectById(form.getSeqRegularWorkMtId()));
            model.addAttribute("mtList", regularWorkMtSearchService.selectAll());

            return AppView.WORK_REGULAR_ENTRY_VIEW.getValue();
        }

        RegularWorkMt mt = regularWorkMtSearchService
                .selectById(form.getSeqRegularWorkMtId());
        modelMapper.map(form, mt);
        regularWorkMtUpdateService.update(mt);

        List<RegularWorkMt> mtList = regularWorkMtSearchService.selectAll();

        model.addAttribute("mt", mt);
        model.addAttribute("mtList", mtList);
        model.addAttribute("entrySuccess", "1");

        return AppView.WORK_REGULAR_ENTRY_VIEW.getValue();
    }

}
