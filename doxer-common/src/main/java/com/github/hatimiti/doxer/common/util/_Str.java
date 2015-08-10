package com.github.hatimiti.doxer.common.util;

import static com.github.hatimiti.doxer.common.util.CharacterEncoding.*;
import static com.github.hatimiti.doxer.common.util._Obj.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Pattern;

import org.seasar.util.lang.StringUtil;

/**
 * 文字列に対する操作を行うユーティリティクラス．
 * @author hatimiti
 */
public final class _Str {

	/**
	 * 空文字を表す定数(="")
	 */
	public static final String EMPTY = "";

	/**
	 * ゼロ(0)を表す定数(="0")
	 */
	public static final String ZERO = "0";

	/**
	 * HTTPエスケープ文字(&)を表す文字(="&amp;")
	 */
	public static final String HTTP_AMP = "&amp;";

	/**
	 * 改行文字
	 */
	public static final String LINE_SEPARATOR = System.lineSeparator();

	/**
	 * private コンストラクタ
	 */
	private _Str() { }

	/**
	 * 文字列配列に指定された値が含まれているかを判定する．
	 * @param value 指定値
	 * @param ary 文字列配列
	 * @return ary の中に value が含まれている場合は true,
	 * 含まれていない場合は false を返す．
	 */
	public static boolean contains(String value, String... ary) {

		if (ary == null || value == null) {
			throw new IllegalArgumentException(
				"You must appoint NOT NULL arguments.");
		}

		for (String str : ary) {
			if (str.equals(value)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 文字列を含んでいるかどうか返します。
	 * @param value 文字列
	 * @param searchString 比較する対象となる文字列
	 * @return 文字列を含んでいるかどうか
	 */
	public static boolean contains(String value, String searchString) {
		Objects.requireNonNull(value);
		Objects.requireNonNull(searchString);
		return StringUtil.contains(value, searchString);
	}

	/**
	 * スタックトレースを文字列として取得します。
	 * @param e 例外およびエラー
	 * @return スタックトレース文字列
	 */
	public static String getStackTraceString(Throwable e) {
		if (e == null) {
			return null;
		}
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		pw.flush();
		String ret = sw.getBuffer().toString();
		pw.close();
		return ret;
	}

	/**
	 * 整数文字列かどうかを判断する．
	 * 整数文字列の場合は true を返す．
	 */
	public static boolean isFullSizeNumeric(String value) {
		Pattern pattern =
			Pattern.compile("^[-]?[1-9|１－９]?[0-9|０-９]+$");
		return pattern.matcher(value).matches();
	}

	/**
	 * 全角整数文字列を半角整数文字列へ変換する．
	 * 与えられた文字列が全角整数文字列でない場合は、
	 * 元の文字列を返す．
	 * @param value 変換対象文字列
	 * @return 半角数字文字列
	 */
	public static String convertToHalfSizeNumString(String value) {

		if (!isFullSizeNumeric(value)) {
			return value;
		}

		StringBuilder result = new StringBuilder();

		int count = 0;

		char ch = value.charAt(0);
		if (ch == '-') {
			result.append(ch);
			count++;
		}

		for (int i = count; i < value.length(); i++) {
			ch = value.charAt(i);

			if ('０' <= ch && ch <= '９') {
				ch = (char) (ch - '０' + '0');
			}

			result.append(ch);
		}

		return result.toString();
	}

	/**
	 * 指定した、0 からの整数の連番である文字列配列を、
	 * 指定したサイズの長さまで拡張した真偽値配列で返す．<br>
	 * 例：配列 = {0, 1, 4, 7, 9}, サイズ = 10<br>
	 * 　　結果 &gt; t, t, f, f, t, f, f, t, f, t<br>
	 * 使用例としては、Webアプリケーションにおいて、
	 * 複数のチェックボックスからサブミットされた値の配列を、
	 * 指定されたサイズに拡張するのに使用する．<br>
	 * &lt;input type="checkbox"&gt; の value 属性には 0 からの連番を
	 * 指定しておく必要がある。<br>
	 * value に数値以外の値が保持されている場合は、
	 * そのインデックスの値は false となる。
	 * @param values
	 *  0 からの連番(抜け番有)である文字列配列<br>
	 * @param size 拡張するサイズ
	 * @return
	 *  指定したサイズに拡張された真偽値配列を返す。<br>
	 * 値の存在するインデックスには true ,
	 * 値の存在しないインデックスには false を格納している。<br>
	 * values が null の場合は、指定されたサイズの false 配列を返す。
	 */
	public static boolean[] getExtendBoolArray(
			String[] values, int size) {

		final boolean[] results = new boolean[size];

		if (values == null) {
			for (int i = 0; i < size; i++) {
				results[i] = false;
			}

			return results;
		}

		int idx = 0;

		for (int i = 0; i < size; i++) {
			try {
				if (values.length <= idx) {
					results[i] = false;
				} else {
					int value = Integer.parseInt(values[idx]);
					if (value == i) {
						results[i] = true;
						idx++;
					} else {
						results[i] = false;
					}
				}
			} catch (NumberFormatException e) {
				results[i] = false;
			}
		}

		return results;
	}

	/**
	 * 与えられた文字列の先頭を大文字に変換する．<br>
	 * @param value 変換対象文字列
	 * @return 先頭が大文字に変換された文字列
	 */
	public static String toUpperCaseFirstChar(final String value) {
		if (isEmpty(value)) {
			return value;
		}

		StringBuilder sb = new StringBuilder();
		sb.append(Character.toUpperCase(value.charAt(0)));

		for (int i = 1; i < value.length(); i++) {
			sb.append(value.charAt(i));
		}

		return sb.toString();
	}

	/**
	 * 与えられた文字列の先頭を小文字に変換する．<br>
	 * @param value 変換対象文字列
	 * @return 先頭が小文字に変換された文字列
	 */
	public static String toLowerCaseFirstChar(final String value) {
		if (isEmpty(value)) {
			return value;
		}

		StringBuilder sb = new StringBuilder();
		sb.append(Character.toLowerCase(value.charAt(0)));

		for (int i = 1; i < value.length(); i++) {
			sb.append(value.charAt(i));
		}

		return sb.toString();
	}

	/**
	 * 与えられたオブジェクトが空の場合は null に変換する．
	 */
	public static String asStrOrNull(final Object value) {
		return asStrOrDefault(value, null);
	}

	/**
	 * 与えられたオブジェクトが空の場合は 0 に変換する．
	 */
	public static String asStrOrZero(final Object value) {
		return asStrOrDefault(value, ZERO);
	}

	/**
	 * 与えられたオブジェクトが null かどうかチェックし、
	 *  null の場合は空文字に変換し返す．
	 * @param value 変換対象
	 * @return 引数 {@code value} が {@code null} の場合は空文字、そうでない場合は {@code Object#toString()} 値を返す．
	 */
	public static String asStrOrEmpty(Object value) {
		return asStrOrDefault(value, EMPTY);
	}

	/**
	 * 与えられた文字列が null、空文字、空オブジェクト(例えばList#isEmpty())かどうかチェックし、
	 *  null の場合はデフォルト文字列に変換し返す．
	 *  @param value チェック対象文字列
	 *  @param def デフォルト文字列
	 *  @param null の場合はデフォルト文字列、そうでない場合はそのまま値を返す．
	 */
	public static String asStrOrDefault(final Object value, final String def) {
		if (isEmpty(value)) {
			return def;
		}
		return value.toString();
	}

	/**
	 * SQLのLIKEを使用する際、入力値として
	 * 「%」「％」「_」「＿」が使用できるように
	 * 指定文字列でエスケープを行う．
	 * @param param エスケープさせる文字列
	 * @param escape エスケープ文字を指定
	 * @return エスケープした文字列<br>
	 * param が null の場合は null を返す
	 */
	public static String escapeForSqlLike(String value, char escape) {

		if (value == null) {
			return null;
		}

		String result = value;

		result = result.replace("%", escape + "%");
		result = result.replace("％", escape + "％");
		result = result.replace("_", escape + "_");
		result = result.replace("＿", escape + "＿");

		return result;
	}

	/**
	 * SQLのLIKEを使用する際、入力値として
	 * 「%」「％」「_」「＿」が使用できるように
	 * 「\」を付加させる．
	 * @param param エスケープさせる文字列
	 * @return エスケープした文字列<br>
	 * param が null の場合は null を返す
	 *
	 */
	public static String escapeForSqlLike(final String value) {

		if (value == null) {
			return null;
		}

		return escapeForSqlLike(value, '\\');
	}

	/**
	 * SQLのLIKEを使用する際、入力値として
	 * 「%」「％」「_」「＿」が使用できるように
	 * 「\」を付加させる．<br>
	 * 同時にUTF-8によるエンコードも行う．
	 * @param param エスケープさせる文字列
	 * @return エスケープした文字列<br>
	 * param が null の場合は null を返す
	 *
	 */
	public static String escapeForSqlLikeWithEncode(final String value) {

		if (value == null) {
			return null;
		}

		return escapeForSqlLike(encode(value), '\\');
	}

	/**
	 * 指定した文字列の左側スペースをトリミングする．
	 * 全角/半角スペースの両方をトリムする．
	 * @param value トリム対象文字列
	 * @return トリム後文字列
	 */
	public static String trimLeft(final String value) {
		if (isEmpty(value)) {
			return value;
		}
		return value.replaceAll("^[　| ]+", EMPTY);
	}

	/**
	 * 指定した文字列の右側スペースをトリミングする．
	 * 全角/半角スペースの両方をトリムする．
	 * @param value トリム対象文字列
	 * @return トリム後文字列
	 */
	public static String trimRight(final String value) {
		if (isEmpty(value)) {
			return value;
		}
		return value.replaceAll("[　| ]+$", EMPTY);
	}

	/**
	 * 指定した文字列の左右両側スペースをトリミングする．
	 * 全角/半角スペースの両方をトリムする．
	 * @param value トリム対象文字列
	 * @return トリム後文字列
	 */
	public static String trimBoth(final String value) {
		return trimLeft(trimRight(value));
	}

	/**
	 * 指定した文字配列の左右両側スペースをトリミングする．
	 * 全角/半角スペースの両方をトリムする．
	 * @param values トリム対象文字配列
	 * @return トリム後文字配列
	 */
	public static String[] trimBoth(final String[] values) {
		return trimLeft(trimRight(values));
	}

	/**
	 * 指定した文字配列の左側スペースをトリミングする．
	 * 全角/半角スペースの両方をトリムする．
	 * @param value トリム対象文字配列
	 * @return トリム後文字列
	 */
	public static String[] trimLeft(final String[] values) {
		if (isEmpty(values)) {
			return values;
		}

		String[] result = new String[values.length];
		for (int i = 0; i < values.length; i++) {
			result[i] = values[i].replaceAll("^[　| ]+", EMPTY);
		}
		return result;
	}

	/**
	 * 指定した文字列の右側スペースをトリミングする．
	 * 全角/半角スペースの両方をトリムする．
	 * @param value トリム対象文字列
	 * @return トリム後文字列
	 */
	public static String[] trimRight(final String[] values) {
		if (isEmpty(values)) {
			return values;
		}

		String[] result = new String[values.length];

		for (int i = 0; i < values.length; i++) {
			result[i] = values[i].replaceAll("[　| ]+$", EMPTY);
		}

		return result;
	}

	/**
	 * 指定した文字列を UTF-8 で変換する．
	 * @param value 変換対象の文字列
	 * @return 変換された文字列
	 */
	public static String encode(String value) {

		if (isEmpty(value)) {
			return value;
		}

		try {
			return new String(value.getBytes("8859_1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return value;
		}
	}

	/**
	 * 与えられた文字列をUTF-8でURLエンコードを行う．
	 * @param value エンコード対象の文字列
	 * @return エンコード後の文字列
	 */
	public static String encodeUrl(final String value) {

		try {
			return URLEncoder.encode(value, UTF8.toString());
		} catch (UnsupportedEncodingException e) {
			return value;
		}
	}

	/**
	 * 与えられた文字列をUTF-8でURLデコードを行う．
	 * @param value デコード対象の文字列
	 * @return デコード後の文字列
	 */
	public static String decodeUrl(final String value) {
		try {
			return URLDecoder.decode(value, UTF8.toString());
		} catch (UnsupportedEncodingException e) {
			return value;
		}
	}

	/**
	 * 与えられた文字列の左側を 0 埋めして返す．<br>
	 * @param value 0 埋め対象文字列
	 * @param length 0 埋め後の桁数を指定する．
	 * @return 左側を 0 埋めされた文字列を返す．
	 */
	public static String paddingLeftByZero(
			final String value,
			final int length) {
		final StringBuilder sb = new StringBuilder(value);
		for (int i = value.length(); i < length; i++) {
			sb.insert(0, "0");
		}
		return sb.toString();
	}

	/**
	 * 与えられた文字列をカンマ(,)の3桁区切りにして返す．<br>
	 * 与えられた文字列が数値形式でなければそのまま返す．
	 * @param value 数値形式の文字列
	 * @return value をカンマで3桁区切りした文字列
	 */
	public static String partition3DigitByComma(
			final String value) {
		try {
			new Integer(value);
		} catch (NumberFormatException e) {
			return value;
		}
		return value.replaceAll("(?<=[0-9])(?=([0-9]{3})+$)", ",");
	}

	public static String partOfInteger(BigDecimal value) {
		if (value == null) {
			return "";
		}
		String strval = asStrOrEmpty(value);
		int idx = strval.indexOf('.');
		return idx < 0 ? strval : strval.substring(0, idx);
	}

	public static String partOfDecimal(BigDecimal value) {
		if (value == null) {
			return "";
		}
		String strval = asStrOrEmpty(value);
		int idx = strval.indexOf('.');
		return idx < 0 ? "0" : strval.substring(idx + 1, strval.length());
	}

	public static String createRandomHexString(int length) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			return EMPTY;
		}
		md.update(Long.valueOf(new Random(System.nanoTime()).nextLong()).toString().getBytes());
		return toHex(md.digest()).substring(0, length);
	}

	/**
	 * Convert a byte array to a String of hexadecimal digits and return it.
	 * @param buffer The byte array to be converted
	 */
	private static String toHex(byte buffer[]) {
		StringBuffer sb = new StringBuffer(buffer.length * 2);
		for (int i = 0; i < buffer.length; i++) {
			sb.append(Character.forDigit((buffer[i] & 0xf0) >> 4, 16));
			sb.append(Character.forDigit(buffer[i] & 0x0f, 16));
		}
		return sb.toString();
	}

}
