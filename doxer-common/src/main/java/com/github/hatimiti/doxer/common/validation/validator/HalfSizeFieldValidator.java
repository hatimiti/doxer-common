package com.github.hatimiti.doxer.common.validation.validator;

import java.util.regex.Pattern;

import com.github.hatimiti.doxer.common.message.AppMessagesContainer;
import com.github.hatimiti.doxer.common.util._Obj;
import com.github.hatimiti.doxer.common.validation.Vval;

/**
 * 半角文字チェックを行うバリデータクラス．
 * @author hatimiti
 * @see BaseFieldValidator
 */
public class HalfSizeFieldValidator extends BaseFieldValidator {

	public static final String VALIDATOR_KEY = "valid.half.size";

	public HalfSizeFieldValidator(AppMessagesContainer container) {
		super(container);
	}

	@Override
	protected boolean checkSpecifically(Vval value) {
		return checkHalfSize(value.getValues()[0]);
	}

	public static boolean checkHalfSize(String value) {
		if (_Obj.isEmpty(value)) {
			return true;
		}
		Pattern pattern = Pattern.compile("^[ -~｡-ﾟ]*$");
		return pattern.matcher(value).matches();
	}

	@Override
	protected String getDefaultMessageKey() {
		return VALIDATOR_KEY;
	}

}
