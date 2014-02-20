package pl.jsolve.sweetener.mapper.collection.stub;

import java.util.List;

import pl.jsolve.sweetener.mapper.annotationDriven.annotation.Map;
import pl.jsolve.sweetener.mapper.annotationDriven.annotation.MappableTo;
import pl.jsolve.sweetener.mapper.annotationDriven.annotation.Mappings;

@MappableTo({ StudentWithSetOfGradesAsStrings.class, StudentWithArrayOfGradesAsStrings.class, StudentWithListOfIntegers.class })
public class StudentWithListOfGradesAsIntegers {

	@Mappings({
		@Map(to = "grades", withElementsType = String.class, of = { StudentWithSetOfGradesAsStrings.class,
			StudentWithArrayOfGradesAsStrings.class }),
			@Map(to = "integers", of = StudentWithListOfIntegers.class)
	})
	private List<Integer> grades;

	public List<Integer> getGrades() {
		return grades;
	}

	public void setGrades(List<Integer> grades) {
		this.grades = grades;
	}
}