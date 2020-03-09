package jp.co.nok.dashboard.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.nok.common.log.Logger;
import jp.co.nok.common.log.LoggerFactory;
import jp.co.nok.dashboard.login.form.LoginForm;

/**
 * ログイン画面コントローラ
 *
 * @version 1.0.0
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

	// @Autowired
	// private OntimeMtDao ontimeMtDao;

	@GetMapping("/index")
	public String index(Model model) {

		LoginForm source = new LoginForm();
		source.setId("testID");
		source.setPassword("testPassword");
		source.setNumber(99999);

		// ontimeMtDao.select().stream().forEach(e -> LOG.infoRes(e));
		return "login/index";
	}
}
