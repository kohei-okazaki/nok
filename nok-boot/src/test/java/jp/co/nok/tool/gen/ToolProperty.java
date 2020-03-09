package jp.co.nok.tool.gen;

import java.util.ArrayList;
import java.util.List;

import jp.co.nok.common.io.property.annotation.Property;

/**
 * tool.propertiesのBeanクラス
 *
 * @version 1.0.0
 */
public class ToolProperty {

	/** 基底パス */
	@Property(name = "tool.basedir")
	private String baseDir;
	/** 自動生成ツールExcelまでのパス */
	@Property(name = "tool.excel.path")
	private String excelPath;
	/** 処理対象テーブルリスト */
	@Property(name = "tool.target.tables")
	private String targetTables;
	/** バージョン情報 */
	@Property(name = "tool.version")
	private String version;
	/** 処理対象テーブルリスト */
	private List<String> targetTableList = new ArrayList<>();

	public String getBaseDir() {
		return baseDir;
	}

	public void setBaseDir(String baseDir) {
		this.baseDir = baseDir;
	}

	public String getExcelPath() {
		return excelPath;
	}

	public void setExcelPath(String excelPath) {
		this.excelPath = excelPath;
	}

	public String getTargetTables() {
		return targetTables;
	}

	public void setTargetTables(String targetTables) {
		this.targetTables = targetTables;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public List<String> getTargetTableList() {
		return targetTableList;
	}

	public void addTargetTable(String targetTable) {
		this.targetTableList.add(targetTable);
	}

}
