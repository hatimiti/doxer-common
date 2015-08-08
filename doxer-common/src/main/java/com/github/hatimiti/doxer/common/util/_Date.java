package com.github.hatimiti.doxer.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

/**
 * 日付系ユーティリティクラス
 * @author hatimiti
 */
public final class _Date {

	/**
	 * private コンストラクタ
	 */
	private _Date() { }

	/**
	 * 与えられた yyyyMmDd の文字列形式に、
	 * 区切り文字列を付加する．
	 * @param yyyyMmDd 日付文字列
	 * @param delimiter 区切り文字列
	 * @return yyyyMmDd に delimiter を付加した文字列．
	 */
	public static String addDelimiter(
			final String yyyyMmDd,
			final String delimiter) {

		if (_Obj.isEmpty(yyyyMmDd)
				|| yyyyMmDd.length() != 8) {
			return yyyyMmDd;
		}

		String _delimiter = delimiter;

		if (_Obj.isEmpty(delimiter)) {
			_delimiter = "";
		}

		return getYyyy(yyyyMmDd)
			+ _delimiter
			+ getMm(yyyyMmDd)
			+ _delimiter
			+ getDd(yyyyMmDd);
	}

	/**
	 * 与えられた日付文字列から指定した区切り文字列を削除する．
	 * @param yyyyMmDd 日付文字列
	 * @param delimiter 区切り文字列
	 * @return yyyyMmDd から delimiter を削除した文字列．
	 */
	public static String removeDelimiter(
			final String yyyyMmDd,
			final String delimiter) {

		if (_Obj.isEmpty(yyyyMmDd) || _Obj.isEmpty(delimiter)) {
			return "";
		}

		return yyyyMmDd.replaceAll(delimiter, "");
	}

	/**
	 * 日付オブジェクトを、指定したフォーマット文字列形式に変換する．
	 * 日付オブジェクトが null の場合は空文字を return する．
	 * @param date 日付オブジェクト
	 * @param format 変換フォーマット
	 * @return 形式変換した日付文字列
	 */
	public static String format(
			final Date date,
			final String format) {

		if (date == null) {
			return "";
		}
		SimpleDateFormat fmt
    		= new SimpleDateFormat(format);
		fmt.setLenient(false);
		return fmt.format(date);
	}

	/**
	 * 日付オブジェクトを、yyyyMMddHHmmss 形式に変換する．
	 * 日付オブジェクトが null の場合は空文字を return する．
	 * @param date 日付オブジェクト
	 * @return 形式変換した日付文字列
	 */
	public static String formatYyyyMmDdHhMiSs(
			final Date date) {
		if (date == null) {
			return "";
		}
		return format(date, "yyyyMMddHHmmss");
	}

	/**
	 * 日付オブジェクトを、yyyymmdd 形式に変換する．
	 * 日付オブジェクトが null の場合は空文字を return する．
	 * @param date 日付オブジェクト
	 * @return 形式変換した日付文字列
	 */
	public static String formatYyyyMmDd(
			final Date date) {
		if (date == null) {
			return "";
		}
		return formatYyyyMmDd(date, "");
	}

	/**
	 * 日付オブジェクトを、yyyymm 形式に変換する．
	 * 日付オブジェクトが null の場合は空文字を return する．
	 * @param date 日付オブジェクト
	 * @return 形式変換した日付文字列
	 */
	public static String formatYyyyMm(
			final Date date) {
		if (date == null) {
			return "";
		}
		return formatYyyyMm(date, "");
	}

	/**
	 * 日付オブジェクトを、指定した区切り文字列で区切った yyyyMMdd 形式に変換する．
	 * 日付オブジェクトが null の場合は空文字を return する．
	 * @param date 日付オブジェクト
	 * @param delimiter 区切り文字列「/」「-」など
	 * @return 形式変換した日付文字列
	 */
	public static String formatYyyyMmDd(
			final Date date,
			final String delimiter) {

		String _delimiter = delimiter;

		if (date == null) {
			return "";
		}
		if (_delimiter == null) {
			_delimiter = "/";
		}

		StringBuilder sb = new StringBuilder();
		sb.append("yyyy");
		sb.append(_delimiter);
		sb.append("MM");
		sb.append(_delimiter);
		sb.append("dd");

		return format(date, sb.toString());
	}

	/**
	 * 日付オブジェクトを、指定した区切り文字列で区切った yyyyMM 形式に変換する．
	 * 日付オブジェクトが null の場合は空文字を return する．
	 * @param date 日付オブジェクト
	 * @param delimiter 区切り文字列「/」「-」など
	 * @return 形式変換した日付文字列(年月)
	 */
	public static String formatYyyyMm(
			final Date date,
			final String delimiter) {

		String _delimiter = delimiter;

		if (date == null) {
			return "";
		}
		if (_delimiter == null) {
			_delimiter = "/";
		}

		StringBuilder sb = new StringBuilder();
		sb.append("yyyy");
		sb.append(_delimiter);
		sb.append("MM");

		return format(date, sb.toString());
	}

	/**
	 * 指定した年月日の Date オブジェクトを取得する．<br>
	 * 月の指定は 0 始まりではなく、1月 = 1 とする．
	 * @param year 年
	 * @param month 月
	 * @param day 日
	 * @throws IllegalArgumentException 不正な引数例外
	 * @return 指定された時間の Date オブジェクト
	 */
	public static Date getDate(
			final int year,
			final int month,
			final int day) {

		return getDate(year, month, day, 0, 0, 0);
	}

	/**
	 * 指定した年月日/時/分の Date オブジェクトを取得する．<br>
	 * 月の指定は 0 始まりではなく、1月 = 1 とする．
	 * @param year 年
	 * @param month 月
	 * @param day 日
	 * @param hour 時
	 * @throws IllegalArgumentException 不正な引数例外
	 * @return 指定された時間の Date オブジェクト
	 */
	public static Date getDate(
			final int year,
			final int month,
			final int day,
			final int hour) {

		return getDate(
				year, month, day, hour, 0, 0);
	}

	/**
	 * 指定した年月日/時/分の Date オブジェクトを取得する．<br>
	 * 月の指定は 0 始まりではなく、1月 = 1 とする．
	 * @param year 年
	 * @param month 月
	 * @param day 日
	 * @param hour 時
	 * @param minute 分
	 * @throws IllegalArgumentException 不正な引数例外
	 * @return 指定された時間の Date オブジェクト
	 */
	public static Date getDate(
			final int year,
			final int month,
			final int day,
			final int hour,
			final int minute) {

		return getDate(
				year, month, day, hour, minute, 0);
	}

	/**
	 * 指定した年月日/時/分/秒 の Date オブジェクトを取得する．<br>
	 * 月の指定は 0 始まりではなく、1月 = 1 とする．
	 * @param year 年
	 * @param month 月
	 * @param day 日
	 * @param hour 時
	 * @param minute 分
	 * @param second 秒
	 * @throws IllegalArgumentException 不正な引数例外
	 * @return 指定された時間の Date オブジェクト
	 */
	public static Date getDate(
			final int year,
			final int month,
			final int day,
			final int hour,
			final int minute,
			final int second) {

		Calendar cal = new GregorianCalendar(
				year, month - 1, day, hour, minute, second);
		cal.setLenient(false);

		return cal.getTime();
	}

	/**
	 * yyyyMMdd または yyyyMd の形式で渡された文字列から
	 * Date オブジェクトを返す．<br>
	 * 変換できない日付の場合は null を返す．
	 * @param yyyyMmDd 日付形式文字列
	 * @return 日付オブジェクト
	 */
	public static Date getDate(
			final String yyyyMmDd) {

		return getDate(yyyyMmDd, "");
	}

	/**
	 * yyyyMd の形式で渡された文字列から
	 * 対象月の1日の Date オブジェクトを返す．<br>
	 * 変換できない日付の場合は null を返す．
	 * @param yyyyMm 日付形式文字列
	 * @return 日付オブジェクト
	 */
	public static Date getFirstDateFromYyyyMm(
			final String yyyyMm) {

		return getDate(yyyyMm + "01", "");
	}

	/**
	 * yyyyMd の形式で渡された文字列から
	 * 対象月の末日の Date オブジェクトを返す．<br>
	 * 変換できない日付の場合は null を返す．
	 * @param yyyyMm 日付形式文字列
	 * @return 日付オブジェクト
	 */
	public static Date getLastDateFromYyyyMm(
			final String yyyyMm) {

		return getDate(yyyyMm + getLastDayFromYyyyMm(yyyyMm), "");
	}

	/**
	 * yyyyMd の形式で渡された文字列から
	 * 対象月の末日文字列(dd)を返す．<br>
	 * 変換できない日付の場合は空文字を返す．
	 * @param yyyyMm 日付形式文字列
	 * @return 対象月末日の2桁0埋めの dd 文字列
	 */
	public static String getLastDayFromYyyyMm(
			final String yyyyMm) {

		Date firstDate = getFirstDateFromYyyyMm(yyyyMm);
		if (firstDate == null) {
			return "";
		}
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.setTime(firstDate);
		return String.format("%02d", cal.getActualMaximum(Calendar.DATE));
	}

	/**
	 * yyyyMMddHHmmssの形式で渡された文字列から
	 * Date オブジェクトを返す．<br>
	 * 変換できない日付の場合は null を返す．
	 *  @param yyyyMmDdhhmmss 日付形式文字列
	 *  @return 日付・時間オブジェクト
	 */
	public static Date getDateFromYyyyMmDdHhMiSs(
			final String yyyyMmDdHhMiSs) {

		if (!isYyyyMmDdHhMiSsTime(yyyyMmDdHhMiSs)) {
			return null;
		}

		SimpleDateFormat fmt
			= new SimpleDateFormat("yyyyMMddHHmmss");

		try {
			return fmt.parse(yyyyMmDdHhMiSs);
		} catch (Throwable e) {
			return null;
		}
	}

	/**
	 * yyyyMMdd または yyyyMd の形式で渡された文字列から
	 * Date オブジェクトを返す．<br>
	 * 変換できない日付の場合は null を返す．
	 * @param yyyyMmDd 日付形式文字列
	 * @param delimiter 区切り文字列
	 * @return 日付オブジェクト
	 */
	public static Date getDate(
			final String yyyyMmDd,
			final String delimiter) {

		if (!isYyyyMmDdDate(yyyyMmDd, delimiter)) {
			return null;
		}

		SimpleDateFormat fmt = new SimpleDateFormat(
				"yyyy" + delimiter + "MM" + delimiter + "dd");

		try {
			return fmt.parse(yyyyMmDd);
		} catch (Throwable e) {
			return null;
		}
	}

	/**
	 * 指定された4桁の日時文字列を、区切り文字で区切った文字列を返す．
	 * 例：hhMm = 1230, delimiter = :
	 * →12:30
	 * @param hhMm 4桁の日時文字列
	 * @param delimiter 区切り文字
	 * @return hh + delimiter + mm
	 */
	public static String formatHhMm(final String hhMm, final String delimiter) {
		return formatHhMm(hhMm, delimiter, "");
	}

	/**
	 * 指定された4桁の日時文字列に、時間、分それぞれの単位を付加して返す．
	 * 例：hhMm = 1230, hhUnit = 時, mmUnit = 分:
	 * →12時30分
	 * @param hhMm 4桁の日時文字列
	 * @param hhUnit 時間単位
	 * @param mmUnit 分単位
	 * @return hh + hhUnit + mm + mmUnit
	 */
	public static String formatHhMm(final String hhMm, final String hhUnit, final String mmUnit) {
		if (_Obj.isEmpty(hhMm) || hhMm.length() != 4) {
			return "";
		}
		return hhMm.substring(0, 2) + hhUnit + hhMm.substring(2, 4) + mmUnit;
	}

	/**
	 * 指定された文字列が日付形式かどうかをチェックする．
	 * 無効な日付が指定された場合は false を返す．
	 * @param value
	 * @return
	 */
	public static boolean isYyyyMmDdDate(
			final String value) {
		return isYyyyMmDdDate(value, "");
	}

	/**
	 * 指定された文字列が日付形式かどうかをチェックする．
	 * 無効な日付が指定された場合は false を返す．
	 * @param value
	 * @param delimiter 区切り文字列
	 * @return
	 */
	public static boolean isYyyyMmDdDate(
			final String value,
			final String delimiter) {

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
			= new SimpleDateFormat("yyyy" + delimiter + "MM" + delimiter + "dd");
		// 2008/13/01 のような日付は ParseException とする．
		fmt.setLenient(false);

		try {
			fmt.parse(value);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 * 指定された文字列が日付形式かどうかをチェックする．
	 * 無効な日付が指定された場合は false を返す．
	 * @param value
	 * @return
	 */
	public static boolean isYyyyMmDdHhMiSsTime(
			final String value) {
		return isYyyyMmDdHhMiSsTime(value, "");
	}

	/**
	 * 指定された文字列が日付+時間形式かどうかをチェックする．
	 * 無効な日付が指定された場合は false を返す．
	 * @param value
	 * @param delimiter 区切り文字列
	 * @return
	 */
	public static boolean isYyyyMmDdHhMiSsTime(
			final String value,
			final String delimiter) {

		// yyyy/MM/dd/HH/Mi/Ss の形式を判断する。
		Pattern pattern =
			Pattern.compile(
					"^[0-9|０-９]{4}"
					+ delimiter
					+ "[0-9|０-９]{1,2}"
					+ delimiter
					+ "[0-9|０-９]{1,2}"
					+ delimiter
					+ "[0-9|０-９]{1,2}"
					+ delimiter
					+ "[0-9|０-９]{1,2}"
					+ delimiter
					+ "[0-9|０-９]{1,2}$");
		if (!pattern.matcher(value).matches()) {
			return false;
		}

		// 妥当な日付・時間をチェックする。
		SimpleDateFormat fmt = new SimpleDateFormat(
				"yyyy" + delimiter + "MM" + delimiter + "dd" + delimiter + "HH" + delimiter + "mm" + delimiter + "ss");
		fmt.setLenient(false);

		try {
			fmt.parse(value);
			return true;
		} catch (ParseException e) {
			return false;

		}

	}

	/**
	 * 指定された文字列が日付(時分秒含む)形式かどうかをチェックする．
	 * 年月日と時分秒の間には、半角スペースが必要である。
	 * 無効な日付が指定された場合は false を返す．
	 * @param value
	 * @param dateDelimiter 年月日 区切り文字列
	 * @param minDelimiter 時分秒 区切り文字列
	 * @param valOfBetweenYmdAndHms 年月日と時分秒の区切り文字
	 * @return
	 */
	public static boolean isYyyyMmDdHhMiSsDate(
			final String yyyyMmDdHhMiSs,
			final String dateDelimiter,
			final String minDelimiter,
			final String delimiterOfBetweenYmdAndHms) {

		// 妥当な日付かをチェックする．
		SimpleDateFormat fmt = new SimpleDateFormat(
				"yyyy" + dateDelimiter + "MM" + dateDelimiter + "dd"
				+ delimiterOfBetweenYmdAndHms
				+ "HH" + minDelimiter + "mm" + minDelimiter + "ss");
		// 2008/13/01 のような日付は ParseException とする．
		fmt.setLenient(false);

		try {
			fmt.parse(yyyyMmDdHhMiSs);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}


	/**
	 * yyyyMmDd の日付形式文字列の曜日を取得する．
	 * @param yyyyMmDd 日付形式文字列
	 * @return 指定日数の曜日(java.util.Calendar の定数)
	 */
	public static int getDayOfWeek(final String yyyyMmDd) {
		Calendar cal = getCalendar(yyyyMmDd);
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * yyyyMmDd の日付形式文字列の曜日と、指定した曜日が同一であるかチェックする．
	 * @param yyyyMmDd 日付形式文字列
	 * @param appointedDayOfWeek 指定曜日
	 * @return 日付の曜日と、指定した曜日が同一であれば真を返す．そうでなければ偽を返す．
	 */
	public static boolean isAppointedDayOfWeek(final String yyyyMmDd, final int appointedDayOfWeek) {
		int dayOfWeek = getDayOfWeek(yyyyMmDd);
		return dayOfWeek == appointedDayOfWeek;
	}

	/**
	 * yyyyMmDd の日付形式文字列に対して、日数を足し引きし、yyyyMmDd 形式で返す．
	 * @param yyyyMmDd 日付形式文字列
	 * @param amount 加減する日数
	 * @return 指定日数が加減された yyyyMmDd 日付形式文字列
	 */
	public static String addDayToYyyyMmDd(final String yyyyMmDd, final int amount) {
		return addDateToYyyyMmDd(Calendar.DATE, yyyyMmDd, amount);
	}

	/**
	 * yyyyMmDd の日付形式文字列に対して、月数を足し引きし、yyyyMmDd 形式で返す．
	 * @param yyyyMmDd 日付形式文字列
	 * @param amount 加減する月数
	 * @return 指定月数が加減された yyyyMmDd 日付形式文字列
	 */
	public static String addMonthToYyyyMmDd(final String yyyyMmDd, final int amount) {
		return addDateToYyyyMmDd(Calendar.MONTH, yyyyMmDd, amount);
	}

	/**
	 * yyyyMmDd の日付形式文字列に対して、年数を足し引きし、yyyyMmDd 形式で返す．
	 * @param yyyyMmDd 日付形式文字列
	 * @param amount 加減する年数
	 * @return 指定年数が加減された yyyyMmDd 日付形式文字列
	 */
	public static String addYearToYyyyMmDd(final String yyyyMmDd, final int amount) {
		return addDateToYyyyMmDd(Calendar.YEAR, yyyyMmDd, amount);
	}

	/**
	 * yyyyMmDd の文字列日付を基に、Calendar オブジェクトを取得する．
	 * @param yyyyMmDd 日付形式文字列
	 * @return カレンダオブジェクト
	 */
	public static Calendar getCalendar(final String yyyyMmDd) {
		if (!isYyyyMmDdDate(yyyyMmDd)) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(getDate(yyyyMmDd));
		return cal;
	}

	/**
	 * yyyyMm の文字列日付を基に、前月の yyyyMm 文字列日付を取得する．
	 * @return yyyyMm 前月の年月
	 */
	public static String getPrevYyyyMm(final String yyyyMm) {
		Date date = getFirstDateFromYyyyMm(yyyyMm);
		String prevYmd = addMonthToYyyyMmDd(formatYyyyMmDd(date), -1);
		return getYyyyMm(prevYmd);
	}

	/**
	 * yyyyMm の文字列日付を基に、次月の yyyyMm 文字列日付を取得する．
	 * @return yyyyMm 次月の年月
	 */
	public static String getNextYyyyMm(final String yyyyMm) {
		Date date = getFirstDateFromYyyyMm(yyyyMm);
		String prevYmd = addMonthToYyyyMmDd(formatYyyyMmDd(date), 1);
		return getYyyyMm(prevYmd);
	}

	/**
	 * yyyyMmDd の文字列日付を基に、yyyyMm の年月部分を取得する．
	 * @return yyyyMm 年月
	 */
	public static String getYyyyMm(final String yyyyMmDd) {
		return getYyyy(yyyyMmDd) + getMm(yyyyMmDd);
	}

	/**
	 * yyyyMmDd の文字列日付を基に、mmMm の月日部分を取得する．
	 * @return mmMm 月日
	 */
	public static String getMmDd(final String yyyyMmDd) {
		return getMm(yyyyMmDd) + getDd(yyyyMmDd);
	}

	/**
	 * yyyyMmDd の文字列日付を基に、yyyyMm の年数部分を取得する．
	 * @return 年数
	 */
	public static String getYyyy(final String yyyyMmDd) {
		if (!isYyyyMmDdDate(yyyyMmDd)) {
			return "";
		}
		return yyyyMmDd.substring(0, 4);
	}

	/**
	 * yyyyMmDd の文字列日付を基に、yyyyMm の月数部分を取得する．
	 * @return 月数
	 */
	public static String getMm(final String yyyyMmDd) {
		if (!isYyyyMmDdDate(yyyyMmDd)) {
			return "";
		}
		return yyyyMmDd.substring(4, 6);
	}

	/**
	 * yyyyMmDd の文字列日付を基に、yyyyMm の日数部分を取得する．
	 * @return 日数
	 */
	public static String getDd(final String yyyyMmDd) {
		if (!isYyyyMmDdDate(yyyyMmDd)) {
			return "";
		}
		return yyyyMmDd.substring(6, 8);
	}

	private static String addDateToYyyyMmDd(
			final int target, final String yyyyMmDd, final int amount) {
		Calendar cal = getCalendar(yyyyMmDd);
		if (cal == null) {
			return "";
		}
		cal.add(target, amount);
		return formatYyyyMmDd(cal.getTime());
	}

	/*
	 * テスト用 main メソッドs
	 */
	public static void main(final String[] args) throws Exception {
		System.out.println(formatHhMm("5533", ":"));
	}

}
