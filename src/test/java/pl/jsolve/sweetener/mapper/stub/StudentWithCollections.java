package pl.jsolve.sweetener.mapper.stub;

import java.util.List;
import java.util.Set;

import pl.jsolve.sweetener.mapper.annotationDriven.annotation.Map;
import pl.jsolve.sweetener.mapper.annotationDriven.annotation.MappableTo;

@MappableTo(StudentWithCollectionsSnapshot.class)
public class StudentWithCollections {

	@Map
	private List<Integer> grades;
	@Map
	private Set<String> subjects;

	public List<Integer> getGrades() {
		return grades;
	}

	public void setGrades(List<Integer> grades) {
		this.grades = grades;
	}

	public Set<String> getSubjects() {
		return subjects;
	}

	public void setSubjects(Set<String> subjects) {
		this.subjects = subjects;
	}
}
