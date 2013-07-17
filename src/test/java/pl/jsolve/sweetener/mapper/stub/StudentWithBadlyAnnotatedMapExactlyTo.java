package pl.jsolve.sweetener.mapper.stub;

import pl.jsolve.sweetener.mapper.annotationDriven.annotation.MapExactlyTo;
import pl.jsolve.sweetener.mapper.annotationDriven.annotation.MappableTo;
import pl.jsolve.sweetener.tests.stub.person.StudentSnapshot;

@MappableTo(StudentSnapshot.class)
public class StudentWithBadlyAnnotatedMapExactlyTo {

	public static final String NOT_EXISTING_FIELD = "notExistingField";

	@MapExactlyTo(NOT_EXISTING_FIELD)
	private int semester;

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}
}