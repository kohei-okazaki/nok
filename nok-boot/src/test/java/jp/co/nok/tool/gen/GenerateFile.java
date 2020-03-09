package jp.co.nok.tool.gen;

import jp.co.nok.common.type.Charset;

/**
 * 自動生成ファイル情報を保持するクラス
 *
 * @version 1.0.0
 */
public class GenerateFile {

	/** 出力先ファイルパス */
	private String outputPath;
	/** ファイル名 */
	private String fileName;
	/** ファイル内容 */
	private String data;
	/** 文字コード */
	private Charset charset;

	public String getOutputPath() {
		return outputPath;
	}

	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Charset getCharset() {
		return charset;
	}

	public void setCharset(Charset charset) {
		this.charset = charset;
	}

}
