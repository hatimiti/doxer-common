package com.github.hatimiti.doxer.common.validation.validator;

import com.github.hatimiti.doxer.common.message.Owner;
import com.github.hatimiti.doxer.common.validation.Vval;



public interface Validator {

	boolean check(
			final Vval value,
			final Owner owner,
			final Object... params);

}
