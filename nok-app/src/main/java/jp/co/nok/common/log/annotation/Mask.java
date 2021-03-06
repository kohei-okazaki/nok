package jp.co.nok.common.log.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ログ出力時マスク処理対象項目につけるアノテーション<br>
 * 出力したいマスク文字列をvalueで指定する
 *
 * @version 1.0.0
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Mask {

    /**
     * マスク文字列
     *
     * @return value
     */
    String value() default "****";
}
