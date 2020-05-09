package jp.co.nok.business.db.select;

import jp.co.nok.db.entity.LoginUserData;

/**
 * ログインユーザ情報検索サービスインターフェース
 *
 * @version 1.0.0
 */
public interface LoginUserDataSearchService {

    LoginUserData selectById(Integer id);
}
