package jp.co.nok.business.db.select;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.nok.db.dao.WorkUserMtDao;
import jp.co.nok.db.entity.WorkUserCompositeMt;

/**
 * 勤怠ユーザマスタ検索サービス実装クラス
 *
 * @version 1.0.0
 */
@Service
public class WorkUserMtSearchServiceImpl implements WorkUserMtSearchService {

    @Autowired
    private WorkUserMtDao dao;

    @Override
    public List<WorkUserCompositeMt> selectCompositeRegularMt() {
        return dao.selectCompositeRegularMt();
    }

}
