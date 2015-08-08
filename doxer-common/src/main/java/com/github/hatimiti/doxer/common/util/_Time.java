package com.github.hatimiti.doxer.common.util;

import java.sql.Time;
import java.util.Calendar;


/**
 * 時間系ユーティリティクラス
 * @author m-kakimi
 */
public final class _Time {

	/**
	 * private コンストラクタ
	 */
	private _Time() { }

	/**
	 * タイムゾーンの違いによるオフセット時間(ms)を取得する。
	 */
	public static int getTimezoneOffset() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.ZONE_OFFSET) + cal.get(Calendar.DST_OFFSET);
	}

	/**
	 *
	 * @param t1
	 * @param t2
	 * @return
	 */
	public static Time addTimeToTime(Time t1, Time t2) {
		if (_Obj.isEmpty(t1) || _Obj.isEmpty(t2)) {
			return null;
		}
		return new Time(t1.getTime() + t2.getTime() + getTimezoneOffset());
	}

	public static Time getFromHh(String hh) {
		try {
			return Time.valueOf(hh + ":00:00");
		} catch (Throwable t) {
			return null;
		}
	}

	public static Time getFromMm(String mm) {
		try {
			return Time.valueOf("00:" + mm + ":00");
		} catch (Throwable t) {
			return null;
		}
	}


	public static String formatHh(Time time) {
		return formatTime(time, Calendar.HOUR_OF_DAY);
	}

	public static String formatMm(Time time) {
		return formatTime(time, Calendar.MINUTE);
	}

	public static String formatSs(Time time) {
		return formatTime(time, Calendar.SECOND);
	}

	private static String formatTime(Time time, int field) {
		if (_Obj.isEmpty(time)) {
			return "";
		}

		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.setTime(time);
		int t = cal.get(field);
		return String.format("%02d", t);
	}

}
