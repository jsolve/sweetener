package pl.jsolve.sweetener.mapper.simple.stub;

import pl.jsolve.sweetener.mapper.annotationdriven.annotation.Map;
import pl.jsolve.sweetener.mapper.annotationdriven.annotation.MappableTo;

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
