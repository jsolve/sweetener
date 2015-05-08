package pl.jsolve.sweetener.math;

import pl.jsolve.sweetener.collection.Collections;
import pl.jsolve.sweetener.core.Objects;
import pl.jsolve.sweetener.exception.InvalidArgumentException;
import pl.jsolve.sweetener.exception.OutOfRangeException;
import pl.jsolve.sweetener.exception.OutOfRangeException.Range;
import pl.jsolve.sweetener.exception.ParseException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Maths {

    private static Generator randomGenerator = new RandomGenerator();

    // Random algorithms:
    //
    // 1. calculate range
    // if range is negative:
    // 2a: generate random value (if generated value is really long, for example 0.9999999999999999999999999999 it is
    // automatically round to 1.0 ;( )
    // 3a: calculate result as larger numeric type
    // 4a: if generated value is equal to 1.0, subtract one from the result
    // otherwise
    // 2b: generate random value
    // 3b: calculate result
    // 4b: if generated value is equal to 1.0, subtract one from the result
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
        byte result;
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
        short result;
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
        int result;
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
        long result;
        if (range < 0) {
            BigDecimal bigIntegerRange = BigDecimal.valueOf(upperRange).subtract(BigDecimal.valueOf(lowerRange))
                    .add(BigDecimal.ONE);

            result = BigDecimal.valueOf(lowerRange).add(BigDecimal.valueOf(random).multiply(bigIntegerRange))
                    .setScale(0, RoundingMode.HALF_UP).longValue();
        } else {
            result = lowerRange + (long) (random * (range + 1));
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
        float result;
        if (range < 0) {
            double longRange = ((double) upperRange) - ((double) lowerRange);

            result = (float) (lowerRange + generator.generate() * longRange);
        } else {
            result = (float) (lowerRange + generator.generate() * range);
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
        double result;
        if (range < 0) {
            BigDecimal bigIntegerRange = BigDecimal.valueOf(upperRange).subtract(BigDecimal.valueOf(lowerRange));

            result = BigDecimal.valueOf(lowerRange)
                    .add(BigDecimal.valueOf(generator.generate()).multiply(bigIntegerRange))
                    .setScale(0, RoundingMode.HALF_UP).longValue();
        } else {
            result = lowerRange + generator.generate() * (range);
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

    private static <T> T adjustToContext(String value, ParseContext context, Exception ex, T minValue, T maxValue,
            T zero) {
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

    public static double normalize(byte value, byte minValue, byte maxValue) {
        if (value < minValue || value > maxValue) {
            throw new OutOfRangeException(String.format("The value %d is out of the range: <%d, %d>", value, minValue,
                    maxValue));
        }
        return (double) (value - minValue) / (double) (maxValue - minValue);
    }

    public static double normalize(short value, short minValue, short maxValue) {
        if (value < minValue || value > maxValue) {
            throw new OutOfRangeException(String.format("The value %d is out of the range: <%d, %d>", value, minValue,
                    maxValue));
        }
        return (double) (value - minValue) / (double) (maxValue - minValue);
    }

    public static double normalize(int value, int minValue, int maxValue) {
        if (value < minValue || value > maxValue) {
            throw new OutOfRangeException(String.format("The value %d is out of the range: <%d, %d>", value, minValue,
                    maxValue));
        }
        return (double) (value - minValue) / (double) (maxValue - minValue);
    }

    public static double normalize(long value, long minValue, long maxValue) {
        if (value < minValue || value > maxValue) {
            throw new OutOfRangeException(String.format("The value %d is out of the range: <%d, %d>", value, minValue,
                    maxValue));
        }
        return (double) (value - minValue) / (double) (maxValue - minValue);
    }

    public static double normalize(float value, float minValue, float maxValue) {
        if (value < minValue || value > maxValue) {
            throw new OutOfRangeException(String.format("The value %f is out of the range: <%f; %f>", value, minValue,
                    maxValue));
        }
        return (double) (value - minValue) / (double) (maxValue - minValue);
    }

    public static double normalize(double value, double minValue, double maxValue) {
        if (value < minValue || value > maxValue) {
            throw new OutOfRangeException(String.format("The value %f is out of the range: <%f; %f>", value, minValue,
                    maxValue));
        }
        return (value - minValue) / (maxValue - minValue);
    }

    // MinMax for collection

    public static MinMaxValue<Byte> minMaxByte(Collection<Byte> values) {
        if (values == null) {
            throw new InvalidArgumentException("Collection cannot be null");
        }

        Byte min = Byte.MAX_VALUE;
        Byte max = Byte.MIN_VALUE;

        for (Byte value : values) {
            if (value < min) {
                min = value;
            }
            if (value > max) {
                max = value;
            }
        }
        return new MinMaxValue<Byte>(min, max);
    }

    public static MinMaxValue<Short> minMaxShort(Collection<Short> values) {
        if (values == null) {
            throw new InvalidArgumentException("Collection cannot be null");
        }

        Short min = Short.MAX_VALUE;
        Short max = Short.MIN_VALUE;

        for (Short value : values) {
            if (value < min) {
                min = value;
            }
            if (value > max) {
                max = value;
            }
        }
        return new MinMaxValue<Short>(min, max);
    }

    public static MinMaxValue<Integer> minMaxInteger(Collection<Integer> values) {
        if (values == null) {
            throw new InvalidArgumentException("Collection cannot be null");
        }

        Integer min = Integer.MAX_VALUE;
        Integer max = Integer.MIN_VALUE;

        for (Integer value : values) {
            if (value < min) {
                min = value;
            }
            if (value > max) {
                max = value;
            }
        }
        return new MinMaxValue<Integer>(min, max);
    }

    public static MinMaxValue<Long> minMaxLong(Collection<Long> values) {
        if (values == null) {
            throw new InvalidArgumentException("Collection cannot be null");
        }

        Long min = Long.MAX_VALUE;
        Long max = Long.MIN_VALUE;

        for (Long value : values) {
            if (value < min) {
                min = value;
            }
            if (value > max) {
                max = value;
            }
        }
        return new MinMaxValue<Long>(min, max);
    }

    public static MinMaxValue<Float> minMaxFloat(Collection<Float> values) {
        if (values == null) {
            throw new InvalidArgumentException("Collection cannot be null");
        }

        Float min = Float.MAX_VALUE;
        Float max = Float.MIN_VALUE;

        for (Float value : values) {
            if (value < min) {
                min = value;
            }
            if (value > max) {
                max = value;
            }
        }
        return new MinMaxValue<Float>(min, max);
    }

    public static MinMaxValue<Double> minMaxDouble(Collection<Double> values) {
        if (values == null) {
            throw new InvalidArgumentException("Collection cannot be null");
        }

        Double min = Double.MAX_VALUE;
        Double max = Double.MIN_VALUE;

        for (Double value : values) {
            if (value < min) {
                min = value;
            }
            if (value > max) {
                max = value;
            }
        }
        return new MinMaxValue<Double>(min, max);
    }

    // MinMax for varArg, array

    public static MinMaxValue<Byte> minMaxByte(Byte... values) {
        if (values == null) {
            throw new InvalidArgumentException("Collection cannot be null");
        }

        Byte min = Byte.MAX_VALUE;
        Byte max = Byte.MIN_VALUE;

        for (Byte value : values) {
            if (value < min) {
                min = value;
            }
            if (value > max) {
                max = value;
            }
        }
        return new MinMaxValue<Byte>(min, max);
    }

    public static MinMaxValue<Short> minMaxShort(Short... values) {
        if (values == null) {
            throw new InvalidArgumentException("Collection cannot be null");
        }

        Short min = Short.MAX_VALUE;
        Short max = Short.MIN_VALUE;

        for (Short value : values) {
            if (value < min) {
                min = value;
            }
            if (value > max) {
                max = value;
            }
        }
        return new MinMaxValue<Short>(min, max);
    }

    public static MinMaxValue<Integer> minMaxInteger(Integer... values) {
        if (values == null) {
            throw new InvalidArgumentException("Collection cannot be null");
        }

        Integer min = Integer.MAX_VALUE;
        Integer max = Integer.MIN_VALUE;

        for (Integer value : values) {
            if (value < min) {
                min = value;
            }
            if (value > max) {
                max = value;
            }
        }
        return new MinMaxValue<Integer>(min, max);
    }

    public static MinMaxValue<Long> minMaxLong(Long... values) {
        if (values == null) {
            throw new InvalidArgumentException("Collection cannot be null");
        }

        Long min = Long.MAX_VALUE;
        Long max = Long.MIN_VALUE;

        for (Long value : values) {
            if (value < min) {
                min = value;
            }
            if (value > max) {
                max = value;
            }
        }
        return new MinMaxValue<Long>(min, max);
    }

    public static MinMaxValue<Float> minMaxFloat(Float... values) {
        if (values == null) {
            throw new InvalidArgumentException("Collection cannot be null");
        }

        Float min = Float.MAX_VALUE;
        Float max = Float.MIN_VALUE;

        for (Float value : values) {
            if (value < min) {
                min = value;
            }
            if (value > max) {
                max = value;
            }
        }
        return new MinMaxValue<Float>(min, max);
    }

    public static MinMaxValue<Double> minMaxDouble(Double... values) {
        if (values == null) {
            throw new InvalidArgumentException("Collection cannot be null");
        }

        Double min = Double.MAX_VALUE;
        Double max = Double.MIN_VALUE;

        for (Double value : values) {
            if (value < min) {
                min = value;
            }
            if (value > max) {
                max = value;
            }
        }
        return new MinMaxValue<Double>(min, max);
    }

    // Normalize collection

    @SuppressWarnings("unchecked")
    public static Collection<Double> normalizeByte(Collection<Byte> values) {
        Collection<Double> result = Collections.createNewInstanceOfCollection(values.getClass());
        MinMaxValue<Byte> minMaxByte = minMaxByte(values);

        for (Byte value : values) {
            result.add(normalize(value, minMaxByte.getMin(), minMaxByte.getMax()));
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public static Collection<Double> normalizeShort(Collection<Short> values) {
        Collection<Double> result = Collections.createNewInstanceOfCollection(values.getClass());
        MinMaxValue<Short> minMaxByte = minMaxShort(values);

        for (Short value : values) {
            result.add(normalize(value, minMaxByte.getMin(), minMaxByte.getMax()));
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public static Collection<Double> normalizeInt(Collection<Integer> values) {
        Collection<Double> result = Collections.createNewInstanceOfCollection(values.getClass());
        MinMaxValue<Integer> minMaxByte = minMaxInteger(values);

        for (Integer value : values) {
            result.add(normalize(value, minMaxByte.getMin(), minMaxByte.getMax()));
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public static Collection<Double> normalizeLong(Collection<Long> values) {
        Collection<Double> result = Collections.createNewInstanceOfCollection(values.getClass());
        MinMaxValue<Long> minMaxByte = minMaxLong(values);

        for (Long value : values) {
            result.add(normalize(value, minMaxByte.getMin(), minMaxByte.getMax()));
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public static Collection<Double> normalizeFloat(Collection<Float> values) {
        Collection<Double> result = Collections.createNewInstanceOfCollection(values.getClass());
        MinMaxValue<Float> minMaxByte = minMaxFloat(values);

        for (Float value : values) {
            result.add(normalize(value, minMaxByte.getMin(), minMaxByte.getMax()));
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public static Collection<Double> normalizeDouble(Collection<Double> values) {
        Collection<Double> result = Collections.createNewInstanceOfCollection(values.getClass());
        MinMaxValue<Double> minMaxByte = minMaxDouble(values);

        for (Double value : values) {
            result.add(normalize(value, minMaxByte.getMin(), minMaxByte.getMax()));
        }
        return result;
    }

    // Normalize array

    public static Collection<Double> normalizeByte(Byte... values) {
        List<Double> result = new ArrayList<Double>();
        MinMaxValue<Byte> minMaxByte = minMaxByte(values);

        for (Byte value : values) {
            result.add(normalize(value, minMaxByte.getMin(), minMaxByte.getMax()));
        }
        return result;
    }

    public static Collection<Double> normalizeShort(Short... values) {
        List<Double> result = new ArrayList<Double>();
        MinMaxValue<Short> minMaxByte = minMaxShort(values);

        for (Short value : values) {
            result.add(normalize(value, minMaxByte.getMin(), minMaxByte.getMax()));
        }
        return result;
    }

    public static Collection<Double> normalizeInt(Integer... values) {
        List<Double> result = new ArrayList<Double>();
        MinMaxValue<Integer> minMaxByte = minMaxInteger(values);

        for (Integer value : values) {
            result.add(normalize(value, minMaxByte.getMin(), minMaxByte.getMax()));
        }
        return result;
    }

    public static Collection<Double> normalizeLong(Long... values) {
        List<Double> result = new ArrayList<Double>();
        MinMaxValue<Long> minMaxByte = minMaxLong(values);

        for (Long value : values) {
            result.add(normalize(value, minMaxByte.getMin(), minMaxByte.getMax()));
        }
        return result;
    }

    public static Collection<Double> normalizeFloat(Float... values) {
        List<Double> result = new ArrayList<Double>();
        MinMaxValue<Float> minMaxByte = minMaxFloat(values);

        for (Float value : values) {
            result.add(normalize(value, minMaxByte.getMin(), minMaxByte.getMax()));
        }
        return result;
    }

    public static List<Double> normalizeDouble(Double... values) {
        List<Double> result = new ArrayList<Double>();
        MinMaxValue<Double> minMaxByte = minMaxDouble(values);

        for (Double value : values) {
            result.add(normalize(value, minMaxByte.getMin(), minMaxByte.getMax()));
        }
        return result;
    }

    public static byte adjustToRange(byte value, byte min, byte max) {
        if (value >= min && value <= max) {
            return value;
        }
        if (value > max) {
            return max;
        }
        return min;
    }

    public static short adjustToRange(short value, short min, short max) {
        if (value >= min && value <= max) {
            return value;
        }
        if (value > max) {
            return max;
        }
        return min;
    }

    public static int adjustToRange(int value, int min, int max) {
        if (value >= min && value <= max) {
            return value;
        }
        if (value > max) {
            return max;
        }
        return min;
    }

    public static long adjustToRange(long value, long min, long max) {
        if (value >= min && value <= max) {
            return value;
        }
        if (value > max) {
            return max;
        }
        return min;
    }

    public static float adjustToRange(float value, float min, float max) {
        if (value >= min && value <= max) {
            return value;
        }
        if (value > max) {
            return max;
        }
        return min;
    }

    public static double adjustToRange(double value, double min, double max) {
        if (value >= min && value <= max) {
            return value;
        }
        if (value > max) {
            return max;
        }
        return min;
    }

    public static double averageByte(Collection<Byte> values) {
        if (values == null || values.size() == 0) {
            throw new InvalidArgumentException("Collection cannot be null or empty");
        }
        double sum = 0.0;
        for (Byte b : values) {
            sum = sum + b;
        }
        return sum / values.size();
    }

    public static double averageShort(Collection<Short> values) {
        if (values == null || values.size() == 0) {
            throw new InvalidArgumentException("Collection cannot be null or empty");
        }
        double sum = 0.0;
        for (Short b : values) {
            sum = sum + b;
        }
        return sum / values.size();
    }

    public static double averageInteger(Collection<Integer> values) {
        if (values == null || values.size() == 0) {
            throw new InvalidArgumentException("Collection cannot be null or empty");
        }
        double sum = 0.0;
        for (Integer b : values) {
            sum = sum + b;
        }
        return sum / values.size();
    }

    public static double averageLong(Collection<Long> values) {
        if (values == null || values.size() == 0) {
            throw new InvalidArgumentException("Collection cannot be null or empty");
        }
        double sum = 0.0;
        for (Long b : values) {
            sum = sum + b;
        }
        return sum / values.size();
    }

    public static double averageFloat(Collection<Float> values) {
        if (values == null || values.size() == 0) {
            throw new InvalidArgumentException("Collection cannot be null or empty");
        }
        double sum = 0.0;
        for (Float b : values) {
            sum = sum + b;
        }
        return sum / values.size();
    }

    public static double averageDouble(Collection<Double> values) {
        if (values == null || values.size() == 0) {
            throw new InvalidArgumentException("Collection cannot be null or empty");
        }
        double sum = 0.0;
        for (Double b : values) {
            sum = sum + b;
        }
        return sum / values.size();
    }

    public static double averageByte(Byte... values) {
        if (values == null || values.length == 0) {
            throw new InvalidArgumentException("Array cannot be null or empty");
        }
        double sum = 0.0;
        for (Byte b : values) {
            sum = sum + b;
        }
        return sum / values.length;
    }

    public static double averageShort(Short... values) {
        if (values == null || values.length == 0) {
            throw new InvalidArgumentException("Array cannot be null or empty");
        }
        double sum = 0.0;
        for (Short b : values) {
            sum = sum + b;
        }
        return sum / values.length;
    }

    public static double averageInteger(Integer... values) {
        if (values == null || values.length == 0) {
            throw new InvalidArgumentException("Array cannot be null or empty");
        }
        double sum = 0.0;
        for (Integer b : values) {
            sum = sum + b;
        }
        return sum / values.length;
    }

    public static double averageLong(Long... values) {
        if (values == null || values.length == 0) {
            throw new InvalidArgumentException("Array cannot be null or empty");
        }
        double sum = 0.0;
        for (Long b : values) {
            sum = sum + b;
        }
        return sum / values.length;
    }

    public static double averageFloat(Float... values) {
        if (values == null || values.length == 0) {
            throw new InvalidArgumentException("Array cannot be null or empty");
        }
        double sum = 0.0;
        for (Float b : values) {
            sum = sum + b;
        }
        return sum / values.length;
    }

    public static double averageDouble(Double... values) {
        if (values == null || values.length == 0) {
            throw new InvalidArgumentException("Array cannot be null or empty");
        }
        double sum = 0.0;
        for (Double b : values) {
            sum = sum + b;
        }
        return sum / values.length;
    }

    public static double varianceByte(Collection<Byte> values) {
        if (values == null || values.size() == 0) {
            throw new InvalidArgumentException("Collection cannot be null or empty");
        }
        double avg = averageByte(values);
        double t = 0.0;
        for (Byte value : values) {
            t += Math.pow((value - avg), 2);
        }
        return t / values.size();
    }

    public static double varianceShort(Collection<Short> values) {
        if (values == null || values.size() == 0) {
            throw new InvalidArgumentException("Collection cannot be null or empty");
        }
        double avg = averageShort(values);
        double t = 0.0;
        for (Short value : values) {
            t += Math.pow((value - avg), 2);
        }
        return t / values.size();
    }

    public static double varianceInteger(Collection<Integer> values) {
        if (values == null || values.size() == 0) {
            throw new InvalidArgumentException("Collection cannot be null or empty");
        }
        double avg = averageInteger(values);
        double t = 0.0;
        for (Integer value : values) {
            t += Math.pow((value - avg), 2);
        }
        return t / values.size();
    }

    public static double varianceLong(Collection<Long> values) {
        if (values == null || values.size() == 0) {
            throw new InvalidArgumentException("Collection cannot be null or empty");
        }
        double avg = averageLong(values);
        double t = 0.0;
        for (Long value : values) {
            t += Math.pow((value - avg), 2);
        }
        return t / values.size();
    }

    public static double varianceFloat(Collection<Float> values) {
        if (values == null || values.size() == 0) {
            throw new InvalidArgumentException("Collection cannot be null or empty");
        }
        double avg = averageFloat(values);
        double t = 0.0;
        for (Float value : values) {
            t += Math.pow((value - avg), 2);
        }
        return t / values.size();
    }

    public static double varianceDouble(Collection<Double> values) {
        if (values == null || values.size() == 0) {
            throw new InvalidArgumentException("Collection cannot be null or empty");
        }
        double avg = averageDouble(values);
        double t = 0.0;
        for (Double value : values) {
            t += Math.pow((value - avg), 2);
        }
        return t / values.size();
    }

    public static double varianceByte(Byte... values) {
        if (values == null || values.length == 0) {
            throw new InvalidArgumentException("Array cannot be null or empty");
        }
        double avg = averageByte(values);
        double t = 0.0;
        for (Byte value : values) {
            t += Math.pow((value - avg), 2);
        }
        return t / values.length;
    }

    public static double varianceShort(Short... values) {
        if (values == null || values.length == 0) {
            throw new InvalidArgumentException("Array cannot be null or empty");
        }
        double avg = averageShort(values);
        double t = 0.0;
        for (Short value : values) {
            t += Math.pow((value - avg), 2);
        }
        return t / values.length;
    }

    public static double varianceInteger(Integer... values) {
        if (values == null || values.length == 0) {
            throw new InvalidArgumentException("Array cannot be null or empty");
        }
        double avg = averageInteger(values);
        double t = 0.0;
        for (Integer value : values) {
            t += Math.pow((value - avg), 2);
        }
        return t / values.length;
    }

    public static double varianceLong(Long... values) {
        if (values == null || values.length == 0) {
            throw new InvalidArgumentException("Collection cannot be null or empty");
        }
        double avg = averageLong(values);
        double t = 0.0;
        for (Long value : values) {
            t += Math.pow((value - avg), 2);
        }
        return t / values.length;
    }

    public static double varianceFloat(Float... values) {
        if (values == null || values.length == 0) {
            throw new InvalidArgumentException("Array cannot be null or empty");
        }
        double avg = averageFloat(values);
        double t = 0.0;
        for (Float value : values) {
            t += Math.pow((value - avg), 2);
        }
        return t / values.length;
    }

    public static double varianceDouble(Double... values) {
        if (values == null || values.length == 0) {
            throw new InvalidArgumentException("Array cannot be null or empty");
        }
        double avg = averageDouble(values);
        double t = 0.0;
        for (Double value : values) {
            t += Math.pow((value - avg), 2);
        }
        return t / values.length;
    }

    public static double standardDeviationByte(Collection<Byte> values) {
        return Math.sqrt(varianceByte(values));
    }

    public static double standardDeviationShort(Collection<Short> values) {
        return Math.sqrt(varianceShort(values));
    }

    public static double standardDeviationInteger(Collection<Integer> values) {
        return Math.sqrt(varianceInteger(values));
    }

    public static double standardDeviationLong(Collection<Long> values) {
        return Math.sqrt(varianceLong(values));
    }

    public static double standardDeviationFloat(Collection<Float> values) {
        return Math.sqrt(varianceFloat(values));
    }

    public static double standardDeviationDouble(Collection<Double> values) {
        return Math.sqrt(varianceDouble(values));
    }

    public static double standardDeviationByte(Byte... values) {
        return Math.sqrt(varianceByte(values));
    }

    public static double standardDeviationShort(Short... values) {
        return Math.sqrt(varianceShort(values));
    }

    public static double standardDeviationInteger(Integer... values) {
        return Math.sqrt(varianceInteger(values));
    }

    public static double standardDeviationLong(Long... values) {
        return Math.sqrt(varianceLong(values));
    }

    public static double standardDeviationFloat(Float... values) {
        return Math.sqrt(varianceFloat(values));
    }

    public static double standardDeviationDouble(Double... values) {
        return Math.sqrt(varianceDouble(values));
    }
}