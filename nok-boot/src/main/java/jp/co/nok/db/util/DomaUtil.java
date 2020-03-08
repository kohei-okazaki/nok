package jp.co.nok.db.util;

import org.seasar.doma.jdbc.SelectOptions;

/**
 * DomaのUtilクラス
 *
 * @version 1.0.0
 */
public class DomaUtil {

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
	 * SelectOptionsを作成して返す
	 *
	 * @param pageable
	 *            Pageable
	 * @return SelectOptions
	 */
	public static SelectOptions createSelectOptions(Pageable pageable) {
		int page = pageable.getPage();
		int perpage = pageable.getPerpage();
		return createSelectOptions(page, perpage);
	}

	/**
	 * SelectOptionsを作成して返す
	 *
	 * @param page
	 *            ページ数
	 * @param perpage
	 *            許容数
	 * @return SelectOptions
	 */
	private static SelectOptions createSelectOptions(int page, int perpage) {
		int offset = (page - 1) * perpage;
		return createSelectOptions().offset(offset).limit(perpage);
	}

}
