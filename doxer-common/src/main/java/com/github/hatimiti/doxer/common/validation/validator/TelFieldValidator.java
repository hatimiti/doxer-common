package com.github.hatimiti.doxer.common.validation.validator;

import java.util.regex.Pattern;

import com.github.hatimiti.doxer.common.message.AppMessagesContainer;
import com.github.hatimiti.doxer.common.util._Obj;
import com.github.hatimiti.doxer.common.validation.Vval;

/**
 * 電話番号形式かどうかのチェックを行うバリデータクラス．
 * @author hatimiti
 * @see BaseFieldValidator
 */
public class TelFieldValidator extends BaseFieldValidator {

	/**
	 * 電話番号形式チェックバリデータのキー文字列定数．
	 */
	public static final String VALIDATOR_KEY = "valid.malformed";

	public TelFieldValidator(AppMessagesContainer container) {
		super(container);
	}

	/**
	 * 区切り文字列「-」など
	 */
	protected String delimiter = "-";

	@Override
	protected boolean checkSpecifically(Vval value) {
		return checkTel(
				value.getValues()[0],
				value.getValues()[1],
				value.getValues()[2],
				this.delimiter);
	}

	/**
	 * 電話番号形式チェック<br>
	 * 指定された文字列が e-mail 形式かどうかをチェックする．
	 * @param tel1 電話番号1
	 * @param tel2 電話番号2
	 * @param tel3 電話番号3
	 * @return 空文字、または 電話番号形式である場合は true,
	 * 	そうでない場合は false を返す．
	 */
	public static boolean checkTel(String tel1, String tel2, String tel3, String delimiter) {
		if (_Obj.isEmpty(tel1) && _Obj.isEmpty(tel2) && _Obj.isEmpty(tel3)) {
			return true;
		}
		Pattern pattern = Pattern.compile(
				"^\\d{2,4}"
				+ delimiter
				+ "\\d{2,4}"
				+ delimiter
				+ "\\d{4}$");
		return pattern.matcher(String.join(delimiter, tel1, tel2, tel3)).matches();
	}

	@Override
	protected String getDefaultMessageKey() {
		return VALIDATOR_KEY;
	}

	public TelFieldValidator delimiter(String delimiter) {
		this.delimiter = delimiter;
		return this;
	}

}
