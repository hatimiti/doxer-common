package com.github.hatimiti.doxer.common.validation.validator;

import static com.github.hatimiti.doxer.common.validation.validator.MaxBytesFieldValidator.*;
import static com.github.hatimiti.doxer.common.validation.validator.MaxBytesFieldValidatorTest.Value.*;
import static org.junit.Assert.*;

import java.nio.charset.Charset;
import java.util.Arrays;

import org.junit.Test;

public class MaxBytesFieldValidatorTest {

	@Test
	public void test() {

		Arrays.asList(
			of(null, 1, "UTF-8"),
			of(""  , 1, "UTF-8"),
			of(" " , 1, "UTF-8"),
			of("1" , 1, "UTF-8"),
			of("11", 2, "UTF-8"),
			of("あ", 3, "UTF-8"),
			of("あ", 2, "SJIS"),
			of("あ", 3, "SJIS")
		).forEach(v -> { assertTrue(checkMaxBytes(v.value, v.max, v.charset)); });

		Arrays.asList(
			of("aaaa", 3, "UTF-8"),
			of("aaaa", 3, "UTF-8"),
			of("あ"  , 2, "UTF-8"),
			of("あ"  , 1, "SJIS")
		).forEach(v -> { assertFalse(checkMaxBytes(v.value, v.max, v.charset)); });

		assertThrow(() -> {
			Value v = of("a", 1, null);
			checkMaxBytes(v.value, v.max, v.charset);
		});

	}

	protected static <T> void assertThrow(Runnable s) {
		try {
			s.run();
		} catch (Throwable t) {
			return;
		}
		assertTrue(false);
	}

	static final class Value {
		String value;
		int max;
		Charset charset;

		static Value of(String value, int max, String charset) {
			Value v = new Value();
			v.value = value;
			v.max = max;
			v.charset = Charset.forName(charset);
			return v;
		}
	}

}
