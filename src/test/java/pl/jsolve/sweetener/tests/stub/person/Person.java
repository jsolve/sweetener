package pl.jsolve.sweetener.tests.stub.person;

import pl.jsolve.sweetener.mapper.annotation.MapExactlyTo;
import pl.jsolve.sweetener.mapper.annotation.NestedMapping;

public class Person {

	@MapExactlyTo("firstName")
	private String firstName;
	@MapExactlyTo("lastName")
	private String lastName;
	@MapExactlyTo("age")
	private int age;
	@NestedMapping
	private Address address;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}