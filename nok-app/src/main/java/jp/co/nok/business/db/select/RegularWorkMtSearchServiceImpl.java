package jp.co.nok.business.db.select;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.nok.db.dao.RegularWorkMtDao;
import jp.co.nok.db.entity.RegularWorkMt;

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
    public List<RegularWorkMt> selectAll() {
        return dao.selectAll();
    }

}
