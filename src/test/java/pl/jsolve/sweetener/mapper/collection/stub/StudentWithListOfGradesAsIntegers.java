package pl.jsolve.sweetener.mapper.collection.stub;

import java.util.List;

import pl.jsolve.sweetener.mapper.annotationdriven.annotation.Map;
import pl.jsolve.sweetener.mapper.annotationdriven.annotation.MappableTo;
import pl.jsolve.sweetener.mapper.annotationdriven.annotation.Mappings;

@MappableTo({ StudentWithSetOfGradesAsStrings.class, StudentWithArrayOfGradesAsStrings.class, StudentWithListOfIntegers.class })
public class StudentWithListOfGradesAsIntegers {

	@Mappings({
		@Map(to = "grades", elementsAs = String.class, of = { StudentWithSetOfGradesAsStrings.class,
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