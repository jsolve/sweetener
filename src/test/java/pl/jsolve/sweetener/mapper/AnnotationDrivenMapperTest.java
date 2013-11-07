package pl.jsolve.sweetener.mapper;

import static org.fest.assertions.Assertions.assertThat;
import static pl.jsolve.sweetener.collection.Collections.newArrayList;
import static pl.jsolve.sweetener.collection.Collections.newHashSet;
import static pl.jsolve.sweetener.mapper.stub.StudentWithBadlyAnnotatedFromNestedField.NOT_EXISTING_NESTED_FIELD;
import static pl.jsolve.sweetener.mapper.stub.StudentWithBadlyAnnotatedMapTo.NOT_EXISTING_FIELD;
import static pl.jsolve.sweetener.mapper.stub.StudentWithMapParsingIntToAnnotationMapping.MAP_PARSING_INT_TO_ANNOTATION_CLASS;
import static pl.jsolve.sweetener.tests.assertion.ThrowableAssertions.assertThrowable;
import static pl.jsolve.sweetener.tests.catcher.ExceptionCatcher.tryToCatch;
import static pl.jsolve.sweetener.tests.stub.hero.HeroBuilder.aHero;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import pl.jsolve.sweetener.collection.data.Person;
import pl.jsolve.sweetener.core.Reflections;
import pl.jsolve.sweetener.mapper.annotationDriven.AnnotationDrivenMapper;
import pl.jsolve.sweetener.mapper.annotationDriven.AnnotationMapping;
import pl.jsolve.sweetener.mapper.annotationDriven.exception.MappingException;
import pl.jsolve.sweetener.mapper.stub.Grade;
import pl.jsolve.sweetener.mapper.stub.StudentWithArrays;
import pl.jsolve.sweetener.mapper.stub.StudentWithBadlyAnnotatedFromNestedField;
import pl.jsolve.sweetener.mapper.stub.StudentWithBadlyAnnotatedMapTo;
import pl.jsolve.sweetener.mapper.stub.StudentWithCollections;
import pl.jsolve.sweetener.mapper.stub.StudentWithDates;
import pl.jsolve.sweetener.mapper.stub.StudentWithDatesSnapshot;
import pl.jsolve.sweetener.mapper.stub.StudentWithGradeAsInteger;
import pl.jsolve.sweetener.mapper.stub.StudentWithGradeAsString;
import pl.jsolve.sweetener.mapper.stub.StudentWithMapParsingIntToAnnotationMapping;
import pl.jsolve.sweetener.mapper.stub.StudentWithMapableGrade;
import pl.jsolve.sweetener.mapper.stub.StudentWithMapableGradeSnapshot;
import pl.jsolve.sweetener.tests.catcher.ExceptionalOperation;
import pl.jsolve.sweetener.tests.stub.hero.Hero;
import pl.jsolve.sweetener.tests.stub.hero.HeroDTO;
import pl.jsolve.sweetener.tests.stub.hero.HeroSnapshot;
import pl.jsolve.sweetener.tests.stub.person.Address;
import pl.jsolve.sweetener.tests.stub.person.City;
import pl.jsolve.sweetener.tests.stub.person.FieldOfStudy;
import pl.jsolve.sweetener.tests.stub.person.Student;
import pl.jsolve.sweetener.tests.stub.person.StudentDTO;
import pl.jsolve.sweetener.tests.stub.person.StudentSnapshot;

public class AnnotationDrivenMapperTest {

	private static final String NICKNAME = "ironMan";
	private static final Long ID = 1L;
	private static final Long FIRST_SEMPTEMBER_2008_TIMESTAMP = 1220227200100L;
	private static final Date FIRST_SEMPTEMBER_2008_DATE = new Date(FIRST_SEMPTEMBER_2008_TIMESTAMP);

	@Test
	public void shouldMapHeroToHeroSnapshot() {
		// given
		Hero hero = aHero().withId(ID).withNickname(NICKNAME).build();

		// when
		HeroSnapshot result = AnnotationDrivenMapper.map(hero, HeroSnapshot.class);

		// then
		assertThat(result.getId()).isEqualTo(hero.getId());
		assertThat(result.getName()).isEqualTo(hero.getNickname());
	}

	@Test
	public void shouldMapHeroToHeroDTO() {
		// given
		Hero hero = aHero().withId(ID).withNickname(NICKNAME).build();

		// when
		HeroDTO result = AnnotationDrivenMapper.map(hero, HeroDTO.class);

		// then
		assertThat(result.getId()).isEqualTo(hero.getId());
		assertThat(result.getNickname()).isEqualTo(hero.getNickname());
	}

	@Test
	public void shouldMapHeroSnapshotToHero() {
		// given
		HeroSnapshot heroSnapshot = new HeroSnapshot();
		heroSnapshot.setId(ID);

		// when
		Hero result = AnnotationDrivenMapper.map(heroSnapshot, Hero.class);

		// then
		assertThat(result.getId()).isEqualTo(heroSnapshot.getId());
		assertThat(result.getFirstName()).as("firstName field is not annotated for mapping").isNull();
		assertThat(result.getLastName()).as("lastName field is not annotated for mapping").isNull();
		assertThat(result.getNickname()).as("nickanme field is not annotated for mapping").isNull();
	}

	@Test
	public void shouldThrowExceptionWhenMappingToUnmappableObject() {
		// when
		MappingException caughtException = tryToCatch(MappingException.class, new ExceptionalOperation() {

			@Override
			public void operate() throws Exception {
				AnnotationDrivenMapper.map(new Hero(), Person.class);
			}
		});

		// then
		assertThrowable(caughtException).withMessageContaining(
				Hero.class + " is not mappable to " + Person.class).isThrown();
	}

	@Test
	public void shouldThrowExceptionWhenTargetFieldDoesNotExist() {
		// when
		MappingException caughtException = tryToCatch(MappingException.class, new ExceptionalOperation() {

			@Override
			public void operate() throws Exception {
				AnnotationDrivenMapper.map(new StudentWithBadlyAnnotatedMapTo(), StudentSnapshot.class);
			}
		});

		// then
		assertThrowable(caughtException).withMessageContaining(
				StudentSnapshot.class + " does not contain field '" + NOT_EXISTING_FIELD + "'")
				.isThrown();
	}

	@Test
	public void shouldThrowExceptionWhenNestedSourceFieldDoesNotExist() {
		// when
		MappingException caughtException = tryToCatch(MappingException.class, new ExceptionalOperation() {

			@Override
			public void operate() throws Exception {
				AnnotationDrivenMapper.map(new StudentWithBadlyAnnotatedFromNestedField(), StudentSnapshot.class);
			}
		});

		// then
		assertThrowable(caughtException).withMessageContaining(
				StudentWithBadlyAnnotatedFromNestedField.class + " does not contain field '" + NOT_EXISTING_NESTED_FIELD + "'")
				.isThrown();
	}

	@Test
	public void shouldMapStudentWithCustomAnnotationMapping() {
		// given
		StudentWithMapParsingIntToAnnotationMapping student = new StudentWithMapParsingIntToAnnotationMapping();
		student.setSemester("5");
		AnnotationDrivenMapper.registerAnnotationMapping(new AnnotationMapping() {

			@Override
			public <S, T> void apply(S sourceObject, T targetObject) {
				for (Field field : Reflections.getFieldsAnnotatedBy(sourceObject, MAP_PARSING_INT_TO_ANNOTATION_CLASS)) {
					String targetFieldName = field.getAnnotation(MAP_PARSING_INT_TO_ANNOTATION_CLASS).value();
					String sourceObjectFieldValue = Reflections.getFieldValue(sourceObject, field.getName()).toString();
					Reflections.setFieldValue(targetObject, targetFieldName, Integer.parseInt(sourceObjectFieldValue));
				}
			}
		});

		// when
		StudentSnapshot studentSnapshot = AnnotationDrivenMapper.map(student, StudentSnapshot.class);

		// then
		assertThat(studentSnapshot.getSemester()).isEqualTo(Integer.parseInt(student.getSemester()));
	}

	@Test
	public void shouldMapStudentToStudentSnapshot() {
		// given
		Student student = prepareStudent();

		// when
		StudentSnapshot studentSnapshot = AnnotationDrivenMapper.map(student, StudentSnapshot.class);

		// then
		assertThat(studentSnapshot.getFirstName()).isEqualTo(student.getFirstName());
		assertThat(studentSnapshot.getLastName()).isEqualTo(student.getLastName());
		assertThat(studentSnapshot.getSemester()).isEqualTo(student.getSemester());
		assertThat(studentSnapshot.getAge()).isEqualTo(student.getAge());
		assertThat(studentSnapshot.getStreet()).isEqualTo(student.getAddress().getStreet());
		assertThat(studentSnapshot.getAddress()).isEqualTo(student.getAddress().getCity().getName());
		assertThat(studentSnapshot.getPopulation()).isEqualTo(student.getAddress().getCity().getPopulation());
		assertThat(studentSnapshot.getFieldOfStudy()).isEqualTo(String.valueOf(student.getFieldOfStudy()));
	}

	@Test
	public void shouldMapStudentToStudentDTO() {
		// given
		Student student = prepareStudent();

		// when
		StudentDTO studentDTO = AnnotationDrivenMapper.map(student, StudentDTO.class);

		// then
		assertThat(studentDTO.getFirstName()).isEqualTo(student.getFirstName());
		assertThat(studentDTO.getSurname()).isEqualTo(student.getLastName());
		assertThat(studentDTO.getStreet()).isEqualTo(student.getAddress().getStreet());
		assertThat(studentDTO.getTotalSemesters()).isEqualTo(student.getSemester());
	}

	private Student prepareStudent() {
		Student student = new Student();
		student.setFirstName("John");
		student.setLastName("White");
		student.setSemester(3);
		student.setAge(20);
		student.setFieldOfStudy(FieldOfStudy.COMPUTER_SCIENCE);
		Address address = new Address();
		address.setStreet("street1");
		address.setCity(new City("New York", 8336697L));
		student.setAddress(address);
		return student;
	}

	@Test
	public void shouldMapStudentSnapshotToStudent() {
		// given
		StudentSnapshot studentSnapshot = prepareStudentSnapshot();

		// when
		Student student = AnnotationDrivenMapper.map(studentSnapshot, Student.class);

		// then
		assertThat(student.getFirstName()).isEqualTo(studentSnapshot.getFirstName());
		assertThat(student.getLastName()).isEqualTo(studentSnapshot.getLastName());
		assertThat(student.getAge()).isEqualTo(studentSnapshot.getAge());
		assertThat(student.getSemester()).isEqualTo(studentSnapshot.getSemester());
		assertThat(student.getAddress().getCity().getName()).isEqualTo(studentSnapshot.getAddress());
		assertThat(student.getAddress().getStreet()).isEqualTo(studentSnapshot.getStreet());
		assertThat(student.getAddress().getCity().getPopulation()).isEqualTo(studentSnapshot.getPopulation());
	}

	private StudentSnapshot prepareStudentSnapshot() {
		StudentSnapshot studentSnapshot = new StudentSnapshot();
		studentSnapshot.setFirstName("Steve");
		studentSnapshot.setLastName("Rogers");
		studentSnapshot.setAge(31);
		studentSnapshot.setSemester(4);
		studentSnapshot.setAddress("Los Angeles");
		studentSnapshot.setStreet("Sunset Boulevard");
		studentSnapshot.setPopulation(4022450L);
		return studentSnapshot;
	}

	@Test
	public void shouldMapStudentWithMappableGrade() {
		// given
		StudentWithMapableGrade studentWithMapableGrade = new StudentWithMapableGrade();
		studentWithMapableGrade.setGrade(Grade.valueOf(5));

		// when
		StudentWithMapableGradeSnapshot studentWithMapableGradeSnapshot = AnnotationDrivenMapper.map(studentWithMapableGrade,
				StudentWithMapableGradeSnapshot.class);

		// then
		assertThat(studentWithMapableGradeSnapshot.getGrade()).isNotSameAs(studentWithMapableGrade.getGrade());
		assertThat(studentWithMapableGradeSnapshot.getGrade().getValue()).isEqualTo(studentWithMapableGrade.getGrade().getValue());
	}

	@Test
	public void shouldMapStudentWithGradeAsIntegerToStudentWithGradeAsString() {
		// given
		StudentWithGradeAsInteger studentWithGradeAsInteger = new StudentWithGradeAsInteger();
		studentWithGradeAsInteger.setGrade(5);

		// when
		StudentWithGradeAsString studentWithGradeAsString = AnnotationDrivenMapper.map(studentWithGradeAsInteger,
				StudentWithGradeAsString.class);

		// then
		assertThat(studentWithGradeAsString.getGrade()).isEqualTo(String.valueOf(studentWithGradeAsInteger.getGrade()));
	}

	@Test
	public void shouldMapStudentWithGradeAsStringToStudentWithGradeAsInteger() {
		// given
		StudentWithGradeAsString studentWithGradeAsString = new StudentWithGradeAsString();
		studentWithGradeAsString.setGrade("5");

		// when
		StudentWithGradeAsInteger studentWithGradeAsInteger = AnnotationDrivenMapper.map(studentWithGradeAsString,
				StudentWithGradeAsInteger.class);

		// then
		assertThat(studentWithGradeAsInteger.getGrade()).isEqualTo(Integer.parseInt(studentWithGradeAsString.getGrade()));
	}

	@Test
	public void shouldMapStudentWithCollectionsToStudentWithArrays() {
		// given
		StudentWithCollections studentWithCollections = new StudentWithCollections();
		studentWithCollections.setGrades(newArrayList(3, 4));
		studentWithCollections.setSubjects(newHashSet("Phisics, Math"));

		// when
		StudentWithArrays studentWithArrays = AnnotationDrivenMapper.map(studentWithCollections, StudentWithArrays.class);

		// then
		assertThat(studentWithArrays.getGrades()).containsOnly(3, 4);
		assertThat(studentWithArrays.getSubjects()).containsOnly("Phisics, Math");
	}

	@Test
	public void shouldMapStudentWithArraysToStudentWithCollections() {
		// given
		StudentWithArrays studentWithArrays = new StudentWithArrays();
		studentWithArrays.setGrades(new Integer[] { 4, 5 });
		studentWithArrays.setSubjects(new String[] { "Phisics", "Math" });

		// when
		StudentWithCollections studentWithCollections = AnnotationDrivenMapper.map(studentWithArrays, StudentWithCollections.class);

		// then
		assertThat(studentWithCollections.getGrades()).containsOnly(4, 5);
		assertThat(studentWithCollections.getSubjects()).containsOnly("Phisics", "Math");
	}

	@Test
	public void shouldMapStudentWithDatesToItsSnapshot() {
		StudentWithDates studentWithDates = new StudentWithDates();
		studentWithDates.setDateAsLong(FIRST_SEMPTEMBER_2008_TIMESTAMP);
		studentWithDates.setDate(FIRST_SEMPTEMBER_2008_DATE);

		// when
		StudentWithDatesSnapshot studentWithDatesSnapshot = AnnotationDrivenMapper.map(studentWithDates, StudentWithDatesSnapshot.class);

		// then
		assertThat(studentWithDatesSnapshot.getCalendar().getTime()).isEqualTo(studentWithDates.getDate());
		assertThat(studentWithDatesSnapshot.getDate().getTime()).isEqualTo(studentWithDates.getDateAsLong());
	}

	@Test
	public void shouldConvertStudentWithDatesSnapshotToStudentWithDates() {
		// given
		Calendar calendar = Calendar.getInstance();
		StudentWithDatesSnapshot studentWithDatesSnapshot = new StudentWithDatesSnapshot();
		studentWithDatesSnapshot.setCalendar(calendar);
		studentWithDatesSnapshot.setDate(calendar.getTime());

		// when
		StudentWithDates studentWithDates = AnnotationDrivenMapper.map(studentWithDatesSnapshot, StudentWithDates.class);

		// then
		assertThat(studentWithDates.getDate()).isEqualTo(studentWithDatesSnapshot.getCalendar().getTime());
		assertThat(studentWithDates.getDateAsLong()).isEqualTo(studentWithDatesSnapshot.getDate().getTime());
	}
}