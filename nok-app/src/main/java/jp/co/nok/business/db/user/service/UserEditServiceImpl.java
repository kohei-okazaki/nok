package jp.co.nok.business.db.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.nok.business.db.create.MailUserDataCreateService;
import jp.co.nok.business.db.update.LoginUserDataUpdateService;
import jp.co.nok.business.db.user.dto.UserEditDto;

/**
 * ユーザ情報変更画面のサービス実装クラス
 *
 * @version 1.0.0
 */
@Service
public class UserEditServiceImpl implements UserEditService {

    /** ログインユーザ情報更新サービス */
    @Autowired
    private LoginUserDataUpdateService loginUserDataUpdateService;
    /** ユーザメール情報作成サービス */
    @Autowired
    private MailUserDataCreateService mailUserDataCreateService;

    @Override
    public void edit(UserEditDto dto) {

    }

}
