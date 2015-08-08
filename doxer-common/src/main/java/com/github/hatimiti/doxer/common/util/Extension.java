package com.github.hatimiti.doxer.common.util;

/**
 * ファイルの拡張子
 * @author hatimiti
 */
public enum Extension {

	/** テキストファイル(.txt) */
	TXT(".txt"),

	/** html ファイル(.html) */
	HTML(".html"),

	/** htm ファイル(.htm) */
	HTM(".htm"),

	/** CSS ファイル(.css) */
	CSS(".css"),

	/** ZIP ファイル(.zip) */
	ZIP(".zip"),

	/** LZH ファイル(.lzh) */
	LZH(".lzh"),

	/** CAB ファイル(.cab) */
	CAB(".cab"),

	/** CSVファイル(.csv) */
	CSV(".csv"),

	/** Word ファイル */
	DOC(".doc"),

	/** Excel ファイル(.xls) */
	XLS(".xls"),

	/** Excel ファイル(.xlsx) */
	XLSX(".xlsx"),

	/** XML ファイル(.xml) */
	XML(".xml"),

	/** PDF ファイル(.pdf) */
	PDF(".pdf"),

	;

	/**
	 * このファイルの拡張子を表した文字列
	 */
	private String value;

	/**
	 * コンストラクタ
	 * @param value ファイルの拡張子を表した文字列
	 */
	private Extension(final String value) {
		this.value = value;
	}

	/**
	 * このファイルの拡張子を表した文字列を取得する．
	 * @return ファイルの拡張子
	 */
	public String getValue() {
		return this.value;
	}

}
