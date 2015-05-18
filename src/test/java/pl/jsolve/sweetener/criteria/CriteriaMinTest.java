package pl.jsolve.sweetener.criteria;

import static org.fest.assertions.Assertions.assertThat;
import static pl.jsolve.sweetener.collection.Collections.newArrayList;

import java.util.Collection;
import java.util.List;

import org.junit.Test;

import pl.jsolve.sweetener.collection.Collections;
import pl.jsolve.sweetener.criteria.data.ObjectWithCollectionOfNumbers;
import pl.jsolve.sweetener.criteria.restriction.AggregationRange;

public class CriteriaMinTest {

    @Test
    public void shouldFilterCollectionByMinRestrictionForList() {
        // given
        List<ObjectWithCollectionOfNumbers> data = Collections.newArrayList();
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(1)
                .withGradesAsList(newArrayList(1, 2, 3, 4)).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(2)
                .withGradesAsList(newArrayList(3, 5)).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(3)
                .withGradesAsList(newArrayList(4, 5)).build());

        // when
        Collection<ObjectWithCollectionOfNumbers> filteredList = Collections.filter(data,
                Criteria.newCriteria().add(Restrictions.min("gradesAsList", 4, AggregationRange.LESS)));

        // then
        assertThat(filteredList).hasSize(2);
        assertThat(filteredList).onProperty("index").contains(1, 2);
    }

    @Test
    public void shouldFilterCollectionByMinRestrictionForList2() {
        // given
        List<ObjectWithCollectionOfNumbers> data = Collections.newArrayList();
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(1)
                .withGradesAsList(newArrayList(1, 2, 3, 4)).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(2)
                .withGradesAsList(newArrayList(3, 5)).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(3)
                .withGradesAsList(newArrayList(4, 5)).build());

        // when
        Collection<ObjectWithCollectionOfNumbers> filteredList = Collections.filter(data,
                Criteria.newCriteria().add(Restrictions.min("gradesAsList", 3.5, AggregationRange.GREATER)));

        // then
        assertThat(filteredList).hasSize(1);
        assertThat(filteredList).onProperty("index").contains(3);
    }

    @Test
    public void shouldFilterCollectionByMinRestrictionForList3() {
        // given
        List<ObjectWithCollectionOfNumbers> data = Collections.newArrayList();
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(1)
                .withGradesAsList(newArrayList(1, 2, 3, 4)).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(2)
                .withGradesAsList(newArrayList(3, 5)).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(3)
                .withGradesAsList(newArrayList(4, 5)).build());

        // when
        Collection<ObjectWithCollectionOfNumbers> filteredList = Collections.filter(data,
                Criteria.newCriteria().add(Restrictions.min("gradesAsList", 2.5, 4.5, AggregationRange.BETWEEN)));

        // then
        assertThat(filteredList).hasSize(2);
        assertThat(filteredList).onProperty("index").contains(2, 3);
    }

    @Test
    public void shouldFilterCollectionByMinRestrictionForList4() {
        // given
        List<ObjectWithCollectionOfNumbers> data = Collections.newArrayList();
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(1)
                .withGradesAsList(newArrayList(1, 2, 3, 4)).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(2)
                .withGradesAsList(newArrayList(3, 5)).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(3)
                .withGradesAsList(newArrayList(4, 5)).build());

        // when
        Collection<ObjectWithCollectionOfNumbers> filteredList = Collections.filter(data,
                Criteria.newCriteria().add(Restrictions.min("gradesAsList", 2.5, 4.5, AggregationRange.NOT_BETWEEN)));

        // then
        assertThat(filteredList).hasSize(1);
        assertThat(filteredList).onProperty("index").contains(1);
    }

    @Test
    public void shouldFilterCollectionByMinRestrictionForList5() {
        // given
        List<ObjectWithCollectionOfNumbers> data = Collections.newArrayList();
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(1)
                .withGradesAsList(newArrayList(1, 2, 3, 4)).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(2)
                .withGradesAsList(newArrayList(3, 5)).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(3)
                .withGradesAsList(newArrayList(4, 5)).build());

        // when
        Collection<ObjectWithCollectionOfNumbers> filteredList = Collections.filter(data,
                Criteria.newCriteria().add(Restrictions.min("gradesAsList", 3, AggregationRange.EQUALS)));

        // then
        assertThat(filteredList).hasSize(1);
        assertThat(filteredList).onProperty("index").contains(2);
    }

    @Test
    public void shouldFilterCollectionByMinRestrictionForList6() {
        // given
        List<ObjectWithCollectionOfNumbers> data = Collections.newArrayList();
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(1)
                .withGradesAsList(newArrayList(1, 2, 3, 4)).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(2)
                .withGradesAsList(newArrayList(3, 5)).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(3)
                .withGradesAsList(newArrayList(4, 5)).build());

        // when
        Collection<ObjectWithCollectionOfNumbers> filteredList = Collections.filter(data,
                Criteria.newCriteria().add(Restrictions.min("gradesAsList", 4, AggregationRange.NOT_EQUALS)));

        // then
        assertThat(filteredList).hasSize(2);
        assertThat(filteredList).onProperty("index").contains(1, 2);
    }

}
