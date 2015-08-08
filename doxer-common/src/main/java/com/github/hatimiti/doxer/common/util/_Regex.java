package com.github.hatimiti.doxer.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 正規表現ユーティリティクラス
 * @author hatimiti
 */
public final class _Regex {

	/**
	 * private コンストラクタ
	 */
	private _Regex() {}

	public static String[] ESCAPE_CHARS = {
		"\\", "*", "+", ".", "?", "{", "}", "(", ")", "[", "]", "^", "$", "-", "|", "/"
	};

	public static Pattern ESCAPE_PATTERN = Pattern.compile("(["
			+ Stream.of(ESCAPE_CHARS)
				.map(it -> "\\" + it)
				.collect(Collectors.joining())
		+ "])");


	public static String escape(String value) {
		return ESCAPE_PATTERN.matcher(value).replaceAll("\\\\$1");
	}

	public static String replaceAll(String target, String regex, String replacement) {
		if (target == null) {
			return null;
		}
		Matcher matcher = Pattern.compile(regex).matcher(target);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			// 一致したグループは matcher.group(n) で取得できる。
			// ここで replacement を加工する。
			matcher.appendReplacement(sb, replacement);
		}
		matcher.appendTail(sb);
		return sb.toString();
	}
}
