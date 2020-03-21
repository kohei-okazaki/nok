package jp.co.nok.common.component;

import org.springframework.stereotype.Component;

import jp.co.nok.common.log.annotation.Ignore;
import jp.co.nok.web.auth.login.LoginAuthDto;

/**
 * Session情報のComponent
 *
 * @version 1.0.0
 */
@Component
public class SessionComponent {

	@Ignore
	public static final String KEY = "sessionComponent";

	/** パスワード */
	private String password;
	/** 確認用パスワード */
	private String confirmPassword;
	/** ログイン認証情報Dto */
	private LoginAuthDto loginAuthDto;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public LoginAuthDto getLoginAuthDto() {
		return loginAuthDto;
	}

	public void setLoginAuthDto(LoginAuthDto loginAuthDto) {
		this.loginAuthDto = loginAuthDto;
	}

}
