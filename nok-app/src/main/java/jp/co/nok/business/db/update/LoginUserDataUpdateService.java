package jp.co.nok.business.db.update;

import jp.co.nok.db.entity.LoginUserData;

/**
 * ログインユーザ情報更新サービスインターフェース
 *
 * @version 1.0.0
 */
public interface LoginUserDataUpdateService {

    /**
     * ログインユーザ情報を更新する
     *
     * @param loginUserData
     *            ログインユーザ情報
     */
    void update(LoginUserData loginUserData);

}
