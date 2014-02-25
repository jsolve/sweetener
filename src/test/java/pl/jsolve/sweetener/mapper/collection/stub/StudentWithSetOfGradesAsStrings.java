package pl.jsolve.sweetener.mapper.collection.stub;

import java.util.Set;

import pl.jsolve.sweetener.mapper.annotationDriven.annotation.Map;
import pl.jsolve.sweetener.mapper.annotationDriven.annotation.MappableTo;

@MappableTo(StudentWithListOfGradesAsIntegers.class)
public class StudentWithSetOfGradesAsStrings {

	@Map(to = "grades", elementsAs = Integer.class)
	private Set<String> grades;

	public Set<String> getGrades() {
		return grades;
	}

	public void setGrades(Set<String> grades) {
		this.grades = grades;
	}
}