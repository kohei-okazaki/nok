package jp.co.nok.business.db.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.nok.db.dao.LoginUserDataDao;
import jp.co.nok.db.entity.LoginUserData;

/**
 * ログインユーザ情報更新サービス実装クラス
 *
 * @version 1.0.0
 */
@Service
public class LoginUserDataUpdateServiceImpl implements LoginUserDataUpdateService {

    @Autowired
    private LoginUserDataDao dao;

    @Override
    public void update(LoginUserData loginUserData) {
        dao.update(loginUserData);
    }
}
