package jp.co.nok.business.db.update;

import jp.co.nok.db.entity.MailUserData;

/**
 * メールユーザ情報更新サービスインターフェース
 *
 * @version 1.0.0
 */
public interface MailUserDataUpdateService {

    /**
     * メールユーザ情報を更新する
     *
     * @param mailUserData
     *            メールユーザ情報
     */
    void update(MailUserData mailUserData);

}
