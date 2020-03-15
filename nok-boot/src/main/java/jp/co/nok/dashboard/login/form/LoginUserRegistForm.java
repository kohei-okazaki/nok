package jp.co.nok.dashboard.login.form;

import javax.validation.constraints.NotEmpty;

/**
 * ログインユーザ登録画面Form
 *
 * @version 1.0.0
 */
public class LoginUserRegistForm {

	/** パスワード */
	// @Mask
	@NotEmpty
	private String password;
	/** 確認用パスワード */
	// @Mask
	@NotEmpty
	private String confirmPassword;

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

}
