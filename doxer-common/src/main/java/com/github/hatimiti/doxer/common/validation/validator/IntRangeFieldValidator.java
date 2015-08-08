package com.github.hatimiti.doxer.common.validation.validator;

import com.github.hatimiti.doxer.common.message.AppMessagesContainer;
import com.github.hatimiti.doxer.common.util._Obj;
import com.github.hatimiti.doxer.common.validation.Vval;


public class IntRangeFieldValidator extends BaseFieldValidator {

	public static final String VALIDATOR_KEY = "valid.int.range";

	/** 最小数 */
	protected int min;
	/** 最大数 */
	protected int max;

	public IntRangeFieldValidator(AppMessagesContainer c) {
		super(c);
	}

	@Override
	protected boolean checkSpecifically(Vval value) {
		return checkIntRange(value.getValues()[0], this.min, this.max);
	}

	public static boolean checkIntRange(
			String value, int min, int max) {

		if (_Obj.isEmpty(value)) {
			return true;
		}
		if (!IntFieldValidator.checkInt(value)) {
			return false;
		}
		int intVal = Integer.parseInt(value);

		return min <= intVal && intVal <= max;
	}

	public IntRangeFieldValidator range(int min, int max) {
		min(min);
		max(max);
		return this;
	}

	public IntRangeFieldValidator min(int min) {
		this.min = min;
		return this;
	}

	public IntRangeFieldValidator max(int max) {
		this.max = max;
		return this;
	}

	@Override
	protected String getDefaultMessageKey() {
		return VALIDATOR_KEY;
	}

}
