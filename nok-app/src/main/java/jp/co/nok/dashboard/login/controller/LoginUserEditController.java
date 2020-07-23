package jp.co.nok.dashboard.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.nok.common.log.Logger;
import jp.co.nok.common.log.LoggerFactory;
import jp.co.nok.dashboard.login.form.LoginUserEditForm;
import jp.co.nok.web.view.AppView;

/**
 * ログインユーザ登録画面コントローラ
 *
 * @version 1.0.0
 */
@Controller
@RequestMapping("/login")
public class LoginUserEditController {

    /** LOG */
    private static final Logger LOG = LoggerFactory
            .getLogger(LoginUserEditController.class);

    @ModelAttribute
    public LoginUserEditForm loginUserRegistForm() {
        return new LoginUserEditForm();
    }

    /**
     * ログインユーザ設定変更画面
     *
     * @return ログインユーザ設定変更View
     */
    @GetMapping("/useredit")
    public String edit() {
        return AppView.LOGIN_EDIT_VIEW.getValue();
    }

    /**
     * ログインユーザ設定変更画面
     *
     * @param model
     *            Model
     * @param loginUserEditForm
     *            ログインユーザ情報変更Form
     * @param result
     *            validation結果
     * @return ログインユーザ設定変更View
     */
    @PostMapping("/usereditconfirm")
    public String editConfirm(Model model,
            @Validated LoginUserEditForm loginUserEditForm, BindingResult result) {
        return AppView.LOGIN_EDIT_CONFIRM_VIEW.getValue();
    }

}
