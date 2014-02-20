package pl.jsolve.sweetener.mapper.collection.stub;

import pl.jsolve.sweetener.mapper.annotationDriven.annotation.Map;
import pl.jsolve.sweetener.mapper.annotationDriven.annotation.MappableTo;

@MappableTo(StudentWithListOfGradesAsIntegers.class)
public class StudentWithArrayOfGradesAsStrings {

	@Map(withElementsType = Integer.class)
	private String[] grades;

	public String[] getGrades() {
		return grades;
	}

	public void setGrades(String[] grades) {
		this.grades = grades;
	}
}