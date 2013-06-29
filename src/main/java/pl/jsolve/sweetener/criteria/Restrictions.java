package pl.jsolve.sweetener.criteria;

import pl.jsolve.sweetener.criteria.restriction.Contains;
import pl.jsolve.sweetener.criteria.restriction.Eq;
import pl.jsolve.sweetener.criteria.restriction.Greater;
import pl.jsolve.sweetener.criteria.restriction.GreaterOrEq;
import pl.jsolve.sweetener.criteria.restriction.Less;
import pl.jsolve.sweetener.criteria.restriction.LessOrEq;
import pl.jsolve.sweetener.criteria.restriction.Like;
import pl.jsolve.sweetener.criteria.restriction.NotContains;
import pl.jsolve.sweetener.criteria.restriction.NotEq;
import pl.jsolve.sweetener.criteria.restriction.NotLike;
import pl.jsolve.sweetener.criteria.restriction.NotNull;
import pl.jsolve.sweetener.criteria.restriction.Null;

public class Restrictions {

    public static Restriction eq(String field, Object value) {
	return new Eq(field, value);
    }

    public static Restriction eq(String field, Object value, boolean ignoreCase) {
	return new Eq(field, value, ignoreCase);
    }

    public static Restriction notEq(String field, Object value) {
	return new NotEq(field, value);
    }

    public static Restriction notEq(String field, Object value, boolean ignoreCase) {
	return new NotEq(field, value, ignoreCase);
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

    public static Restriction greaterOrEq(String field, Number value) {
	return new GreaterOrEq(field, value);
    }

    public static Restriction lessOrEq(String field, Number value) {
	return new LessOrEq(field, value);
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
}
