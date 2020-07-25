package jp.co.nok.business.db.select;

import java.util.List;

import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jp.co.nok.db.dao.RegularWorkMtDao;
import jp.co.nok.db.entity.RegularWorkMt;
import jp.co.nok.db.util.DomaUtil;

/**
 * 定時情報マスタ検索サービス実装クラス
 *
 * @version 1.0.0
 */
@Service
public class RegularWorkMtSearchServiceImpl implements RegularWorkMtSearchService {

    /** 定時情報マスタDao */
    @Autowired
    private RegularWorkMtDao dao;

    @Override
    public int count() {
        return dao.count();
    }

    @Override
    // @Cacheable(key = "'regularWorkMtList'", value = "app")
    public List<RegularWorkMt> selectAll() {
        return dao.selectAll();
    }

    @Override
    public List<RegularWorkMt> selectAll(Pageable pageable) {
        SelectOptions option = DomaUtil.createSelectOptions(pageable, false);
        return dao.selectAll(option);
    }

    @Override
    public RegularWorkMt selectById(Integer id) {
        return dao.selectById(id);
    }

}
