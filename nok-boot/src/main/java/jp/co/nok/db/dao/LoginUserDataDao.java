package jp.co.nok.db.dao;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Update;

import jp.co.nok.db.entity.LoginUserData;

/**
 * ログインユーザ情報 Dao
 *
 * @version 1.0.0
 */
@Dao
public interface LoginUserDataDao extends BaseDao {

	@Delete
	public int delete(LoginUserData entity);

	@Update
	public int update(LoginUserData entity);

	@Insert
	public int insert(LoginUserData entity);

}