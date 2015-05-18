package pl.jsolve.sweetener.criteria.restriction;

import java.util.Collection;
import java.util.Map;

import pl.jsolve.sweetener.criteria.FieldRestriction;
import pl.jsolve.sweetener.criteria.restriction.CollectionExecutor.Executor;
import pl.jsolve.sweetener.exception.AccessToFieldException;

public class Max implements FieldRestriction {

    private final static Double DELTA = 0.000001;

    private final String fieldName;
    private final Number leftRange;
    private final AggregationRange aggregationRange;
    private final Number rightRange;

    public Max(String fieldName, Number leftRange, Number rightRange, AggregationRange aggregationRange) {
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
        Double maxValue = Double.MIN_VALUE;
        for (int i = 0; i < fieldValueAsArray.length; i++) {
            if (!(fieldValueAsArray[i] instanceof Number)) {
                throw new AccessToFieldException("Type mismatch. Expected Number but was "
                        + fieldValueAsArray[i].getClass().getCanonicalName());
            }
            double doubleValue = ((Number) fieldValueAsArray[i]).doubleValue();
            if (maxValue < doubleValue) {
                maxValue = doubleValue;
            }
        }
        return checkMax(maxValue);
    }

    private boolean forCollection(Collection<?> fieldValueAsCollection) {
        Double maxValue = Double.MIN_VALUE;
        for (Object o : fieldValueAsCollection) {
            if (!(o instanceof Number)) {
                throw new AccessToFieldException("Type mismatch. Expected Number but was "
                        + o.getClass().getCanonicalName());
            }
            double doubleValue = ((Number) o).doubleValue();
            if (maxValue < doubleValue) {
                maxValue = doubleValue;
            }
        }
        return checkMax(maxValue);
    }

    private boolean checkMax(Double maxValue) {
        switch (aggregationRange) {
        case LESS:
            return maxValue < leftRange.doubleValue();
        case GREATER:
            return maxValue > leftRange.doubleValue();
        case BETWEEN:
            return maxValue >= leftRange.doubleValue() && maxValue <= rightRange.doubleValue();
        case NOT_BETWEEN:
            return maxValue < leftRange.doubleValue() || maxValue > rightRange.doubleValue();
        case EQUALS:
            return Math.abs(maxValue - leftRange.doubleValue()) <= DELTA;
        }
        return false;
    }
}
