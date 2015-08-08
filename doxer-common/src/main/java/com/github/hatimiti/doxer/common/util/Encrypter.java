package com.github.hatimiti.doxer.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 暗号化アルゴリズム列挙型
 * @author hatimiti
 */
public enum Encrypter {

	/** MD5("MD5") */
	MD5("MD5"),

	/** SHA1("SHA-1") */
	SHA1("SHA-1"),

	/** SHA2("SHA-256") */
	SHA256("SHA-256"),

	/** SHA384("SHA-384") */
	SHA384("SHA-384"),

	/** SHA512("SHA-512") */
	SHA512("SHA-512"),

	;

	/** このエンコードを表した文字列 */
	private String value;

	/**
	 * コンストラクタ
	 * @param value このエンコードを表した文字列
	 */
	private Encrypter(final String value) {
		this.value = value;
	}

	/**
	 * このエンコードを表した文字列を取得する．
	 * @return このエンコードを表した文字列
	 */
	@Override
	public String toString() {
		return this.value;
	}

	/**
	 * 当アルゴリズムで、与えられた値を暗号化する．
	 * 暗号化した文字列は16進数で表現されている．
	 * 与えられた値が null または 空文字 の場合は、空文字を返す．
	 * @param algorithm 暗号アルゴリズム
	 * @param value 暗号対象文字列
	 * @return value を暗号化した16進数文字列
	 * @throws NoSuchAlgorithmException アルゴリズム存在例外
	 */
	public String encrypt(String value){
		try {
			return encrypt(this.value, value);
		} catch (NoSuchAlgorithmException e) {
			return _Str.EMPTY;
		}
	}

	/**
	 * 指定のアルゴリズムで、与えられた値を暗号化する．
	 * 暗号化した文字列は16進数で表現されている．
	 * 与えられた値が null または 空文字 の場合は、空文字を返す．
	 * @param algorithm 暗号アルゴリズム
	 * @param value 暗号対象文字列
	 * @return value を暗号化した16進数文字列
	 * @throws NoSuchAlgorithmException アルゴリズム存在例外
	 */
	public static String encrypt(
			String algorithm,
			String value) throws NoSuchAlgorithmException {

		if (_Obj.isEmpty(value)) {
			return _Str.EMPTY;
		}

		MessageDigest md5 = MessageDigest.getInstance(algorithm);
		md5.reset();
		md5.update(value.getBytes());

		byte[] digest = md5.digest();

		StringBuilder result = new StringBuilder();

		for (byte b : digest) {
			result.append(Integer.toHexString(0xFF & b));
		}

		return result.toString();
	}

}
