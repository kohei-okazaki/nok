package jp.co.nok.db.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.seasar.doma.jdbc.SelectOptions;
import org.springframework.data.domain.Pageable;

import jp.co.nok.common.log.Logger;
import jp.co.nok.common.log.LoggerFactory;
import jp.co.nok.web.view.PagingView;
import jp.co.nok.web.view.PagingViewElement;

/**
 * DomaのUtilクラス
 *
 * @version 1.0.0
 */
public class DomaUtil {

    /** LOG */
    private static final Logger LOG = LoggerFactory.getLogger(DomaUtil.class);

    /**
     * プライベートコンストラクタ
     */
    private DomaUtil() {
    }

    /**
     * SelectOptionsを作成して返す
     *
     * @return SelectOptions
     */
    public static SelectOptions createSelectOptions() {
        return SelectOptions.get();
    }

    /**
     * {@linkplain SelectOptions}を返す
     *
     * @param pageable
     *            Pageable
     * @param countFlg
     *            カウント取得フラグ
     * @return SelectOptions
     */
    public static SelectOptions createSelectOptions(Pageable pageable, boolean countFlg) {
        int offset = pageable.getPageNumber() * pageable.getPageSize();
        int limit = pageable.getPageSize();

        SelectOptions selectOptions = SelectOptions.get().offset(offset).limit(limit);
        if (countFlg) {
            selectOptions = selectOptions.count();
        }

        return selectOptions;
    }

    /**
     * 画面のページング用のクラスを返す<br>
     * <code>path</code>には遷移先のURLのパスを指定する<br>
     * 例:http://localhost:88/work/userregular/entry?page=nnに遷移したい場合、<br>
     * <b>/work/userregular/entry?page</b>を指定する
     *
     * @param pageable
     *            Pageable
     * @param path
     *            遷移先パスの固定文言
     * @param count
     *            総レコード件数
     * @return PagingView
     */
    public static PagingView getPageView(Pageable pageable, String path, long count) {

        LOG.debug(
                // 要求しているページ番号(0スタート)
                "pageable.getPageNumber()=" + pageable.getPageNumber()
                // 表示件数(5で固定)
                        + ", pageable.getPageSize()=" + pageable.getPageSize()
                        // ↑2つの積
                        + ", pageable.getOffset()=" + pageable.getOffset());

        PagingView pv = new PagingView();

        pv.setCanGoFirst(pageable.getPageNumber() != 0);
        pv.setCanGoPrevious(pageable.getPageNumber() != 0);
        pv.setCanGoNext(
                pageable.getPageNumber() < (count / pageable.getPageSize()));
        pv.setCanGoLast(
                pageable.getPageNumber() < (count / pageable.getPageSize()));

        pv.setCurrentPageNum(pageable.getPageNumber());
        pv.setFirstHref("/work/userregular/entry?page=0");

        int fromRecordNum = pageable.getPageSize() * pageable.getPageNumber() + 1;
        pv.setFromRecordNum(fromRecordNum);
        pv.setLastHref(
                "/work/userregular/entry?page=" + (count / pageable.getPageSize()));
        pv.setNextHref(
                "/work/userregular/entry?page=" + (pageable.getPageNumber() + 1));
        pv.setPreviousHref(
                "/work/userregular/entry?page=" + (pageable.getPageNumber() - 1));
        pv.setRecordPerPage(5);

        long toRecordNum = pageable.getPageSize() * pageable.getPageNumber()
                + pageable.getPageSize();
        toRecordNum = count < toRecordNum ? count : toRecordNum;
        pv.setToRecordNum(toRecordNum);
        pv.setTotalRecordNum(count);

        return pv;
    }

    /**
     * {@linkplain PagingViewElement}のリストを返す
     *
     * @param currentPageNum
     *            現在のページ数
     * @param totalPageNum
     *            総ページ数
     * @param length
     *            長さ
     * @param preAppendPageNum
     *            遷移先のリンクの接頭語(/hoge/huga)
     * @return PagingViewElementリスト
     */
    @Deprecated
    public static List<PagingViewElement> generatePagingViewElements(
            int currentPageNum,
            int totalPageNum,
            int length,
            String preAppendPageNum) {
        /* 偶数個のリストが要求された場合は現在のページが前寄せになる。
           例) [] がついているのが現在ページ
             << < 1 2 [3] 4 5 6 > >>
        */
        int backSpan = (length - 1) / 2;
        int forthSpan = (length - 1) - backSpan;

        int startIndex;
        int endIndex;

        if (currentPageNum - backSpan < 1) {
            // 表示幅に従うと存在しないページ(0ページ以下)が生成されるので、1ページから始める
            startIndex = 1;
            endIndex = length < totalPageNum ? length : totalPageNum;
        } else if (currentPageNum + forthSpan > totalPageNum) {
            // 表示幅に従うと存在しないページ(最終ページ以降)が生成されるので、表示領域を最終ページから逆算する
            startIndex = totalPageNum - (length - 1) > 1 ? totalPageNum - (length - 1)
                    : 1;
            endIndex = totalPageNum;
        } else {
            // その間なので、中央にcurrentPageNumがくるように配置する。
            // ページのリストの端に当たっていないので、単純に中央にくるような両端を考えればよい。
            startIndex = currentPageNum - backSpan;
            endIndex = currentPageNum + forthSpan;
        }
        return IntStream.range(startIndex, endIndex + 1)
                .mapToObj(n -> new PagingViewElement(String.valueOf(n),
                        preAppendPageNum + (n - 1)))
                // preAppendPageNum + n))
                .collect(Collectors.toList());
    }

    // /**
    // * SelectOptionsを作成して返す
    // *
    // * @param pageable
    // * Pageable
    // * @return SelectOptions
    // */
    // public static SelectOptions createSelectOptions(Pageable pageable) {
    // return createSelectOptions(pageable.getPage(), pageable.getPerpage());
    // }
    //
    // /**
    // * SelectOptionsを作成して返す
    // *
    // * @param page
    // * ページ数
    // * @param perpage
    // * 許容数
    // * @return SelectOptions
    // */
    // private static SelectOptions createSelectOptions(int page, int perpage) {
    // int offset = (page - 1) * perpage;
    // return createSelectOptions().offset(offset).limit(perpage);
    // }

}
