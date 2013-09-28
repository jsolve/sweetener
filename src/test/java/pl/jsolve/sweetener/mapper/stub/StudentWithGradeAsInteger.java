package pl.jsolve.sweetener.mapper.stub;

import pl.jsolve.sweetener.mapper.annotationDriven.annotation.Map;
import pl.jsolve.sweetener.mapper.annotationDriven.annotation.MappableTo;

@MappableTo(StudentWithGradeAsString.class)
public class StudentWithGradeAsInteger {

	@Map(to = "grade", of = StudentWithGradeAsString.class)
	private Integer grade;

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}
}
