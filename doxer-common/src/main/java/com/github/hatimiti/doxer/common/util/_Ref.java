package com.github.hatimiti.doxer.common.util;

import static java.util.Arrays.*;
import static java.util.Optional.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.seasar.util.lang.MethodUtil;

/**
 * リフレクションに関する操作を行うユーティリティクラス．
 * @author hatimiti
 */
public final class _Ref {

	/*
	 * private コンストラクタ
	 */
	private _Ref() {}

	public static List<Method> getMethodsByName(
			final Class<?> targetClass,
			final String methodName) {
		return Stream.of(targetClass.getMethods())
			.filter(m -> m.getName().equals(methodName))
			.collect(Collectors.toList());
	}

	public static Optional<Method> getMethod(
			final Class<?> targetClass,
			final String methodName,
			final Class<?>... parameterTypes) {

		try {
			return ofNullable(targetClass.getMethod(methodName, parameterTypes));
		} catch (Exception t) {
			return empty();
		}
	}

	/**
	 * 親クラスのフィールドも取得
	 */
	public static List<Field> getAllFields(
			final Class<?> clazz) {

		List<Field> fieldList = new ArrayList<Field>();

		Class<?> superClazz = clazz.getSuperclass();
		if (superClazz != null) {
			// 再帰的に親クラスのフィールドを取得
			fieldList.addAll(getAllFields(superClazz));
		}
		fieldList.addAll(Arrays.asList(clazz.getDeclaredFields()));

		return fieldList;
	}

	public static Optional<Field> getFieldIncludedSuperByName(
			final Class<?> target,
			final String fieldName) {

		Objects.requireNonNull(target);
		Objects.requireNonNull(fieldName);

		Class<?> clazz = target;
		Optional<Field> result;
		do {
			result = getFieldByName(clazz, fieldName);
			if (result.isPresent()) {
				return result;
			}
			clazz = clazz.getSuperclass();
		} while (clazz != null);

		return Optional.empty();
	}

	/**
	 * annotationClass のついた 親クラスのフィールドも取得
	 */
	public static List<Field> getAllFieldsByAnnotation(
			final Class<?> clazz,
			final Class<? extends Annotation> annotationClass) {

		List<Field> fieldList = new ArrayList<>();

		Class<?> superClazz = clazz.getSuperclass();
		if (superClazz != null) {
			// 再帰的に親クラスのフィールドを取得
			fieldList.addAll(getAllFieldsByAnnotation(superClazz, annotationClass));
		}

		stream(clazz.getDeclaredFields())
			.filter(f -> f.getAnnotation(annotationClass) != null)
			.forEach(fieldList::add);

		return fieldList;
	}

	/**
	 * 対象のクラスに指定したアノテーションが付加されているフィールドクラスを取得する．
	 * 複数フィールドが存在する場合は初めに見つけたフィールドクラスを返す．
	 * @param target 走査対象オブジェクト
	 * @param annotationClass 検索対象アノテーション
	 * @return　検索対象アノテーションが付加されたフィールドが存在すれば、
	 * そのフィールドクラス、そうでなければ Optional.empty を返す．
	 */
	public static Optional<Object> getFieldValueByAnnotation(
			final Object target,
			final Class<? extends Annotation> annotationClass) {

		try {
			Optional<Field> field = getFieldByAnnotation(target.getClass(), annotationClass);
			return field.isPresent()
					? ofNullable(field.get().get(target)) : empty();
		} catch (Exception e) {
			return empty();
		}
	}

	/**
	 * 対象のクラスに指定したアノテーションが付加されているフィールドを取得する．
	 * 複数フィールドが存在する場合は初めに見つけたフィールドを返す．
	 * @param target 走査対象オブジェクト
	 * @param annotationClass 検索対象アノテーション
	 * @return　検索対象アノテーションが付加されたフィールドが存在すれば、
	 * そのフィールド、そうでなければ null を返す．
	 */
	public static Optional<Field> getFieldByAnnotation(
			final Class<?> target,
			final Class<? extends Annotation> annotationClass) {

		return Arrays.stream(target.getDeclaredFields())
			.map(f -> {
				f.setAccessible(true);
				return f;
			})
			.filter(f -> f.getAnnotation(annotationClass) != null)
			.findFirst();
	}

	/**
	 * 対象のクラスの指定した名前のフィールドを取得する．
	 * @param target 走査対象オブジェクト
	 * @param fieldName 検索対象フィールド名
	 * @return フィールドが存在すればそのフィールド、そうでなければ null を返す．
	 */
	public static Optional<Field> getFieldByName(
			final Class<?> target,
			final String fieldName) {

		Objects.requireNonNull(target);
		Objects.requireNonNull(fieldName);

		Field[] fields = target.getDeclaredFields();
		return Arrays.stream(fields).map(f -> {
				f.setAccessible(true);
				return f;
			})
			.filter(f -> fieldName.equals(f.getName()))
			.findFirst();
	}

	/**
	 * Get the value by specified field name of target.
	 * If the field is not exist, return Optional.empty() value.
	 * @param target searching target
	 * @param fieldName
	 * @return
	 */
	public static Optional<Object> getFieldValueByName(
			final Object target,
			final String fieldName) {

		Objects.requireNonNull(target);
		Objects.requireNonNull(fieldName);

		Optional<Field> field = getFieldByName(target.getClass(), fieldName);
		if (!field.isPresent()) {
			return Optional.empty();
		}
		try {
			return Optional.ofNullable(field.get().get(target));
		} catch (IllegalAccessException | IllegalArgumentException e) {
			return Optional.empty();
		}
	}

	/**
	 * 対象のクラスに指定したアノテーションが付加されているフィールドが
	 * 存在するかチェックする．
	 * @param target 走査対象オブジェクト
	 * @param annotationClass 検索対象アノテーション
	 * @return 検索対象アノテーションが付加されたフィールドが存在すれば、
	 * 真、そうでなければ偽の値を返す．
	 */
	public static boolean hasAnnotationInFields(
			Class<?> target,
			Class<? extends Annotation> annotationClass) {

		return getFieldByAnnotation(target, annotationClass).isPresent();
	}

	/**
	 * 渡されたオブジェクトのインスタンス変数が
	 * 設定されたマップオブジェクトを取得する．
	 */
	public static Map<String, Object> getFieldsMapOf(final Object obj) {

		Map<String, Object> result = new HashMap<>();

		if (obj == null) {
			return result;
		}

		stream(obj.getClass().getFields())
			.forEach(f -> {
				f.setAccessible(true);
				try {
					result.put(f.getName(), f.get(obj));
				} catch (Exception ignore) {}
			});

		return result;
	}

	public static Object invoke(Method method, Object target, Object... args) {
		return MethodUtil.invoke(method, target, args);
	}

}
