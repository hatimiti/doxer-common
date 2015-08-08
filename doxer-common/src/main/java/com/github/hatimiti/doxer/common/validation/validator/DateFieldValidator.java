package com.github.hatimiti.doxer.common.validation.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import com.github.hatimiti.doxer.common.message.AppMessagesContainer;
import com.github.hatimiti.doxer.common.util._Obj;
import com.github.hatimiti.doxer.common.validation.Vval;

/**
 * 日付形式チェックバリデータ．
 * 例：2008/01/22, 2008/1/22
 * @author hatimiti
 * @see BaseFieldValidator
 */
public class DateFieldValidator extends BaseFieldValidator {

	public static final String VALIDATOR_KEY = "valid.date";

	/**
	 * 区切り文字列「/」「-」など
	 */
	protected String delimiter = "";

	public DateFieldValidator(AppMessagesContainer container) {
		super(container);
	}

	@Override
	protected boolean checkSpecifically(Vval value) {
		return checkDate(value.getValues()[0], this.delimiter);
	}

	/**
	 * 区切り文字列｢""｣に使用
	 * @return
	 */

	public static boolean checkDate(String value, String delimiter) {

		if (_Obj.isEmpty(value)) {
			return true;
		}

		// yyyy/MM/dd の形式かを判断する．
		Pattern pattern =
			Pattern.compile(
					"^[0-9|０-９]{4}"
					+ delimiter
					+ "[0-9|０-９]{1,2}"
					+ delimiter
					+ "[0-9|０-９]{1,2}$");

		if (!pattern.matcher(value).matches()) {
			return false;
		}

		// 妥当な日付かをチェックする．
		SimpleDateFormat fmt
			= new SimpleDateFormat(getFormat(delimiter));
		// 2008/13/01 のような日付は ParseException とする．
		fmt.setLenient(false);

		try {
			fmt.parse(value);
			return true;
		} catch (ParseException e) {
			return false;
		}

	}

	protected static String getFormat(final String dlmt) {
		return "yyyy" + dlmt + "MM" + dlmt + "dd";
	}

	@Override
	protected String getDefaultMessageKey() {
		return VALIDATOR_KEY;
	}

	public DateFieldValidator delimiter(String delimiter) {
		this.delimiter = delimiter;
		return this;
	}

	public String getDelimiter() {
		return this.delimiter;
	}

}
