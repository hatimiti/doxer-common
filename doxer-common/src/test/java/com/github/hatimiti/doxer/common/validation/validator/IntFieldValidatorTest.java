package com.github.hatimiti.doxer.common.validation.validator;

import static com.github.hatimiti.doxer.common.validation.validator.IntFieldValidator.*;
import static java.lang.String.*;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class IntFieldValidatorTest {

	@Test
	public void test() {

		Arrays.asList(
			null, "", "-1", "0", "1", "１",
			valueOf(Integer.MAX_VALUE), // 2147483647
			valueOf(Integer.MIN_VALUE) // -2147483648
		).forEach(v -> { assertTrue(checkInt(v)); });

		Arrays.asList(
			" ", "a", "あ", "　",
			"2147483648",  // Integer.MAX_VALUE + 1
			"-2147483649", // Integer.MIN_VALUE - 1
			"99999999999"
		).forEach(v -> { assertFalse(checkInt(v)); });

	}

}
