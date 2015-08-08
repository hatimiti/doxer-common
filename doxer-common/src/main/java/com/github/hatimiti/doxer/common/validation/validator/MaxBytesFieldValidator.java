package com.github.hatimiti.doxer.common.validation.validator;

import java.nio.charset.Charset;
import java.util.Objects;

import com.github.hatimiti.doxer.common.message.AppMessagesContainer;
import com.github.hatimiti.doxer.common.util._Obj;
import com.github.hatimiti.doxer.common.validation.Vval;

/**
 * 最大バイト数チェックバリデータ．
 * @author hatimiti
 * @see BaseFieldValidator
 */
public class MaxBytesFieldValidator extends BaseFieldValidator {

	/**
	 * 最大バイト数チェックバリデータのキー文字列定数．
	 */
	public static final String VALIDATOR_KEY = "valid.max.bytes";

	/**
	 * 最大バイト数
	 */
	protected long max;

	/**
	 * エンコーディング
	 */
	protected Charset charset = Charset.forName("UTF-8");

	public MaxBytesFieldValidator(AppMessagesContainer container) {
		super(container);
	}

	@Override
	protected boolean checkSpecifically(Vval value) {
		return checkMaxBytes(value.getValues()[0], this.max, this.charset);
	}

	/**
	 * 最大バイト数チェック．<br>
	 * 指定された文字列のバイト数が最大バイト数以下であるかチェックする．
	 * @param value チェック対象文字列
	 * @param max 最大バイト数
	 * @return 最大バイト数以下であれば true そうでなければ false を返す．
	 */
	public static boolean checkMaxBytes(String value, long max, Charset charset) {
		if (_Obj.isEmpty(value)) {
			return true;
		}
		Objects.requireNonNull(charset);
		byte[] bytes = value.getBytes(charset);
		return bytes.length <= max;
	}

	/**
	 * 最大バイト数を設定する．
	 * @param max 最大バイト数
	 */
	public MaxBytesFieldValidator max(long max) {
		this.max = max;
		return this;
	}

	/**
	 * エンコードを設定する．
	 * @param charset エンコード名
	 */
	public MaxBytesFieldValidator charset(String charset) {
		this.charset = Charset.forName(charset);
		return this;
	}

	/**
	 * エンコードを設定する．
	 * @param charset エンコード名
	 */
	public MaxBytesFieldValidator charset(Charset charset) {
		this.charset = charset;
		return this;
	}

	@Override
	protected String getDefaultMessageKey() {
		return VALIDATOR_KEY;
	}

}
