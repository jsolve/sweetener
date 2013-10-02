package pl.jsolve.sweetener.converter.stringTo;

public class StringToFloatConverter extends StringToAbstractConverter<Float> {

	@Override
	public Float convert(String source) {
		return Float.valueOf(source);
	}
}