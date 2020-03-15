package jp.co.nok.tool.gen;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.StringJoiner;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

import jp.co.nok.common.type.Charset;
import jp.co.nok.common.util.FileUtil.FileExtension;
import jp.co.nok.common.util.FileUtil.FileSeparator;
import jp.co.nok.common.util.StringUtil;
import jp.co.nok.db.dao.BaseDao;
import jp.co.nok.tool.excel.Row;
import jp.co.nok.tool.source.Field;
import jp.co.nok.tool.source.Import;
import jp.co.nok.tool.source.JavaSource;
import jp.co.nok.tool.source.Method;
import jp.co.nok.tool.source.Signature;
import jp.co.nok.tool.source.type.AccessType;
import jp.co.nok.tool.source.type.ClassType;
import jp.co.nok.tool.util.ToolUtil;

/**
 * Daoクラスの自動生成クラス
 *
 * @version 1.0.0
 */
public class DaoGenerator extends BaseGenerator {

	@Override
	List<GenerateFile> generateImpl() throws Exception {

		// 自動生成ファイルリスト
		List<GenerateFile> list = new ArrayList<>();

		for (String table : prop.getTargetTableList()) {

			LOG.debug("テーブル名:" + table);

			JavaSource source = new JavaSource();
			setCommonInfo(source);

			boolean isCreatedDeleteMethod = false;
			boolean isCreatedUpdateMethod = false;
			boolean isCreatedInsertMethod = false;
			for (Row row : excel.getRowList()) {

				if (!ToolUtil.isTargetTable(row, table)) {
					continue;
				}

				source.setClassJavaDoc(ToolUtil.getLogicalName(row) + " Dao");
				source.setClassName(
						ToolUtil.toJavaFileName(ToolUtil.getPhysicalName(row)) + "Dao");

				Class<?> entityClass = Class.forName("jp.co.nok.db.entity."
						+ ToolUtil.toJavaFileName(ToolUtil.getPhysicalName(row)));
				Field field = new Field("", "", entityClass, null);

				if (!isCreatedDeleteMethod) {
					Method deleteMethod = getDeleteMethod(field, source);
					source.addMethod(deleteMethod);
					isCreatedDeleteMethod = true;
				}

				if (!isCreatedUpdateMethod) {
					Method updatMethod = getUpdatMethod(field, source);
					source.addMethod(updatMethod);
					isCreatedUpdateMethod = true;
				}

				if (!isCreatedInsertMethod) {
					Method insertMethod = getInsertMethod(field, source);
					source.addMethod(insertMethod);
					isCreatedInsertMethod = true;
				}

			}

			GenerateFile generateFile = new GenerateFile();
			generateFile.setCharset(Charset.UTF_8);
			generateFile.setFileName(ToolUtil.toJavaFileName(table) + "Dao"
					+ FileExtension.JAVA.getValue());
			generateFile.setData(ToolUtil.toStrJavaSource(source, prop));
			generateFile.setOutputPath(prop.getBaseDir() + FileSeparator.SYSTEM.getValue()
					+ GenerateType.DAO.getPath());

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
		source.setPackage(new jp.co.nok.tool.source.Package("jp.co.nok.db.dao"));
		// ソースのクラスタイプ
		source.setClassType(ClassType.INTERFACE);
		// ソースのアクセス修飾子
		source.setAccessType(AccessType.PUBLIC);
		// 親Interface
		source.addImplInterface(BaseDao.class);
		// 親InterfaceのImport
		source.addImport(new Import(BaseDao.class));
		// クラスに付与するorg.seasar.doma.Daoアノテーション
		source.addClassAnnotation(Dao.class, "");
		// クラスに付与するorg.seasar.doma.DaoアノテーションのImport
		source.addImport(new Import(Dao.class));
		// クラスに付与するorg.seasar.doma.boot.ConfigAutowireableアノテーション
		source.addClassAnnotation(ConfigAutowireable.class, "");
		// クラスに付与するorg.seasar.doma.boot.ConfigAutowireableアノテーションのImport
		source.addImport(new Import(ConfigAutowireable.class));

	}

	/**
	 * deleteメソッドを返す
	 *
	 * @param field
	 *            Javaフィールド
	 * @param source
	 *            JavaSource
	 * @return deleteメソッド
	 */
	private Method getDeleteMethod(Field field, JavaSource source) {

		Method deleteMethod = new Method(field, AccessType.PUBLIC) {

			@Override
			protected String getMethodName() {
				return "delete";
			}

			@Override
			public String toString() {
				final String TAB = StringUtil.TAB;
				StringJoiner body = new StringJoiner(StringUtil.NEW_LINE);
				for (Entry<Class<?>, String> entry : annotationMap.entrySet()) {
					body.add(TAB + "@" + entry.getKey().getSimpleName()
							+ entry.getValue());
				}
				body.add(TAB + accessType.getValue() + " int " + getMethodName() + "("
						+ signature.toString() + ");");

				return body.toString();
			}
		};

		Signature signature = new Signature();
		signature.addArgs(field.getClassType(), "entity");
		source.addImport(new Import(field.getClassType()));
		deleteMethod.setSignature(signature);

		deleteMethod.addAnnotation(Delete.class, "");
		source.addImport(new Import(Delete.class));

		return deleteMethod;
	}

	/**
	 * updateメソッドを返す
	 *
	 * @param field
	 *            Javaフィールド
	 * @param source
	 *            JavaSource
	 * @return updateメソッド
	 */
	private Method getUpdatMethod(Field field, JavaSource source) {

		Method updateMethod = new Method(field, AccessType.PUBLIC) {

			@Override
			protected String getMethodName() {
				return "update";
			}

			@Override
			public String toString() {
				final String TAB = StringUtil.TAB;
				StringJoiner body = new StringJoiner(StringUtil.NEW_LINE);
				for (Entry<Class<?>, String> entry : annotationMap.entrySet()) {
					body.add(TAB + "@" + entry.getKey().getSimpleName()
							+ entry.getValue());
				}
				body.add(TAB + this.accessType.getValue() + " int " + getMethodName()
						+ "(" + signature.toString() + ");");

				return body.toString();
			}
		};
		Signature signature = new Signature();
		signature.addArgs(field.getClassType(), "entity");
		source.addImport(new Import(field.getClassType()));
		updateMethod.setSignature(signature);

		updateMethod.addAnnotation(Update.class, "");
		source.addImport(new Import(Update.class));

		return updateMethod;
	}

	/**
	 * insertメソッドを返す
	 *
	 * @param field
	 *            Javaフィールド
	 * @param source
	 *            JavaSource
	 * @return insertメソッド
	 */
	private Method getInsertMethod(Field field, JavaSource source) {

		Method insertMethod = new Method(field, AccessType.PUBLIC) {

			@Override
			protected String getMethodName() {
				return "insert";
			}

			@Override
			public String toString() {
				final String TAB = "	";
				StringJoiner body = new StringJoiner(StringUtil.NEW_LINE);
				for (Entry<Class<?>, String> entry : annotationMap.entrySet()) {
					body.add(TAB + "@" + entry.getKey().getSimpleName()
							+ entry.getValue());
				}
				body.add(TAB + this.accessType.getValue() + " int " + getMethodName()
						+ "(" + signature.toString() + ");");

				return body.toString();
			}
		};
		Signature signature = new Signature();
		signature.addArgs(field.getClassType(), "entity");
		source.addImport(new Import(field.getClassType()));
		insertMethod.setSignature(signature);

		insertMethod.addAnnotation(Insert.class, "");
		source.addImport(new Import(Insert.class));

		return insertMethod;
	}

}
