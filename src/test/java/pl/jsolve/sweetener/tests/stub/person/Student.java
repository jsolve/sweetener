package pl.jsolve.sweetener.tests.stub.person;

import pl.jsolve.sweetener.mapper.annotationDriven.annotation.MapExactlyTo;
import pl.jsolve.sweetener.mapper.annotationDriven.annotation.MappableTo;

@MappableTo(StudentSnapshot.class)
public class Student extends Person {

	@MapExactlyTo("semester")
	private int semester;

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}
}