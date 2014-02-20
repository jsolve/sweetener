package pl.jsolve.sweetener.mapper.collection.stub;

import java.util.List;

import pl.jsolve.sweetener.mapper.annotationDriven.annotation.Map;
import pl.jsolve.sweetener.mapper.annotationDriven.annotation.MappableTo;
import pl.jsolve.sweetener.mapper.annotationDriven.annotation.Mappings;
import pl.jsolve.sweetener.mapper.simple.stub.Grade;
import pl.jsolve.sweetener.mapper.simple.stub.GradeSnapshot;

@MappableTo({ StudentWithListOfGradeSnapshots.class, StudentWithArrayOfGradeSnapshots.class, StudentWithArrayOfGrades.class })
public class StudentWithListOfGrades {

	@Mappings({
		@Map(to = "gradeSnapshots", withElementsType = GradeSnapshot.class, of = { StudentWithListOfGradeSnapshots.class,
			StudentWithArrayOfGradeSnapshots.class }),
			@Map(to = "grades", withElementsType = Grade.class, of = StudentWithArrayOfGrades.class)
	})
	private List<Grade> grades;

	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}
}
