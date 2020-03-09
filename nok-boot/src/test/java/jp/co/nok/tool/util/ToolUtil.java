package jp.co.nok.tool.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import org.apache.commons.codec.digest.Crypt;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;

import jp.co.nok.common.log.Logger;
import jp.co.nok.common.log.LoggerFactory;
import jp.co.nok.common.log.annotation.Mask;
import jp.co.nok.common.util.BeanUtil;
import jp.co.nok.common.util.CollectionUtil;
import jp.co.nok.common.util.FileUtil;
import jp.co.nok.common.util.StringUtil;
import jp.co.nok.tool.excel.Cell;
import jp.co.nok.tool.excel.Row;
import jp.co.nok.tool.excel.type.CellPositionType;
import jp.co.nok.tool.excel.type.ColumnType;
import jp.co.nok.tool.gen.GenerateFile;
import jp.co.nok.tool.gen.ToolProperty;
import jp.co.nok.tool.source.Import;
import jp.co.nok.tool.source.JavaSource;
import jp.co.nok.tool.source.type.ClassType;

/**
 * 自動生成ツールのUtilクラス
 *
 * @version 1.0.0
 */
public class ToolUtil {

	/** LOG */
	private static final Logger LOG = LoggerFactory.getLogger(ToolUtil.class);

	/**
	 * プライベートコンストラクタ
	 */
	private ToolUtil() {
	}

	/**
	 * 自動生成ファイルの作成処理を行う
	 *
	 * @param genFileList
	 *            自動生成ファイルリスト
	 */
	public static void createGenFileList(List<GenerateFile> genFileList) {
		try {
			for (GenerateFile genFile : genFileList) {
				Path path = FileUtil.createFile(
						genFile.getOutputPath() + FileUtil.FileSeparator.SYSTEM.getValue()
								+ genFile.getFileName());
				Files.write(path,
						genFile.getData().getBytes(genFile.getCharset().getValue()));
			}
		} catch (IOException e) {
			LOG.error("自動生成に失敗", e);
		}
	}

	/**
	 * 指定されたテーブル名が処理対象の行であるかどうか判定する
	 *
	 * @param row
	 *            行情報
	 * @param tableName
	 *            テーブル名
	 * @return 処理対象の場合true、それ以外の場合false
	 */
	public static boolean isTargetTable(Row row, String tableName) {
		Cell cell = row.getCell(CellPositionType.PHYSICAL_NAME);
		return tableName.equals(cell.getValue());
	}

	/**
	 * 指定された行情報から物理名を返す
	 *
	 * @param row
	 *            行情報
	 * @return 物理名
	 */
	public static String getPhysicalName(Row row) {
		return row.getCell(CellPositionType.PHYSICAL_NAME).getValue();
	}

	/**
	 * 指定された行情報から論理名を返す
	 *
	 * @param row
	 *            行情報
	 * @return 論理名
	 */
	public static String getLogicalName(Row row) {
		return row.getCell(CellPositionType.LOGICAL_NAME).getValue();
	}

	/**
	 * 指定された行情報からフィールド名を返す
	 *
	 * @param row
	 *            行情報
	 * @return フィールド名
	 */
	public static String getFieldName(Row row) {
		return row.getCell(CellPositionType.COLUMN_NAME).getValue();
	}

	/**
	 * 指定された行情報からクラスタイプを返す
	 *
	 * @param row
	 *            行情報
	 * @return クラスタイプ
	 */
	public static Class<?> getClassType(Row row) {
		String columnType = row.getCell(CellPositionType.COLUMN_TYPE).getValue();
		return ColumnType.of(columnType).getClassType();
	}

	/**
	 * 指定された行情報からカラム名(コメント)を返す
	 *
	 * @param row
	 *            行情報
	 * @return カラム名(コメント)
	 */
	public static String getColumnComment(Row row) {
		return row.getCell(CellPositionType.COLUMN_NAME_COMMENT).getValue();
	}

	/**
	 * 指定された行情報からフィールドに付与するアノテーションのMapを返す
	 *
	 * @param row
	 *            行情報
	 * @param source
	 *            Javaソース
	 * @return フィールドに付与するアノテーションのMap
	 */
	public static Map<Class<?>, String> getFieldAnnotationMap(Row row,
			JavaSource source) {

		Map<Class<?>, String> map = new HashMap<>();
		Cell primaryKeyCell = row.getCell(CellPositionType.PRIMARY_KEY);
		if (StringUtil.hasValue(primaryKeyCell.getValue())) {
			map.put(Id.class, "");
			source.addImport(new Import(Id.class));
		}
		Cell sequenceCell = row.getCell(CellPositionType.SEQUENCE);
		if (StringUtil.hasValue(sequenceCell.getValue())) {
			map.put(GeneratedValue.class, "strategy = GenerationType.IDENTITY");
			source.addImport(new Import(GeneratedValue.class));
			source.addImport(new Import(GenerationType.class));
		}
		Cell cryptCell = row.getCell(CellPositionType.CRYPT);
		if (StringUtil.hasValue(cryptCell.getValue())) {
			map.put(Mask.class, "");
			source.addImport(new Import(Mask.class));

			map.put(Crypt.class, "");
			source.addImport(new Import(Crypt.class));
		}
		return map;
	}

	/**
	 * Javaファイル名に変換する<br>
	 * (例)<br>
	 * TEST_NAME -> TestName<br>
	 *
	 * @param fileName
	 *            ファイル名
	 * @return Javaファイル名
	 */
	public static String toJavaFileName(String fileName) {

		String result = toCamelCase(fileName);
		// 先頭の文字列を大文字に変換
		Character startChar = result.charAt(0);
		Character large = Character.toUpperCase(startChar);

		return result.replaceFirst(startChar.toString(), large.toString());
	}

	/**
	 * <code>name</code>をキャメルケースに変換する<br>
	 * (例)<br>
	 * TEST_NAME -> testName<br>
	 *
	 * @param name
	 *            テーブル名
	 * @return キャメルケースに変換した文字列
	 */
	public static String toCamelCase(String name) {

		String result = name.toLowerCase();
		while (result.indexOf(StringUtil.UNDER_SCORE) != -1) {
			int pos = result.indexOf(StringUtil.UNDER_SCORE);
			String target = result.substring(pos, pos + 2);
			String after = target.replace(StringUtil.UNDER_SCORE, StringUtil.EMPTY)
					.toUpperCase();
			result = result.replaceFirst(target, after);
		}

		return result;
	}

	/**
	 * 指定されたJavaSourceのClassのパッケージの文字列表現を返す
	 *
	 * @param source
	 *            JavaSource
	 * @return Javadocの文字列表現
	 */
	public static String toStrPackage(JavaSource source) {
		return source.getPackage().toString();
	}

	/**
	 * 指定されたJavaSourceのClassのImport文の文字列表現を返す
	 *
	 * @param source
	 *            JavaSource
	 * @return Javadocの文字列表現
	 */
	public static String toStrImportList(JavaSource source) {

		List<String> strImportList = new ArrayList<>();
		source.getImportList().stream()
				.filter(e -> !strImportList.contains(e.toString()))
				.map(e -> e.toString())
				.forEach(e -> strImportList.add(e));
		StringJoiner packageBody = new StringJoiner(StringUtil.NEW_LINE);
		strImportList.stream().forEach(e -> packageBody.add(e));

		return packageBody.toString();
	}

	/**
	 * 指定されたJavaSourceのClassのJavaDocの文字列表現を返す
	 *
	 * @param source
	 *            JavaSource
	 * @param prop
	 *            自動生成ツール設定ファイル
	 * @return Javadocの文字列表現
	 */
	public static String toStrClassJavaDoc(JavaSource source, ToolProperty prop) {
		StringJoiner sj = new StringJoiner(StringUtil.NEW_LINE);
		sj.add("/**");
		sj.add(" * " + source.getClassJavaDoc());
		sj.add(" *");
		sj.add(" * @version " + prop.getVersion());
		sj.add(" */");
		return sj.toString();
	}

	/**
	 * 指定されたJavaSourceのClassに付与するアノテーション部分を組み立てる
	 *
	 * @param source
	 *            JavaSource
	 * @return クラスに付与するアノテーション部分
	 */
	public static String toStrClassAnnotation(JavaSource source) {

		StringJoiner body = new StringJoiner(StringUtil.NEW_LINE);
		source.getClassAnnotationMap().entrySet().stream()
				.forEach(e -> body.add("@" + e.getKey().getSimpleName() + e.getValue()));

		return body.toString();
	}

	/**
	 * 指定されたJavaSourceのClass名部分を組み立てる<br>
	 * ex<br>
	 * <code>public class XXXX</code>
	 *
	 * @param source
	 *            生成するJavaファイルのリソース
	 * @return クラス名
	 */
	public static String toStrClassName(JavaSource source) {

		String accessType = source.getAccessType().getValue();
		String classType = source.getClassType().getValue();
		String className = source.getClassName();
		StringJoiner body = new StringJoiner(StringUtil.SPACE);

		return body.add(accessType).add(classType).add(className).toString();
	}

	/**
	 * 指定されたJavaSourceのClassの継承部分を組み立てる<br>
	 * <cpde>extends AAAA</code>
	 *
	 * @param source
	 *            生成するJavaファイルのリソース
	 * @return Classの継承部分
	 */
	public static String toStrExtendsClass(JavaSource source) {

		if (BeanUtil.isNull(source.getExtendsClass())) {
			return "";
		}

		return " extends " + source.getExtendsClass().getSimpleName();
	}

	/**
	 * interfaceのリストの継承部分を組み立てる<br>
	 * <code>implements AAAA, BBBB</code><br>
	 * or<br>
	 * <code>extends AAAA, BBBB</code>
	 *
	 * @param source
	 *            生成するJavaファイルのリソース
	 * @return interfaceのリストの継承部分
	 */
	public static String toStrInterfaceList(JavaSource source) {

		if (CollectionUtil.isEmpty(source.getImplInterfaceList())) {
			return "";
		}

		String prefix = ClassType.CLASS == source.getClassType()
				? " implements "
				: " extends ";
		StringJoiner body = new StringJoiner(StringUtil.COMMA + StringUtil.SPACE);
		source.getImplInterfaceList().stream().forEach(e -> body.add(e.getSimpleName()));

		return prefix + body.toString();
	}

	/**
	 * 指定されたJavaSourceのClassのFieldの文字列表現を返す
	 *
	 * @param source
	 *            JavaSource
	 * @return Javadocの文字列表現
	 */
	public static String toStrFieldList(JavaSource source) {

		StringJoiner body = new StringJoiner(StringUtil.NEW_LINE);
		source.getFieldList().stream().forEach(e -> body.add(e.toString()));
		return body.toString();
	}

	/**
	 * 指定されたJavaSourceのClassのMethodの文字列表現を返す
	 *
	 * @param source
	 *            JavaSource
	 * @return Javadocの文字列表現
	 */
	public static String toStrMethodList(JavaSource source) {

		StringJoiner body = new StringJoiner(StringUtil.NEW_LINE + StringUtil.NEW_LINE);
		source.getMethodList().stream().forEach(e -> body.add(e.toString()));
		return body.toString();
	}

}
