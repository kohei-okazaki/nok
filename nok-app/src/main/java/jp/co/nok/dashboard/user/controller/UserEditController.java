package jp.co.nok.dashboard.user.controller;

import javax.servlet.http.HttpSession;

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

import jp.co.nok.business.user.dto.UserEditDto;
import jp.co.nok.business.user.service.UserEditService;
import jp.co.nok.common.component.SessionComponent;
import jp.co.nok.common.log.Logger;
import jp.co.nok.common.log.LoggerFactory;
import jp.co.nok.dashboard.user.form.UserEditForm;
import jp.co.nok.web.view.AppView;

/**
 * ユーザ情報変更画面コントローラ
 *
 * @version 1.0.0
 */
@Controller
@RequestMapping("/user")
public class UserEditController {

    /** LOG */
    private static final Logger LOG = LoggerFactory
            .getLogger(UserEditController.class);
    @Autowired
    private HttpSession session;
    @Autowired
    private UserEditService userEditService;
    @Autowired
    private ModelMapper modelMapper;

    @ModelAttribute
    public UserEditForm userEditForm() {

        SessionComponent sessionComponent = (SessionComponent) session
                .getAttribute(SessionComponent.KEY);
        UserEditDto dto = userEditService
                .getUserEditDto(sessionComponent.getLoginAuthDto().getSeqLoginId());

        UserEditForm form = modelMapper.map(dto, UserEditForm.class);
        LOG.debugBean(form);

        return form;
    }

    /**
     * ユーザ情報設定変更画面
     *
     * @return ユーザ情報設定変更View
     */
    @GetMapping("/edit")
    public String edit() {
        return AppView.USER_EDIT_VIEW.getValue();
    }

    /**
     * ユーザ情報設定変更確認画面
     *
     * @param model
     *            Model
     * @param userEditForm
     *            ログインユーザ情報変更Form
     * @param result
     *            validation結果
     * @return ユーザ情報設定変更確認View
     */
    @PostMapping("/editconfirm")
    public String editConfirm(Model model, @Validated UserEditForm userEditForm,
            BindingResult result) {

        if (result.hasErrors()) {
            return AppView.USER_EDIT_VIEW.getValue();
        }

        SessionComponent sessionComponent = (SessionComponent) session
                .getAttribute(SessionComponent.KEY);
        sessionComponent.setUserEditForm(userEditForm);
        LOG.debugBean(sessionComponent);

        session.setAttribute(SessionComponent.KEY, sessionComponent);

        model.addAttribute("userEditForm", userEditForm);

        return AppView.USER_EDIT_CONFIRM_VIEW.getValue();
    }

    /**
     * ユーザ情報設定完了画面
     *
     * @param model
     *            Model
     * @return ユーザ情報設定完了View
     */
    @PostMapping("/editprocess")
    public String editProcess(Model model) {

        SessionComponent sessionComponent = (SessionComponent) session
                .getAttribute(SessionComponent.KEY);
        UserEditForm form = sessionComponent.getUserEditForm();
        UserEditDto dto = modelMapper.map(form, UserEditDto.class);
        dto.setSeqLoginId(sessionComponent.getLoginAuthDto().getSeqLoginId());

        userEditService.edit(dto);

        return AppView.USER_EDIT_PROCESS_VIEW.getValue();
    }

}
