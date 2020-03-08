package jp.co.nok.dashboard.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.nok.common.config.CommonConfig;
import jp.co.nok.common.log.LogConfig;
import jp.co.nok.common.log.Logger;
import jp.co.nok.common.log.LoggerFactory;
import jp.co.nok.dashboard.login.form.LoginForm;
import jp.co.nok.db.dao.OntimeMtDao;

/**
 * ログイン画面コントローラ
 *
 * @version 1.0.0
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	private static final Logger LOG = LoggerFactory
			.getLogger(LoginController.class);

	@Autowired
	private CommonConfig commonConfig;
	@Autowired
	private LogConfig logConfig;
	@Autowired
	private OntimeMtDao ontimeMtDao;

	@GetMapping("/index")
	public String index(Model model) {

		model.addAttribute("msg", commonConfig.getName());

		LoginForm source = new LoginForm();
		source.setId("testID");
		source.setPassword("testPassword");
		source.setNumber(99999);

		LOG.infoRes(logConfig);

		ontimeMtDao.select().stream().forEach(e -> LOG.infoRes(e));
		return "login/index";
	}
}
