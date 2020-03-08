package jp.co.nok.dashboard.login.form;

/**
 * ログインFormクラス
 *
 * @version 1.0.0
 */
public class LoginForm {

	/** ID */
	private String id;
	/** パスワード */
	private String password;
	/** 数字 */
	private int number;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
