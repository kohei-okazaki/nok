package jp.co.nok.business.db.update;

import jp.co.nok.db.entity.LoginUserData;

/**
 * ログインユーザ情報更新サービスインターフェース
 *
 * @version 1.0.0
 */
public interface LoginUserDataUpdateService {

	void update(LoginUserData loginUserData);

}
