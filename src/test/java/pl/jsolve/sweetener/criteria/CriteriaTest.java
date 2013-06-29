package pl.jsolve.sweetener.criteria;

import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import pl.jsolve.sweetener.collection.Collections;
import pl.jsolve.sweetener.collection.data.Address;
import pl.jsolve.sweetener.collection.data.Company;
import pl.jsolve.sweetener.collection.data.Person;
import pl.jsolve.sweetener.criteria.Restriction.RestrictionLevel;

public class CriteriaTest {

    @Test
    public void shouldSortListOfRestrictionByRestrictionLevel() throws Exception {
	// given
	Criteria criteria = Criteria.newCriteria().add(Restrictions.eq("name", "John")).add(Restrictions.isNotNull("lastName"))
		.add(Restrictions.notEq("age", 35)).add(Restrictions.isNull("company"));

	// when
	List<Restriction> sortedRestrictions = criteria.getSortedRestrictions();

	// then
	assertThat(sortedRestrictions).onProperty("restrictionLevel").containsExactly(RestrictionLevel.HIGH, RestrictionLevel.HIGH,
		RestrictionLevel.MEDIUM, RestrictionLevel.MEDIUM);
    }

    @Test
    public void shouldFilterGivenCollectionByNotNullRestriction() throws Exception {
	// given
	List<Person> people = prepareListOfPerson();

	// when
	Collection<Person> filteredList = Collections.filter(people, Criteria.newCriteria().add(Restrictions.isNotNull("company")));

	// then
	assertThat(filteredList).hasSize(2);
    }

    @Test
    public void shouldFilterGivenCollectionByNotNullRestrictionForNestedObject() throws Exception {
	// given
	List<Person> people = prepareListOfPerson();

	// when
	Collection<Person> filteredList = Collections.filter(people, Criteria.newCriteria().add(Restrictions.isNotNull("company.name")));

	// then
	assertThat(filteredList).hasSize(2);
    }

    @Test
    public void shouldFilterGivenCollectionByNotNullRestrictionForNestedObject2() throws Exception {
	// given
	List<Person> people = prepareListOfPerson();

	// when
	Collection<Person> filteredList = Collections.filter(people,
		Criteria.newCriteria().add(Restrictions.isNotNull("company.name")).add(Restrictions.isNull("company.address.city")));

	// then
	assertThat(filteredList).hasSize(1);
    }

    @Test
    public void shouldFilterGivenCollectionByEqWithIgnoringCase() throws Exception {
	// given
	List<Person> people = prepareListOfPerson();

	// when
	Collection<Person> filteredList = Collections.filter(people, Criteria.newCriteria().add(Restrictions.eq("name", "Marry")));

	// then
	assertThat(filteredList).hasSize(1);
    }

    @Test
    public void shouldFilterGivenCollectionByEq() throws Exception {
	// given
	List<Person> people = prepareListOfPerson();

	// when
	Collection<Person> filteredList = Collections.filter(people, Criteria.newCriteria().add(Restrictions.eq("age", 27)));

	// then
	assertThat(filteredList).hasSize(1);
	assertThat(filteredList).onProperty("name").contains("John");
	assertThat(filteredList).onProperty("lastName").contains("Wolf");
    }

    @Test
    public void shouldFilterGivenCollectionByNotEqWithIgnoringCase() throws Exception {
	// given
	List<Person> people = prepareListOfPerson();

	// when
	Collection<Person> filteredList = Collections.filter(people, Criteria.newCriteria().add(Restrictions.notEq("name", "marry", true)));

	// then
	assertThat(filteredList).hasSize(3);
	assertThat(filteredList).onProperty("name").contains("John", "John", "Peter");
	assertThat(filteredList).onProperty("lastName").contains("Wolf", "Sky", "Hunt");
    }

    @Test
    public void shouldFilterGivenCollectionByGreater() throws Exception {
	// given
	List<Person> people = prepareListOfPerson();

	// when
	Collection<Person> filteredList = Collections.filter(people, Criteria.newCriteria().add(Restrictions.greater("age", 27)));

	// then
	assertThat(filteredList).hasSize(3);
	assertThat(filteredList).onProperty("name").contains("John", "Marry", "Peter");
	assertThat(filteredList).onProperty("lastName").contains("Sky", "Duke", "Hunt");
    }
    
    @Test
    public void shouldFilterGivenCollectionByLess() throws Exception {
	// given
	List<Person> people = prepareListOfPerson();

	// when
	Collection<Person> filteredList = Collections.filter(people, Criteria.newCriteria().add(Restrictions.less("age", 27)));

	// then
	assertThat(filteredList).hasSize(0);
    }
    
    @Test
    public void shouldFilterGivenCollectionByEqOrGreater() throws Exception {
	// given
	List<Person> people = prepareListOfPerson();

	// when
	Collection<Person> filteredList = Collections.filter(people, Criteria.newCriteria().add(Restrictions.greaterOrEq("age", 31)));

	// then
	assertThat(filteredList).hasSize(3);
	assertThat(filteredList).onProperty("name").contains("John", "Marry", "Peter");
	assertThat(filteredList).onProperty("lastName").contains("Sky", "Duke", "Hunt");
    }
    
    @Test
    public void shouldFilterGivenCollectionByEqOrLess() throws Exception {
	// given
	List<Person> people = prepareListOfPerson();

	// when
	Collection<Person> filteredList = Collections.filter(people, Criteria.newCriteria().add(Restrictions.lessOrEq("age", 27)));

	// then
	assertThat(filteredList).hasSize(1);
	assertThat(filteredList).onProperty("name").contains("John");
	assertThat(filteredList).onProperty("lastName").contains("Wolf");
    }
    
    @Test
    public void shouldFilterGivenCollectionByLike() throws Exception {
	// given
	List<Person> people = prepareListOfPerson();

	// when
	Collection<Person> filteredList = Collections.filter(people, Criteria.newCriteria().add(Restrictions.like("name", "ar")));

	// then
	assertThat(filteredList).hasSize(1);
	assertThat(filteredList).onProperty("name").contains("Marry");
	assertThat(filteredList).onProperty("lastName").contains("Duke");
    }

    @Test
    public void shouldFilterGivenCollectionByNotLike() throws Exception {
	// given
	List<Person> people = prepareListOfPerson();

	// when
	Collection<Person> filteredList = Collections.filter(people, Criteria.newCriteria().add(Restrictions.notLike("name", "ar")));

	// then
	assertThat(filteredList).hasSize(3);
	assertThat(filteredList).onProperty("name").contains("John", "John", "Peter");
	assertThat(filteredList).onProperty("lastName").contains("Wolf", "Sky", "Hunt");
    } //categoryOfDrivingLicense

    @Test
    public void shouldFilterGivenCollectionByContains() throws Exception {
	// given
	List<Person> people = prepareListOfPerson();

	// when
	Collection<Person> filteredList = Collections.filter(people, Criteria.newCriteria().add(Restrictions.contains("categoriesOfDrivingLicense", "B")));

	// then
	assertThat(filteredList).hasSize(3);
	assertThat(filteredList).onProperty("name").contains("John", "Marry", "Peter");
	assertThat(filteredList).onProperty("lastName").contains("Sky", "Duke", "Hunt");
    }
    
    @Test
    public void shouldFilterGivenCollectionByContainsForAnyObject() throws Exception {
	// given
	List<Person> people = prepareListOfPerson();

	// when
	Collection<Person> filteredList = Collections.filter(people, Criteria.newCriteria().add(Restrictions.containsAny("categoriesOfDrivingLicense", "A", "D")));

	// then
	assertThat(filteredList).hasSize(2);
	assertThat(filteredList).onProperty("name").contains("Marry", "Peter");
	assertThat(filteredList).onProperty("lastName").contains("Duke", "Hunt");
    }
    
    @Test
    public void shouldFilterGivenCollectionByNotContains() throws Exception {
	// given
	List<Person> people = prepareListOfPerson();

	// when
	Collection<Person> filteredList = Collections.filter(people, Criteria.newCriteria().add(Restrictions.notContains("categoriesOfDrivingLicense", "A", "B")));

	// then
	assertThat(filteredList).hasSize(3);
	assertThat(filteredList).onProperty("name").contains("John", "John", "Peter");
	assertThat(filteredList).onProperty("lastName").contains("Wolf", "Sky", "Hunt");
    }
    
    @Test
    public void shouldFilterGivenCollectionByNotContainsForAnyObjects() throws Exception {
	// given
	List<Person> people = prepareListOfPerson();

	// when
	Collection<Person> filteredList = Collections.filter(people, Criteria.newCriteria().add(Restrictions.notContains("categoriesOfDrivingLicense", "A", "B")));

	// then
	assertThat(filteredList).hasSize(3);
	assertThat(filteredList).onProperty("name").contains("John", "John");
	assertThat(filteredList).onProperty("lastName").contains("Wolf", "Sky");
    }
    
    private List<Person> prepareListOfPerson() {
	List<Person> people = new ArrayList<Person>();
	
	people.add(new Person("John", "Wolf", 27, null, null));
	people.add(new Person("John", "Sky", 31, new Company("EA", new Address("street1", "city1")), prepareListOfCategories("B")));
	people.add(new Person("Marry", "Duke", 45, new Company("Oracle", new Address("street2", null)), prepareListOfCategories("A", "B")));
	people.add(new Person("Peter", "Hunt", 41, null, prepareListOfCategories("B", "D")));
	return people;
    }
    
    private List<String> prepareListOfCategories(String ... values) {
	List<String> categories = new ArrayList<String>();
	for(String s : values) {
	    categories.add(s);
	}
	return categories;
    }
    
}
