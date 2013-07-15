package pl.jsolve.sweetener.tests.stub.person;

import pl.jsolve.sweetener.mapper.annotationDriven.annotation.MapExactlyTo;
import pl.jsolve.sweetener.mapper.annotationDriven.annotation.MappableTo;

@MappableTo(Student.class)
public class StudentSnapshot {

	@MapExactlyTo("firstName")
	private String firstName;
	@MapExactlyTo("lastName")
	private String lastName;
	@MapExactlyTo("age")
	private int age;
	@MapExactlyTo("address.city.name")
	private String address;
	@MapExactlyTo("semester")
	private int semester;
	@MapExactlyTo("address.city.population")
	private long population;
	@MapExactlyTo("address.street")
	private String street;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public long getPopulation() {
		return population;
	}

	public void setPopulation(long population) {
		this.population = population;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

}
