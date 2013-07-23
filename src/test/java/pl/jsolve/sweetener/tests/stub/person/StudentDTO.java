package pl.jsolve.sweetener.tests.stub.person;


public class StudentDTO {

	private String firstName;
	private String surname;
	private String street;
	private int totalSemesters;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getTotalSemesters() {
		return totalSemesters;
	}

	public void setTotalSemesters(int totalSemesters) {
		this.totalSemesters = totalSemesters;
	}
}