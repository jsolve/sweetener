package pl.jsolve.sweetener.collection;

import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import pl.jsolve.sweetener.collection.data.Person;
import pl.jsolve.sweetener.exception.InvalidArgumentException;
import pl.jsolve.sweetener.tests.stub.person.Department;
import pl.jsolve.sweetener.tests.stub.person.FieldOfStudy;
import pl.jsolve.sweetener.tests.stub.person.Student;

public class CollectionsTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	private List<String> values;

	@Before
	public void setUp() {
		values = new ArrayList<>();
		values.add("A");
		values.add("B");
		values.add("C");
		values.add("D");
		values.add("E");
		values.add("F");
		values.add("G");
		values.add("H");
		values.add("I");
	}

	@Test
	public void shouldThrowExceptionWhenFirstValueIsNegative() {
		// given
		int from = -1;
		int to = 6;

		// then
		expectedException.expect(InvalidArgumentException.class);
		expectedException.expectMessage("The 'From' value cannot be negative");

		// when
		Collections.truncate(values, from, to);
	}

	@Test
	public void shouldThrowExceptionWhenFirstParameterIsGreatherThatNumberOfElements() {
		// given
		int from = 15;
		int to = 30;

		// then
		expectedException.expect(InvalidArgumentException.class);
		expectedException.expectMessage("The 'From' value cannot be greater than size of collection");

		// when
		Collections.truncate(values, from, to);
	}

	@Test
	public void shouldThrowExceptionWhenFirstValueIsGreatherThenSecondParameter() {
		// given
		int from = 5;
		int to = 1;

		// then
		expectedException.expect(InvalidArgumentException.class);
		expectedException.expectMessage("The 'From' value cannot be greater than the 'to' value");

		// when
		Collections.truncate(values, from, to);
	}

	@Test
	public void shouldThrowExceptionWhenSecondParameterIsGreaterThanNumberOfElements() {
		// given
		int from = 5;
		int to = 11;

		// then
		expectedException.expect(InvalidArgumentException.class);
		expectedException.expectMessage("The 'To' value cannot be greater than size of collection");

		// when
		Collections.truncate(values, from, to);
	}

	@Test
	public void shouldTruncateCollection() {
		// given
		int from = 1;
		int to = 5;

		// when
		List<String> truncatedCollection = Collections.truncate(values, from, to);

		// then
		assertThat(truncatedCollection).hasSize(5);
		assertThat(truncatedCollection).containsSequence("B", "C", "D", "E", "F");
	}

	@Test
	public void shouldTruncateCollection2() {
		// given
		int from = 0;
		int to = 8;

		// when
		List<String> truncatedCollection = Collections.truncate(values, from, to);

		// then
		assertThat(truncatedCollection).hasSize(9);
		assertThat(truncatedCollection).containsSequence("B", "C", "D", "E", "F", "G", "H", "I");
	}

	@Test
	public void shouldTruncateCollectionWhenSecondParameterIsNegative() {
		// given
		int from = 0;
		int to = -1;

		// when
		List<String> truncatedCollection = Collections.truncate(values, from, to);

		// then
		assertThat(truncatedCollection).hasSize(9);
		assertThat(truncatedCollection).containsSequence("A", "B", "C", "D", "E", "F", "G", "H", "I");
	}

	@Test
	public void shouldTruncateCollectionWhenSecondParameterIsNegative2() {
		// given
		int from = 0;
		int to = -8;

		// when
		List<String> truncatedCollection = Collections.truncate(values, from, to);

		// then
		assertThat(truncatedCollection).hasSize(2);
		assertThat(truncatedCollection).containsSequence("A", "B");
	}

	@Test
	public void shouldPaginateTheCollection() {
		// given
		int page = 0;
		int resultsPerPage = 3;

		// when
		Pagination<String> pagination = Collections.paginate(values, page, resultsPerPage);

		// then
		assertThat(pagination.getPage()).isEqualTo(page);
		assertThat(pagination.getResultsPerPage()).isEqualTo(resultsPerPage);
		assertThat(pagination.getTotalElements()).isEqualTo(9);
		assertThat(pagination.getNumberOfPages()).isEqualTo(3);
		assertThat(pagination.getElementsOfPage()).containsOnly("A", "B", "C");
	}

	@Test
	public void shouldPaginateTheCollection2() {
		// given
		int page = 2;
		int resultsPerPage = 4;

		// when
		Pagination<String> pagination = Collections.paginate(values, page, resultsPerPage);

		// then
		assertThat(pagination.getPage()).isEqualTo(page);
		assertThat(pagination.getResultsPerPage()).isEqualTo(resultsPerPage);
		assertThat(pagination.getTotalElements()).isEqualTo(9);
		assertThat(pagination.getNumberOfPages()).isEqualTo(3);
		assertThat(pagination.getElementsOfPage()).containsOnly("I");
	}

	@Test
	public void shouldPaginateTheCollection3() {
		// given
		int page = 0;
		int resultsPerPage = 40;

		// when
		Pagination<String> pagination = Collections.paginate(values, page, resultsPerPage);

		// then
		assertThat(pagination.getPage()).isEqualTo(page);
		assertThat(pagination.getResultsPerPage()).isEqualTo(resultsPerPage);
		assertThat(pagination.getTotalElements()).isEqualTo(9);
		assertThat(pagination.getNumberOfPages()).isEqualTo(1);
		assertThat(pagination.getElementsOfPage()).containsOnly("A", "B", "C", "D", "E", "F", "G", "H", "I");
	}

	@Test
	public void shouldChopCollection() {
		// given
		int resultsPerPage = 40;

		// when
		ChoppedElements<String> choppedElements = Collections.chopElements(values, resultsPerPage);

		// then
		assertThat(choppedElements.getPage()).isEqualTo(0);
		assertThat(choppedElements.getResultsPerPage()).isEqualTo(resultsPerPage);
		assertThat(choppedElements.getTotalElements()).isEqualTo(9);
		assertThat(choppedElements.getNumberOfPages()).isEqualTo(1);
		assertThat(choppedElements.getListOfPages()).hasSize(1);
		assertThat(choppedElements.getElementsOfPage()).containsOnly("A", "B", "C", "D", "E", "F", "G", "H", "I");
	}

	@Test
	public void shouldChopCollection2() {
		// given
		int resultsPerPage = 3;

		// when
		ChoppedElements<String> choppedElements = Collections.chopElements(values, resultsPerPage);

		// then
		assertThat(choppedElements.getPage()).isEqualTo(0);
		assertThat(choppedElements.getResultsPerPage()).isEqualTo(resultsPerPage);
		assertThat(choppedElements.getTotalElements()).isEqualTo(9);
		assertThat(choppedElements.getNumberOfPages()).isEqualTo(3);
		assertThat(choppedElements.getListOfPages()).hasSize(3);
		assertThat(choppedElements.getElementsOfPage()).containsOnly("A", "B", "C");
	}

	@Test
	public void shouldChopCollection3() {
		// given
		int resultsPerPage = 3;

		// when
		ChoppedElements<String> choppedElements = Collections.chopElements(values, resultsPerPage);
		choppedElements.setPage(2);

		// then
		assertThat(choppedElements.getPage()).isEqualTo(2);
		assertThat(choppedElements.getResultsPerPage()).isEqualTo(resultsPerPage);
		assertThat(choppedElements.getTotalElements()).isEqualTo(9);
		assertThat(choppedElements.getNumberOfPages()).isEqualTo(3);
		assertThat(choppedElements.getListOfPages()).hasSize(3);
		assertThat(choppedElements.getElementsOfPage()).containsOnly("G", "H", "I");
	}

	@Test
	public void shouldChopCollection4() {
		// given
		int resultsPerPage = 4;

		// when
		ChoppedElements<String> choppedElements = Collections.chopElements(values, resultsPerPage);
		choppedElements.setPage(2);

		// then
		assertThat(choppedElements.getPage()).isEqualTo(2);
		assertThat(choppedElements.getResultsPerPage()).isEqualTo(resultsPerPage);
		assertThat(choppedElements.getTotalElements()).isEqualTo(9);
		assertThat(choppedElements.getNumberOfPages()).isEqualTo(3);
		assertThat(choppedElements.getListOfPages()).hasSize(3);
		assertThat(choppedElements.getElementsOfPage()).containsOnly("I");
	}

	@Test
	public void shouldChopCollection5() {
		// given
		int resultsPerPage = 4;

		// when
		ChoppedElements<String> choppedElements = Collections.chopElements(values, resultsPerPage);

		// then
		assertThat(choppedElements.getPage()).isEqualTo(0);
		assertThat(choppedElements.getResultsPerPage()).isEqualTo(resultsPerPage);
		assertThat(choppedElements.getTotalElements()).isEqualTo(9);
		assertThat(choppedElements.getNumberOfPages()).isEqualTo(3);
		assertThat(choppedElements.getListOfPages()).hasSize(3);

		// first page
		assertThat(choppedElements.getElementsOfPage()).containsOnly("A", "B", "C", "D");

		choppedElements.nextPage();

		// second page
		assertThat(choppedElements.getElementsOfPage()).containsOnly("E", "F", "G", "H");

		choppedElements.nextPage();

		// third page
		assertThat(choppedElements.getElementsOfPage()).containsOnly("I");
	}

	@Test
	public void shouldReturnGroups() {
		// given
		List<Person> people = new ArrayList<>();
		people.add(new Person("John", "Deep", 23, null, null));
		people.add(new Person("Marry", "Deep", 32, null, null));
		people.add(new Person("John", "Knee", 37, null, null));

		// when
		Map<GroupKey, List<Person>> groups = Collections.group(people, "lastName");

		// then
		assertThat(groups).hasSize(2);
		assertThat(groups.get(new GroupKey("Knee"))).onProperty("name").contains("John");
		assertThat(groups.get(new GroupKey("Deep"))).onProperty("name").contains("John", "Marry");
	}

	@Test
	public void shouldReturnGroupsForMultiKey() {
		// given
		List<Student> students = new ArrayList<>();
		students.add(new Student("John", "Deep", 3, FieldOfStudy.MATHS, Department.AEI));
		students.add(new Student("Marry", "Duke", 3, FieldOfStudy.BIOINFORMATICS, Department.AEI));
		students.add(new Student("John", "Knee", 3, FieldOfStudy.BIOINFORMATICS, Department.AEI));
		students.add(new Student("Peter", "Hunt", 5, FieldOfStudy.BIOINFORMATICS, Department.MT));
		students.add(new Student("Lucas", "Sky", 7, FieldOfStudy.COMPUTER_SCIENCE, Department.AEI));

		// when
		Map<GroupKey, List<Student>> groups = Collections.group(students, "semester", "fieldOfStudy", "department");

		// then
		assertThat(groups.keySet()).containsOnly(new GroupKey(3, FieldOfStudy.MATHS, Department.AEI),
				new GroupKey(3, FieldOfStudy.BIOINFORMATICS, Department.AEI), new GroupKey(5, FieldOfStudy.BIOINFORMATICS, Department.MT),
				new GroupKey(7, FieldOfStudy.COMPUTER_SCIENCE, Department.AEI));
		assertThat(groups.get(new GroupKey(3, FieldOfStudy.MATHS, Department.AEI))).onProperty("lastName").contains("Deep");
		assertThat(groups.get(new GroupKey(3, FieldOfStudy.BIOINFORMATICS, Department.AEI))).onProperty("lastName").contains("Duke", "Knee");
		assertThat(groups.get(new GroupKey(5, FieldOfStudy.BIOINFORMATICS, Department.MT))).onProperty("lastName").contains("Hunt");
		assertThat(groups.get(new GroupKey(7, FieldOfStudy.COMPUTER_SCIENCE, Department.AEI))).onProperty("lastName").contains("Sky");
	}

	@Test
	public void shouldReturnDuplicates() {
		// given
		List<Person> people = new ArrayList<>();
		people.add(new Person("John", "Deep", 23, null, null));
		people.add(new Person("Marry", "Deep", 32, null, null));
		people.add(new Person("John", "Knee", 37, null, null));

		// when
		Map<GroupKey, List<Person>> duplicates = Collections.duplicates(people, "lastName");

		// then
		assertThat(duplicates).hasSize(1);
		assertThat(duplicates.get(new GroupKey("Deep"))).onProperty("name").contains("John", "Marry");
	}

	@Test
	public void shouldReturnUniques() {
		// given
		List<Person> people = new ArrayList<>();
		people.add(new Person("John", "Deep", 23, null, null));
		people.add(new Person("Marry", "Deep", 32, null, null));
		people.add(new Person("John", "Knee", 37, null, null));

		// when
		List<Person> uniques = Collections.uniques(people, "lastName");

		// then
		assertThat(uniques).hasSize(1);
		assertThat(uniques).onProperty("name").contains("John");
	}
}