package com.github.hatimiti.doxer.common.validation.validator;

import java.util.regex.Pattern;

import com.github.hatimiti.doxer.common.message.AppMessagesContainer;
import com.github.hatimiti.doxer.common.validation.Vval;

/**
 * URL形式チェックを行うバリデータクラス．
 * @author hatimiti
 * @see BaseFieldValidator
 */
public class URLFieldValidator extends BaseFieldValidator {

	/**
	 * URL形式チェックバリデータのキー文字列定数．
	 */
	public static final String VALIDATOR_KEY = "valid.url";

	public URLFieldValidator(AppMessagesContainer container) {
		super(container);
	}

	@Override
	protected boolean checkSpecifically(Vval value) {
		return checkURL(value.getValues()[0]);
	}

	/**
	 * URL形式チェック．<br>
	 * 指定された文字列が URL 形式かどうかをチェックする．
	 * @param value チェック対象文字列
	 * @return URL形式であれば true そうでなければ false を返す．
	 */
	public static boolean checkURL(String value) {
		// URL の形式かを判断する．
		Pattern pattern =
			Pattern.compile(
					"s?https?://[-_.!~*'()a-zA-Z0-9;/?:@&=+$,%#]+");

		return pattern.matcher(value).matches();
	}

	@Override
	protected String getDefaultMessageKey() {
		return VALIDATOR_KEY;
	}

}
