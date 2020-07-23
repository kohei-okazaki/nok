package jp.co.nok.business.db.update;

import jp.co.nok.db.entity.MailUserData;

/**
 * ユーザメール情報更新サービスインターフェース
 *
 * @version 1.0.0
 */
public interface MailUserDataUpdateService {

    /**
     * @param mailUserData
     */
    void update(MailUserData mailUserData);

}
