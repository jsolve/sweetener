package pl.jsolve.sweetener.mapper.stub;

import pl.jsolve.sweetener.mapper.annotationDriven.annotation.MappableTo;
import pl.jsolve.sweetener.tests.stub.person.StudentSnapshot;

@MappableTo(StudentSnapshot.class)
public class StudentWithMapParsingIntToAnnotationMapping {

	public static final Class<MapParsingIntTo> MAP_PARSING_INT_TO_ANNOTATION_CLASS = MapParsingIntTo.class;

	@MapParsingIntTo("semester")
	private String semester;

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}
}