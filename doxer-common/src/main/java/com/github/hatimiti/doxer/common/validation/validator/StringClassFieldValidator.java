package com.github.hatimiti.doxer.common.validation.validator;

import java.lang.reflect.Field;

import org.seasar.util.lang.ModifierUtil;

import com.github.hatimiti.doxer.common.message.AppMessagesContainer;
import com.github.hatimiti.doxer.common.util._Obj;
import com.github.hatimiti.doxer.common.validation.Vval;

/**
 * クラス変数(静的変数)に含まれるかどうかのチェックを行うバリデータクラス．
 * public static final な変数をチェックするため、コード値チェックなどに利用する．
 * @author hatimiti
 * @see BaseFieldValidator
 */
public class StringClassFieldValidator extends BaseFieldValidator {

	public static final String VALIDATOR_KEY = "valid.invalid";

	protected Class<?> clazz;

	public StringClassFieldValidator(AppMessagesContainer container,  Class<?> clazz) {
		super(container);
		this.clazz = clazz;
	}

	@Override
	protected boolean checkSpecifically(Vval value) {
		return checkClassField(value.getValues()[0], this.clazz);
	}

	/**
	 * 引数として渡された値がクラス変数(静的変数)に含まれるかをチェックする．
	 * @param value チェック対象文字列(クラス変数名)
	 * @param clazz チェック対象クラス
	 * @return
	 * 		フラグ値であれば true<br>
	 * 		フラグ値でない文字列の場合は false を返す．
	 */
	public static boolean checkClassField(
			final String value,
			final Class<?> clazz) {

		if (_Obj.isEmpty(value)) {
			return true;
		}

		Field[] fields = clazz.getDeclaredFields();

		for (Field field : fields) {
			if (ModifierUtil.isPublicStaticFinalField(field)
					&& field.getType().equals(String.class)) {

				try {
					String fieldValue = (String) field.get(clazz);
					if (fieldValue.equals(value)) {
						return true;
					}
				} catch (Exception e) {
					throw new RuntimeException(e);
				}

			}
		}

		return false;
	}

	@Override
	protected String getDefaultMessageKey() {
		return VALIDATOR_KEY;
	}

}
