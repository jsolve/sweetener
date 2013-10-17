package pl.jsolve.sweetener.converter.stringTo;

import java.text.NumberFormat;
import java.text.ParseException;

import pl.jsolve.sweetener.exception.ConversionException;

public class StringToNumberConverter extends StringToAbstractConverter<Number> {

	@Override
	public Number convert(String source) {
		try {
			return NumberFormat.getNumberInstance().parse(source);
		} catch (ParseException e) {
			throw new ConversionException(String.class, Number.class, e);
		}
	}
}