package pl.jsolve.sweetener.tests.stub.person;

import pl.jsolve.sweetener.mapper.annotationDriven.annotation.Map;
import pl.jsolve.sweetener.mapper.annotationDriven.annotation.MappableTo;

@MappableTo(Student.class)
public class StudentSnapshot {

	@Map(to = "firstName")
	private String firstName;
	@Map(to = "lastName")
	private String lastName;
	@Map(to = "age")
	private int age;
	@Map(to = "address.city.name")
	private String address;
	@Map(to = "semester")
	private int semester;
	@Map(to = "address.city.population")
	private long population;
	@Map(to = "address.street")
	private String street;
	private String fieldOfStudy;

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

	public String getFieldOfStudy() {
		return fieldOfStudy;
	}

	public void setFieldOfStudy(String fieldOfStudy) {
		this.fieldOfStudy = fieldOfStudy;
	}
}