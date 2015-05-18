package pl.jsolve.sweetener.criteria;

import java.util.HashMap;
import java.util.Map;

import pl.jsolve.sweetener.criteria.restriction.After;
import pl.jsolve.sweetener.criteria.restriction.AggregationRange;
import pl.jsolve.sweetener.criteria.restriction.And;
import pl.jsolve.sweetener.criteria.restriction.Avg;
import pl.jsolve.sweetener.criteria.restriction.Before;
import pl.jsolve.sweetener.criteria.restriction.Between;
import pl.jsolve.sweetener.criteria.restriction.Contains;
import pl.jsolve.sweetener.criteria.restriction.Count;
import pl.jsolve.sweetener.criteria.restriction.DateExtractor;
import pl.jsolve.sweetener.criteria.restriction.Equals;
import pl.jsolve.sweetener.criteria.restriction.Greater;
import pl.jsolve.sweetener.criteria.restriction.GreaterOrEquals;
import pl.jsolve.sweetener.criteria.restriction.In;
import pl.jsolve.sweetener.criteria.restriction.Less;
import pl.jsolve.sweetener.criteria.restriction.LessOrEquals;
import pl.jsolve.sweetener.criteria.restriction.Like;
import pl.jsolve.sweetener.criteria.restriction.Max;
import pl.jsolve.sweetener.criteria.restriction.NotBetween;
import pl.jsolve.sweetener.criteria.restriction.NotContains;
import pl.jsolve.sweetener.criteria.restriction.NotEquals;
import pl.jsolve.sweetener.criteria.restriction.NotIn;
import pl.jsolve.sweetener.criteria.restriction.NotLike;
import pl.jsolve.sweetener.criteria.restriction.NotNull;
import pl.jsolve.sweetener.criteria.restriction.Null;
import pl.jsolve.sweetener.criteria.restriction.Or;
import pl.jsolve.sweetener.criteria.restriction.Sum;

public class Restrictions {

    private static Map<Class<?>, DateExtractor> dateExtractors = new HashMap<Class<?>, DateExtractor>();

    public static <T> void registerDateExtractor(Class<T> clazz, DateExtractor<T> extractor) {
        dateExtractors.put(clazz, extractor);
    }

    public static void clearDateExtractors() {
        dateExtractors.clear();
    }

    public static Restriction equals(String field, Object value) {
        return new Equals(field, value);
    }

    public static Restriction equals(String field, Object value, boolean ignoreCase) {
        return new Equals(field, value, ignoreCase);
    }

    public static Restriction notEquals(String field, Object value) {
        return new NotEquals(field, value);
    }

    public static Restriction notEquals(String field, Object value, boolean ignoreCase) {
        return new NotEquals(field, value, ignoreCase);
    }

    public static Restriction isNull(String field) {
        return new Null(field);
    }

    public static Restriction isNotNull(String field) {
        return new NotNull(field);
    }

    public static Restriction greater(String field, Number value) {
        return new Greater(field, value);
    }

    public static Restriction less(String field, Number value) {
        return new Less(field, value);
    }

    public static Restriction greaterOrEquals(String field, Number value) {
        return new GreaterOrEquals(field, value);
    }

    public static Restriction lessOrEquals(String field, Number value) {
        return new LessOrEquals(field, value);
    }

    public static Restriction between(String field, Number minValue, Number maxValue) {
        return new Between(field, minValue, maxValue);
    }

    public static Restriction between(String field, Number minValue, Number maxValue, boolean leftInclusive,
            boolean rightInclusive) {
        return new Between(field, minValue, maxValue, leftInclusive, rightInclusive);
    }

    public static Restriction notBetween(String field, Number minValue, Number maxValue) {
        return new NotBetween(field, minValue, maxValue);
    }

    public static Restriction notBetween(String field, Number minValue, Number maxValue, boolean leftInclusive,
            boolean rightInclusive) {
        return new NotBetween(field, minValue, maxValue, leftInclusive, rightInclusive);
    }

    public static Restriction like(String field, String value) {
        return new Like(field, value);
    }

    public static Restriction notLike(String field, String value) {
        return new NotLike(field, value);
    }

    public static Restriction contains(String field, Object... values) {
        return new Contains(field, true, values);
    }

    public static Restriction containsAny(String field, Object... values) {
        return new Contains(field, false, values);
    }

    public static Restriction notContains(String field, Object... values) {
        return new NotContains(field, true, values);
    }

    public static Restriction notContainsAny(String field, Object... values) {
        return new NotContains(field, false, values);
    }

    public static Restriction in(String field, Object... values) {
        return new In(field, values);
    }

    public static Restriction notIn(String field, Object... values) {
        return new NotIn(field, values);
    }

    public static Restriction before(String field, Object value) {
        return new Before(field, value, dateExtractors);
    }

    public static Restriction after(String field, Object value) {
        return new After(field, value, dateExtractors);
    }

    public static Restriction avg(String field, Double avg, AggregationRange aggregationRange) {
        return new Avg(field, avg, null, aggregationRange);
    }

    public static Restriction avg(String field, Double leftRange, Double rightRange, AggregationRange aggregationRange) {
        return new Avg(field, leftRange, rightRange, aggregationRange);
    }

    public static Restriction sum(String field, Number sum, AggregationRange aggregationRange) {
        return new Sum(field, sum, null, aggregationRange);
    }

    public static Restriction sum(String field, Number leftRange, Number rightRange, AggregationRange aggregationRange) {
        return new Sum(field, leftRange, rightRange, aggregationRange);
    }

    public static Restriction count(String field, Integer count, AggregationRange aggregationRange) {
        return new Count(field, count, null, aggregationRange);
    }

    public static Restriction count(String field, Integer leftRange, Integer rightRange,
            AggregationRange aggregationRange) {
        return new Count(field, leftRange, rightRange, aggregationRange);
    }

    public static Restriction max(String field, Number count, AggregationRange aggregationRange) {
        return new Max(field, count, null, aggregationRange);
    }

    public static Restriction max(String field, Number leftRange, Number rightRange, AggregationRange aggregationRange) {
        return new Max(field, leftRange, rightRange, aggregationRange);
    }

    public static Restriction or(Restriction... restrictions) {
        return new Or(restrictions);
    }

    public static Restriction and(Restriction... restrictions) {
        return new And(restrictions);
    }

}
