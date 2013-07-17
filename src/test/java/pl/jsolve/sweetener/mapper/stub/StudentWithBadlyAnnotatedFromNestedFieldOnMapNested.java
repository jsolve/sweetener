package pl.jsolve.sweetener.mapper.stub;

import pl.jsolve.sweetener.mapper.annotationDriven.annotation.MapNested;
import pl.jsolve.sweetener.mapper.annotationDriven.annotation.MappableTo;
import pl.jsolve.sweetener.tests.stub.person.StudentSnapshot;

@MappableTo(StudentSnapshot.class)
public class StudentWithBadlyAnnotatedFromNestedFieldOnMapNested {

	private static final String NOT_EXISTING_FIELD = "notExistingField";
	public static final String NOT_EXISTING_NESTED_FIELD = "semester.notExistingField";

	@MapNested(fromNested = NOT_EXISTING_FIELD, to = "semester")
	private int semester;

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}
}