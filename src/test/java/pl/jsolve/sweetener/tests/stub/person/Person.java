package pl.jsolve.sweetener.tests.stub.person;

import pl.jsolve.sweetener.mapper.annotationDriven.annotation.ExactlyToMappings;
import pl.jsolve.sweetener.mapper.annotationDriven.annotation.MapExactlyTo;
import pl.jsolve.sweetener.mapper.annotationDriven.annotation.MapNested;
import pl.jsolve.sweetener.mapper.annotationDriven.annotation.NestedMappings;

public class Person {

	@MapExactlyTo("firstName")
	protected String firstName;
	@ExactlyToMappings({
		@MapExactlyTo(value = "lastName", of = StudentSnapshot.class),
		@MapExactlyTo(value = "surname", of = StudentDTO.class)
	})
	protected String lastName;
	@MapExactlyTo(value = "age", of = StudentSnapshot.class)
	private int age;
	@NestedMappings({
		@MapNested(fromNested = "city.name", to = "address", of = StudentSnapshot.class),
		@MapNested(fromNested = "city.population", to = "population", of = StudentSnapshot.class),
	})
	@MapNested(fromNested = "street", to = "street")
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
