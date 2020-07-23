package jp.co.nok.business.db.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.nok.db.dao.MailUserDataDao;

/**
 * ユーザメール情報サービス実装クラス
 *
 * @version 1.0.0
 */
@Service
public class MailUserDataCreateServiceImpl implements MailUserDataCreateService {

    @Autowired
    private MailUserDataDao dao;

}
