package pl.jsolve.sweetener.converter;

import static org.fest.assertions.Assertions.assertThat;
import static pl.jsolve.sweetener.tests.assertion.ThrowableAssertions.assertThrowable;
import static pl.jsolve.sweetener.tests.catcher.ExceptionCatcher.tryToCatch;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;

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
		// given
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
		int result = TypeConverter.convert(true, Integer.class);

		// then
		assertThat(result).isEqualTo(1);
	}

	@Test
	public void shouldConvertBooleanToInteger2() {
		// when
		int result = TypeConverter.convert(false, Integer.class);

		// then
		assertThat(result).isEqualTo(0);
	}

	@Test
	public void shouldConvertIntegerToBoolean() {
		// when
		boolean result = TypeConverter.convert(1, Boolean.class);

		// then
		assertThat(result).isEqualTo(true);
	}

	@Test
	public void shouldConvertIntegerToBoolean2() {
		// when
		boolean result = TypeConverter.convert(0, Boolean.class);

		// then
		assertThat(result).isEqualTo(false);
	}

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

	// string to numbers conversion

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

	// number to string conversion

	@Test
	public void shouldConvertStringToString() {
		// given
		String string = "Silence is golden.";

		// when
		String result = TypeConverter.convert(string, String.class);

		// then
		assertThat(result).isEqualTo(string).isSameAs(string);
	}

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
		Hero hero = new Hero();
		hero.setFirstName("Steve");
		hero.setLastName("Rogers");
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
}