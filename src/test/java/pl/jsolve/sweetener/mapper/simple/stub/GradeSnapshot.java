package pl.jsolve.sweetener.mapper.simple.stub;

import pl.jsolve.sweetener.mapper.annotationdriven.annotation.Map;
import pl.jsolve.sweetener.mapper.annotationdriven.annotation.MappableTo;

@MappableTo(Grade.class)
public class GradeSnapshot {

	@Map
	private int grade;

	public GradeSnapshot() {
	}

	private GradeSnapshot(int grade) {
		this.grade = grade;
	}

	public static GradeSnapshot valueOf(int grade) {
		return new GradeSnapshot(grade);
	}

	public int getValue() {
		return grade;
	}
}