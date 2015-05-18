package pl.jsolve.sweetener.criteria.restriction;

import java.util.Collection;
import java.util.Map;

import pl.jsolve.sweetener.criteria.FieldRestriction;
import pl.jsolve.sweetener.criteria.restriction.CollectionExecutor.Executor;
import pl.jsolve.sweetener.exception.AccessToFieldException;

public class Min implements FieldRestriction {

    private final static Double DELTA = 0.000001;

    private final String fieldName;
    private final Number leftRange;
    private final AggregationRange aggregationRange;
    private final Number rightRange;

    public Min(String fieldName, Number leftRange, Number rightRange, AggregationRange aggregationRange) {
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
        Double minValue = Double.MAX_VALUE;
        for (int i = 0; i < fieldValueAsArray.length; i++) {
            if (!(fieldValueAsArray[i] instanceof Number)) {
                throw new AccessToFieldException("Type mismatch. Expected Number but was "
                        + fieldValueAsArray[i].getClass().getCanonicalName());
            }
            double doubleValue = ((Number) fieldValueAsArray[i]).doubleValue();
            if (minValue > doubleValue) {
                minValue = doubleValue;
            }
        }
        return checkMin(minValue);
    }

    private boolean forCollection(Collection<?> fieldValueAsCollection) {
        Double minValue = Double.MAX_VALUE;
        for (Object o : fieldValueAsCollection) {
            if (!(o instanceof Number)) {
                throw new AccessToFieldException("Type mismatch. Expected Number but was "
                        + o.getClass().getCanonicalName());
            }
            double doubleValue = ((Number) o).doubleValue();
            if (minValue > doubleValue) {
                minValue = doubleValue;
            }
        }
        return checkMin(minValue);
    }

    private boolean checkMin(Double minValue) {
        switch (aggregationRange) {
        case LESS:
            return minValue < leftRange.doubleValue();
        case GREATER:
            return minValue > leftRange.doubleValue();
        case BETWEEN:
            return minValue >= leftRange.doubleValue() && minValue <= rightRange.doubleValue();
        case NOT_BETWEEN:
            return minValue < leftRange.doubleValue() || minValue > rightRange.doubleValue();
        case EQUALS:
            return Math.abs(minValue - leftRange.doubleValue()) <= DELTA;
        case NOT_EQUALS:
            return Math.abs(minValue - leftRange.doubleValue()) > DELTA;
        }
        return false;
    }
}
