package jp.co.nok.dashboard.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

	@GetMapping
	public String index() {
		return AppView.LOGIN_VIEW.getValue();
	}

	@RequestMapping(path = "/processLogin", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String loginPost(@ModelAttribute LoginForm form, BindingResult br) {
		// TODO このメソッド使ってなさそうなら削除

		if (br.hasErrors()) {
			// 入力チェックエラーがある場合は、ログイン画面に戻る
			return AppView.LOGIN_VIEW.getValue();
		}

		return "forward:/login/login";
	}

	@PostMapping("/success")
	public String success() {
		return AppView.TOP_VIEW.getValue();
	}

	@GetMapping("/error")
	public String error(Model model) {
		model.addAttribute("loginError", true);
		return AppView.LOGIN_VIEW.getValue();
	}

	public String logout(Model model) {
		// TODO プロパティファイルに移植
		model.addAttribute("errorMessage", "ログアウトしました");
		return AppView.LOGIN_VIEW.getValue();
	}
}
