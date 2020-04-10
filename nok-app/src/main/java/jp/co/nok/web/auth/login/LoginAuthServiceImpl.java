package jp.co.nok.web.auth.login;

import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.co.nok.business.db.select.LoginUserDataSearchService;
import jp.co.nok.common.component.SessionComponent;
import jp.co.nok.common.log.LoggerFactory;
import jp.co.nok.common.util.StringUtil;
import jp.co.nok.db.entity.LoginUserData;

/**
 * ログイン認証サービス
 *
 * @version 1.0.0
 */
@Service("loginAuthService")
public class LoginAuthServiceImpl implements UserDetailsService {

    /** ログインユーザ情報検索サービス */
    @Autowired
    private LoginUserDataSearchService loginUserDataSearchService;
    /** データマッピング */
    @Autowired
    private ModelMapper mapper;
    /** HttpSession情報 */
    @Autowired
    private HttpSession httpSession;

    @Override
    public LoginAuthDto loadUserByUsername(String username)
            throws UsernameNotFoundException {

        if (StringUtil.isEmpty(username)) {
            LoggerFactory.getLogger(this.getClass())
                    .warn("指定したログインIDが無効です。ログインID:" + username);
            throw new UsernameNotFoundException("指定したログインIDが無効です。ログインID:" + username);
        }

        LoginUserData entity = loginUserDataSearchService
                .selectById(Integer.valueOf(username));

        if (entity == null) {
            LoggerFactory.getLogger(this.getClass())
                    .warn("指定したログインIDが存在しません。ログインID:" + username);
            throw new UsernameNotFoundException("指定したログインIDが存在しません。ログインID:" +
                    username);
        }
        LoginAuthDto loginAuthDto = mapper.map(entity, LoginAuthDto.class);

        // ログインユーザ認証情報をHTTPSessionに保存
        SessionComponent component = new SessionComponent();
        component.setLoginAuthDto(loginAuthDto);
        httpSession.setAttribute(SessionComponent.KEY, component);

        return loginAuthDto;
    }

}
