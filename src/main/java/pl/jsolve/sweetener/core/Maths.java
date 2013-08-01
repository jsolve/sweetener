package pl.jsolve.sweetener.core;

import pl.jsolve.sweetener.exception.InvalidArgumentException;

public class Maths {

	public static byte randomByte(byte lowerRange, byte upperRange) {
		if (lowerRange > upperRange) {
			throw new InvalidArgumentException("Lower range cannot be greater than or equal to upper range");
		}
		byte range = (byte) (upperRange - lowerRange);
		if (range < 0) { // if range is negative, we need numeric type with bigger range. In this case: int
			int intRange = upperRange - lowerRange + 1;
			return (byte) (lowerRange + (int)(Math.random() * intRange));
		}
		else {
			return (byte) (lowerRange + (int)(Math.random() * (range + 1)));
		}
	}


}
