package pl.jsolve.sweetener.converter.numberTo;


public class NumberToDoubleConverter extends NumberToAbstractConverter<Double> {

	@Override
	public Double convert(Number source) {
		return source.doubleValue();
	}
}