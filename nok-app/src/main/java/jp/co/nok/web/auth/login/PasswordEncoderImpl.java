package jp.co.nok.web.auth.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jp.co.nok.common.algorithm.Sha256HashEncoder;

/**
 * パスワードエンコードクラス<br>
 * SHA-256でのハッシュ化を行い、SpringSecurityで利用する
 *
 * @version 1.0.0
 */
@Service("passwordEncoderImpl")
public class PasswordEncoderImpl implements PasswordEncoder {

	/** ハッシュ関数 */
	@Autowired
	private Sha256HashEncoder encoder;

	@Override
	public String encode(CharSequence rawPassword) {
		return encoder.encode(rawPassword.toString());
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return this.encode(rawPassword).equals(encodedPassword);
	}

}
