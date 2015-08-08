package com.github.hatimiti.doxer.common.validation.validator;

import com.github.hatimiti.doxer.common.message.AppMessagesContainer;
import com.github.hatimiti.doxer.common.validation.Vval;

/**
 * 最小文字数チェックバリデータ．
 * @author hatimiti
 * @see BaseFieldValidator
 */
public abstract class MinLengthFieldValidator extends BaseFieldValidator {

	public static final String VALIDATOR_KEY = "valid.min.length";

	/** 最大文字数 */
	protected int min;

	public MinLengthFieldValidator(AppMessagesContainer container) {
		super(container);
	}

	@Override
	protected boolean checkSpecifically(Vval value) {
		return checkMinLengthWithHalfSize(value.getValues()[0], this.min);
	}

	public static boolean checkMinLengthWithHalfSize(String value, int min) {
		return min <= value.length();
	}

	public MinLengthFieldValidator min(int min) {
		this.min = min;
		return this;
	}

}
