package jp.co.nok.db.util;

/**
 * 暗号/復号インターフェース
 *
 * @version 1.0.0
 */
public interface Crypter {

	/**
	 * 暗号化を行う
	 *
	 * @param str
	 *            暗号化したい文字列
	 * @return 暗号化後の文字列
	 */
	String encrypt(String str);

	/**
	 * 復号を行う
	 *
	 * @param str
	 *            復号したい文字列
	 * @return 復号後の文字列
	 */
	String decrypt(String str);
}
