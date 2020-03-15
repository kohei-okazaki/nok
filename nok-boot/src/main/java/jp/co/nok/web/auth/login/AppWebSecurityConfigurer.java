package jp.co.nok.web.auth.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * アプリのログイン認証設定クラス
 *
 * @version 1.0.0
 */
@Configuration
@EnableWebSecurity
public class AppWebSecurityConfigurer extends WebSecurityConfigurerAdapter {

	/** 認証処理を行わないURI */
	private static final String[] PERMIT_URIS = new String[] { "/login",
			"/login/userregist", "/login/userregistconfirm", "/login/userregistprocess",
			"/login/login-error", "/css/**", "/js/**", "/webjars/**" };
	/** ログイン認証サービス */
	@Autowired
	@Qualifier("loginAuthService")
	private UserDetailsService userService;
	/** パスワードEncoder */
	@Autowired
	@Qualifier("passwordEncoderImpl")
	private PasswordEncoder passwordEncoder;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// TODO cssなどのstaticファイルなどの許可を追加
		http.authorizeRequests()
				// ログイン等の画面URIと静的ファイルを許可
				.antMatchers(PERMIT_URIS)
				.permitAll()
				.anyRequest()
				.authenticated();

		http.formLogin()
				// ログイン処理をするURL
				.loginProcessingUrl("/login/processLogin")
				// ログイン画面のURL
				.loginPage("/login")
				// 認証失敗時のURL
				.failureUrl("/login/error")
				// 認証成功時のURL
				.successForwardUrl("/login/success")
				// ログインIDのパラメータ名
				.usernameParameter("loginId")
				// パスワードのパラメータ名
				.passwordParameter("password")
				.permitAll();

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
	}

}
