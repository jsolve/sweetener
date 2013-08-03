package pl.jsolve.sweetener.math;

import java.math.BigDecimal;
import java.math.RoundingMode;

import pl.jsolve.sweetener.core.Objects;
import pl.jsolve.sweetener.exception.InvalidArgumentException;
import pl.jsolve.sweetener.exception.OutOfRangeException;
import pl.jsolve.sweetener.exception.OutOfRangeException.Range;
import pl.jsolve.sweetener.exception.ParseException;

public class Maths {

	private static Generator randomGenerator = new RandomGenerator();

	// Random algorithms:
	//
	// 1. calculate range
	// if range is negative:
	// 2a: generate random value (if generated value is really long, for example 0.9999999999999999999999999999 it is
	// automatically round to 1.0 ;( )
	// 3a: calcute result as larger numeric type
	// 4a: if generated value is equal to 1.0, substract one from the result
	// otherwise
	// 2b: generate random value
	// 3b: calculate result
	// 4b: if generated value is equal to 1.0, substract one from the result
	//

	public static byte random(byte lowerRange, byte upperRange) {
		return random(lowerRange, upperRange, randomGenerator);
	}

	public static byte random(byte lowerRange, byte upperRange, Generator generator) {
		if (lowerRange > upperRange) {
			throw new InvalidArgumentException("Lower range cannot be greater than or equal to upper range");
		}
		byte range = (byte) (upperRange - lowerRange);
		double random = generator.generate();
		byte result = 0;
		if (range < 0) {
			int intRange = upperRange - lowerRange + 1;

			result = (byte) (lowerRange + (int) (random * intRange));
		} else {
			result = (byte) (lowerRange + (int) (random * (range + 1)));
		}
		return random == 1.0 ? (byte) (result - 1) : result;
	}

	public static short random(short lowerRange, short upperRange) {
		return random(lowerRange, upperRange, randomGenerator);
	}

	public static short random(short lowerRange, short upperRange, Generator generator) {
		if (lowerRange > upperRange) {
			throw new InvalidArgumentException("Lower range cannot be greater than or equal to upper range");
		}
		short range = (short) (upperRange - lowerRange);
		double random = generator.generate();
		short result = 0;
		if (range < 0) {
			int intRange = upperRange - lowerRange + 1;

			result = (short) (lowerRange + (int) (random * intRange));
		} else {
			result = (short) (lowerRange + (int) (random * (range + 1)));
		}
		return random == 1.0 ? (short) (result - 1) : result;
	}

	public static int random(int lowerRange, int upperRange) {
		return random(lowerRange, upperRange, randomGenerator);
	}

	public static int random(int lowerRange, int upperRange, Generator generator) {
		if (lowerRange > upperRange) {
			throw new InvalidArgumentException("Lower range cannot be greater than or equal to upper range");
		}
		int range = (upperRange - lowerRange);
		double random = generator.generate();
		int result = 0;
		if (range < 0) {
			long longRange = ((long) upperRange) - ((long) lowerRange) + 1;

			result = (int) (lowerRange + (long) (random * longRange));
		} else {
			result = (int) (lowerRange + (long) (random * (range + 1)));
		}
		return random == 1.0 ? (result - 1) : result;
	}

	public static long random(long lowerRange, long upperRange) {
		return random(lowerRange, upperRange, randomGenerator);
	}

	public static long random(long lowerRange, long upperRange, Generator generator) {
		if (lowerRange > upperRange) {
			throw new InvalidArgumentException("Lower range cannot be greater than or equal to upper range");
		}
		long range = upperRange - lowerRange;
		double random = generator.generate();
		long result = 0;
		if (range < 0) {
			BigDecimal bigIntegerRange = BigDecimal.valueOf(upperRange).subtract(BigDecimal.valueOf(lowerRange))
					.add(BigDecimal.ONE);

			result = BigDecimal.valueOf(lowerRange).add(BigDecimal.valueOf(random).multiply(bigIntegerRange))
					.setScale(0, RoundingMode.HALF_UP).longValue();
		} else {
			result = (long) (lowerRange + (long) (random * (range + 1)));
		}
		return random == 1.0 ? result - 1 : result;
	}

	public static float random(float lowerRange, float upperRange) {
		return random(lowerRange, upperRange, randomGenerator);
	}

	public static float random(float lowerRange, float upperRange, Generator generator) {
		if (lowerRange > upperRange) {
			throw new InvalidArgumentException("Lower range cannot be greater than or equal to upper range");
		}
		float range = (upperRange - lowerRange);
		float result = 0;
		if (range < 0) {
			double longRange = ((double) upperRange) - ((double) lowerRange);

			result = (float) (lowerRange + (double) (generator.generate() * longRange));
		} else {
			result = (float) (lowerRange + (double) (generator.generate() * range));
		}
		return result;
	}

	public static double random(double lowerRange, double upperRange) {
		return random(lowerRange, upperRange, randomGenerator);
	}

	public static double random(double lowerRange, double upperRange, Generator generator) {
		if (lowerRange > upperRange) {
			throw new InvalidArgumentException("Lower range cannot be greater than or equal to upper range");
		}
		double range = upperRange - lowerRange;
		double result = 0;
		if (range < 0) {
			BigDecimal bigIntegerRange = BigDecimal.valueOf(upperRange).subtract(BigDecimal.valueOf(lowerRange));

			result = BigDecimal.valueOf(lowerRange).add(BigDecimal.valueOf(generator.generate()).multiply(bigIntegerRange))
					.setScale(0, RoundingMode.HALF_UP).longValue();
		} else {
			result = (double) (lowerRange + (double) (generator.generate() * (range)));
		}
		return result;
	}

	public static double distance(int x1, int y1, int x2, int y2) {
		return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}

	public static double distance(int x1, int y1, int z1, int x2, int y2, int z2) {
		return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) + Math.pow(z2 - z1, 2));
	}

	public static double distance(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}

	public static double distance(double x1, double y1, double z1, double x2, double y2, double z2) {
		return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) + Math.pow(z2 - z1, 2));
	}

	public static double distance(Point2D p1, Point2D p2) {
		return distance(p1.getX(), p1.getY(), p2.getX(), p2.getY());
	}

	public static double distance(Point3D p1, Point3D p2) {
		return distance(p1.getX(), p1.getY(), p1.getZ(), p2.getX(), p2.getY(), p2.getZ());
	}

	public static byte parseByte(String value) {
		return parseByte(value, ParseContext.TRY_ADJUST);
	}

	public static byte parseByte(String value, ParseContext context) {
		try {
			value = Objects.nullSafeToString(value);
			return Byte.valueOf(value.trim());
		} catch (NumberFormatException ex) {
			return parseByBigDecimal(value.trim(), context, Byte.MIN_VALUE, Byte.MAX_VALUE, (byte) 0);
		}
	}

	public static short parseShort(String value) {
		return parseShort(value, ParseContext.TRY_ADJUST);
	}

	public static short parseShort(String value, ParseContext context) {
		try {
			value = Objects.nullSafeToString(value);
			return Short.valueOf(value.trim());
		} catch (NumberFormatException ex) {
			return parseByBigDecimal(value.trim(), context, Short.MIN_VALUE, Short.MAX_VALUE, (short) 0);
		}
	}

	public static int parseInteger(String value) {
		return parseInteger(value, ParseContext.TRY_ADJUST);
	}

	public static int parseInteger(String value, ParseContext context) {
		try {
			value = Objects.nullSafeToString(value);
			return Integer.valueOf(value.trim());
		} catch (NumberFormatException ex) {
			return parseByBigDecimal(value.trim(), context, Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
		}
	}

	public static long parseLong(String value) {
		return parseLong(value, ParseContext.TRY_ADJUST);
	}

	public static long parseLong(String value, ParseContext context) {
		try {
			value = Objects.nullSafeToString(value);
			return Long.valueOf(value.trim());
		} catch (NumberFormatException ex) {
			return parseByBigDecimal(value.trim(), context, Long.MIN_VALUE, Long.MAX_VALUE, 0L);
		}
	}

	public static float parseFloat(String value) {
		return parseFloat(value, ParseContext.TRY_ADJUST);
	}

	public static float parseFloat(String value, ParseContext context) {
		try {
			value = Objects.nullSafeToString(value);
			Float f = Float.valueOf(value.trim());
			if (f == Float.NEGATIVE_INFINITY || f == Float.POSITIVE_INFINITY) {
				throw new NumberFormatException("Overflow");
			}
			return f;
		} catch (NumberFormatException ex) {
			return parseByBigDecimal(value.trim(), context, Float.MIN_VALUE, Float.MAX_VALUE, 0.0f);
		}
	}

	public static double parseDouble(String value) {
		return parseDouble(value, ParseContext.TRY_ADJUST);
	}

	public static double parseDouble(String value, ParseContext context) {
		try {
			value = Objects.nullSafeToString(value);
			Double d = Double.valueOf(value.trim());
			if (d == Double.NEGATIVE_INFINITY || d == Double.POSITIVE_INFINITY) {
				throw new NumberFormatException("Overflow");
			}
			return d;
		} catch (NumberFormatException ex) {
			return parseByBigDecimal(value.trim(), context, Double.MIN_VALUE, Double.MAX_VALUE, 0.0);
		}
	}

	private static <T> T parseByBigDecimal(String value, ParseContext context, T minValue, T maxValue, T zero) {
		try {
			BigDecimal valueAsBigDecimal = new BigDecimal(value);
			if (minValue instanceof Byte) {
				valueAsBigDecimal.byteValueExact();
			}
			if (minValue instanceof Short) {
				valueAsBigDecimal.shortValueExact();
			}
			if (minValue instanceof Integer) {
				valueAsBigDecimal.intValueExact();
			}
			if (minValue instanceof Long) {
				valueAsBigDecimal.longValueExact();
			}
			if (minValue instanceof Float) {
				Float f = valueAsBigDecimal.floatValue();
				if (f == Float.NEGATIVE_INFINITY || f == Float.POSITIVE_INFINITY) {
					throw new ArithmeticException("Overflow");
				}
			}
			if (minValue instanceof Double) {
				Double d = valueAsBigDecimal.doubleValue();
				if (d == Double.NEGATIVE_INFINITY || d == Double.POSITIVE_INFINITY) {
					throw new ArithmeticException("Overflow");
				}
			}
		} catch (Exception ex) {
			return adjustToContext(value, context, ex, minValue, maxValue, zero);
		}
		return zero;
	}

	private static <T> T adjustToContext(String value, ParseContext context, Exception ex, T minValue, T maxValue, T zero) {
		switch (context) {
		case ALWAYS_ZERO:
			return zero;
		case ZERO_WHEN_INCORRECT:
			try {
				parseException(value, ex);
			} catch (ParseException e) {
				return zero;
			}
			break;
		case TRY_ADJUST:
			try {
				parseException(value, ex);
			} catch (ParseException e) {
				return zero;
			} catch (OutOfRangeException oe) {
				if (oe.getRange() == Range.MIN) {
					return minValue;
				}
				return maxValue;
			}
		case EXCEPTION:
		default:
			parseException(value, ex);
			break;
		}
		return zero;
	}

	private static void parseException(String value, Exception ex) {
		String message = ex.getMessage();
		if (message != null && message.startsWith("Overflow")) {
			if (value.charAt(0) == '-') {
				throw new OutOfRangeException(ex, Range.MIN);
			} else {
				throw new OutOfRangeException(ex, Range.MAX);
			}
		}
		throw new ParseException(ex);
	}
}