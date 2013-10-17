package pl.jsolve.sweetener.converter;

import static org.fest.assertions.Assertions.assertThat;
import static pl.jsolve.sweetener.tests.assertion.ThrowableAssertions.assertThrowable;
import static pl.jsolve.sweetener.tests.catcher.ExceptionCatcher.tryToCatch;
import static pl.jsolve.sweetener.tests.stub.hero.HeroProfiledBuilder.aCaptainAmerica;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import pl.jsolve.sweetener.collection.Collections;
import pl.jsolve.sweetener.exception.ConversionException;
import pl.jsolve.sweetener.tests.catcher.ExceptionalOperation;
import pl.jsolve.sweetener.tests.stub.hero.Hero;
import pl.jsolve.sweetener.tests.stub.person.Person;
import pl.jsolve.sweetener.tests.stub.person.Student;

public class TypeConverterTest {

	@Test
	public void shouldConvertNull() {
		// when
		Hero result = TypeConverter.convert(null, Hero.class);

		// then
		assertThat(result).isNull();
	}

	@Test
	public void shouldCastStudentToPerson() {
		// given
		Student student = prepareStudent();

		// when
		Person person = TypeConverter.convert(student, Person.class);

		// then
		assertThat(person.getFirstName()).isEqualTo(student.getFirstName());
		assertThat(person.getLastName()).isEqualTo(student.getLastName());
		assertThat(person.getAge()).isEqualTo(student.getAge());
	}

	private Student prepareStudent() {
		Student student = new Student();
		student.setFirstName("Adam");
		student.setLastName("Bien");
		student.setAge(28);
		return student;
	}

	@Test
	public void shouldNotFindSuitableConverter() {
		// when
		ConversionException caughtException = tryToCatch(ConversionException.class, new ExceptionalOperation() {

			@Override
			public void operate() throws Exception {
				TypeConverter.convert(new Hero(), Student.class);
			}
		});

		// then
		assertThrowable(caughtException).withMessageContaining("Cannot convert from " + Hero.class + " to " + Student.class).isThrown();
	}

	// boolean conversion

	@Test
	public void shouldConvertBooleanToInteger() {
		// when
		Integer result = TypeConverter.convert(Boolean.TRUE, Integer.class);

		// then
		assertThat(result).isEqualTo(1);
	}

	@Test
	public void shouldConvertBooleanToInteger2() {
		// when
		Integer result = TypeConverter.convert(Boolean.FALSE, Integer.class);

		// then
		assertThat(result).isEqualTo(0);
	}

	@Test
	public void shouldConvertIntegerToBoolean() {
		// when
		Boolean result = TypeConverter.convert(1, Boolean.class);

		// then
		assertThat(result).isSameAs(Boolean.TRUE);
	}

	@Test
	public void shouldConvertIntegerToBoolean2() {
		// when
		Boolean result = TypeConverter.convert(0, Boolean.class);

		// then
		assertThat(result).isSameAs(Boolean.FALSE);
	}

	// primitive boolean conversion

	@Test
	public void shouldConvertPrimitiveBooleanToInt() {
		// when
		int result = TypeConverter.convert(true, int.class);

		// then
		assertThat(result).isEqualTo(1);
	}

	@Test
	public void shouldConvertPrimitiveBooleanToInt2() {
		// when
		int result = TypeConverter.convert(false, int.class);

		// then
		assertThat(result).isEqualTo(0);
	}

	@Test
	public void shouldConvertIntToPrimitiveBoolean() {
		// when
		boolean result = TypeConverter.convert(1, boolean.class);

		// then
		assertThat(result).isSameAs(true);
	}

	@Test
	public void shouldConvertIntToPrimitiveBoolean2() {
		// when
		boolean result = TypeConverter.convert(0, boolean.class);

		// then
		assertThat(result).isSameAs(false);
	}

	// string to ... conversion

	@Test
	public void shouldConvertStringToBoolean() {
		// when
		boolean result = TypeConverter.convert("     TrUe    	", Boolean.class);

		// then
		assertThat(result).isTrue();
	}

	@Test
	public void shouldConvertStringToBoolean2() {
		// when
		boolean result = TypeConverter.convert("False", Boolean.class);

		// then
		assertThat(result).isFalse();
	}

	@Test
	public void shouldConvertStringToNumber() {
		// when
		Number result = TypeConverter.convert("12", Number.class);

		// then
		assertThat(result.intValue()).isEqualTo(12);
	}

	@Test
	public void shouldConvertStringToInteger() {
		// when
		Integer result = TypeConverter.convert("12", Integer.class);

		// then
		assertThat(result).isEqualTo(12);
	}

	@Test
	public void shouldConvertStringToBigDecimal() {
		// when
		BigDecimal result = TypeConverter.convert("12", BigDecimal.class);

		// then
		assertThat(result).isEqualTo(BigDecimal.valueOf(12.0));
	}

	@Test
	public void shouldConvertStringToBigInteger() {
		// when
		BigInteger result = TypeConverter.convert("12", BigInteger.class);

		// then
		assertThat(result).isEqualTo(BigInteger.valueOf(12L));
	}

	@Test
	public void shouldConvertStringToByte() {
		// when
		Byte result = TypeConverter.convert("12", Byte.class);

		// then
		assertThat(result).isEqualTo(Byte.valueOf("12"));
	}

	@Test
	public void shouldConvertStringToDouble() {
		// when
		Double result = TypeConverter.convert("12", Double.class);

		// then
		assertThat(result).isEqualTo(Double.valueOf("12"));
	}

	@Test
	public void shouldConvertStringToFloat() {
		// when
		Float result = TypeConverter.convert("12", Float.class);

		// then
		assertThat(result).isEqualTo(Float.valueOf("12"));
	}

	@Test
	public void shouldConvertStringToLong() {
		// when
		Long result = TypeConverter.convert("12", Long.class);

		// then
		assertThat(result).isEqualTo(12L);
	}

	@Test
	public void shouldConvertStringToShort() {
		// when
		Short result = TypeConverter.convert("12", Short.class);

		// then
		assertThat(result).isEqualTo(Short.valueOf("12"));
	}

	@Test
	public void shouldConvertStringToString() {
		// given
		String string = "Silence is golden.";

		// when
		String result = TypeConverter.convert(string, String.class);

		// then
		assertThat(result).isSameAs(string);
	}

	// number to string conversion

	@Test
	public void shouldConvertBigIntegerToString() {
		// when
		String result = TypeConverter.convert(BigInteger.ONE, String.class);

		// then
		assertThat(result).isEqualTo("1");
	}

	@Test
	public void shouldConvertBigDecimalToString() {
		// when
		String result = TypeConverter.convert(BigDecimal.ONE, String.class);

		// then
		assertThat(result).isEqualTo("1");
	}

	@Test
	public void shouldConvertBooleanToString() {
		// when
		String result = TypeConverter.convert(true, String.class);

		// then
		assertThat(result).isEqualTo("true");
	}

	@Test
	public void shouldConvertBooleanToString2() {
		// when
		String result = TypeConverter.convert(Boolean.FALSE, String.class);

		// then
		assertThat(result).isEqualTo("false");
	}

	@Test
	public void shouldConvertByteToString() {
		// when
		String result = TypeConverter.convert(Byte.MIN_VALUE, String.class);

		// then
		assertThat(result).isEqualTo("-128");
	}

	@Test
	public void shouldConvertDoubleToString() {
		// when
		String result = TypeConverter.convert(Double.valueOf("0"), String.class);

		// then
		assertThat(result).isEqualTo("0.0");
	}

	@Test
	public void shouldConvertFloatToString() {
		// when
		String result = TypeConverter.convert(Float.valueOf("0"), String.class);

		// then
		assertThat(result).isEqualTo("0.0");
	}

	@Test
	public void shouldConvertLongToString() {
		// when
		String result = TypeConverter.convert(13L, String.class);

		// then
		assertThat(result).isEqualTo("13");
	}

	@Test
	public void shouldConvertShortToString() {
		// when
		String result = TypeConverter.convert(Short.valueOf("0"), String.class);

		// then
		assertThat(result).isEqualTo("0");
	}

	@Test
	public void shouldConvertIntegerToString() {
		// when
		String result = TypeConverter.convert(12, String.class);

		// then
		assertThat(result).isEqualTo("12");
	}

	// custom conversions

	@Test
	public void shouldConvertHeroToPerson() {
		// given
		Hero hero = aCaptainAmerica().build();
		TypeConverter.registerConverter(Hero.class, Person.class, new Converter<Hero, Person>() {

			@Override
			public Person convert(Hero source) {
				Person target = new Person();
				target.setFirstName(source.getFirstName());
				target.setLastName(source.getLastName());
				return target;
			}
		});

		// when
		Person person = TypeConverter.convert(hero, Person.class);

		// then
		assertThat(person.getFirstName()).isEqualTo(hero.getFirstName());
		assertThat(person.getLastName()).isEqualTo(hero.getLastName());

		TypeConverter.unregisterConverter(Hero.class, Person.class);
	}

	@Test
	public void shouldConvertHeroToObject() {
		// given
		Hero hero = aCaptainAmerica().build();

		// when
		Object result = TypeConverter.convert(hero, Object.class);

		// then
		assertThat(result).isInstanceOf(Hero.class);
	}

	// integer to numbers conversion

	@Test
	public void shouldConvertIntegerToBigDecimal() {
		// given
		Integer integer = 12;

		// when
		BigDecimal result = TypeConverter.convert(integer, BigDecimal.class);

		// then
		assertThat(result.intValue()).isEqualTo(integer);
	}

	@Test
	public void shouldConvertIntegerToBigInteger() {
		// given
		Integer integer = 12;

		// when
		BigInteger result = TypeConverter.convert(integer, BigInteger.class);

		// then
		assertThat(result.intValue()).isEqualTo(integer);
	}

	@Test
	public void shouldConvertIntegerToByte() {
		// given
		Integer integer = 12;

		// when
		Byte result = TypeConverter.convert(integer, Byte.class);

		// then
		assertThat(result.intValue()).isEqualTo(integer);
	}

	@Test
	public void shouldConvertIntegerToDouble() {
		// given
		Integer integer = 12;

		// when
		Double result = TypeConverter.convert(integer, Double.class);

		// then
		assertThat(result.intValue()).isEqualTo(integer);
	}

	@Test
	public void shouldConvertIntegerToFloat() {
		// given
		Integer integer = 12;

		// when
		Float result = TypeConverter.convert(integer, Float.class);

		// then
		assertThat(result.intValue()).isEqualTo(integer);
	}

	@Test
	public void shouldConvertIntegerToLong() {
		// given
		Integer integer = 12;

		// when
		Long result = TypeConverter.convert(integer, Long.class);

		// then
		assertThat(result.intValue()).isEqualTo(integer);
	}

	@Test
	public void shouldConvertIntegerToNumber() {
		// given
		Integer integer = 12;

		// when
		Number result = TypeConverter.convert(integer, Number.class);

		// then
		assertThat(result.intValue()).isEqualTo(integer);
	}

	@Test
	public void shouldConvertIntegerToShort() {
		// given
		Integer integer = 12;

		// when
		Short result = TypeConverter.convert(integer, Short.class);

		// then
		assertThat(result.intValue()).isEqualTo(integer);
	}

	@Test
	public void shouldConvertIntegerToInteger() {
		// given
		Integer integer = 12;

		// when
		Integer result = TypeConverter.convert(integer, Integer.class);

		// then
		assertThat(result).isEqualTo(integer);
	}

	// BigDecimal to numbers conversion

	@Test
	public void shouldConvertBigDecimalToInteger() {
		// given
		BigDecimal bigDecimal = BigDecimal.TEN;

		// when
		Integer result = TypeConverter.convert(bigDecimal, Integer.class);

		// then
		assertThat(result).isEqualTo(bigDecimal.intValue());
	}

	@Test
	public void shouldConvertBigDecimalToBigInteger() {
		// given
		BigDecimal bigDecimal = BigDecimal.TEN;

		// when
		BigInteger result = TypeConverter.convert(bigDecimal, BigInteger.class);

		// then
		assertThat(result.intValue()).isEqualTo(bigDecimal.intValue());
	}

	@Test
	public void shouldConvertBigDecimalToBoolean() {
		// given
		BigDecimal bigDecimal = BigDecimal.ZERO;

		// when
		Boolean result = TypeConverter.convert(bigDecimal, Boolean.class);

		// then
		assertThat(result).isFalse();
	}

	@Test
	public void shouldConvertBigDecimalToBoolean2() {
		// given
		BigDecimal bigDecimal = BigDecimal.TEN;

		// when
		Boolean result = TypeConverter.convert(bigDecimal, Boolean.class);

		// then
		assertThat(result).isTrue();
	}

	@Test
	public void shouldConvertBigDecimalToByte() {
		// given
		BigDecimal bigDecimal = BigDecimal.TEN;

		// when
		Byte result = TypeConverter.convert(bigDecimal, Byte.class);

		// then
		assertThat(result).isEqualTo(bigDecimal.byteValue());
	}

	@Test
	public void shouldConvertBigDecimalToDouble() {
		// given
		BigDecimal bigDecimal = BigDecimal.TEN;

		// when
		Double result = TypeConverter.convert(bigDecimal, Double.class);

		// then
		assertThat(result).isEqualTo(bigDecimal.doubleValue());
	}

	@Test
	public void shouldConvertBigDecimalToFloat() {
		// given
		BigDecimal bigDecimal = BigDecimal.TEN;

		// when
		Float result = TypeConverter.convert(bigDecimal, Float.class);

		// then
		assertThat(result).isEqualTo(bigDecimal.floatValue());
	}

	@Test
	public void shouldConvertBigDecimalToLong() {
		// given
		BigDecimal bigDecimal = BigDecimal.TEN;

		// when
		Long result = TypeConverter.convert(bigDecimal, Long.class);

		// then
		assertThat(result).isEqualTo(bigDecimal.longValue());
	}

	@Test
	public void shouldConvertBigDecimalToShort() {
		// given
		BigDecimal bigDecimal = BigDecimal.TEN;

		// when
		Short result = TypeConverter.convert(bigDecimal, Short.class);

		// then
		assertThat(result).isEqualTo(bigDecimal.shortValue());
	}

	// array to collections conversion

	@Test
	public void shouldConvertArrayToHashSet() {
		// given
		Integer[] arrayOfIntegers = new Integer[] { 1, 2, 3 };

		// when
		HashSet<Integer> result = TypeConverter.convert(arrayOfIntegers, HashSet.class);

		// then
		assertThat(result).contains(1, 2, 3);
	}

	@Test
	public void shouldConvertArrayToSet() {
		// given
		Integer[] arrayOfIntegers = new Integer[] { 1, 2, 3 };

		// when
		Set<Integer> result = TypeConverter.convert(arrayOfIntegers, Set.class);

		// then
		assertThat(result).contains(1, 2, 3);
	}

	@Test
	public void shouldConvertArrayToTreeSet() {
		// given
		Integer[] arrayOfIntegers = new Integer[] { 1, 2, 3 };

		// when
		TreeSet<Integer> result = TypeConverter.convert(arrayOfIntegers, TreeSet.class);

		// then
		assertThat(result).contains(1, 2, 3);
	}

	@Test
	public void shouldConvertArrayToLinkedHashSet() {
		// given
		String[] arrayOfStrings = new String[] { "one", "two", "three" };

		// when
		LinkedHashSet<String> result = TypeConverter.convert(arrayOfStrings, LinkedHashSet.class);

		// then
		assertThat(result).contains("one", "two", "three");
	}

	@Test
	public void shouldConvertArrayToArrayList() {
		// given
		String[] arrayOfStrings = new String[] { "one", "two", "three" };

		// when
		ArrayList<String> result = TypeConverter.convert(arrayOfStrings, ArrayList.class);

		// then
		assertThat(result).contains("one", "two", "three");
	}

	@Test
	public void shouldConvertArrayToList() {
		// given
		String[] arrayOfStrings = new String[] { "one", "two", "three" };

		// when
		ArrayList<String> result = TypeConverter.convert(arrayOfStrings, ArrayList.class);

		// then
		assertThat(result).contains("one", "two", "three");
	}

	@Test
	public void shouldConvertArrayToLinkedList() {
		// given
		Integer[] arrayOfIntegers = new Integer[] { 1, 2, 3 };

		// when
		LinkedList<Integer> result = TypeConverter.convert(arrayOfIntegers, LinkedList.class);

		// then
		assertThat(result).contains(1, 2, 3);
	}

	// lists to array conversion

	@Test
	public void shouldConvertListToStringsArray() {
		// given
		List<String> listOfStrings = Collections.newArrayList("one", "two", "three");

		// when
		String[] result = TypeConverter.convert(listOfStrings, String[].class);

		// then
		assertThat(result).contains("one", "two", "three");
	}

	@Test
	public void shouldConvertListToBooleanArray() {
		// given
		List<Boolean> list = Collections.newArrayList(true, false, false);

		// when
		Boolean[] result = TypeConverter.convert(list, Boolean[].class);

		// then
		assertThat(result).contains(true, false, false);
	}

	@Test
	public void shouldConvertListToByteArray() {
		// given
		List<Byte> list = Collections.newArrayList(Byte.MIN_VALUE, Byte.MAX_VALUE);

		// when
		Byte[] result = TypeConverter.convert(list, Byte[].class);

		// then
		assertThat(result).contains(Byte.MAX_VALUE, Byte.MIN_VALUE);
	}

	@Test
	public void shouldConvertListToCharArray() {
		// given
		List<Character> list = Collections.newArrayList('a', 'b', 'c');

		// when
		Character[] result = TypeConverter.convert(list, Character[].class);

		// then
		assertThat(result).contains('a', 'b', 'c');
	}

	@Test
	public void shouldConvertListToDoubleArray() {
		// given
		List<Double> list = Collections.newArrayList(0.1, 0.2, 0.3);

		// when
		Double[] result = TypeConverter.convert(list, Double[].class);

		// then
		assertThat(result).contains(0.1, 0.2, 0.3);
	}

	@Test
	public void shouldConvertListToFloatArray() {
		// given
		List<Float> list = Collections.newArrayList(0.1f, 0.2f, 0.3f);

		// when
		Float[] result = TypeConverter.convert(list, Float[].class);

		// then
		assertThat(result).contains(0.1f, 0.2f, 0.3f);
	}

	@Test
	public void shouldConvertListToIntegerArray() {
		// given
		List<Integer> list = Collections.newArrayList(1, 2, 3);

		// when
		Integer[] result = TypeConverter.convert(list, Integer[].class);

		// then
		assertThat(result).contains(1, 2, 3);
	}

	@Test
	public void shouldConvertListToLongArray() {
		// given
		List<Long> list = Collections.newArrayList(1L, 2L, 3L);

		// when
		Long[] result = TypeConverter.convert(list, Long[].class);

		// then
		assertThat(result).contains(1L, 2L, 3L);
	}

	@Test
	public void shouldConvertListToShortArray() {
		// given
		List<Short> list = Collections.newArrayList(Short.MIN_VALUE, Short.MAX_VALUE);

		// when
		Short[] result = TypeConverter.convert(list, Short[].class);

		// then
		assertThat(result).contains(Short.MIN_VALUE, Short.MAX_VALUE);
	}

	@Test
	public void shouldConvertListToObjectArray() {
		// given
		Object object = new Object();
		List<Object> list = Collections.newArrayList(object);

		// when
		Object[] result = TypeConverter.convert(list, Object[].class);

		// then
		assertThat(result).contains(object);
	}

	@Test
	public void shouldNotConvertListToArrayWithDifferentElementsType() {
		// when
		ConversionException caughtException = tryToCatch(ConversionException.class, new ExceptionalOperation() {

			@Override
			public void operate() throws Exception {
				TypeConverter.convert(Collections.newArrayList("one", "two", "three"), Integer[].class);
			}
		});

		// then
		assertThrowable(caughtException).isThrown().withMessageContaining(ArrayStoreException.class.getName());
	}

	// collection to collections

	@Test
	public void shouldConvertListToSet() {
		// given
		List<String> list = Collections.newArrayList("one", "two", "three");

		// when
		Set<String> set = TypeConverter.convert(list, Set.class);

		// then
		assertThat(set).contains("one", "two", "three");
	}

	@Test
	public void shouldConvertLinkedListToLinkedHashSet() {
		// given
		LinkedList<String> list = Collections.newLinkedList("one", "two", "three");

		// when
		Set<String> set = TypeConverter.convert(list, LinkedHashSet.class);

		// then
		assertThat(set).contains("one", "two", "three");
	}

	@Test
	public void shouldConvertListToHashSet() {
		// given
		List<String> list = Collections.newArrayList("one", "two", "three");

		// when
		Set<String> set = TypeConverter.convert(list, HashSet.class);

		// then
		assertThat(set).contains("one", "two", "three");
	}

	@Test
	public void shouldConvertLinkedListToHashSet() {
		// given
		LinkedList<String> list = Collections.newLinkedList("one", "two", "three");

		// when
		Set<String> set = TypeConverter.convert(list, HashSet.class);

		// then
		assertThat(set).contains("one", "two", "three");
	}

	@Test
	public void shouldConvertListToTreeSet() {
		// given
		List<Integer> arrayList = Collections.newArrayList(1, 2, 3);

		// when
		TreeSet<Integer> result = TypeConverter.convert(arrayList, TreeSet.class);

		// then
		assertThat(result).contains(1, 2, 3);
	}

	@Test
	public void shouldConvertHashSetToTreeSet() {
		// given
		HashSet<String> hashSet = Collections.newHashSet("one", "two", "three");

		// when
		TreeSet<String> result = TypeConverter.convert(hashSet, TreeSet.class);

		// then
		assertThat(result).contains("one", "two", "three");
	}

	@Test
	public void shouldConvertSetToList() {
		// given
		Set<String> set = Collections.newHashSet("one", "two", "three");

		// when
		List<String> result = TypeConverter.convert(set, List.class);

		// then
		assertThat(result).contains("one", "two", "three");
	}

	@Test
	public void shouldConvertLinkedHashSetToLinkedList() {
		// given
		LinkedHashSet<String> linkedHashSet = Collections.newLinkedHashSet("one", "two", "three");

		// when
		LinkedList<String> result = TypeConverter.convert(linkedHashSet, LinkedList.class);

		// then
		assertThat(result).contains("one", "two", "three");
	}

	@Test
	public void shouldConvertHashSetToArrayList() {
		// given
		HashSet<String> linkedHashSet = Collections.newHashSet("one", "two", "three");

		// when
		ArrayList<String> result = TypeConverter.convert(linkedHashSet, ArrayList.class);

		// then
		assertThat(result).contains("one", "two", "three");
	}

	@Test
	public void shouldConvertHashSetToLinkedList() {
		// given
		HashSet<String> linkedHashSet = Collections.newHashSet("one", "two", "three");

		// when
		LinkedList<String> result = TypeConverter.convert(linkedHashSet, LinkedList.class);

		// then
		assertThat(result).contains("one", "two", "three");
	}


	// primitives

	@Test
	public void shouldConvertIntToString() {
		// when
		int result = TypeConverter.convert("2", int.class);

		// then
		assertThat(result).isEqualTo(2);
	}

	@Test
	public void shouldConvertPrimitiveByteToString() {
		// when
		byte result = TypeConverter.convert("2", byte.class);

		// then
		assertThat(result).isEqualTo((byte) 2);
	}

	@Test
	public void shouldConvertPrimitiveDoubleToPrimitiveInt() {
		// when
		int result = TypeConverter.convert(12.5, int.class);

		// then
		assertThat(result).isEqualTo(12);
	}

	@Test
	public void shouldConvertPrimitiveIntToPrimitiveDouble() {
		// when
		double result = TypeConverter.convert(12, double.class);

		// then
		assertThat(result).isEqualTo(12.0);
	}
}