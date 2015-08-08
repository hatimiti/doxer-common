package com.github.hatimiti.doxer.common.validation.validator;

import static com.github.hatimiti.doxer.common.validation.validator.RegexFieldValidator.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class RegexFieldValidatorTest {

	@Test
	public void test() {
		assertTrue(checkRegex(null, "."));
		assertTrue(checkRegex("", "."));

		assertTrue(checkRegex("abc", "..."));
		assertTrue(checkRegex("abc", "[a-z]+"));
		assertTrue(checkRegex("", ".*"));
		assertTrue(checkRegex("0A0", "[\\dA-Z]+"));

		assertFalse(checkRegex("0A0a", "[\\dA-Z]+"));
	}

}
