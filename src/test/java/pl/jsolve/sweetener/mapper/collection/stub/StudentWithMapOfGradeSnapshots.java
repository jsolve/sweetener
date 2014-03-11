package pl.jsolve.sweetener.mapper.collection.stub;

import java.util.Map;

import pl.jsolve.sweetener.mapper.simple.stub.GradeSnapshot;

public class StudentWithMapOfGradeSnapshots {

	private Map<ExamSnapshot, GradeSnapshot> gradeSnapshots;

	public Map<ExamSnapshot, GradeSnapshot> getGradeSnapshots() {
		return gradeSnapshots;
	}

	public void setGradeSnapshots(Map<ExamSnapshot, GradeSnapshot> gradeSnapshots) {
		this.gradeSnapshots = gradeSnapshots;
	}
}