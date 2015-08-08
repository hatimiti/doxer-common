package com.github.hatimiti.doxer.common.util;


/**
 * Pathに関するユーティリティクラス．
 * @author m-kakimi
 */
public final class _Path {

	/*
	 * private コンストラクタ
	 */
	private _Path() { }

	/**
	 *
	 * @param action
	 * @return
	 */
	public static String getActionPath(Class<?> action) {

		if (action == null) {
			return "";
		}
//		String packName = action.getPackage().getName(); // hotdeploy の場合、クラスローダが異なり null
		String packName = action.getName().substring(0, action.getName().lastIndexOf("."));
		String actionName = _Str.toLowerCaseFirstChar(action.getSimpleName());

		int pFromIndex = packName.indexOf("action.") + "action.".length();
		int aLastIndex = actionName.indexOf("Action");

		return "/" + (packName.substring(pFromIndex) + "."
				+ actionName.substring(0, aLastIndex))
					.replaceAll("\\.", "/");
	}

}
