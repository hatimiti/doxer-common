package com.github.hatimiti.doxer.common.domain.type;

import java.util.ArrayList;
import java.util.List;

public class ArrayListType<O>
		extends ListType<O, ArrayList<O>> {

	public ArrayListType() {
		super(new ArrayList<O>());
	}
	
	public ArrayListType(List<O> list) {
		super(new ArrayList<O>(list));
	}

}
