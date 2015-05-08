package pl.jsolve.sweetener.math;

public class MinMaxValue<T extends Number> {

    private final T min;
    private final T max;

    public MinMaxValue(T min, T max) {
        this.min = min;
        this.max = max;
    }

    public T getMin() {
        return min;
    }

    public T getMax() {
        return max;
    }

}
