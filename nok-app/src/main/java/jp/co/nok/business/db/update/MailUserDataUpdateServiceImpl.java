package jp.co.nok.business.db.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.nok.db.dao.MailUserDataDao;
import jp.co.nok.db.entity.MailUserData;

/**
 * メールユーザ情報更新サービス実装クラス
 *
 * @version 1.0.0
 */
@Service
public class MailUserDataUpdateServiceImpl implements MailUserDataUpdateService {

    /** メールユーザ情報Dao */
    @Autowired
    private MailUserDataDao dao;

    @Override
    public void update(MailUserData mailUserData) {
        dao.update(mailUserData);
    }

}
