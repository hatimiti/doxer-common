package com.github.hatimiti.doxer.common.message;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class OwnerTest {

	@Test
	public void test_equals() {
		assertEquals(Owner.of("property"), Owner.of("property"));
		assertEquals(Owner.of("property", "name"), Owner.of("property", "name"));
		assertEquals(Owner.of("property", "name", 0), Owner.of("property", "name", 0));

		Owner o = Owner.of("property");
		assertEquals(o, o);

		Owner w = o;
		assertEquals(o, w);
	}

	@Test
	public void test_notEquals() {
		assertNotEquals(Owner.of("property"), Owner.of("xproperty"));

		assertNotEquals(Owner.of("property", "name"), Owner.of("xproperty", "name"));
		assertNotEquals(Owner.of("property", "name"), Owner.of("property", "xname"));

		assertNotEquals(Owner.of("property", "name", 0), Owner.of("xproperty", "name", 0));
		assertNotEquals(Owner.of("property", "name", 0), Owner.of("property", "xname", 0));
		assertNotEquals(Owner.of("property", "name", 0), Owner.of("property", "name", 1));
	}

	@Test
	public void test_equalsHashcode() {
		assertEquals(Owner.of("property").hashCode(), Owner.of("property").hashCode());
		assertEquals(Owner.of("property", "name").hashCode(), Owner.of("property", "name").hashCode());
		assertEquals(Owner.of("property", "name", 0).hashCode(), Owner.of("property", "name", 0).hashCode());

		Owner o = Owner.of("property");
		assertEquals(o.hashCode(), o.hashCode());

		Owner w = o;
		assertEquals(o.hashCode(), w.hashCode());
	}

	@Test
	public void test_keyInHashMap() {
		test_keyInMap(new HashMap<>());
	}

	@Test
	public void test_keyInLinkedHashMap() {
		test_keyInMap(new LinkedHashMap<>());
	}

	private void test_keyInMap(Map<Owner, String> m) {

		m.put(Owner.of("property1"), "a");
		m.put(Owner.of("property2"), "b");
		m.put(Owner.of("property3"), "c");

		assertEquals(m.size(), 3);
		assertEquals(m.get(Owner.of("property2")), "b");

		m.put(Owner.of("property2"), "B");
		assertEquals(m.get(Owner.of("property2")), "B");

		//-----------------------------------------

		m.clear();

		m.put(Owner.of("property1", "name1"), "a");
		m.put(Owner.of("property2", "name2"), "b");
		m.put(Owner.of("property3", "name3"), "c");

		assertEquals(m.size(), 3);
		assertEquals(m.get(Owner.of("property2", "name2")), "b");

		m.put(Owner.of("property2", "name2"), "B");
		assertEquals(m.get(Owner.of("property2", "name2")), "B");

		//-----------------------------------------

		m.clear();

		m.put(Owner.of("property1", "name1", 1), "a");
		m.put(Owner.of("property2", "name2", 1), "b");
		m.put(Owner.of("property3", "name3", 1), "c");

		assertEquals(m.size(), 3);
		assertEquals(m.get(Owner.of("property2", "name2", 1)), "b");

		m.put(Owner.of("property2", "name2", 1), "B");
		assertEquals(m.get(Owner.of("property2", "name2", 1)), "B");

		//-----------------------------------------

		assertEquals(m.computeIfAbsent(Owner.of("property2", "name2", 1), k -> "new"), "B");
		assertEquals(m.computeIfAbsent(Owner.of("propertyX", "nameX", 0), k -> "new"), "new");

	}

}
