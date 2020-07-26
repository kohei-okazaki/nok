package jp.co.nok.business.db.select;

import java.util.List;

import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jp.co.nok.db.dao.WorkUserMtDao;
import jp.co.nok.db.entity.WorkUserCompositeMt;
import jp.co.nok.db.util.DomaUtil;

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
    public long count() {
        return dao.count();
    }

    @Override
    public List<WorkUserCompositeMt> selectCompositeRegularMt() {
        return dao.selectCompositeRegularMt();
    }

    @Override
    public List<WorkUserCompositeMt> selectCompositeRegularMt(Pageable pageable) {
        SelectOptions option = DomaUtil.createSelectOptions(pageable, false);
        return dao.selectCompositeRegularMt(option);
    }

}
