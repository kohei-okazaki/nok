package jp.co.nok.business.db.create;

import jp.co.nok.db.entity.LoginUserData;

/**
 * ログインユーザ情報作成サービスインターフェース
 *
 * @version 1.0.0
 */
public interface LoginUserDataCreateService {

    /**
     * ログインユーザ情報を登録する
     *
     * @param loginUserData
     *            ログインユーザ情報
     */
    void create(LoginUserData loginUserData);

}
