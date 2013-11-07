package pl.jsolve.sweetener.tests.stub.person;

import pl.jsolve.sweetener.mapper.annotationDriven.annotation.Map;
import pl.jsolve.sweetener.mapper.annotationDriven.annotation.Mappings;

public class Person {

	@Map
	protected String firstName;

	@Mappings({
		@Map(to = "lastName", of = StudentSnapshot.class),
		@Map(to = "surname", of = StudentDTO.class)
	})
	protected String lastName;

	@Map(of = StudentSnapshot.class)
	private int age;

	@Mappings({
		@Map(fromNested = "city.name", to = "address", of = StudentSnapshot.class),
		@Map(fromNested = "city.population", to = "population", of = StudentSnapshot.class),
		@Map(fromNested = "street", to = "street", of = { StudentSnapshot.class, StudentDTO.class })
	})
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