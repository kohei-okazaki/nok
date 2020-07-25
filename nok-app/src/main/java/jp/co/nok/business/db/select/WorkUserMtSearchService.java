package jp.co.nok.business.db.select;

import java.util.List;

import jp.co.nok.db.entity.WorkUserCompositeMt;

/**
 * 勤怠ユーザマスタ検索サービスインターフェース
 *
 * @version 1.0.0
 */
public interface WorkUserMtSearchService {

    List<WorkUserCompositeMt> selectCompositeRegularMt();

}
