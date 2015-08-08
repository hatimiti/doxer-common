package com.github.hatimiti.doxer.common.validation.validator;

import com.github.hatimiti.doxer.common.message.AppMessagesContainer;
import com.github.hatimiti.doxer.common.validation.Vval;

/**
 * 半角数字チェックバリデータ．
 * @author hatimiti
 * @see BaseFieldValidator
 *
 */
public class HalfSizeIntFieldValidator extends BaseFieldValidator {

	public static final String VALIDATOR_KEY = "valid.half.size.int";

	public HalfSizeIntFieldValidator(AppMessagesContainer c) {
		super(c);
	}

	@Override
	protected boolean checkSpecifically(Vval value) {
		return checkHalfSizeInt(value.getValues()[0]);
	}

	public static boolean checkHalfSizeInt(String value) {
		return HalfSizeFieldValidator.checkHalfSize(value)
				&& IntFieldValidator.checkInt(value);
	}

	@Override
	protected String getDefaultMessageKey() {
		return VALIDATOR_KEY;
	}

}
