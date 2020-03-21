package jp.co.nok.business.db.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.nok.common.algorithm.Sha256HashEncoder;
import jp.co.nok.common.util.DateUtil;
import jp.co.nok.db.dao.LoginUserDataDao;
import jp.co.nok.db.entity.LoginUserData;

/**
 * ログインユーザ情報作成サービス実装クラス
 *
 * @version 1.0.0
 */
@Service
public class LoginUserDataCreateServiceImpl implements LoginUserDataCreateService {

	@Autowired
	private LoginUserDataDao dao;
	@Autowired
	private Sha256HashEncoder sha256HashEncoder;

	@Override
	public void create(LoginUserData loginUserData) {

		// パスワード(SHA-256でハッシュ化)
		loginUserData.setPassword(sha256HashEncoder.encode(loginUserData.getPassword()));
		// パスワード有効期限(システム日時 + 12ヶ月)
		loginUserData.setPasswordExpire(
				DateUtil.addMonth(DateUtil.toLocalDate(DateUtil.getSysDate()), 12));
		dao.insert(loginUserData);
	}

}
