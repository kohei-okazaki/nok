package jp.co.nok.business.user.service;

import jp.co.nok.business.user.dto.UserEditDto;

/**
 * ユーザ情報変更画面のサービスインターフェース
 *
 * @version 1.0.0
 */
public interface UserEditService {

    /**
     * ユーザ情報変更処理を行う
     * 
     * @param dto
     *            ユーザ情報設定変更画面DTO
     */
    void edit(UserEditDto dto);

}
