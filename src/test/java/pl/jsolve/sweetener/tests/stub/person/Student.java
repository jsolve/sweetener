package pl.jsolve.sweetener.tests.stub.person;

import pl.jsolve.sweetener.mapper.annotationDriven.annotation.ExactlyToMappings;
import pl.jsolve.sweetener.mapper.annotationDriven.annotation.MapExactlyTo;
import pl.jsolve.sweetener.mapper.annotationDriven.annotation.MappableTo;

@MappableTo({ StudentSnapshot.class, StudentDTO.class })
public class Student extends Person {

	@ExactlyToMappings({
		@MapExactlyTo(value = "semester", of = StudentSnapshot.class),
		@MapExactlyTo(value = "totalSemesters", of = StudentDTO.class)
	})
	private int semester;

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}
}