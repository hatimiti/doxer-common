package com.github.hatimiti.doxer.common.validation.validator;

import com.github.hatimiti.doxer.common.message.AppMessagesContainer;
import com.github.hatimiti.doxer.common.util._Str;
import com.github.hatimiti.doxer.common.validation.Vval;

/**
 * 整数桁の範囲チェックを行う．
 * 最小桁以上 かつ 最大桁以下であるかどうか．
 * 指定された文字列が整数かどうかも合わせてチェックする．
 * 「-123」などは3桁と判断してチェックする(マイナス記号は桁数に含めない)．
 * @author hatimiti
 * @see BaseFieldValidator
 */
public class IntDigitRangeFieldValidator extends BaseFieldValidator {

	public static final String VALIDATOR_KEY = "valid.int.digit.range";

	/** 最小桁 */
	protected int min;
	/** 最大桁 */
	protected int max;

	public IntDigitRangeFieldValidator(AppMessagesContainer container) {
		super(container);
	}

	@Override
	protected boolean checkSpecifically(Vval value) {
		return checkIntDigitRange(value.getValues()[0], this.min, this.max);
	}

	public static boolean checkIntDigitRange(
			String value, int min, int max) {

		if (!IntMaxDigitFieldValidator.checkIntMaxDigit(value, max)) {
			return false;
		}

		String _value = value;

		_value = _Str.convertToHalfSizeNumString(_value);

		// マイナス記号があるかどうか
		int minus = 0;
		if (_value.charAt(0) == '-') {
			minus++;
		}

		return min <= (_value.length() - minus);
	}

	@Override
	protected String getDefaultMessageKey() {
		return VALIDATOR_KEY;
	}

	public IntDigitRangeFieldValidator min(int min) {
		this.min = min;
		return this;
	}

	public IntDigitRangeFieldValidator max(int max) {
		this.max = max;
		return this;
	}

}
