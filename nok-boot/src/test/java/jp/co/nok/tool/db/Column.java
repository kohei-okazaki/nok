package jp.co.nok.tool.db;

/**
 * 自動生成ExcelのColumn情報
 *
 * @version 1.0.0
 */
public class Column {

	/** カラム名 */
	private String name;
	/** コメント */
	private String comment;
	/** カラム定義 */
	private String type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
