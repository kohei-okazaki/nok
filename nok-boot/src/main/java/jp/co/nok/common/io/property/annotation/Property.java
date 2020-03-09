package jp.co.nok.common.io.property.annotation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Propertiesファイルのアノテーション
 *
 * @see jp.co.nok.common.io.property.reader.PropertyReader#read(String,
 *      Class<T>)
 * @version 1.0.0
 */
@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface Property {

	/**
	 * 名前
	 *
	 * @return name
	 */
	String name();
}
