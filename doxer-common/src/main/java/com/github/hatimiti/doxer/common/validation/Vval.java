package com.github.hatimiti.doxer.common.validation;

import static java.util.Arrays.*;

import java.io.Serializable;
import java.util.Objects;

public class Vval implements Serializable {

	protected String[] values;
	
	private Vval(String... values) {
		this.values = values;
	}
	
	public static Vval of(String... values) {
		Objects.requireNonNull(values);
		return new Vval(values);
	}
	
	public String[] getValues() {
		return copyOf(values, values.length);
	}
	
}
