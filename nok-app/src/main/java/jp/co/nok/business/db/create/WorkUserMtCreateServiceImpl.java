package jp.co.nok.business.db.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.nok.db.dao.WorkUserMtDao;
import jp.co.nok.db.entity.WorkUserMt;

/**
 * 勤怠ユーザマスタ作成サービス実装クラス
 *
 * @version 1.0.0
 */
@Service
public class WorkUserMtCreateServiceImpl implements WorkUserMtCreateService {

    @Autowired
    private WorkUserMtDao dao;

    @Override
    public void create(WorkUserMt workUserMt) {
        dao.insert(workUserMt);
    }

}
