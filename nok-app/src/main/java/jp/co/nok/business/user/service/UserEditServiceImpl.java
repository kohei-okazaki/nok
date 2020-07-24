package jp.co.nok.business.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import jp.co.nok.business.db.create.MailUserDataCreateService;
import jp.co.nok.business.db.select.LoginUserDataSearchService;
import jp.co.nok.business.db.select.MailUserDataSearchService;
import jp.co.nok.business.db.update.LoginUserDataUpdateService;
import jp.co.nok.business.db.update.MailUserDataUpdateService;
import jp.co.nok.business.user.dto.UserEditDto;
import jp.co.nok.common.log.Logger;
import jp.co.nok.common.log.LoggerFactory;
import jp.co.nok.common.util.DateUtil;
import jp.co.nok.db.entity.LoginUserData;
import jp.co.nok.db.entity.MailUserData;

/**
 * ユーザ情報変更画面のサービス実装クラス
 *
 * @version 1.0.0
 */
@Service
public class UserEditServiceImpl implements UserEditService {

    /** LOG */
    private static final Logger LOG = LoggerFactory.getLogger(UserEditServiceImpl.class);

    /** トランザクション管理クラス */
    @Autowired
    private PlatformTransactionManager transactionManager;
    /** トランザクションクラス */
    @Autowired
    private DefaultTransactionDefinition defaultTransactionDefinition;
    /** ログインユーザ情報検索サービス */
    @Autowired
    private LoginUserDataSearchService loginUserDataSearchService;
    /** ログインユーザ情報更新サービス */
    @Autowired
    private LoginUserDataUpdateService loginUserDataUpdateService;
    /** メールユーザ情報検索サービス */
    @Autowired
    private MailUserDataSearchService mailUserDataSearchService;
    /** メールユーザ情報作成サービス */
    @Autowired
    private MailUserDataCreateService mailUserDataCreateService;
    /** メールユーザ情報更新サービス */
    @Autowired
    private MailUserDataUpdateService mailUserDataUpdateService;

    @Override
    public void edit(UserEditDto dto) {

        // トランザクション開始
        TransactionStatus status = transactionManager
                .getTransaction(defaultTransactionDefinition);

        try {

            if (dto.getPasswordEditFlag().booleanValue()) {
                // パスワードを変更する場合
                LoginUserData loginUserData = loginUserDataSearchService
                        .selectById(dto.getSeqLoginId());
                loginUserData.setPassword(dto.getPassword());
                loginUserData.setPasswordExpire(DateUtil
                        .addMonth(DateUtil.toLocalDate(DateUtil.getSysDate()), 12));

                // ログインユーザ情報 更新
                loginUserDataUpdateService.update(loginUserData);

            }

            // メールユーザ情報を検索
            MailUserData mailUserData = mailUserDataSearchService
                    .selectBySeqLoginId(dto.getSeqLoginId());
            // 正常にDB更新出来た場合、コミット
            transactionManager.commit(status);

        } catch (Exception e) {
            // 登録処理中にエラーが起きた場合、ロールバック
            transactionManager.rollback(status);
            LOG.error("ユーザ情報変更処理をrollbackしました", e);
        }

    }

}
