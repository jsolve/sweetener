package pl.jsolve.sweetener.mapper.collection.stub;

import pl.jsolve.sweetener.mapper.annotationdriven.annotation.Map;
import pl.jsolve.sweetener.mapper.annotationdriven.annotation.MappableTo;

@MappableTo(ExamSnapshot.class)
public class Exam {

	@Map
	private final String name;

	public Exam(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}