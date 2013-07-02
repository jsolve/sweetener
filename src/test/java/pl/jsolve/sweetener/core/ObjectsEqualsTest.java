package pl.jsolve.sweetener.core;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import pl.jsolve.sweetener.collection.data.Address;
import pl.jsolve.sweetener.collection.data.Company;
import pl.jsolve.sweetener.collection.data.Person;

public class ObjectsEqualsTest {

    private Person firstPerson;
    private Person secondPerson;

    @Before
    public void setUp() {
	firstPerson = new Person("John", "Sky", 31, new Company("Jsolve", new Address("street1", "city1")), null);
	secondPerson = new Person("Marry", "Duke", 45, new Company("Oracle", new Address("street1", "Jsolve")), null);

    }

    @Test
    public void shouldCompareTwoNullObjects() {
	// given
	String s1 = null;
	String s2 = null;

	// when
	boolean equals = Objects.equals(s1, s2);

	// then
	assertThat(equals).isTrue();
    }

    @Test
    public void shouldCompareTwoObjectsWhenFirstObjectIsNull() {
	// given
	String s1 = null;
	String s2 = "";

	// when
	boolean equals = Objects.equals(s1, s2);

	// then
	assertThat(equals).isFalse();
    }

    @Test
    public void shouldCompareTwoObjectsWhenSecondObjectIsNull() {
	// given
	String s1 = "";
	String s2 = null;

	// when
	boolean equals = Objects.equals(s1, s2);

	// then
	assertThat(equals).isFalse();
    }

    @Test
    public void shouldCompareTwoNotNullObjects() {
	// given
	String s1 = "a";
	String s2 = "a";

	// when
	boolean equals = Objects.equals(s1, s2);

	// then
	assertThat(equals).isTrue();
    }

    @Test
    public void shouldCompareTwoPerson() {
	// given : prepared two person objects

	// when
	boolean equals = Objects.equals(firstPerson, secondPerson, "company.address.street");

	// then
	assertThat(equals).isTrue();
    }

    @Test
    public void shouldCompareTwoPersonWithNullValue() {
	// given : prepared two person objects

	// when
	boolean equals = Objects.equals(firstPerson, secondPerson, "categoriesOfDrivingLicense");

	// then
	assertThat(equals).isTrue();
    }

    @Test
    public void shouldCompareTwoPersonWithTwoIdenticalPaths() {
	// given : prepared two person objects

	// when
	boolean equals = Objects.equals(firstPerson, "company.address.street", secondPerson, "company.address.street");

	// then
	assertThat(equals).isTrue();
    }

    @Test
    public void shouldCompareTwoPersonWithTwoPaths() {
	// given : prepared two person objects

	// when
	boolean equals = Objects.equals(firstPerson, "company.address.street", secondPerson, "company.address.city");

	// then
	assertThat(equals).isFalse();
    }

    @Test
    public void shouldCompareTwoPersonWithTwoDiferentPaths() {
	// given : prepared two person objects

	// when
	boolean equals = Objects.equals(firstPerson, "company.name", secondPerson, "company.address.city");

	// then
	assertThat(equals).isTrue();
    }

}
