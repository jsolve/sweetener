package pl.jsolve.sweetener.criteria.restriction;

import java.util.Collection;
import java.util.Map;

import pl.jsolve.sweetener.criteria.FieldRestriction;
import pl.jsolve.sweetener.criteria.restriction.CollectionExecutor.Executor;
import pl.jsolve.sweetener.exception.AccessToFieldException;

public class Sum implements FieldRestriction {

    private final String fieldName;
    private final Double leftRange;
    private final AggregationRange aggregationRange;
    private final Double rightRange;

    public Sum(String fieldName, Double leftRange, Double rightRange, AggregationRange aggregationRange) {
        this.fieldName = fieldName;
        this.leftRange = leftRange;
        this.rightRange = rightRange == null ? leftRange : rightRange;
        this.aggregationRange = aggregationRange;
    }

    @Override
    public String getFieldName() {
        return fieldName;
    }

    public Double getValue() {
        return leftRange;
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
        double sum = 0.0;
        for (int i = 0; i < fieldValueAsArray.length; i++) {
            if (!(fieldValueAsArray[i] instanceof Number)) {
                throw new AccessToFieldException("Type mismatch. Expected Number but was "
                        + fieldValueAsArray[i].getClass().getCanonicalName());
            }
            sum += ((Number) fieldValueAsArray[i]).doubleValue();
        }
        return checkSum(sum);
    }

    private boolean forCollection(Collection<?> fieldValueAsCollection) {
        double sum = 0.0;
        for (Object o : fieldValueAsCollection) {
            if (!(o instanceof Number)) {
                throw new AccessToFieldException("Type mismatch. Expected Number but was "
                        + o.getClass().getCanonicalName());
            }
            sum += ((Number) o).doubleValue();
        }
        return checkSum(sum);
    }

    private boolean checkSum(double sum) {
        switch (aggregationRange) {
        case LESS:
            return sum < leftRange;
        case GREATER:
            return sum > leftRange;
        case BETWEEN:
            return sum >= leftRange && sum <= rightRange;
        case NOT_BETWEEN:
            return sum < leftRange || sum > rightRange;
        }
        return false;
    }
}
