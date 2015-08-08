package com.github.hatimiti.doxer.common.validation.validator;

import static com.github.hatimiti.doxer.common.validation.validator.RequiredFieldValidator.*;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class RequiredFieldValidatorTest {

	@Test
	public void test() {

		Arrays.asList(" ", "　", "	", "_", ".", "\\", "a", "0", "あ", "ァ", "①", "ｱ")
			.forEach(v -> assertTrue(checkRequired(v)));

		assertFalse(checkRequired(null));
		assertFalse(checkRequired(""));
	}

}
