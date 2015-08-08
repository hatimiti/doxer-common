package com.github.hatimiti.doxer.common.validation.validator;

import java.util.regex.Pattern;

import com.github.hatimiti.doxer.common.message.AppMessagesContainer;
import com.github.hatimiti.doxer.common.util._Obj;
import com.github.hatimiti.doxer.common.validation.Vval;

/**
 * e-mail 形式チェックバリデータ．
 * @author hatimiti
 * @see BaseFieldValidator
 */
public class EMailFieldValidator extends BaseFieldValidator {

	/** e-mail 形式チェックバリデータのキー文字列定数． */
	public static final String VALIDATOR_KEY = "valid.email";

	public EMailFieldValidator(AppMessagesContainer container) {
		super(container);
	}

	@Override
	protected boolean checkSpecifically(Vval value) {
		return checkEMail(value.getValues()[0]);
	}

	/**
	 * e-mail 形式チェック<br>
	 * 指定された文字列が e-mail 形式かどうかをチェックする．
	 * @param value チェック対象文字列
	 * @return 空文字、または e-mail 形式である場合は true,
	 * 	そうでない場合は false を返す．
	 */
	public static boolean checkEMail(String value) {
		if (_Obj.isEmpty(value)) {
			return true;
		}
		Pattern pattern = Pattern.compile("^(?=.*@.+[.].+)[ -~｡-ﾟ]+$");
		return pattern.matcher(value).matches();
	}

	@Override
	protected String getDefaultMessageKey() {
		return VALIDATOR_KEY;
	}

}
