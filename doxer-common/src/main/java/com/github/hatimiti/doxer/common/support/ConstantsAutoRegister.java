package com.github.hatimiti.doxer.common.support;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContext;

import org.seasar.util.beans.util.BeanMap;
import org.seasar.util.io.ResourceUtil;
import org.seasar.util.lang.ClassLoaderUtil;
import org.seasar.util.lang.FieldUtil;

import com.github.hatimiti.doxer.common.util._Obj;

/**
 * @author hatimiti
 */
public abstract class ConstantsAutoRegister {

	private List<ClassPattern> classPatterns = new ArrayList<>();

	// 定数として登録する対象の型
	private List<Class<?>> targetTypes = new LinkedList<Class<?>>() {
		private static final long serialVersionUID = 1L;
		{
			add(int.class);
			add(long.class);
			add(double.class);
			add(boolean.class);
			add(Integer.class);
			add(Long.class);
			add(Double.class);
			add(Boolean.class);
			add(String.class);
			add(Enum.class);
		}
	};

	public ConstantsAutoRegister() {
		this.targetTypes.addAll(getAdditionalTargetTypes());
		getAdditionalClassPatterns()
			.forEach(tci -> classPatterns.add(tci.classPattern));
	}

//	public void processClass(
//			final ServletContext application,
//			final String packageName,
//			final String shortClassName) {
//
//		for (ClassPattern cp : classPatterns) {
//			if (cp.isAppliedPackageName(packageName)
//					&& cp.isAppliedShortClassName(shortClassName)) {
//				register(application, ClassUtil.concatName(packageName, shortClassName));
//				return;
//			}
//		}
//	}

	// 指定されたクラスの定数をアプリケーション・スコープに登録
	public void register(
			final ServletContext application,
			final String className) {

		Class<?> clazz = getClassByName(className);
		registTargetFields(application, clazz);
	}

	protected Class<?> getClassByName(String className) {
		return ClassLoaderUtil.loadClass(ResourceUtil.getClassLoader(), className);
	}

	protected void registTargetFields(
			final ServletContext application,
			final Class<?> clazz) {

		BeanMap beanMap = new BeanMap();

		for (Field f : clazz.getFields()) {
			if (f.getDeclaringClass() != clazz) {
				continue;
			}
			if (!this.targetTypes.contains(f.getType())) {
				continue;
			}
			int mod = f.getModifiers();
			if (Modifier.isPublic(mod) && Modifier.isStatic(mod)) {
				String key = f.getName();
				Object value = FieldUtil.get(f, null);
				beanMap.put(key,
						value instanceof Enum ? value.toString() : value);
			}
		}

		if (0 < beanMap.size()) {
			String shortClassName = _Obj.getLeastClassName(clazz);
			application.setAttribute(shortClassName, beanMap);
		}
	}

	protected abstract List<Class<?>> getAdditionalTargetTypes();
	protected abstract List<TargetClassInfo> getAdditionalClassPatterns();

	protected static class TargetClassInfo {
		private ClassPattern classPattern;
		public TargetClassInfo(
				final Class<?> clazz,
				final String shortClassNames) {
			this.classPattern = new ClassPattern(clazz.getPackage().getName(), shortClassNames);
		}
	}

}
