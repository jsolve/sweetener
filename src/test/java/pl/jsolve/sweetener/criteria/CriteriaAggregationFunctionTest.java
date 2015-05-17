package pl.jsolve.sweetener.criteria;

import static org.fest.assertions.Assertions.assertThat;
import static pl.jsolve.sweetener.collection.Collections.newArrayList;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import pl.jsolve.sweetener.collection.Collections;
import pl.jsolve.sweetener.criteria.data.ObjectWithCollectionOfNumbers;
import pl.jsolve.sweetener.criteria.restriction.AggregationRange;

public class CriteriaAggregationFunctionTest {

    // As list

    @Test
    public void shouldFilterCollectionByAvgRestrictionForList() {
        // given
        List<ObjectWithCollectionOfNumbers> data = Collections.newArrayList();
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(1)
                .withGradesAsList(newArrayList(1, 2, 3, 4, 5)).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(2)
                .withGradesAsList(newArrayList(2, 1, 1, 4, 5)).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(3)
                .withGradesAsList(newArrayList(5, 5, 3, 4, 5)).build());

        // when
        Collection<ObjectWithCollectionOfNumbers> filteredList = Collections.filter(data,
                Criteria.newCriteria().add(Restrictions.avg("gradesAsList", 3.5, AggregationRange.LESS)));

        // then
        assertThat(filteredList).hasSize(2);
        assertThat(filteredList).onProperty("index").contains(1, 2);
    }

    @Test
    public void shouldFilterCollectionByAvgRestrictionForList2() {
        // given
        List<ObjectWithCollectionOfNumbers> data = Collections.newArrayList();
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(1)
                .withGradesAsList(newArrayList(1, 2, 3, 4, 5)).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(2)
                .withGradesAsList(newArrayList(2, 1, 1, 4, 5)).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(3)
                .withGradesAsList(newArrayList(5, 5, 3, 4, 5)).build());

        // when
        Collection<ObjectWithCollectionOfNumbers> filteredList = Collections.filter(data,
                Criteria.newCriteria().add(Restrictions.avg("gradesAsList", 3.5, AggregationRange.GREATER)));

        // then
        assertThat(filteredList).hasSize(1);
        assertThat(filteredList).onProperty("index").contains(3);
    }

    @Test
    public void shouldFilterCollectionByAvgRestrictionForList3() {
        // given
        List<ObjectWithCollectionOfNumbers> data = Collections.newArrayList();
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(1)
                .withGradesAsList(newArrayList(1, 2, 3, 4, 5)).build()); // 3
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(2)
                .withGradesAsList(newArrayList(2, 1, 1, 4, 5)).build()); // 2.6
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(3)
                .withGradesAsList(newArrayList(5, 5, 3, 4, 5)).build()); // 4.4

        // when
        Collection<ObjectWithCollectionOfNumbers> filteredList = Collections.filter(data,
                Criteria.newCriteria().add(Restrictions.avg("gradesAsList", 2.8, 3.3, AggregationRange.BETWEEN)));

        // then
        assertThat(filteredList).hasSize(1);
        assertThat(filteredList).onProperty("index").contains(1);
    }

    @Test
    public void shouldFilterCollectionByAvgRestrictionForList4() {
        // given
        List<ObjectWithCollectionOfNumbers> data = Collections.newArrayList();
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(1)
                .withGradesAsList(newArrayList(1, 2, 3, 4, 5)).build()); // 3
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(2)
                .withGradesAsList(newArrayList(2, 1, 1, 4, 5)).build()); // 2.6
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(3)
                .withGradesAsList(newArrayList(5, 5, 3, 4, 5)).build()); // 4.4

        // when
        Collection<ObjectWithCollectionOfNumbers> filteredList = Collections.filter(data,
                Criteria.newCriteria().add(Restrictions.avg("gradesAsList", 2.8, 3.3, AggregationRange.NOT_BETWEEN)));

        // then
        assertThat(filteredList).hasSize(2);
        assertThat(filteredList).onProperty("index").contains(2, 3);
    }

    // as Set

    @Test
    public void shouldFilterCollectionByAvgRestrictionForSet() {
        // given
        Set<ObjectWithCollectionOfNumbers> data = Collections.newHashSet();
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(1)
                .withGradesAsSet(Collections.newHashSet(1, 2, 3, 4, 5)).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(2)
                .withGradesAsSet(Collections.newHashSet(1, 2, 3)).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(3)
                .withGradesAsSet(Collections.newHashSet(3, 4, 5)).build());

        // when
        Collection<ObjectWithCollectionOfNumbers> filteredList = Collections.filter(data,
                Criteria.newCriteria().add(Restrictions.avg("gradesAsSet", 3.5, AggregationRange.LESS)));

        // then
        assertThat(filteredList).hasSize(2);
        assertThat(filteredList).onProperty("index").contains(1, 2);
    }

    @Test
    public void shouldFilterCollectionByAvgRestrictionForSet2() {
        // given
        Set<ObjectWithCollectionOfNumbers> data = Collections.newHashSet();
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(1)
                .withGradesAsSet(Collections.newHashSet(1, 2, 3, 4, 5)).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(2)
                .withGradesAsSet(Collections.newHashSet(1, 2, 3)).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(3)
                .withGradesAsSet(Collections.newHashSet(3, 4, 5)).build());

        // when
        Collection<ObjectWithCollectionOfNumbers> filteredList = Collections.filter(data,
                Criteria.newCriteria().add(Restrictions.avg("gradesAsSet", 3.5, AggregationRange.GREATER)));

        // then
        assertThat(filteredList).hasSize(1);
        assertThat(filteredList).onProperty("index").contains(3);
    }

    @Test
    public void shouldFilterCollectionByAvgRestrictionForSet3() {
        // given
        Set<ObjectWithCollectionOfNumbers> data = Collections.newHashSet();
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(1)
                .withGradesAsSet(Collections.newHashSet(1, 2, 3, 4, 5)).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(2)
                .withGradesAsSet(Collections.newHashSet(1, 2, 3)).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(3)
                .withGradesAsSet(Collections.newHashSet(3, 4, 5)).build());

        // when
        Collection<ObjectWithCollectionOfNumbers> filteredList = Collections.filter(data,
                Criteria.newCriteria().add(Restrictions.avg("gradesAsSet", 2.8, 3.3, AggregationRange.BETWEEN)));

        // then
        assertThat(filteredList).hasSize(1);
        assertThat(filteredList).onProperty("index").contains(1);
    }

    @Test
    public void shouldFilterCollectionByAvgRestrictionForSet4() {
        // given
        Set<ObjectWithCollectionOfNumbers> data = Collections.newHashSet();
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(1)
                .withGradesAsSet(Collections.newHashSet(1, 2, 3, 4, 5)).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(2)
                .withGradesAsSet(Collections.newHashSet(1, 2, 3)).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(3)
                .withGradesAsSet(Collections.newHashSet(3, 4, 5)).build());

        // when
        Collection<ObjectWithCollectionOfNumbers> filteredList = Collections.filter(data,
                Criteria.newCriteria().add(Restrictions.avg("gradesAsSet", 2.8, 3.3, AggregationRange.NOT_BETWEEN)));

        // then
        assertThat(filteredList).hasSize(2);
        assertThat(filteredList).onProperty("index").contains(2, 3);
    }

    // as Integer[]

    @Test
    public void shouldFilterCollectionByAvgRestrictionForArrayOfInteger() {
        // given
        List<ObjectWithCollectionOfNumbers> data = Collections.newArrayList();
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(1)
                .withGradesAsArrayOfInteger(new Integer[] {1, 2, 3, 4, 5}).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(2)
                .withGradesAsArrayOfInteger(new Integer[] {1, 2, 3}).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(3)
                .withGradesAsArrayOfInteger(new Integer[] {3, 4, 5}).build());

        // when
        Collection<ObjectWithCollectionOfNumbers> filteredList = Collections.filter(data,
                Criteria.newCriteria().add(Restrictions.avg("gradesAsInteger", 3.5, AggregationRange.LESS)));

        // then
        assertThat(filteredList).hasSize(2);
        assertThat(filteredList).onProperty("index").contains(1, 2);
    }

    @Test
    public void shouldFilterCollectionByAvgRestrictionForArrayOfInteger2() {
        // given
        List<ObjectWithCollectionOfNumbers> data = Collections.newArrayList();
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(1)
                .withGradesAsArrayOfInteger(new Integer[] {1, 2, 3, 4, 5}).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(2)
                .withGradesAsArrayOfInteger(new Integer[] {1, 2, 3}).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(3)
                .withGradesAsArrayOfInteger(new Integer[] {3, 4, 5}).build());

        // when
        Collection<ObjectWithCollectionOfNumbers> filteredList = Collections.filter(data,
                Criteria.newCriteria().add(Restrictions.avg("gradesAsInteger", 3.5, AggregationRange.GREATER)));

        // then
        assertThat(filteredList).hasSize(1);
        assertThat(filteredList).onProperty("index").contains(3);
    }

    @Test
    public void shouldFilterCollectionByAvgRestrictionForArrayOfInteger3() {
        // given
        List<ObjectWithCollectionOfNumbers> data = Collections.newArrayList();
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(1)
                .withGradesAsArrayOfInteger(new Integer[] {1, 2, 3, 4, 5}).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(2)
                .withGradesAsArrayOfInteger(new Integer[] {1, 2, 3}).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(3)
                .withGradesAsArrayOfInteger(new Integer[] {3, 4, 5}).build());

        // when
        Collection<ObjectWithCollectionOfNumbers> filteredList = Collections.filter(data,
                Criteria.newCriteria().add(Restrictions.avg("gradesAsInteger", 2.8, 3.3, AggregationRange.BETWEEN)));

        // then
        assertThat(filteredList).hasSize(1);
        assertThat(filteredList).onProperty("index").contains(1);
    }

    @Test
    public void shouldFilterCollectionByAvgRestrictionForArrayOfInteger4() {
        // given
        List<ObjectWithCollectionOfNumbers> data = Collections.newArrayList();
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(1)
                .withGradesAsArrayOfInteger(new Integer[] {1, 2, 3, 4, 5}).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(2)
                .withGradesAsArrayOfInteger(new Integer[] {1, 2, 3}).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(3)
                .withGradesAsArrayOfInteger(new Integer[] {3, 4, 5}).build());

        // when
        Collection<ObjectWithCollectionOfNumbers> filteredList = Collections
                .filter(data,
                        Criteria.newCriteria().add(
                                Restrictions.avg("gradesAsInteger", 2.8, 3.3, AggregationRange.NOT_BETWEEN)));

        // then
        assertThat(filteredList).hasSize(2);
        assertThat(filteredList).onProperty("index").contains(2, 3);
    }

    // as int[]

    @Test
    public void shouldFilterCollectionByAvgRestrictionForArrayOfInt() {
        // given
        List<ObjectWithCollectionOfNumbers> data = Collections.newArrayList();
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(1)
                .withGradesAsArrayOfInt(new int[] {1, 2, 3, 4, 5}).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(2)
                .withGradesAsArrayOfInt(new int[] {1, 2, 3}).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(3)
                .withGradesAsArrayOfInt(new int[] {3, 4, 5}).build());

        // when
        Collection<ObjectWithCollectionOfNumbers> filteredList = Collections.filter(data,
                Criteria.newCriteria().add(Restrictions.avg("gradesAsInt", 3.5, AggregationRange.LESS)));

        // then
        assertThat(filteredList).hasSize(2);
        assertThat(filteredList).onProperty("index").contains(1, 2);
    }

    @Test
    public void shouldFilterCollectionByAvgRestrictionForArrayOfInt2() {
        // given
        List<ObjectWithCollectionOfNumbers> data = Collections.newArrayList();
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(1)
                .withGradesAsArrayOfInt(new int[] {1, 2, 3, 4, 5}).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(2)
                .withGradesAsArrayOfInt(new int[] {1, 2, 3}).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(3)
                .withGradesAsArrayOfInt(new int[] {3, 4, 5}).build());

        // when
        Collection<ObjectWithCollectionOfNumbers> filteredList = Collections.filter(data,
                Criteria.newCriteria().add(Restrictions.avg("gradesAsInt", 3.5, AggregationRange.GREATER)));

        // then
        assertThat(filteredList).hasSize(1);
        assertThat(filteredList).onProperty("index").contains(3);
    }

    @Test
    public void shouldFilterCollectionByAvgRestrictionForArrayOfInt3() {
        // given
        List<ObjectWithCollectionOfNumbers> data = Collections.newArrayList();
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(1)
                .withGradesAsArrayOfInt(new int[] {1, 2, 3, 4, 5}).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(2)
                .withGradesAsArrayOfInt(new int[] {1, 2, 3}).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(3)
                .withGradesAsArrayOfInt(new int[] {3, 4, 5}).build());

        // when
        Collection<ObjectWithCollectionOfNumbers> filteredList = Collections.filter(data,
                Criteria.newCriteria().add(Restrictions.avg("gradesAsInt", 2.8, 3.3, AggregationRange.BETWEEN)));

        // then
        assertThat(filteredList).hasSize(1);
        assertThat(filteredList).onProperty("index").contains(1);
    }

    @Test
    public void shouldFilterCollectionByAvgRestrictionForArrayOfInt4() {
        // given
        List<ObjectWithCollectionOfNumbers> data = Collections.newArrayList();
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(1)
                .withGradesAsArrayOfInt(new int[] {1, 2, 3, 4, 5}).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(2)
                .withGradesAsArrayOfInt(new int[] {1, 2, 3}).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(3)
                .withGradesAsArrayOfInt(new int[] {3, 4, 5}).build());

        // when
        Collection<ObjectWithCollectionOfNumbers> filteredList = Collections.filter(data,
                Criteria.newCriteria().add(Restrictions.avg("gradesAsInt", 2.8, 3.3, AggregationRange.NOT_BETWEEN)));

        // then
        assertThat(filteredList).hasSize(2);
        assertThat(filteredList).onProperty("index").contains(2, 3);
    }

    @Test
    public void shouldFilterCollectionByAvgRestrictionForArrayOfInt5() {
        // given
        List<ObjectWithCollectionOfNumbers> data = Collections.newArrayList();
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(1)
                .withGradesAsArrayOfInt(new int[] {}).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(2)
                .withGradesAsArrayOfInt(new int[] {}).build());
        data.add(ObjectWithCollectionOfNumbers.ObjectWithCollectionOfNumbersBuilder.aBuilder(3)
                .withGradesAsArrayOfInt(new int[] {}).build());

        // when
        Collection<ObjectWithCollectionOfNumbers> filteredList = Collections.filter(data,
                Criteria.newCriteria().add(Restrictions.avg("gradesAsInt", 2.8, AggregationRange.LESS)));

        // then
        assertThat(filteredList).hasSize(3);
        assertThat(filteredList).onProperty("index").contains(1, 2, 3);
    }
}
