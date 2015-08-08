package com.github.hatimiti.doxer.common.validation.validator;

import java.util.regex.Pattern;

import com.github.hatimiti.doxer.common.message.AppMessagesContainer;
import com.github.hatimiti.doxer.common.util._Obj;
import com.github.hatimiti.doxer.common.validation.Vval;

/**
 * 全角文字チェックバリデータ．
 * @author hatimiti
 * @see BaseFieldValidator
 */
public class FullSizeFieldValidator extends BaseFieldValidator {

	/**
	 * 全角文字チェックバリデータのキー文字列定数．
	 */
	public static final String VALIDATOR_KEY = "valid.full.size";

	public FullSizeFieldValidator(AppMessagesContainer container) {
		super(container);
	}

	@Override
	protected boolean checkSpecifically(Vval value) {
		return checkFullSize(value.getValues()[0]);
	}

	/**
	 * 全角文字チェック．<br>
	 * 指定された文字列が全角文字列かどうかをチェックする．
	 * @param value チェック対象文字列
	 * @return 空文字、または全角文字列の場合は true,
	 * 	そうでない場合は false を返す．
	 */
	public static boolean checkFullSize(String value) {
		if (_Obj.isEmpty(value)) {
			return true;
		}
		Pattern pattern = Pattern.compile("^[^ -~｡-ﾟ]*$");
		return pattern.matcher(value).matches();
	}

	@Override
	protected String getDefaultMessageKey() {
		return VALIDATOR_KEY;
	}

}
