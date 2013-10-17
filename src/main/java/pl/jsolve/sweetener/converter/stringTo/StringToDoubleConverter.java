package pl.jsolve.sweetener.converter.stringTo;

public class StringToDoubleConverter extends StringToAbstractConverter<Double> {

	@Override
	public Double convert(String source) {
		return Double.valueOf(source);
	}
}