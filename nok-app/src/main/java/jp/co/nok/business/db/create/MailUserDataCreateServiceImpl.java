package jp.co.nok.business.db.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.nok.db.dao.MailUserDataDao;
import jp.co.nok.db.entity.MailUserData;

/**
 * メールユーザ情報サービス実装クラス
 *
 * @version 1.0.0
 */
@Service
public class MailUserDataCreateServiceImpl implements MailUserDataCreateService {

    /** メールユーザ情報Dao */
    @Autowired
    private MailUserDataDao dao;

    @Override
    public void create(MailUserData mailUserData) {
        dao.insert(mailUserData);
    }

}
