package pl.jsolve.sweetener.mapper.stub;

import pl.jsolve.sweetener.mapper.annotationDriven.annotation.Map;
import pl.jsolve.sweetener.mapper.annotationDriven.annotation.MappableTo;

@MappableTo(StudentWithMapableGradeSnapshot.class)
public class StudentWithMapableGrade {

	@Map(to = "gradeSnapshot")
	private Grade grade;

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}
}