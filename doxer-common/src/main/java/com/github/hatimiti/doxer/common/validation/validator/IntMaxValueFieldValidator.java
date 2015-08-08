package com.github.hatimiti.doxer.common.validation.validator;

import com.github.hatimiti.doxer.common.message.AppMessagesContainer;
import com.github.hatimiti.doxer.common.validation.Vval;

/**
 * 整数の最大値チェックバリデータ．
 * 最大値以下であるかどうか．
 * 指定された文字列が整数かどうかも合わせてチェックする．
 * @author hatimiti
 * @see BaseFieldValidator
 */
public class IntMaxValueFieldValidator extends BaseFieldValidator {

	public static final String VALIDATOR_KEY = "valid.int.max.value";

	/** 最大数 */
	protected int max;

	public IntMaxValueFieldValidator(AppMessagesContainer container) {
		super(container);
	}

	@Override
	protected boolean checkSpecifically(Vval value) {
		return checkIntMaxValue(value.getValues()[0], this.max);
	}

	public static boolean checkIntMaxValue(String value, int max) {

		// 整数かどうかチェック
		if (!IntFieldValidator.checkInt(value)) {
			return false;
		}

		int intVal = Integer.parseInt(value);

		return intVal <= max;
	}

	@Override
	protected String getDefaultMessageKey() {
		return VALIDATOR_KEY;
	}

	public IntMaxValueFieldValidator setMax(int max) {
		this.max = max;
		return this;
	}

}
