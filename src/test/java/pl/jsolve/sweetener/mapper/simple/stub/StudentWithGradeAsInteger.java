package pl.jsolve.sweetener.mapper.simple.stub;

import pl.jsolve.sweetener.mapper.annotationDriven.annotation.Map;
import pl.jsolve.sweetener.mapper.annotationDriven.annotation.MappableTo;

@MappableTo(StudentWithGradeAsString.class)
public class StudentWithGradeAsInteger {

	@Map(to = "grade", of = StudentWithGradeAsString.class)
	private int grade;

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
}
