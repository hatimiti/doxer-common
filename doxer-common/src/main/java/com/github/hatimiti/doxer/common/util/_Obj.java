package com.github.hatimiti.doxer.common.util;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.seasar.util.beans.util.BeanUtil;
import org.seasar.util.lang.ClassUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * オブジェクト全般ユーティリティクラス
 * @author hatimiti
 */
public final class _Obj {

	/**
	 * private コンストラクタ
	 */
	private _Obj() {
	}

	public static boolean isEmpty(Object val) {
		if (val instanceof String) {
			return ((String) val).isEmpty();
		} else if (val instanceof Collection<?>) {
			return ((Collection<?>) val).isEmpty();
		} else if (val instanceof AbstractMap<?, ?>) {
			return ((AbstractMap<?, ?>) val).isEmpty();
		} else if (val instanceof Iterator<?>) {
			return !((Iterator<?>) val).hasNext();
		}
		return val == null;
	}

	public static boolean isNotEmpty(Object val) {
		return !isEmpty(val);
	}

	/**
	 * いずれかが空であれば真を返す．
	 * @param values
	 * @return
	 */
	@SafeVarargs
	public static <O> boolean isEmptyAnyIn(O... values) {
		if (isEmpty(values)) {
			return true;
		}
		return Arrays.stream(values)
			.anyMatch(v -> isEmpty(v));
	}

	/**
	 * 渡された文字列配列が null または、空配列かどうかを判断する．
	 * 配列内の値も走査し、全て空文字の配列である場合も真を返す．
	 * @param 判定文字列配列
	 * @return null または空配列の場合は true を返す．<br>
	 */
	@SafeVarargs
	public static <O> boolean isEmptyAllIn(O... values) {
		if (isEmpty(values)) {
			return true;
		}
		return !Arrays.stream(values)
				.anyMatch(v -> isNotEmpty(v));
	}

	public static boolean eq(Object a, Object b) {
		if (a == null || b == null) {
			return false;
		}
		return a.equals(b);
	}

	public static boolean ne(Object a, Object b) {
		return !eq(a, b);
	}

	public static <O> void copy(O src, O dest) {

		if (isEmpty(src) || isEmpty(dest)) {
			throw new IllegalArgumentException("arguments src or dest is null.");
		}

		if (ne(src.getClass(), dest.getClass())) {
			throw new IllegalArgumentException("arguments src and dest must adapt same class type.");
		}

		BeanUtil.copyBeanToBean(src, dest);
	}

	public static <O> O createAndCopy(O src, Class<O> dest) {
		return BeanUtil.copyBeanToNewBean(src, dest);
	}

	/**
	 * Get the logging object.
	 * @return Get the logging object of current stack trace.
	 */
	public static Logger getLogger() {
		return LoggerFactory.getLogger(new Throwable().getStackTrace()[1].getClassName());
	}

	// TODO hatimiti trim to flutist-base
//	/**
//	 * 空白トリム処理
//	 * @param obj 処理対象フィールドを保持しているクラス
//	 * @param field 処理対象フィールド
//	 * @param defaultDoTrim
//	 * 	トリム施すかどうかのデフォルト値
//	 * 	Trim/DoNotTrim アノテーションが存在しない場合はこの値で判定する．
//	 * @param value 処理対象値
//	 * @param トリム後オブジェクト
//	 */
//	public static String doTrim(Field field, boolean defaulDoTrim, String value) {
//		DoTrim trim = field.getAnnotation(DoTrim.class);
//		DoNotTrim notTrim = field.getAnnotation(DoNotTrim.class);
//		if (notTrim != null) {
//			return value;
//		} else if (trim != null) {
//			switch (trim.value()) {
//			case BOTH:
//				return _Str.trimBoth(value);
//			case LEFT:
//				return _Str.trimLeft(value);
//			case RIGHT:
//				return _Str.trimRight(value);
//			case NONE:
//				break;
//			}
//		} else if (defaulDoTrim) {
//			return _Str.trimBoth(value);
//		}
//
//		return value;
//	}
//	/**
//	 * 空白トリム処理
//	 * @param obj 処理対象フィールドを保持しているクラス
//	 * @param field 処理対象フィールド
//	 * @param defaultDoTrim
//	 * 	トリム施すかどうかのデフォルト値
//	 * 	Trim/DoNotTrim アノテーションが存在しない場合はこの値で判定する．
//	 * @param value 処理対象値
//	 * @param トリム後オブジェクト
//	 */
//	public static String[] doTrim(Field field, boolean defaulDoTrim, String... values) {
//		DoTrim trim = field.getAnnotation(DoTrim.class);
//		DoNotTrim notTrim = field.getAnnotation(DoNotTrim.class);
//		if (notTrim != null) {
//			return values;
//		} else if (trim != null) {
//			switch (trim.value()) {
//			case BOTH:
//				return _Str.trimBoth(values);
//			case LEFT:
//				return _Str.trimLeft(values);
//			case RIGHT:
//				return _Str.trimRight(values);
//			case NONE:
//				break;
//			}
//		} else if (defaulDoTrim) {
//			return _Str.trimBoth(values);
//		}
//
//		return values;
//	}

	/**
	 * オブジェクトのシャローコピーを行う．
	 * (参照コピー)
	 * 例外が発生した場合は null を返す．
	 * @param object シャローコピーの対象オブジェクト
	 * @return シャローコピーされたオブジェクト
	 */
	@SuppressWarnings("unchecked")
	public static <T> T shallowCopy(T object) {
		try {
			return (T) BeanUtils.cloneBean(object);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * オブジェクトのディープコピーを行う．
	 * 例外が発生した場合は null を返す．
	 * @param object Serializable インタフェースを実装したオブジェクト
	 * @return ディープコピーされたオブジェクト
	 */
	public static <T extends Serializable> T deepCopy(final T object) {
		try {
			return (T) SerializationUtils.clone(object);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * パッケージを除いた最小のクラス名を取得する。
	 * 内部クラスの場合は、親クラス名も除いて取得する。
	 * @param clazz
	 * @return
	 */
	public static String getLeastClassName(final Class<?> clazz) {
		if (isEmpty(clazz)) {
			return "";
		}
		String name = ClassUtil.getShortClassName(clazz.getName());
		int spidx = name.lastIndexOf('$');
		return spidx < 0 ? name : name.substring(spidx + 1, name.length());
	}

	/**
	 * パッケージを除いた最小のクラス名を取得する。
	 * 内部クラスの場合は、親クラス名も除いて取得する。
	 * 先頭の文字を小文字で返す。(LowerCase)
	 * @param clazz
	 * @return
	 */
	public static String getLeastClassNameLC(final Class<?> clazz) {
		return _Str.toLowerCaseFirstChar(getLeastClassName(clazz));
	}

	public static <D> D get(final List<D> list, final int index) {
		try {
			return list.get(index);
		} catch (Throwable t) {
			return null;
		}
	}

	/**
	 * 空オブジェクトでない場合はリストに追加する
	 * @param list
	 * @param val
	 * @return
	 */
	public static <D> boolean addIfNotEmpty(final List<D> list, final D val) {
		if (isEmpty(list) || isEmpty(val)) {
			return false;
		}
		return list.add(val);
	}

}
