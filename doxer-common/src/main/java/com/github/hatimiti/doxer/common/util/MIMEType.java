package com.github.hatimiti.doxer.common.util;

/**
 * ファイルのMIMEタイプ
 */
public enum MIMEType {

	/** テキストファイル (text/plain) */
	TEXT_PLAIN("text/plain"),

	/** CSVファイル(text/x-csv) */
	TEXT_X_CSV("text/x-csv"),

	/** CSVファイル(text/comma-separated-values) */
	TEXT_COMMA_SEP_VALS("text/comma-separated-values"),

	/** htmlファイル (text/html) */
	TEXT_HTML("text/html"),

	/** xml ファイル (text/xml) */
	TEXT_XML("text/xml"),

	/** JavaScript ファイル (text/javascript) */
	TEXT_JAVASCRIPT("text/javascript"),

	/** スタイルシート (text/css) */
	TEXT_CSS("text/css"),

	/** GIF 画像 (image/gif) */
	IMAGE_GIF("image/gif"),

	/** JPEG 画像 (image/jpeg) */
	IMAGE_JPEG("image/jpeg"),

	/** PNG 画像 (image/png) */
	IMAGE_PNG("image/png"),

	/** (application/octet-stream) */
	APPL_OCTET_STREAM("application/octet-stream"),

	/** Excel1(application/vnd.ms-excel) */
	APPL_VND_MS_EXCEL("application/vnd.ms-excel"),

	;

	/**
	 * このファイルのMIMEタイプを表した文字列
	 */
	private String value;

	/**
	 * コンストラクタ
	 * @param value ファイルのMIMEタイプを表した文字列
	 */
	private MIMEType(final String value) {
		this.value = value;
	}

	/**
	 * このファイルのMIMEタイプを表した文字列を取得する．
	 * @return ファイルのMIMEタイプ
	 */
	public String getValue() {
		return this.value;
	}

}
