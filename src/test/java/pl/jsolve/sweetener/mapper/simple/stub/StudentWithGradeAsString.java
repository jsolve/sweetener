package pl.jsolve.sweetener.mapper.simple.stub;

import pl.jsolve.sweetener.mapper.annotationDriven.annotation.Map;
import pl.jsolve.sweetener.mapper.annotationDriven.annotation.MappableTo;

@MappableTo(StudentWithGradeAsInteger.class)
public class StudentWithGradeAsString {

	@Map
	private String grade;

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
}