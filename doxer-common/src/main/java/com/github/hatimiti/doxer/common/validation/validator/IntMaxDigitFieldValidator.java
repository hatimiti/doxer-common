package com.github.hatimiti.doxer.common.validation.validator;

import com.github.hatimiti.doxer.common.message.AppMessagesContainer;
import com.github.hatimiti.doxer.common.util._Str;
import com.github.hatimiti.doxer.common.validation.Vval;

/**
 * 整数最大桁チェックバリデータ．<br />
 * 指定された文字列が整数かどうかも合わせてチェックする．
 * 「-123」などは3桁と判断してチェックする(マイナス記号は桁数に含めない)．
 * @author hatimiti
 * @see BaseFieldValidator
 */
public class IntMaxDigitFieldValidator extends BaseFieldValidator {

	/**
	 * 整数 & 最大桁チェック(IntDigitFieldValidator.java)のデフォルトメッセージキー
	 */
	public static final String VALIDATOR_KEY = "valid.int.max.digit";

	/** 桁数 */
	public int max;

	public IntMaxDigitFieldValidator(AppMessagesContainer container) {
		super(container);
	}

	@Override
	protected boolean checkSpecifically(Vval value) {
		return checkIntMaxDigit(value.getValues()[0], this.max);
	}

	public static boolean checkIntMaxDigit(String value, int max) {

		String _value = value;

		// 整数かどうかチェック
		if (!IntFieldValidator.checkInt(_value)) {
			return false;
		}
		// 半角数字列に変換
		_value = _Str.convertToHalfSizeNumString(_value);

		// マイナス記号があるかどうか
		int minus = 0;
		if (_value.charAt(0) == '-') {
			minus++;
		}

		// 桁チェック
		return (_value.length() - minus) <= max;
	}

	public IntMaxDigitFieldValidator setMax(int max) {
		this.max = max;
		return this;
	}

	@Override
	protected String getDefaultMessageKey() {
		return VALIDATOR_KEY;
	}

}
