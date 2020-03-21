package jp.co.nok.dashboard.login.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.co.nok.dashboard.login.form.LoginForm;
import jp.co.nok.web.view.AppView;

/**
 * ログイン画面コントローラ
 *
 * @version 1.0.0
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private HttpSession session;

	@ModelAttribute
	public LoginForm loginUserRegistForm() {
		return new LoginForm();
	}

	@GetMapping
	public String index() {
		return AppView.LOGIN_VIEW.getValue();
	}

	@RequestMapping(path = "/processLogin", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String loginPost(@Validated LoginForm form, BindingResult br) {
		// TODO このメソッド使ってなさそうなら削除

		if (br.hasErrors()) {
			// 入力チェックエラーがある場合は、ログイン画面に戻る
			return AppView.LOGIN_VIEW.getValue();
		}

		return "forward:/success";
	}

	@RequestMapping(path = "/success", method = { RequestMethod.GET, RequestMethod.POST })
	public String success() {
		return AppView.TOP_VIEW.getValue();
	}

	@GetMapping("/error")
	public String error(Model model) {
		model.addAttribute("loginError", true);
		return AppView.LOGIN_VIEW.getValue();
	}

	@GetMapping("/logout")
	public String logout(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("isLogout", true);
		redirectAttributes.addFlashAttribute("infoMessage", "ログアウトしました");
		return "redirect:/login";
	}
}
