package com.github.hatimiti.doxer.common.validation.validator;

import java.util.regex.Pattern;

import com.github.hatimiti.doxer.common.message.AppMessagesContainer;
import com.github.hatimiti.doxer.common.util._Obj;
import com.github.hatimiti.doxer.common.validation.Vval;

/**
 * 半角英数文字チェックバリデータ．
 * @author hatimiti
 * @see BaseFieldValidator
 */
public class HalfSizeAlphanumericValidator extends BaseFieldValidator {

	public static final String VALIDATOR_KEY = "valid.half.size.alpha";

	public HalfSizeAlphanumericValidator(AppMessagesContainer c) {
		super(c);
	}

	@Override
	protected boolean checkSpecifically(Vval value) {
		return checkHalfSizeAlphanumericValidator(value.getValues()[0]);
	}

	public static boolean checkHalfSizeAlphanumericValidator(String value) {
		if (_Obj.isEmpty(value)) {
			return true;
		}
		Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+$");
		return pattern.matcher(value).matches();
	}

	@Override
	protected String getDefaultMessageKey() {
		return VALIDATOR_KEY;
	}
}
