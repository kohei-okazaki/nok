package jp.co.nok.business.db.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.nok.db.dao.RegularWorkMtDao;
import jp.co.nok.db.entity.RegularWorkMt;

/**
 * 定時情報マスタ作成サービス実装クラス
 *
 * @version 1.0.0
 */
@Service
public class RegularWorkMtCreateServiceImpl implements RegularWorkMtCreateService {

    @Autowired
    private RegularWorkMtDao dao;

    @Override
    public void create(RegularWorkMt regularWorkMt) {
        dao.insert(regularWorkMt);
    }

}
