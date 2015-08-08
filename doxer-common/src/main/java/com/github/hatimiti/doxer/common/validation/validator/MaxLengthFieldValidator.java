package com.github.hatimiti.doxer.common.validation.validator;

import com.github.hatimiti.doxer.common.message.AppMessagesContainer;
import com.github.hatimiti.doxer.common.util._Obj;
import com.github.hatimiti.doxer.common.validation.Vval;

/**
 * 最大文字数チェックを行うバリデータクラス．
 * @author hatimiti
 * @see BaseFieldValidator
 */
public class MaxLengthFieldValidator extends BaseFieldValidator {

	public static final String VALIDATOR_KEY = "valid.max.length";

	/** 最大文字数 */
	protected int max;

	public MaxLengthFieldValidator(AppMessagesContainer container) {
		super(container);
	}

	@Override
	protected boolean checkSpecifically(Vval value) {
		return checkMaxLength(value.getValues()[0], this.max);
	}

	/**
	 * 最大文字数チェックを行う．
	 * @param value チェック対象文字列
	 * @param max 最大文字数
	 * @return value の length が max を超えている場合は false,
	 * 	空文字、または max 以下であれば true を返す．
	 */
	public static boolean checkMaxLength(
			final String value,
			final int max) {
		if (_Obj.isEmpty(value)) {
			return true;
		}
		return value.length() <= max;
	}

	/**
	 * 最大文字数を設定する．
	 * @param max 最大文字数
	 */
	public MaxLengthFieldValidator max(final int max) {
		this.max = max;
		return this;
	}

	@Override
	protected String getDefaultMessageKey() {
		return VALIDATOR_KEY;
	}

}
