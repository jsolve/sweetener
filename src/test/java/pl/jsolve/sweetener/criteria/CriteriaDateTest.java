package pl.jsolve.sweetener.criteria;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.junit.Test;

import pl.jsolve.sweetener.collection.Collections;
import pl.jsolve.sweetener.criteria.data.ObjectWithDate;
import pl.jsolve.sweetener.criteria.data.ObjectWithLocalDate;
import pl.jsolve.sweetener.criteria.data.ObjectWithLocalDateTime;
import pl.jsolve.sweetener.criteria.data.ObjectWithLocalTime;
import pl.jsolve.sweetener.criteria.restriction.DateExtractor;

public class CriteriaDateTest {

    @Test
    public void shouldFilterGivenCollectionByBeforeRestrictionForDateType() {
        // given
        List<ObjectWithDate> dates = Collections.newArrayList();
        dates.add(new ObjectWithDate(1, LocalDateTime.now().toDate()));
        dates.add(new ObjectWithDate(2, LocalDateTime.now().minusDays(2).toDate()));
        dates.add(new ObjectWithDate(3, LocalDateTime.now().minusDays(5).toDate()));
        Date threshold = LocalDateTime.now().minusDays(1).toDate();

        // when
        Collection<ObjectWithDate> filteredList = Collections.filter(dates,
                Criteria.newCriteria().add(Restrictions.before("now", threshold)));

        // then
        assertThat(filteredList).hasSize(2);
        assertThat(filteredList).onProperty("index").contains(2, 3);
    }

    @Test
    public void shouldFilterGivenCollectionByBeforeRestrictionForLocalDateType() {
        // given
        Restrictions.registerDateExtractor(LocalDate.class, new DateExtractor<LocalDate>() {

            @Override
            public Date extract(LocalDate d) {
                return d.toDate();
            }
        });

        List<ObjectWithLocalDate> dates = Collections.newArrayList();
        dates.add(new ObjectWithLocalDate(1, LocalDate.now()));
        dates.add(new ObjectWithLocalDate(2, LocalDate.now().minusDays(2)));
        dates.add(new ObjectWithLocalDate(3, LocalDate.now().minusDays(5)));
        LocalDate threshold = LocalDate.now().minusDays(1);

        // when
        Collection<ObjectWithLocalDate> filteredList = Collections.filter(dates,
                Criteria.newCriteria().add(Restrictions.before("now", threshold)));

        // then
        assertThat(filteredList).hasSize(2);
        assertThat(filteredList).onProperty("index").contains(2, 3);
    }

    @Test
    public void shouldFilterGivenCollectionByBeforeRestrictionForLocalDateTypeAndThresholdAsDate() {
        // given
        Restrictions.registerDateExtractor(LocalDate.class, new DateExtractor<LocalDate>() {

            @Override
            public Date extract(LocalDate d) {
                return d.toDate();
            }
        });

        List<ObjectWithLocalDate> dates = Collections.newArrayList();
        dates.add(new ObjectWithLocalDate(1, LocalDate.now()));
        dates.add(new ObjectWithLocalDate(2, LocalDate.now().minusDays(2)));
        dates.add(new ObjectWithLocalDate(3, LocalDate.now().minusDays(5)));
        Date threshold = LocalDate.now().minusDays(1).toDate();

        // when
        Collection<ObjectWithLocalDate> filteredList = Collections.filter(dates,
                Criteria.newCriteria().add(Restrictions.before("now", threshold)));

        // then
        assertThat(filteredList).hasSize(2);
        assertThat(filteredList).onProperty("index").contains(2, 3);
    }

    @Test
    public void shouldFilterGivenCollectionByBeforeRestrictionForLocalTimeType() {
        // given
        Restrictions.registerDateExtractor(LocalTime.class, new DateExtractor<LocalTime>() {

            @Override
            public Date extract(LocalTime d) {
                return d.toDateTimeToday().toDate();
            }
        });

        List<ObjectWithLocalTime> dates = Collections.newArrayList();
        dates.add(new ObjectWithLocalTime(1, LocalTime.now()));
        dates.add(new ObjectWithLocalTime(2, LocalTime.now().minusHours(2)));
        dates.add(new ObjectWithLocalTime(3, LocalTime.now().minusHours(5)));
        LocalTime threshold = LocalTime.now().minusHours(1);

        // when
        Collection<ObjectWithLocalTime> filteredList = Collections.filter(dates,
                Criteria.newCriteria().add(Restrictions.before("now", threshold)));

        // then
        assertThat(filteredList).hasSize(2);
        assertThat(filteredList).onProperty("index").contains(2, 3);
    }

    @Test
    public void shouldFilterGivenCollectionByBeforeRestrictionForLocalTimeTypeAndThresholdAsDate() {
        // given
        Restrictions.registerDateExtractor(LocalTime.class, new DateExtractor<LocalTime>() {

            @Override
            public Date extract(LocalTime d) {
                return d.toDateTimeToday().toDate();
            }
        });

        List<ObjectWithLocalTime> dates = Collections.newArrayList();
        dates.add(new ObjectWithLocalTime(1, LocalTime.now()));
        dates.add(new ObjectWithLocalTime(2, LocalTime.now().minusHours(2)));
        dates.add(new ObjectWithLocalTime(3, LocalTime.now().minusHours(5)));
        Date threshold = LocalTime.now().minusHours(1).toDateTimeToday().toDate();

        // when
        Collection<ObjectWithLocalTime> filteredList = Collections.filter(dates,
                Criteria.newCriteria().add(Restrictions.before("now", threshold)));

        // then
        assertThat(filteredList).hasSize(2);
        assertThat(filteredList).onProperty("index").contains(2, 3);
    }

    @Test
    public void shouldFilterGivenCollectionByBeforeRestrictionForLocalDateTimeType() {
        // given
        Restrictions.registerDateExtractor(LocalDateTime.class, new DateExtractor<LocalDateTime>() {

            @Override
            public Date extract(LocalDateTime d) {
                return d.toDate();
            }
        });

        List<ObjectWithLocalDateTime> dates = Collections.newArrayList();
        dates.add(new ObjectWithLocalDateTime(1, LocalDateTime.now()));
        dates.add(new ObjectWithLocalDateTime(2, LocalDateTime.now().minusHours(2)));
        dates.add(new ObjectWithLocalDateTime(3, LocalDateTime.now().minusHours(5)));
        LocalDateTime threshold = LocalDateTime.now().minusHours(1);

        // when
        Collection<ObjectWithLocalDateTime> filteredList = Collections.filter(dates,
                Criteria.newCriteria().add(Restrictions.before("now", threshold)));

        // then
        assertThat(filteredList).hasSize(2);
        assertThat(filteredList).onProperty("index").contains(2, 3);
    }

    @Test
    public void shouldFilterGivenCollectionByBeforeRestrictionForLocalDateTimeTypeAndThresholdAsDate() {
        // given
        Restrictions.registerDateExtractor(LocalDateTime.class, new DateExtractor<LocalDateTime>() {

            @Override
            public Date extract(LocalDateTime d) {
                return d.toDate();
            }
        });

        List<ObjectWithLocalDateTime> dates = Collections.newArrayList();
        dates.add(new ObjectWithLocalDateTime(1, LocalDateTime.now()));
        dates.add(new ObjectWithLocalDateTime(2, LocalDateTime.now().minusHours(2)));
        dates.add(new ObjectWithLocalDateTime(3, LocalDateTime.now().minusHours(5)));
        Date threshold = LocalDateTime.now().minusHours(1).toDate();

        // when
        Collection<ObjectWithLocalDateTime> filteredList = Collections.filter(dates,
                Criteria.newCriteria().add(Restrictions.before("now", threshold)));

        // then
        assertThat(filteredList).hasSize(2);
        assertThat(filteredList).onProperty("index").contains(2, 3);
    }

    @Test
    public void shouldFilterGivenCollectionByAfterRestrictionForDateType() {
        // given
        List<ObjectWithDate> dates = Collections.newArrayList();
        dates.add(new ObjectWithDate(1, LocalDateTime.now().toDate()));
        dates.add(new ObjectWithDate(2, LocalDateTime.now().minusDays(2).toDate()));
        dates.add(new ObjectWithDate(3, LocalDateTime.now().minusDays(5).toDate()));
        Date threshold = LocalDateTime.now().minusDays(1).toDate();

        // when
        Collection<ObjectWithDate> filteredList = Collections.filter(dates,
                Criteria.newCriteria().add(Restrictions.after("now", threshold)));

        // then
        assertThat(filteredList).hasSize(1);
        assertThat(filteredList).onProperty("index").contains(1);
    }

    @Test
    public void shouldFilterGivenCollectionByAfterRestrictionForLocalDateType() {
        // given
        Restrictions.registerDateExtractor(LocalDate.class, new DateExtractor<LocalDate>() {

            @Override
            public Date extract(LocalDate d) {
                return d.toDate();
            }
        });

        List<ObjectWithLocalDate> dates = Collections.newArrayList();
        dates.add(new ObjectWithLocalDate(1, LocalDate.now()));
        dates.add(new ObjectWithLocalDate(2, LocalDate.now().minusDays(2)));
        dates.add(new ObjectWithLocalDate(3, LocalDate.now().minusDays(5)));
        LocalDate threshold = LocalDate.now().minusDays(1);

        // when
        Collection<ObjectWithLocalDate> filteredList = Collections.filter(dates,
                Criteria.newCriteria().add(Restrictions.after("now", threshold)));

        // then
        assertThat(filteredList).hasSize(1);
        assertThat(filteredList).onProperty("index").contains(1);
    }

    @Test
    public void shouldFilterGivenCollectionByAfterRestrictionForLocalDateTypeAndThresholdAsDate() {
        // given
        Restrictions.registerDateExtractor(LocalDate.class, new DateExtractor<LocalDate>() {

            @Override
            public Date extract(LocalDate d) {
                return d.toDate();
            }
        });

        List<ObjectWithLocalDate> dates = Collections.newArrayList();
        dates.add(new ObjectWithLocalDate(1, LocalDate.now()));
        dates.add(new ObjectWithLocalDate(2, LocalDate.now().minusDays(2)));
        dates.add(new ObjectWithLocalDate(3, LocalDate.now().minusDays(5)));
        Date threshold = LocalDate.now().minusDays(1).toDate();

        // when
        Collection<ObjectWithLocalDate> filteredList = Collections.filter(dates,
                Criteria.newCriteria().add(Restrictions.after("now", threshold)));

        // then
        assertThat(filteredList).hasSize(1);
        assertThat(filteredList).onProperty("index").contains(1);
    }

    @Test
    public void shouldFilterGivenCollectionByAfterRestrictionForLocalTimeType() {
        // given
        Restrictions.registerDateExtractor(LocalTime.class, new DateExtractor<LocalTime>() {

            @Override
            public Date extract(LocalTime d) {
                return d.toDateTimeToday().toDate();
            }
        });

        List<ObjectWithLocalTime> dates = Collections.newArrayList();
        dates.add(new ObjectWithLocalTime(1, LocalTime.now()));
        dates.add(new ObjectWithLocalTime(2, LocalTime.now().minusHours(2)));
        dates.add(new ObjectWithLocalTime(3, LocalTime.now().minusHours(5)));
        LocalTime threshold = LocalTime.now().minusHours(1);

        // when
        Collection<ObjectWithLocalTime> filteredList = Collections.filter(dates,
                Criteria.newCriteria().add(Restrictions.after("now", threshold)));

        // then
        assertThat(filteredList).hasSize(1);
        assertThat(filteredList).onProperty("index").contains(1);
    }

    @Test
    public void shouldFilterGivenCollectionByAfterRestrictionForLocalTimeTypeAndThresholdAsDate() {
        // given
        Restrictions.registerDateExtractor(LocalTime.class, new DateExtractor<LocalTime>() {

            @Override
            public Date extract(LocalTime d) {
                return d.toDateTimeToday().toDate();
            }
        });

        List<ObjectWithLocalTime> dates = Collections.newArrayList();
        dates.add(new ObjectWithLocalTime(1, LocalTime.now()));
        dates.add(new ObjectWithLocalTime(2, LocalTime.now().minusHours(2)));
        dates.add(new ObjectWithLocalTime(3, LocalTime.now().minusHours(5)));
        Date threshold = LocalTime.now().minusHours(1).toDateTimeToday().toDate();

        // when
        Collection<ObjectWithLocalTime> filteredList = Collections.filter(dates,
                Criteria.newCriteria().add(Restrictions.after("now", threshold)));

        // then
        assertThat(filteredList).hasSize(1);
        assertThat(filteredList).onProperty("index").contains(1);
    }

    @Test
    public void shouldFilterGivenCollectionByAfterRestrictionForLocalDateTimeType() {
        // given
        Restrictions.registerDateExtractor(LocalDateTime.class, new DateExtractor<LocalDateTime>() {

            @Override
            public Date extract(LocalDateTime d) {
                return d.toDate();
            }
        });

        List<ObjectWithLocalDateTime> dates = Collections.newArrayList();
        dates.add(new ObjectWithLocalDateTime(1, LocalDateTime.now()));
        dates.add(new ObjectWithLocalDateTime(2, LocalDateTime.now().minusHours(2)));
        dates.add(new ObjectWithLocalDateTime(3, LocalDateTime.now().minusHours(5)));
        LocalDateTime threshold = LocalDateTime.now().minusHours(1);

        // when
        Collection<ObjectWithLocalDateTime> filteredList = Collections.filter(dates,
                Criteria.newCriteria().add(Restrictions.after("now", threshold)));

        // then
        assertThat(filteredList).hasSize(1);
        assertThat(filteredList).onProperty("index").contains(1);
    }

    @Test
    public void shouldFilterGivenCollectionByAfterRestrictionForLocalDateTimeTypeAndThresholdAsDate() {
        // given
        Restrictions.registerDateExtractor(LocalDateTime.class, new DateExtractor<LocalDateTime>() {

            @Override
            public Date extract(LocalDateTime d) {
                return d.toDate();
            }
        });

        List<ObjectWithLocalDateTime> dates = Collections.newArrayList();
        dates.add(new ObjectWithLocalDateTime(1, LocalDateTime.now()));
        dates.add(new ObjectWithLocalDateTime(2, LocalDateTime.now().minusHours(2)));
        dates.add(new ObjectWithLocalDateTime(3, LocalDateTime.now().minusHours(5)));
        Date threshold = LocalDateTime.now().minusHours(1).toDate();

        // when
        Collection<ObjectWithLocalDateTime> filteredList = Collections.filter(dates,
                Criteria.newCriteria().add(Restrictions.after("now", threshold)));

        // then
        assertThat(filteredList).hasSize(1);
        assertThat(filteredList).onProperty("index").contains(1);
    }
}
