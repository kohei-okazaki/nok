package jp.co.nok.business.db.select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.nok.db.dao.MailUserDataDao;
import jp.co.nok.db.entity.MailUserData;

/**
 * メールユーザ情報検索サービス実装クラス
 *
 * @version 1.0.0
 */
@Service
public class MailUserDataSearchServiceImpl implements MailUserDataSearchService {

    /** メールユーザ情報Dao */
    @Autowired
    private MailUserDataDao dao;

    @Override
    public MailUserData selectBySeqLoginId(Integer seqLoginId) {
        return dao.selectBySeqLoginId(seqLoginId);
    }

}
