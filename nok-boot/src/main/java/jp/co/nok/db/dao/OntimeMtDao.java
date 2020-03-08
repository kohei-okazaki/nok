package jp.co.nok.db.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;

import jp.co.nok.db.entity.OntimeMt;
import jp.co.nok.db.util.DaoRepository;

/**
 * 定時情報マスタ Dao<br>
 * TODO 削除
 */
@Dao
@DaoRepository
public interface OntimeMtDao extends BaseDao {

	@Delete
	public int delete(OntimeMt entity);

	@Update
	public int update(OntimeMt entity);

	@Insert
	public int insert(OntimeMt entity);

	@Select
	public List<OntimeMt> select();

}
