package com.github.hatimiti.doxer.common.message;

import static com.github.hatimiti.doxer.common.util._Obj.*;
import static com.github.hatimiti.doxer.common.util._Str.*;
import static java.lang.String.*;

import com.github.hatimiti.doxer.common.util._Obj;

public class Owner {

	static final Owner GLOBAL_OWNER = new Owner("", "", -1);

	private String property;
	private String name;
	private Integer index;

	private Owner(String property, String name, Integer index) {
		this.property = property;
		this.name = name;
		this.index = index;
	}

	public static Owner empty() {
		return of("_");
	}

	public static Owner of(String property) {
		return of(property, "");
	}

	public static Owner of(String property, String name) {
		return of(property, name, (Integer) null);
	}

	public static Owner of(String property, String name, Integer index) {
		if (_Obj.isEmpty(property)) {
			throw new IllegalArgumentException(
					format("%s: Can't appoint a null character to a property. name: %s index: %s",
							Owner.class, name, index));
		}
		return new Owner(property, asStrOrEmpty(name), index);
	}

	@Override
	public String toString() {
		StringBuilder pn = new StringBuilder();
		if (_Obj.isNotEmpty(name)) {
			pn.append(name);
		}
		if (isNotEmpty(index)) {
			pn.append("[" + index + "]");
		}
		if ((isNotEmpty(name) || isNotEmpty(index))
				&& isNotEmpty(property)) {
			pn.append(".");
		}
		if (isNotEmpty(property)) {
			pn.append(this.property);
		}
		return pn.toString();
	}

	public String getProperty() {
		return property;
	}

	public String getName() {
		return name;
	}

	public Integer getIndex() {
		return index;
	}

	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Owner)) {
			return false;
		}
		return this.toString().equals(obj.toString());
	}

}
