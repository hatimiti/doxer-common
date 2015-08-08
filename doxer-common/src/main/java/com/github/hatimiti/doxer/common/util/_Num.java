package com.github.hatimiti.doxer.common.util;

/**
 * 数値に対する操作を行うユーティリティクラス．
 * @author hatimiti
 */
public final class _Num {

	/*
	 * private コンストラクタ
	 */
	private _Num() { }

	/**
	 * 文字列をLong値として取得します．<br />
	 * Long値へ変換できないような文字列が指定された場合はNull(null)を返します．
	 * @param val 変換対象の数字
	 * @return Long 数値
	 */
	public static Long asLongOrNull(String val) {
		return asLongOrDefault(val, null);
	}

	/**
	 * 文字列をLong値として取得します．<br />
	 * Long値へ変換できないような文字列が指定された場合はZero(0L)を返します．
	 * @param val 変換対象の数字
	 * @return Long 数値
	 */
	public static Long asLongOrZero(String val) {
		return asLongOrDefault(val, 0L);
	}

	/**
	 * 文字列を Long 値として取得します．<br />
	 * Long 値へ変換できないような文字列が指定された場合は指定されたデフォルト値を返します．
	 * @param val 変換対象の数字
	 * @param def 変換不可時のデフォルト値
	 * @return Long 数値
	 */
	public static Long asLongOrDefault(String val, Long def) {
		try {
			return Long.valueOf(val);
		} catch (Throwable t) {
			return def;
		}
	}

	/**
	 * 文字列を Integer 型として取得します．<br />
	 * Integer 値へ変換できないような文字列が指定された場合は Null を返します．
	 */
	public static Integer asIntOrNull(String val) {
		return asIntOrDefault(val, null);
	}

	/**
	 * 文字列を Integer 値として取得します．<br />
	 * Integer 値へ変換できないような文字列が指定された場合は Zero(0) を返します．
	 * @param val 変換対象の数字
	 * @return Integer 数値
	 */
	public static Integer asIntOrZero(String val) {
		return asIntOrDefault(val, 0);
	}

	/**
	 * 文字列を Integer 値として取得します．<br />
	 * Integer 値へ変換できないような文字列が指定された場合は指定されたデフォルト値を返します．
	 * @param val 変換対象の数字
	 * @param def 変換不可時のデフォルト値
	 * @return Integer 数値
	 */
	public static Integer asIntOrDefault(String val, Integer def) {
		try {
			return Integer.valueOf(val);
		} catch (Throwable t) {
			return def;
		}
	}

}
