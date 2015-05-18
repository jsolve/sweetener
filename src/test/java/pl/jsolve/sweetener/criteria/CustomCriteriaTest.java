package pl.jsolve.sweetener.criteria;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Collection;
import java.util.List;

import org.junit.Test;

import pl.jsolve.sweetener.collection.Collections;
import pl.jsolve.sweetener.collection.data.Address;
import pl.jsolve.sweetener.collection.data.Company;
import pl.jsolve.sweetener.collection.data.Person;
import pl.jsolve.sweetener.criteria.data.StartsWithRestriction;

public class CustomCriteriaTest {

    @Test
    public void shouldFilterCollectionByCustomStartsWithRestrictionForList() {
        // given
        List<Person> people = prepareListOfPeople();

        // when
        Collection<Person> filteredList = Collections.filter(people,
                Criteria.newCriteria().add(new StartsWithRestriction("name", "J")));

        // then
        assertThat(filteredList).hasSize(2);
        assertThat(filteredList).onProperty("name").contains("John", "Jim");
    }

    private List<Person> prepareListOfPeople() {
        List<Person> people = Collections.newArrayList();

        people.add(new Person("John", "Wolf", 27, null, null, null, new int[] {7, 11, 16}));
        people.add(new Person("Jim", "Sky", 31, new Company("EA", new Address("street1", "city1")),
                prepareListOfCategories("B"), new String[] {"Kate"}, new int[] {7, 15}));
        people.add(new Person("Marry", "Duke", 45, new Company("Oracle", new Address("street2", null)),
                prepareListOfCategories("A", "B"), new String[] {"Ainsley", "Ash"}, new int[] {13}));
        people.add(new Person("Peter", "Hunt", 41, null, prepareListOfCategories("B", "D"), new String[] {"Aston"},
                null));
        return people;
    }

    private List<String> prepareListOfCategories(String... values) {
        List<String> categories = Collections.newArrayList();
        for (String s : values) {
            categories.add(s);
        }
        return categories;
    }

}
