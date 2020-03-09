package jp.co.nok.dashboard.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.nok.common.log.Logger;
import jp.co.nok.common.log.LoggerFactory;

/**
 * ログイン画面コントローラ
 *
 * @version 1.0.0
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

	@GetMapping("/index")
	public String index(Model model) {

		return "login/index";
	}
}
