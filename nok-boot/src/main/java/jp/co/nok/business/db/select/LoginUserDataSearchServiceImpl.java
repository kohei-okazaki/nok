package jp.co.nok.business.db.select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.nok.db.dao.LoginUserDataDao;
import jp.co.nok.db.entity.LoginUserData;

/**
 * ログインユーザ情報検索サービス実装クラス
 *
 * @version 1.0.0
 */
@Service
public class LoginUserDataSearchServiceImpl implements LoginUserDataSearchService {

	/** ログインユーザ情報Dao */
	@Autowired
	private LoginUserDataDao dao;

	@Override
	public LoginUserData selectById(Integer id) {
		return dao.selectById(id);
	}

}
