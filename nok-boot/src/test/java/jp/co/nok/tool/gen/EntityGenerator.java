package jp.co.nok.tool.gen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.seasar.doma.Entity;
import org.seasar.doma.jdbc.entity.NamingType;

import jp.co.nok.common.util.FileUtil.FileExtension;
import jp.co.nok.common.util.FileUtil.FileSeparator;
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

	/** BaseEntityで定義されているため、フィールド作成を無視するカラムリスト */
	private static final List<String> IGNORE_FIELD_LIST = Arrays.asList("VERSION",
			"REG_DATE", "UPDATE_DATE");

	@Override
	List<GenerateFile> generateImpl() throws Exception {

		// 自動生成ファイルリスト
		List<GenerateFile> list = new ArrayList<>();

		for (String table : prop.getTargetTableList()) {

			LOG.debug("テーブル名:" + table);

			JavaSource source = new JavaSource();
			setCommonInfo(source);

			excel.getRowList().stream()
					.filter(row -> ToolUtil.isTargetTable(row, table))
					.filter(row -> !IGNORE_FIELD_LIST
							.contains(ToolUtil.getFieldName(row)))
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
			generateFile.setFileName(
					ToolUtil.toJavaFileName(table) + FileExtension.JAVA.getValue());
			generateFile.setData(ToolUtil.toStrJavaSource(source, prop));
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

}
