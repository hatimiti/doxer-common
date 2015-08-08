package com.github.hatimiti.doxer.common.validation.validator;

import com.github.hatimiti.doxer.common.message.AppMessagesContainer;
import com.github.hatimiti.doxer.common.validation.Vval;


public class IntValueRangeFieldValidator extends BaseFieldValidator {

	public static final String VALIDATOR_KEY = "valid.int.value.range";

	/** 最小数 */
	protected int min;
	/** 最大数 */
	protected int max;

	public IntValueRangeFieldValidator(AppMessagesContainer container) {
		super(container);
	}

	@Override
	protected boolean checkSpecifically(Vval value) {
		return checkIntValueRange(value.getValues()[0], this.min, this.max);
	}

	public static boolean checkIntValueRange(
			String value, int min, int max) {

		if (!IntMaxValueFieldValidator.checkIntMaxValue(value, max)) {
			return false;
		}

		int intVal = Integer.parseInt(value);

		return min <= intVal;
	}

	public IntValueRangeFieldValidator setMin(int min) {
		this.min = min;
		return this;
	}

	public IntValueRangeFieldValidator setMax(int max) {
		this.max = max;
		return this;
	}

	@Override
	protected String getDefaultMessageKey() {
		return VALIDATOR_KEY;
	}

}
