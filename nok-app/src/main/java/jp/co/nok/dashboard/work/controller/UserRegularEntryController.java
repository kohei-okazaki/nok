package jp.co.nok.dashboard.work.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.nok.business.db.create.WorkUserMtCreateService;
import jp.co.nok.business.db.select.LoginUserDataSearchService;
import jp.co.nok.business.db.select.RegularWorkMtSearchService;
import jp.co.nok.business.db.select.WorkUserMtSearchService;
import jp.co.nok.dashboard.work.form.UserRegularEntryForm;
import jp.co.nok.db.entity.RegularWorkMt;
import jp.co.nok.db.entity.WorkUserCompositeMt;
import jp.co.nok.db.entity.WorkUserMt;
import jp.co.nok.db.util.DomaUtil;
import jp.co.nok.web.view.AppView;
import jp.co.nok.web.view.PagingView;

/**
 * ユーザ定時情報登録Controller
 *
 * @version 1.0.0
 */
@Controller
@RequestMapping("/work/userregular")
public class UserRegularEntryController {

    /** ModelMapper */
    @Autowired
    private ModelMapper modelMapper;
    /** ログインユーザ情報検索サービス */
    @Autowired
    private LoginUserDataSearchService loginUserDataSearchService;
    /** 定時情報マスタ検索サービス */
    @Autowired
    private RegularWorkMtSearchService regularWorkMtSearchService;
    /** 勤怠ユーザマスタ検索サービス */
    @Autowired
    private WorkUserMtSearchService workUserMtSearchService;
    /** 勤怠ユーザマスタ作成サービス */
    @Autowired
    private WorkUserMtCreateService workUserMtCreateService;

    @ModelAttribute
    public UserRegularEntryForm userRegularEntryForm() {
        return new UserRegularEntryForm();
    }

    /**
     * ユーザ定時情報登録画面表示処理
     *
     * @param model
     *            Model
     * @param pageable
     *            Pageable
     * @return ユーザ定時情報登録画面View
     */
    @GetMapping("entry")
    public String entry(Model model,
            @PageableDefault(size = 5, page = 0) Pageable pageable) {

        // 総レコード件数
        long count = regularWorkMtSearchService.count();

        PagingView paging = DomaUtil.getPageView(pageable, "/work/userregular/entry?page",
                count);
        model.addAttribute("paging", paging);

        List<RegularWorkMt> mtList = regularWorkMtSearchService.selectAll(pageable);
        model.addAttribute("mtList", mtList);

        List<Integer> seqLoginIdList = loginUserDataSearchService.selectIdList();
        model.addAttribute("seqLoginIdList", seqLoginIdList);

        List<Integer> seqRegularWorkMtIdList = mtList.stream()
                .map(RegularWorkMt::getSeqRegularWorkMtId).collect(Collectors.toList());
        model.addAttribute("seqRegularWorkMtIdList", seqRegularWorkMtIdList);

        List<WorkUserCompositeMt> workUserCopositeMtList = workUserMtSearchService
                .selectCompositeRegularMt();
        model.addAttribute("workUserCopositeMtList", workUserCopositeMtList);

        return AppView.WORK_USER_REGULAR_ENTRY_VIEW.getValue();
    }

    /**
     * ユーザ定時情報登録処理
     *
     * @param model
     *            Model
     * @param form
     *            ユーザ定時情報登録画面Form
     * @param result
     *            validation結果
     * @param redirectAttributes
     *            RedirectAttributes
     * @return ユーザ定時情報登録画面View
     */
    @PostMapping("entry")
    public String entry(Model model, @Validated UserRegularEntryForm form,
            BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return AppView.WORK_USER_REGULAR_ENTRY_VIEW.getValue();
        }

        WorkUserMt workUserMt = modelMapper.map(form, WorkUserMt.class);
        workUserMtCreateService.create(workUserMt);

        redirectAttributes.addFlashAttribute("entrySuccess", "1");

        return AppView.WORK_USER_REGULAR_ENTRY_VIEW.toRedirect();
    }

}
