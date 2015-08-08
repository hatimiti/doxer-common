package com.github.hatimiti.doxer.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 機能を表すマーキングアノテーション．
 * @author hatimiti
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Function {
	
	/** 機能コード */
	String value();
	
}
