package jp.co.nok.dashboard.work.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.nok.business.db.select.RegularWorkMtSearchService;
import jp.co.nok.business.db.select.WorkUserMtSearchService;
import jp.co.nok.dashboard.work.form.UserRegularEntryForm;
import jp.co.nok.db.entity.RegularWorkMt;
import jp.co.nok.db.entity.WorkUserCompositeMt;
import jp.co.nok.web.view.AppView;

/**
 * ユーザ定時情報登録Controller
 *
 * @version 1.0.0
 */
@Controller
@RequestMapping("/work/userregular")
public class UserRegularEntryController {

    /** 定時情報マスタ検索サービス */
    @Autowired
    private RegularWorkMtSearchService regularWorkMtSearchService;
    /** 勤怠ユーザマスタ検索サービス */
    @Autowired
    private WorkUserMtSearchService workUserMtSearchService;

    @ModelAttribute
    public UserRegularEntryForm userRegularEntryForm() {
        return new UserRegularEntryForm();
    }

    /**
     * ユーザ定時情報登録画面初期表示 userregularentry
     *
     * @param model
     *            Model
     * @return ユーザ定時情報登録画面View
     */
    @GetMapping("entry")
    public String entry(Model model) {

        List<RegularWorkMt> mtList = regularWorkMtSearchService.selectAll();
        model.addAttribute("mtList", mtList);

        List<WorkUserCompositeMt> workUserCopositeMtList = workUserMtSearchService
                .selectCompositeRegularMt();
        model.addAttribute("workUserCopositeMtList", workUserCopositeMtList);

        return AppView.WORK_USER_REGULAR_ENTRY_VIEW.getValue();
    }

}
