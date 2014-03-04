package pl.jsolve.sweetener.mapper.collection.stub;

import pl.jsolve.sweetener.mapper.simple.stub.GradeSnapshot;

public class StudentWithArrayOfGradeSnapshots {

	private GradeSnapshot[] gradeSnapshots;

	public GradeSnapshot[] getGradeSnapshots() {
		return gradeSnapshots;
	}

	public void setGradeSnapshots(GradeSnapshot[] gradeSnapshots) {
		this.gradeSnapshots = gradeSnapshots;
	}
}