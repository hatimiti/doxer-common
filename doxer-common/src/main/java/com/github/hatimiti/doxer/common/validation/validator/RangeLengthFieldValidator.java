package com.github.hatimiti.doxer.common.validation.validator;

import com.github.hatimiti.doxer.common.message.AppMessagesContainer;
import com.github.hatimiti.doxer.common.util._Obj;
import com.github.hatimiti.doxer.common.validation.Vval;

/**
 * 文字数(範囲)チェックを行うバリデータクラス．
 * @author hatimiti
 * @see BaseFieldValidator
 */
public class RangeLengthFieldValidator extends BaseFieldValidator {

	public static final String VALIDATOR_KEY = "valid.range.length";

	/** 最小文字数 */
	protected int min;
	/** 最大文字数 */
	protected int max;

	public RangeLengthFieldValidator(AppMessagesContainer container) {
		super(container);
	}

	@Override
	protected boolean checkSpecifically(Vval value) {
		return checkRangeLength(value.getValues()[0], this.min, this.max);
	}

	/**
	 * 文字数(範囲)チェックを行います．
	 * @param value チェック対象文字列
	 * @param min 最少文字数
	 * @param max 最大文字数
	 * @return {@code value.length()} が {@code min} 以上 {@code max} 以下である場合は
	 * {@code true} を返します．範囲外の場合は {@code false} を返します．
	 */
	public static boolean checkRangeLength(
			final String value,
			final int min,
			final int max) {
		if (_Obj.isEmpty(value)) {
			return true;
		}
		return min <= value.length() && value.length() <= max;
	}

	/**
	 * 文字数(範囲)を設定します．
	 * @param min 最少文字数
	 * @param max 最大文字数
	 */
	public RangeLengthFieldValidator range(int min, int max) {
		min(min);
		max(max);
		return this;
	}

	/**
	 * 最小文字数を設定します．
	 * @param min 最少文字数
	 */
	public RangeLengthFieldValidator min(final int min) {
		this.min = min;
		return this;
	}

	/**
	 * 最大文字数を設定します．
	 * @param max 最大文字数
	 */
	public RangeLengthFieldValidator max(final int max) {
		this.max = max;
		return this;
	}

	@Override
	protected String getDefaultMessageKey() {
		return VALIDATOR_KEY;
	}

}
