package pl.jsolve.sweetener.mapper.collection.stub;

import java.util.List;

import pl.jsolve.sweetener.mapper.annotationDriven.annotation.Map;
import pl.jsolve.sweetener.mapper.annotationDriven.annotation.MappableTo;
import pl.jsolve.sweetener.mapper.simple.stub.Grade;
import pl.jsolve.sweetener.mapper.simple.stub.GradeSnapshot;

@MappableTo(StudentWithListOfGrades.class)
public class StudentWithListOfGradeSnapshots {

	@Map(to = "grades", elementsAs = Grade.class)
	private List<GradeSnapshot> gradeSnapshots;

	public List<GradeSnapshot> getGradeSnapshots() {
		return gradeSnapshots;
	}

	public void setGradeSnapshots(List<GradeSnapshot> gradeSnapshots) {
		this.gradeSnapshots = gradeSnapshots;
	}
}