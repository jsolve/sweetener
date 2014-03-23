package pl.jsolve.sweetener.mapper.collection.stub;

import pl.jsolve.sweetener.mapper.annotationdriven.annotation.Map;
import pl.jsolve.sweetener.mapper.annotationdriven.annotation.MappableTo;
import pl.jsolve.sweetener.mapper.simple.stub.Grade;
import pl.jsolve.sweetener.mapper.simple.stub.GradeSnapshot;

@MappableTo(StudentWithMapOfGradeSnapshots.class)
public class StudentWithMapOfGrades {

	@Map(to = "gradeSnapshots", keysAs = ExamSnapshot.class, valuesAs = GradeSnapshot.class)
	private java.util.Map<Exam, Grade> grades;

	public java.util.Map<Exam, Grade> getGrades() {
		return grades;
	}

	public void setGrades(java.util.Map<Exam, Grade> grades) {
		this.grades = grades;
	}
}