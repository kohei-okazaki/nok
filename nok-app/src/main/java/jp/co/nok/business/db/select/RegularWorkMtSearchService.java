package jp.co.nok.business.db.select;

import java.util.List;

import jp.co.nok.db.entity.RegularWorkMt;

/**
 * 定時情報マスタ検索サービスインターフェース
 *
 * @version 1.0.0
 */
public interface RegularWorkMtSearchService {

    List<RegularWorkMt> selectAll();

    RegularWorkMt selectById(Integer seqRegularWorkMtId);

}
