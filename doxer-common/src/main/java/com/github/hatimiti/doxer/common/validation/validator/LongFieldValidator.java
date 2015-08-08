package com.github.hatimiti.doxer.common.validation.validator;

import com.github.hatimiti.doxer.common.message.AppMessagesContainer;
import com.github.hatimiti.doxer.common.util._Obj;
import com.github.hatimiti.doxer.common.validation.Vval;

/**
 * 整数チェック(Long値)バリデータ．
 * @author hatimiti
 * @see BaseFieldValidator
 */
public class LongFieldValidator extends BaseFieldValidator {

	public static final String VALIDATOR_KEY ="valid.long";

	public LongFieldValidator(AppMessagesContainer container) {
		super(container);
	}

	@Override
	protected boolean checkSpecifically(Vval value) {
		return checkLong(value.getValues()[0]);
	}

	/**
	 * 引数として渡された値が整数かどうかをチェックする．
	 * @param value チェック対象文字列
	 * @return
	 * 		整数に変換可能な文字列であれば true<br>
	 * 		整数に変換できない文字列の場合は false を返す．
	 */
	public static boolean checkLong(String value) {
		if (_Obj.isEmpty(value)) {
			return true;
		}
		try {
			new Long(value);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	protected String getDefaultMessageKey() {
		return VALIDATOR_KEY;
	}

}
