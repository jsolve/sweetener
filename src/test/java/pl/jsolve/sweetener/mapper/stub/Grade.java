package pl.jsolve.sweetener.mapper.stub;

import pl.jsolve.sweetener.mapper.annotationDriven.annotation.Map;
import pl.jsolve.sweetener.mapper.annotationDriven.annotation.MappableTo;

@MappableTo(GradeSnapshot.class)
public class Grade {

	@Map
	private final int grade;

	public Grade(int grade) {
		this.grade = grade;
	}

	public static Grade valueOf(int grade) {
		return new Grade(grade);
	}

	public int getValue() {
		return grade;
	}
}