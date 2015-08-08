package com.github.hatimiti.doxer.common.util;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class _RefTest {

	@Test
	public void test_getMethod() {
		assertTrue(_Ref.getMethod(Person.class, "getName", new Class[]{}).isPresent());
		assertTrue(_Ref.getMethod(Person.class, "getAge", new Class[]{}).isPresent());
		assertFalse(_Ref.getMethod(Person.class, "getage", new Class[]{}).isPresent());
		assertFalse(_Ref.getMethod(Person.class, "thinkX", new Class[]{}).isPresent());
		assertFalse(_Ref.getMethod(Person.class, "thinkY", Integer.class).isPresent());
		assertTrue(_Ref.getMethod(Person.class, "thinkY", int.class).isPresent());
	}

	@Test
	public void test_getAllFields() {
		List<Field> p = _Ref.getAllFields(Person.class);
		assertFalse(p.isEmpty());

		List<Field> e = _Ref.getAllFields(Empty.class);
		assertTrue(e.isEmpty());
	}

	@Test
	public void test_getFieldIncludedSuperByName() {

		LivingThing p = new Japanese();

		Optional<Field> f = _Ref.getFieldIncludedSuperByName(p.getClass(), "thinkingPower");
		assertTrue(f.isPresent());

		Optional<Field> ff = _Ref.getFieldByName(p.getClass(), "thinkingPower");
		assertTrue(!ff.isPresent());
	}

	@Data
	public static abstract class Animal implements LivingThing {
		private String name;
		private int age;
	}

	@Data
	@EqualsAndHashCode(callSuper = true)
	public static class Person extends Animal {
		private int thinkingPower;

		protected String thinkX() {
			return "thinkX";
		}

		public String thinkY(int y) {
			return "thinkY" + y;
		}
	}

	public static class Japanese extends Person {
		private String lang;
		public String getLang() {
			return this.lang;
		}
	}

	public static class Empty {
	}

	public interface LivingThing {
	}
}
