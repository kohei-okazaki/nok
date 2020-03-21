package jp.co.nok.tool.excel;

import jp.co.nok.tool.excel.type.ColumnType;

/**
 * 自動生成ツールのCellクラス
 *
 * @version 1.0.0
 */
public class Cell {

	/** 値 */
	private String value;

	/**
	 * コンストラクタ
	 *
	 * @param value
	 *            値
	 */
	public Cell(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public ColumnType getColumnType() {
		return ColumnType.of(this.value);
	}
}
