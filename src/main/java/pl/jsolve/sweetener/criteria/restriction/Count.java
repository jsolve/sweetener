package pl.jsolve.sweetener.criteria.restriction;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import pl.jsolve.sweetener.criteria.FieldRestriction;
import pl.jsolve.sweetener.criteria.restriction.CollectionExecutor.Executor;
import pl.jsolve.sweetener.exception.AccessToFieldException;

public class Count implements FieldRestriction {

    private final String fieldName;
    private final Integer leftRange;
    private final AggregationRange aggregationRange;
    private final Integer rightRange;

    public Count(String fieldName, Integer leftRange, Integer rightRange, AggregationRange aggregationRange) {
        this.fieldName = fieldName;
        this.leftRange = leftRange;
        this.rightRange = rightRange == null ? leftRange : rightRange;
        this.aggregationRange = aggregationRange;
    }

    @Override
    public String getFieldName() {
        return fieldName;
    }

    @Override
    public RestrictionLevel getRestrictionLevel() {
        return RestrictionLevel.LOW;
    }

    @Override
    public boolean satisfies(Object fieldValue) {
        if (fieldValue == null) {
            return false;
        }

        CollectionExecutor executor = new CollectionExecutor();
        return executor.perform(fieldValue, new Executor() {

            @Override
            public boolean execute(Object[] elements) {
                return forArray(elements);
            }

            @Override
            public boolean execute(Collection elements) {
                return forCollection(elements);
            }

            @Override
            public boolean execute(Map elements) {
                throw new AccessToFieldException("Type mismatch. Expected List, Set or Array but was "
                        + elements.getClass().getCanonicalName());
            }
        });
    }

    private boolean forArray(Object[] fieldValueAsArray) {
        return checkCount(fieldValueAsArray.length);
    }

    private boolean forCollection(Collection<?> fieldValueAsCollection) {
        return checkCount(fieldValueAsCollection.size());
    }

    private boolean checkCount(double count) {
        switch (aggregationRange) {
        case LESS:
            return count < leftRange;
        case GREATER:
            return count > leftRange;
        case BETWEEN:
            return count >= leftRange && count <= rightRange;
        case NOT_BETWEEN:
            return count < leftRange || count > rightRange;
        }
        return false;
    }
}
