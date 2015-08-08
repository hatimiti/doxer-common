package com.github.hatimiti.doxer.common.domain.supports;

/**
 * 入力フィールドが、必須入力か、任意入力かを表す
 * @author m-kakimi
 */
public enum InputAttribute {
	/** 必須入力 */
	REQUIRED,
	/** 条件必須 */
	CONDITION,
	/** 任意入力 */
	ARBITRARY
}
