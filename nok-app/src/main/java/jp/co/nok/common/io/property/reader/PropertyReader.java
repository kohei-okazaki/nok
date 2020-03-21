package jp.co.nok.common.io.property.reader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import jp.co.nok.common.io.property.annotation.Property;
import jp.co.nok.common.log.Logger;
import jp.co.nok.common.log.LoggerFactory;
import jp.co.nok.common.util.BeanUtil;
import jp.co.nok.common.util.BeanUtil.AccessorType;
import jp.co.nok.common.util.FileUtil.FileExtension;
import jp.co.nok.common.util.StringUtil;

/**
 * PropertiesファイルのReader
 *
 * @version 1.0.0
 */
public class PropertyReader {

	/** LOG */
	private static final Logger LOG = LoggerFactory.getLogger(PropertyReader.class);

	/**
	 * 指定されたファイルパスからpropertiesファイルの読込を行う
	 *
	 * @param filePath
	 *            ファイルパス
	 * @return Properties
	 */
	public Properties read(String filePath) {

		if (StringUtil.isEmpty(filePath)
				|| !filePath.endsWith(FileExtension.PROPERTY.getValue())) {
			// プロパティファイルが存在しない、または拡張子が ".properties" でない場合
			return null;
		}

		LOG.debug(filePath + "の読込 開始");
		Properties prop = new Properties();
		try (InputStream is = new FileInputStream(filePath)) {
			prop.load(is);
		} catch (FileNotFoundException e) {
			LOG.error("プロパティファイル読込エラー ファイル名=" + filePath, e);
		} catch (IOException e) {
			LOG.error("読込エラー", e);
		} finally {
			LOG.debug(filePath + "の読込 終了");
		}
		return prop;
	}

	/**
	 * 指定したパスのプロパティを読み取り、対応するBeanを返す
	 *
	 * @param filePath
	 *            ファイルパス
	 * @param clazz
	 *            bean
	 * @return 対応するBean
	 */
	public <T> T read(String filePath, Class<T> clazz) {

		Properties prop = read(filePath);
		if (prop == null) {
			return null;
		}
		try {
			T t = clazz.getDeclaredConstructor().newInstance();

			for (Field f : BeanUtil.getFieldList(clazz)) {
				if (!f.isAnnotationPresent(Property.class)) {
					// @Propertyが付与されていないフィールドの場合、次のフィールドへ
					continue;
				}

				// プロパティファイル.プロパティ名から値を取得
				Object value = prop.get(f.getAnnotation(Property.class).name());
				BeanUtil.getAccessor(f.getName(), clazz, AccessorType.SETTER)
						.invoke(t, value);
			}
			return t;

		} catch (InstantiationException e) {
			LOG.error("インスタンスの生成に失敗しました", e);
		} catch (IllegalAccessException e) {
			LOG.error("アクセス修飾子が不正です", e);
		} catch (IllegalArgumentException e) {
			LOG.error("constructor or setterの引数が不正です", e);
		} catch (InvocationTargetException e) {
			LOG.error("constructor or setterの処理に失敗しました", e);
		} catch (NoSuchMethodException e) {
			LOG.error("setterが存在しません", e);
		} catch (SecurityException e) {
			LOG.error("インスタンスの生成に失敗しました", e);
		}
		return null;
	}

}
