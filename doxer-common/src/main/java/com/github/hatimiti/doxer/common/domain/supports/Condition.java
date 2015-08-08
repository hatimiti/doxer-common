package com.github.hatimiti.doxer.common.domain.supports;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Condition {

	boolean escape() default true;

	boolean session() default false;

	/**
	 * セッション属性名と同一の値が
	 * リクエストパラメータとして送信されてきた場合に、
	 * リクエストパラメータを優先するかどうかのフラグ．
	 * session() が false の場合は無視される．
	 * @return リクエストを優先する場合は true, そうでなければ false を指定する．
	 */
	boolean prefRequest() default true;

}
