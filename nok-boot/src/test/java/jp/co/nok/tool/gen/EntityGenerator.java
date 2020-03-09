package jp.co.nok.tool.gen;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import org.seasar.doma.Entity;
import org.seasar.doma.jdbc.entity.NamingType;

import jp.co.nok.common.type.Charset;
import jp.co.nok.common.util.FileUtil.FileExtension;
import jp.co.nok.common.util.FileUtil.FileSeparator;
import jp.co.nok.common.util.FileUtil.LineFeedType;
import jp.co.nok.db.entity.BaseEntity;
import jp.co.nok.tool.source.Field;
import jp.co.nok.tool.source.Getter;
import jp.co.nok.tool.source.Import;
import jp.co.nok.tool.source.JavaSource;
import jp.co.nok.tool.source.Setter;
import jp.co.nok.tool.source.type.AccessType;
import jp.co.nok.tool.source.type.ClassType;
import jp.co.nok.tool.util.ToolUtil;

/**
 * Entityクラスの自動生成クラス
 *
 * @version 1.0.0
 */
public class EntityGenerator extends BaseGenerator {

	@Override
	List<GenerateFile> generateImpl() {

		// 自動生成ファイルリスト
		List<GenerateFile> list = new ArrayList<>();

		for (String table : prop.getTargetTableList()) {

			JavaSource source = new JavaSource();
			setCommonInfo(source);

			excel.getRowList().stream()
					.filter(row -> ToolUtil.isTargetTable(row, table))
					.forEach(row -> {

						source.setClassJavaDoc(ToolUtil.getLogicalName(row) + " Entity");
						source.setClassName(
								ToolUtil.toJavaFileName(ToolUtil.getPhysicalName(row)));

						// fieldの設定
						Field field = new Field(
								ToolUtil.toCamelCase(ToolUtil.getFieldName(row)),
								ToolUtil.getColumnComment(row),
								ToolUtil.getClassType(row),
								ToolUtil.getFieldAnnotationMap(row, source));
						source.addField(field);

						// fieldのimport文を設定
						Import im = new Import(field);
						source.addImport(im);

						// setterの設定
						Setter setter = new Setter(field);
						source.addMethod(setter);

						// getterの設定
						Getter getter = new Getter(field);
						source.addMethod(getter);
					});

			GenerateFile generateFile = new GenerateFile();
			generateFile.setCharset(Charset.UTF_8);
			generateFile.setFileName(
					ToolUtil.toJavaFileName(table) + FileExtension.JAVA.getValue());
			generateFile.setData(toStrSource(source));
			generateFile.setOutputPath(prop.getBaseDir() + FileSeparator.SYSTEM.getValue()
					+ GenerateType.ENTITY.getPath());
			list.add(generateFile);
		}

		return list;
	}

	/**
	 * 共通情報を設定する
	 *
	 * @param source
	 *            JavaSource
	 */
	private void setCommonInfo(JavaSource source) {

		// パッケージ
		source.setPackage(new jp.co.nok.tool.source.Package("jp.co.nok.db.entity"));
		// ソースのクラスタイプ
		source.setClassType(ClassType.CLASS);
		// ソースのアクセス修飾子
		source.setAccessType(AccessType.PUBLIC);
		// 親クラス
		source.setExtendsClass(BaseEntity.class);
		// 親クラスのImport
		source.addImport(new Import(BaseEntity.class));
		// クラスに付与するアノテーション
		source.addClassAnnotation(Entity.class, "(naming = NamingType.SNAKE_UPPER_CASE)");
		// org.seasar.doma.EntityのImport
		source.addImport(new Import(Entity.class));
		// org.seasar.doma.jdbc.entity.NamingTypeのImport
		source.addImport(new Import(NamingType.class));

	}

	/**
	 * 指定されたJavaSourceからEntityクラスの文字列表現を返す
	 *
	 * @param source
	 *            JavaSource
	 * @return Entityクラスの文字列表現
	 */
	private String toStrSource(JavaSource source) {

		StringJoiner result = new StringJoiner(
				LineFeedType.CRLF.getValue() + LineFeedType.CRLF.getValue());

		// package情報
		result.add(ToolUtil.toStrPackage(source));

		// import情報
		result.add(ToolUtil.toStrImportList(source));

		// class情報
		result.add(
				ToolUtil.toStrClassJavaDoc(source, prop)
						+ LineFeedType.CRLF.getValue()
						+ ToolUtil.toStrClassAnnotation(source)
						+ LineFeedType.CRLF.getValue()
						+ ToolUtil.toStrClassName(source)
						+ ToolUtil.toStrExtendsClass(source)
						+ ToolUtil.toStrInterfaceList(source)
						+ " {");

		// field情報
		result.add(ToolUtil.toStrFieldList(source));

		// method情報
		result.add(ToolUtil.toStrMethodList(source));

		result.add("}");

		return result.toString();
	}

}
