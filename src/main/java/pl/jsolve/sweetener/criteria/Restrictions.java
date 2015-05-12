package pl.jsolve.sweetener.criteria;

import pl.jsolve.sweetener.criteria.restriction.AndRestriction;
import pl.jsolve.sweetener.criteria.restriction.Between;
import pl.jsolve.sweetener.criteria.restriction.Contains;
import pl.jsolve.sweetener.criteria.restriction.Equals;
import pl.jsolve.sweetener.criteria.restriction.Greater;
import pl.jsolve.sweetener.criteria.restriction.GreaterOrEquals;
import pl.jsolve.sweetener.criteria.restriction.Less;
import pl.jsolve.sweetener.criteria.restriction.LessOrEquals;
import pl.jsolve.sweetener.criteria.restriction.Like;
import pl.jsolve.sweetener.criteria.restriction.NotBetween;
import pl.jsolve.sweetener.criteria.restriction.NotContains;
import pl.jsolve.sweetener.criteria.restriction.NotEquals;
import pl.jsolve.sweetener.criteria.restriction.NotLike;
import pl.jsolve.sweetener.criteria.restriction.NotNull;
import pl.jsolve.sweetener.criteria.restriction.Null;
import pl.jsolve.sweetener.criteria.restriction.OrRestriction;

public class Restrictions {

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

    public static Restriction or(Restriction... restrictions) {
        return new OrRestriction(restrictions);
    }

    public static Restriction and(Restriction... restrictions) {
        return new AndRestriction(restrictions);
    }

}
