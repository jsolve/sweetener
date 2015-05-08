package pl.jsolve.sweetener.math;

import pl.jsolve.sweetener.exception.InvalidArgumentException;

public abstract class Generator {

    public abstract double random();

    public final double generate() {
        double randomValue = random();
        if (randomValue < 0 || randomValue > 1) {
            throw new InvalidArgumentException("Generated value cannot be less than 0 and greater than 1");
        }
        return randomValue;
    }
}
