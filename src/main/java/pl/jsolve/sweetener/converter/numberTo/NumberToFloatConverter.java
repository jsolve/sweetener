package pl.jsolve.sweetener.converter.numberTo;


public class NumberToFloatConverter extends NumberToAbstractConverter<Float> {

	@Override
	public Float convert(Number source) {
		return source.floatValue();
	}
}