package pl.jsolve.sweetener.tests.stub.person;

import pl.jsolve.sweetener.mapper.annotationDriven.annotation.ExactlyToMappings;
import pl.jsolve.sweetener.mapper.annotationDriven.annotation.MapExactlyTo;
import pl.jsolve.sweetener.mapper.annotationDriven.annotation.MappableTo;

@MappableTo({ StudentSnapshot.class, StudentDTO.class })
public class Student extends Person {

	@ExactlyToMappings({
			@MapExactlyTo(value = "semester", of = StudentSnapshot.class),
			@MapExactlyTo(value = "totalSemesters", of = StudentDTO.class) })
	private int semester;

	private FieldOfStudy fieldOfStudy;

	private Department department;

	public Student() {
	}

	public Student(String firstName, String lastName, int semester, FieldOfStudy fieldOfStudy, Department department) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.semester = semester;
		this.fieldOfStudy = fieldOfStudy;
		this.department = department;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public FieldOfStudy getFieldOfStudy() {
		return fieldOfStudy;
	}

	public void setFieldOfStudy(FieldOfStudy fieldOfStudy) {
		this.fieldOfStudy = fieldOfStudy;
	}

}