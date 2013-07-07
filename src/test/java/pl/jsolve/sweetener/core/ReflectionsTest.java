package pl.jsolve.sweetener.core;

import static org.fest.assertions.Assertions.assertThat;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.Test;

import pl.jsolve.sweetener.collection.data.Person;

public class ReflectionsTest {

    private static final String NAME = "John";
    private static final String COMPANY_NAME = "Jsolve";
    
    @Test
    public void shouldSetValueToObject() {
	// given
	Person person = new Person();

	// when
	Reflections.setFieldValue(person, "name", NAME);

	// then
	assertThat(person.getName()).isEqualTo(NAME);
    }
    
    @Test
    public void shouldSetValueToNestedObject() {
	// given
	Person person = new Person();

	// when
	Reflections.setFieldValue(person, "company.name", COMPANY_NAME);

	// then
	assertThat(person.getCompany().getName()).isEqualTo(COMPANY_NAME);
    }
    
    @Test
    public void shouldGetAllClassesForGivenObject() {
	// given
	Person person = new Person();
	
	// when
	List<Class<?>> classes = Reflections.getClasses(person);

	// then
	assertThat(classes).hasSize(2);
	assertThat(classes).containsSequence(Person.class, Object.class);
    }
    
    @Test
    public void shouldGetAllFieldsForGivenObject() {
	// given
	Person person = new Person();
	
	// when
	 List<Field> fields = Reflections.getFields(person);

	// then
	assertThat(fields).hasSize(5);
    }
    
}
