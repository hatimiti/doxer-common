package com.github.hatimiti.doxer.common.validation.validator;

import com.github.hatimiti.doxer.common.message.AppMessagesContainer;
import com.github.hatimiti.doxer.common.util._Obj;
import com.github.hatimiti.doxer.common.validation.Vval;

/**
 * 指定の文字が含まれているかどうかのチェックバリデータ．
 * @author hatimiti
 * @see BaseFieldValidator
 */
public class ContainsCharsFieldValidator extends BaseFieldValidator {

	public static final String VALIDATOR_KEY = "valid.contains.chars";

	/** 文字群 */
	protected char[] chars;
	/** 含有判断 */
	protected boolean checksOr;

	public ContainsCharsFieldValidator(AppMessagesContainer container) {
		super(container);
	}

	@Override
	protected boolean checkSpecifically(Vval value) {
		return checkContainsChars(value.getValues()[0], true, this.chars);
	}

	/**
	 * 指定の記号が含まれているかどうかのチェックを行います．
	 * @param value チェック対象文字列
	 * @param checksOr {@code true}を指定した場合、{@code signs}で指定した記号の内
	 * いずれかの記号が含まれていればOKと判断します．
	 * {@code false}を指定した場合は、{@code signs}で指定した記号が全て含まれている場合はOKと判断します．
	 * @param chars チェック対象記号
	 * @return {@code value} に {@code chars} が含まれている場合に {@code true} を返します．
	 * 含まれているという判断は {@code checksOr} の値により変わります．
	 */
	public static boolean checkContainsChars(
			final String value,
			final boolean checksOr,
			final char... chars) {

		if (_Obj.isEmpty(value) || (chars == null || chars.length == 0)) {
			return true;
		}

		if (checksOr) {
			return new String(chars).chars()
				.mapToObj(i -> (char) i) // Like a CharStream
				.filter(c -> value.contains(c.toString())).findAny().isPresent();
		}
		return !new String(chars).chars()
				.mapToObj(i -> (char) i) // Like a CharStream
				.filter(c -> !value.contains(c.toString())).findAny().isPresent();
	}

	/**
	 * 指定文字(複数可)を設定します．
	 */
	public ContainsCharsFieldValidator chars(char... chars) {
		this.chars = chars;
		return this;
	}

	/**
	 * 含有判断を設定します．
	 */
	public ContainsCharsFieldValidator checksOr(boolean checksOr) {
		this.checksOr = checksOr;
		return this;
	}

	@Override
	protected String getDefaultMessageKey() {
		return VALIDATOR_KEY;
	}

}
