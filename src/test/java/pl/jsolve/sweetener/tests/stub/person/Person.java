package pl.jsolve.sweetener.tests.stub.person;

import pl.jsolve.sweetener.mapper.annotationDriven.annotation.MapExactlyTo;
import pl.jsolve.sweetener.mapper.annotationDriven.annotation.MapNested;
import pl.jsolve.sweetener.mapper.annotationDriven.annotation.NestedMappings;

public class Person {

	@MapExactlyTo("firstName")
	private String firstName;
	@MapExactlyTo("lastName")
	private String lastName;
	@MapExactlyTo("age")
	private int age;
	@NestedMappings({
		@MapNested(fromNested = "city.name", to = "address"),
		@MapNested(fromNested = "city.population", to = "population")
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
