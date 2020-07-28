package jp.co.nok.db.dao;

import jp.co.nok.db.dao.BaseDao;
import org.seasar.doma.Dao;
import org.seasar.doma.boot.ConfigAutowireable;
import jp.co.nok.db.entity.BusinessCalendarMt;
import org.seasar.doma.Delete;
import org.seasar.doma.Update;
import org.seasar.doma.Insert;

/**
 * 営業日マスタ Dao
 *
 * @version 1.0.0
 */
@Dao
@ConfigAutowireable
public interface BusinessCalendarMtDao extends BaseDao {

    @Delete
    public int delete(BusinessCalendarMt entity);

    @Update
    public int update(BusinessCalendarMt entity);

    @Insert
    public int insert(BusinessCalendarMt entity);

}