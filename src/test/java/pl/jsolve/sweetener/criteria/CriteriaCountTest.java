package pl.jsolve.sweetener.criteria;

import static org.fest.assertions.Assertions.assertThat;
import static pl.jsolve.sweetener.collection.Collections.newArrayList;

import java.util.Collection;
import java.util.List;

import org.junit.Test;

import pl.jsolve.sweetener.collection.Collections;
import pl.jsolve.sweetener.criteria.data.ObjectWithCollectionOfNumbers;
import pl.jsolve.sweetener.criteria.restriction.AggregationRange;

public class CriteriaCountTest {

    @Test
    public void shouldFilterCollectionByCountRestrictionForList() {
        // given
        List<ObjectWithCollectionOfNumbers> data = Collections.newArrayList();
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(1)
                .withGradesAsList(newArrayList(1, 1, 4, 3, 3, 4, 5, 2, 3, 4, 5)).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(2)
                .withGradesAsList(newArrayList(2, 1, 1, 4)).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(3)
                .withGradesAsList(newArrayList(5, 5)).build());

        // when
        Collection<ObjectWithCollectionOfNumbers> filteredList = Collections.filter(data,
                Criteria.newCriteria().add(Restrictions.count("gradesAsList", 5, AggregationRange.LESS)));

        // then
        assertThat(filteredList).hasSize(2);
        assertThat(filteredList).onProperty("index").contains(2, 3);
    }

    @Test
    public void shouldFilterCollectionByCountRestrictionForList2() {
        // given
        List<ObjectWithCollectionOfNumbers> data = Collections.newArrayList();
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(1)
                .withGradesAsList(newArrayList(1, 1, 4, 3, 3, 4, 5, 2, 3, 4, 5)).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(2)
                .withGradesAsList(newArrayList(2, 1, 1, 4)).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(3)
                .withGradesAsList(newArrayList(5, 5)).build());

        // when
        Collection<ObjectWithCollectionOfNumbers> filteredList = Collections.filter(data,
                Criteria.newCriteria().add(Restrictions.count("gradesAsList", 5, AggregationRange.GREATER)));

        // then
        assertThat(filteredList).hasSize(1);
        assertThat(filteredList).onProperty("index").contains(1);
    }

    @Test
    public void shouldFilterCollectionByCountRestrictionForList3() {
        // given
        List<ObjectWithCollectionOfNumbers> data = Collections.newArrayList();
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(1)
                .withGradesAsList(newArrayList(1, 1, 4, 3, 3, 4, 5, 2, 3, 4, 5)).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(2)
                .withGradesAsList(newArrayList(2, 1, 1, 4)).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(3)
                .withGradesAsList(newArrayList(5, 5)).build());

        // when
        Collection<ObjectWithCollectionOfNumbers> filteredList = Collections.filter(data,
                Criteria.newCriteria().add(Restrictions.count("gradesAsList", 4, 18, AggregationRange.BETWEEN)));

        // then
        assertThat(filteredList).hasSize(2);
        assertThat(filteredList).onProperty("index").contains(1, 2);
    }

    @Test
    public void shouldFilterCollectionByCountRestrictionForList4() {
        // given
        List<ObjectWithCollectionOfNumbers> data = Collections.newArrayList();
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(1)
                .withGradesAsList(newArrayList(1, 1, 4, 3, 3, 4, 5, 2, 3, 4, 5)).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(2)
                .withGradesAsList(newArrayList(2, 1, 1, 4)).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(3)
                .withGradesAsList(newArrayList(5, 5)).build());

        // when
        Collection<ObjectWithCollectionOfNumbers> filteredList = Collections.filter(data,
                Criteria.newCriteria().add(Restrictions.count("gradesAsList", 4, 18, AggregationRange.NOT_BETWEEN)));

        // then
        assertThat(filteredList).hasSize(1);
        assertThat(filteredList).onProperty("index").contains(3);
    }

    @Test
    public void shouldFilterCollectionByCountRestrictionForList5() {
        // given
        List<ObjectWithCollectionOfNumbers> data = Collections.newArrayList();
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(1)
                .withGradesAsList(newArrayList(1, 1, 4, 3, 3, 4, 5, 2, 3, 4, 5)).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(2)
                .withGradesAsList(newArrayList(2, 1, 1, 4)).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(3)
                .withGradesAsList(newArrayList(5, 5)).build());

        // when
        Collection<ObjectWithCollectionOfNumbers> filteredList = Collections.filter(data,
                Criteria.newCriteria().add(Restrictions.count("gradesAsList", 4, AggregationRange.EQUALS)));

        // then
        assertThat(filteredList).hasSize(1);
        assertThat(filteredList).onProperty("index").contains(2);
    }

    @Test
    public void shouldFilterCollectionByCountRestrictionForList6() {
        // given
        List<ObjectWithCollectionOfNumbers> data = Collections.newArrayList();
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(1)
                .withGradesAsList(newArrayList(1, 1, 4, 3, 3, 4, 5, 2, 3, 4, 5)).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(2)
                .withGradesAsList(newArrayList(2, 1, 1, 4)).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(3)
                .withGradesAsList(newArrayList(5, 5)).build());

        // when
        Collection<ObjectWithCollectionOfNumbers> filteredList = Collections.filter(data,
                Criteria.newCriteria().add(Restrictions.count("gradesAsList", 4, AggregationRange.NOT_EQUALS)));

        // then
        assertThat(filteredList).hasSize(2);
        assertThat(filteredList).onProperty("index").contains(1, 3);
    }

}
