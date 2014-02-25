package pl.jsolve.sweetener.mapper.collection;

import static org.fest.assertions.Assertions.assertThat;
import static pl.jsolve.sweetener.collection.Collections.newArrayList;
import static pl.jsolve.sweetener.collection.Collections.newHashSet;

import java.util.Collection;

import org.junit.Test;

import pl.jsolve.sweetener.mapper.annotationDriven.AnnotationDrivenMapper;
import pl.jsolve.sweetener.mapper.collection.stub.StudentWithArrayOfGradeSnapshots;
import pl.jsolve.sweetener.mapper.collection.stub.StudentWithArrayOfGrades;
import pl.jsolve.sweetener.mapper.collection.stub.StudentWithArrayOfGradesAsStrings;
import pl.jsolve.sweetener.mapper.collection.stub.StudentWithListOfGradeSnapshots;
import pl.jsolve.sweetener.mapper.collection.stub.StudentWithListOfGrades;
import pl.jsolve.sweetener.mapper.collection.stub.StudentWithListOfGradesAsIntegers;
import pl.jsolve.sweetener.mapper.collection.stub.StudentWithListOfIntegers;
import pl.jsolve.sweetener.mapper.collection.stub.StudentWithSetOfGradesAsStrings;
import pl.jsolve.sweetener.mapper.simple.stub.Grade;
import pl.jsolve.sweetener.mapper.simple.stub.GradeSnapshot;

public class AnnotationDrivenMapperCollectionsTest {

	private static final String CLASS = "class";
	private static final String GRADE_VALUE_PROPERTY = "value";

	@Test
	public void shouldMapStudentWithListOfGradesToStudentWithListOfGradeSnapshots() {
		// given
		StudentWithListOfGrades studentWithGrades = new StudentWithListOfGrades();
		studentWithGrades.setGrades(newArrayList(Grade.valueOf(3), Grade.valueOf(2), Grade.valueOf(4), Grade.valueOf(5)));

		// when
		StudentWithListOfGradeSnapshots studentWithGradeSnapshots = AnnotationDrivenMapper
				.map(studentWithGrades, StudentWithListOfGradeSnapshots.class);

		// then
		assertCollectionHasElementsWithType(studentWithGradeSnapshots.getGradeSnapshots(), GradeSnapshot.class);
		assertThat(studentWithGradeSnapshots.getGradeSnapshots()).onProperty(GRADE_VALUE_PROPERTY).containsExactly(3, 2, 4, 5);
	}

	@Test
	public void shouldMapStudentWithListOfGradeSnapshotsToStudentWithListOfGrades() {
		// given
		StudentWithListOfGradeSnapshots studentWithGradeSnapshots = new StudentWithListOfGradeSnapshots();
		studentWithGradeSnapshots.setGradeSnapshots(newArrayList(GradeSnapshot.valueOf(3), GradeSnapshot.valueOf(2),
				GradeSnapshot.valueOf(4), GradeSnapshot.valueOf(5)));

		// when
		StudentWithListOfGrades studentWithGrades = AnnotationDrivenMapper.map(studentWithGradeSnapshots, StudentWithListOfGrades.class);

		// then
		assertCollectionHasElementsWithType(studentWithGrades.getGrades(), Grade.class);
		assertThat(studentWithGrades.getGrades()).onProperty(GRADE_VALUE_PROPERTY).containsExactly(3, 2, 4, 5);
	}

	private void assertCollectionHasElementsWithType(Collection<?> collection, Class<?> clazz) {
		assertThat(collection).onProperty(CLASS).containsOnly(clazz);
	}

	@Test
	public void shouldMapStudentWithListOfGradesAsIntegersToStudentWithSetOfGradesAsStrings() {
		// given
		StudentWithListOfGradesAsIntegers studentWithGradesAsIntegers = new StudentWithListOfGradesAsIntegers();
		studentWithGradesAsIntegers.setGrades(newArrayList(3, 2, 4, 5));

		// when
		StudentWithSetOfGradesAsStrings studentWithGradesAsStrings = AnnotationDrivenMapper.map(studentWithGradesAsIntegers,
				StudentWithSetOfGradesAsStrings.class);

		// then
		assertThat(studentWithGradesAsStrings.getGrades()).containsOnly("3", "2", "4", "5");
	}

	@Test
	public void shouldMapStudentWithSetOfGradesAsStringsToStudentWithListOfGradesAsIntegers() {
		// given
		StudentWithSetOfGradesAsStrings studentWithGradesAsStrings = new StudentWithSetOfGradesAsStrings();
		studentWithGradesAsStrings.setGrades(newHashSet("3", "2", "4", "5"));

		// when
		StudentWithListOfGradesAsIntegers studentWithGradesAsIntegers = AnnotationDrivenMapper.map(studentWithGradesAsStrings,
				StudentWithListOfGradesAsIntegers.class);

		// then
		assertThat(studentWithGradesAsIntegers.getGrades()).containsOnly(3, 2, 4, 5);
	}

	@Test
	public void shouldMapStudentWithListOfGradesToStudentWithArrayOfGrades() {
		// given
		StudentWithListOfGrades studentWithListOfGrades = new StudentWithListOfGrades();
		studentWithListOfGrades.setGrades(newArrayList(Grade.valueOf(3), Grade.valueOf(2), Grade.valueOf(4), Grade.valueOf(5)));

		// when
		StudentWithArrayOfGrades studentWithArrayOfGrades = AnnotationDrivenMapper.map(studentWithListOfGrades,
				StudentWithArrayOfGrades.class);

		// then
		assertArrayHasElementsWithType(studentWithArrayOfGrades.getGrades(), Grade.class);
		assertThat(studentWithArrayOfGrades.getGrades()).onProperty(GRADE_VALUE_PROPERTY).contains(3, 2, 4, 5);
	}

	@Test
	public void shouldMapStudentWithListOfGradesToStudentWithArrayOfGradeSnapshots() {
		// given
		StudentWithListOfGrades studentWithListOfGrades = new StudentWithListOfGrades();
		studentWithListOfGrades.setGrades(newArrayList(Grade.valueOf(3), Grade.valueOf(2), Grade.valueOf(4), Grade.valueOf(5)));

		// when
		StudentWithArrayOfGradeSnapshots studentWithArrayOfGradeSnapshots = AnnotationDrivenMapper.map(studentWithListOfGrades,
				StudentWithArrayOfGradeSnapshots.class);

		// then
		assertArrayHasElementsWithType(studentWithArrayOfGradeSnapshots.getGradeSnapshots(), GradeSnapshot.class);
		assertThat(studentWithArrayOfGradeSnapshots.getGradeSnapshots()).onProperty(GRADE_VALUE_PROPERTY).contains(3, 2, 4, 5);
	}

	private <T> void assertArrayHasElementsWithType(T[] array, Class<?> clazz) {
		assertThat(array).onProperty(CLASS).containsOnly(clazz);
	}

	@Test
	public void shouldMapStudentWithListOfGradesAsIntegersToStudentWithArrayOfGradesAsStrings() {
		// given
		StudentWithListOfGradesAsIntegers studentWithIntegers = new StudentWithListOfGradesAsIntegers();
		studentWithIntegers.setGrades(newArrayList(3, 2, 4, 5));

		// when
		StudentWithArrayOfGradesAsStrings studentWithStrings = AnnotationDrivenMapper.map(studentWithIntegers,
				StudentWithArrayOfGradesAsStrings.class);

		// then
		assertThat(studentWithStrings.getGrades()).contains("3", "2", "4", "5");
	}

	@Test
	public void shouldStudentWithArrayOfGradesAsStringsToMapStudentWithListOfGradesAsIntegers() {
		// given
		StudentWithArrayOfGradesAsStrings studentWithStrings = new StudentWithArrayOfGradesAsStrings();
		studentWithStrings.setGrades(new String[] { "3", "2", "4", "5" });

		// when
		StudentWithListOfGradesAsIntegers studentWithIntegers = AnnotationDrivenMapper.map(studentWithStrings,
				StudentWithListOfGradesAsIntegers.class);

		// then
		assertThat(studentWithIntegers.getGrades()).contains(3, 2, 4, 5);
	}

	@Test
	public void shouldMapStudentWithListOfGradesAsIntegersToStudentWithListOfIntegers() {
		// given
		StudentWithListOfGradesAsIntegers studentWithGradesAsIntegers = new StudentWithListOfGradesAsIntegers();
		studentWithGradesAsIntegers.setGrades(newArrayList(3, 2, 4, 5));

		// when
		StudentWithListOfIntegers studentWithIntegers = AnnotationDrivenMapper.map(studentWithGradesAsIntegers,
				StudentWithListOfIntegers.class);

		// then
		assertThat(studentWithIntegers.getIntegers()).contains(3, 2, 4, 5);
	}
}