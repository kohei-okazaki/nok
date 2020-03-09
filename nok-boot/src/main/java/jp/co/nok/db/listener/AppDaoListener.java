package jp.co.nok.db.listener;

import org.seasar.doma.jdbc.entity.EntityListener;
import org.seasar.doma.jdbc.entity.PreInsertContext;
import org.seasar.doma.jdbc.entity.PreUpdateContext;

import jp.co.nok.common.util.DateUtil;
import jp.co.nok.db.entity.BaseEntity;

/**
 * Daoのリスナークラス
 *
 * @version 1.0.0
 */
public class AppDaoListener<T extends BaseEntity> implements EntityListener<T> {

	/**
	 * デフォルトコンストラクタ
	 */
	public AppDaoListener() {
	}

	@Override
	public void preInsert(T entity, PreInsertContext<T> context) {
		// バージョン情報の設定
		entity.setVersion(Integer.valueOf(1));
		// 登録日時の設定
		entity.setRegDate(DateUtil.getSysDate());
		// 更新日時の設定
		entity.setUpdateDate(DateUtil.getSysDate());
	}

	@Override
	public void preUpdate(T entity, PreUpdateContext<T> context) {
		// バージョン情報の設定
		entity.setVersion(Integer.valueOf(entity.getVersion() + 1));
		// 更新日時の設定
		entity.setUpdateDate(DateUtil.getSysDate());
	}
}
