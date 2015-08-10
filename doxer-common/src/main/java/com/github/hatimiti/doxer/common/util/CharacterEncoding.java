package com.github.hatimiti.doxer.common.util;

/**
 * 文字エンコード列挙型
 * @author hatimiti
 */
public enum CharacterEncoding {

	/** UTF-8("UTF-8") */
	UTF8("UTF-8"),

	/** MS932("MS932") */
	MS932("MS932"),

	/** Shift_JIS("Shift_JIS") */
	SHIFT_JIS("Shift_JIS"),

	/** ISO-2022-JP("ISO-2022-JP") */
	ISO_2022_JP("ISO-2022-JP"),

	;

	/** このエンコードを表した文字列 */
	private String value;

	/**
	 * コンストラクタ
	 * @param value このエンコードを表した文字列
	 */
	private CharacterEncoding(final String value) {
		this.value = value;
	}

	/**
	 * このエンコードを表した文字列を取得する．
	 * @return このエンコードを表した文字列
	 */
	@Override
	public String toString() {
		return this.value;
	}

}
