package jp.co.nok.business.db.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.nok.common.algorithm.Sha256HashEncoder;
import jp.co.nok.common.util.DateUtil;
import jp.co.nok.db.dao.LoginUserDataDao;
import jp.co.nok.db.entity.LoginUserData;

/**
 * ログインユーザ情報更新サービス実装クラス
 *
 * @version 1.0.0
 */
@Service
public class LoginUserDataUpdateServiceImpl implements LoginUserDataUpdateService {

    /** ログインユーザ情報Dao */
    @Autowired
    private LoginUserDataDao dao;
    /** SHA256変換クラス */
    @Autowired
    private Sha256HashEncoder sha256HashEncoder;

    @Override
    public void update(LoginUserData loginUserData) {

        // パスワード(SHA-256でハッシュ化)
        loginUserData.setPassword(sha256HashEncoder.encode(loginUserData.getPassword()));
        // パスワード有効期限(システム日時 + 12ヶ月)
        loginUserData.setPasswordExpire(
                DateUtil.addMonth(DateUtil.toLocalDate(DateUtil.getSysDate()), 12));
        dao.update(loginUserData);
    }
}
