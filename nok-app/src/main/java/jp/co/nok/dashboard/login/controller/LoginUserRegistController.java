package jp.co.nok.dashboard.login.controller;

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

import jp.co.nok.business.db.create.LoginUserDataCreateService;
import jp.co.nok.common.component.SessionComponent;
import jp.co.nok.common.log.Logger;
import jp.co.nok.common.log.LoggerFactory;
import jp.co.nok.dashboard.login.form.LoginUserRegistForm;
import jp.co.nok.db.entity.LoginUserData;
import jp.co.nok.web.view.AppView;

/**
 * ログインユーザ登録画面コントローラ
 *
 * @version 1.0.0
 */
@Controller
@RequestMapping("/login")
public class LoginUserRegistController {

	/** LOG */
	private static final Logger LOG = LoggerFactory
			.getLogger(LoginUserRegistController.class);
	@Autowired
	private HttpSession session;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private LoginUserDataCreateService loginUserCreateService;

	@ModelAttribute
	public LoginUserRegistForm loginUserRegistForm() {
		return new LoginUserRegistForm();
	}

	/**
	 * 登録情報入力画面
	 *
	 * @return ログインユーザ登録View
	 */
	@GetMapping("/userregist")
	public String userRegist() {
		return AppView.LOGIN_REGIST_VIEW.getValue();
	}

	/**
	 * 登録情報入力確認画面
	 *
	 * @param model
	 *            Model
	 * @param loginUserRegistForm
	 *            ログインユーザ情報登録Form
	 * @param result
	 *            validation結果
	 * @return ログインユーザ登録確認View
	 */
	@PostMapping("/userregistconfirm")
	public String userRegistConfirm(Model model,
			@Validated LoginUserRegistForm loginUserRegistForm, BindingResult result) {

		if (result.hasErrors()) {
			model.addAttribute("errorMessage", "入力情報が不正です");
			return AppView.LOGIN_REGIST_VIEW.getValue();
		}

		SessionComponent sessionComponent = modelMapper.map(loginUserRegistForm,
				SessionComponent.class);
		session.setAttribute("sessionComponent", sessionComponent);
		return AppView.LOGIN_REGIST_CONFIRM_VIEW.getValue();
	}

	/**
	 * 登録完了画面
	 *
	 * @param model
	 *            Model
	 * @return ログインユーザ登録完了View
	 */
	@PostMapping("/userregistprocess")
	public String userRegistProcess(Model model) {

		SessionComponent sessionComponent = (SessionComponent) session
				.getAttribute(SessionComponent.KEY);
		LOG.debugRes(sessionComponent);

		LoginUserData loginUserData = modelMapper.map(sessionComponent,
				LoginUserData.class);
		LOG.debugRes(loginUserData);
		loginUserCreateService.create(loginUserData);

		model.addAttribute("loginId", loginUserData.getSeqLoginId());

		return AppView.LOGIN_REGIST_PROCESS_VIEW.getValue();
	}

}
