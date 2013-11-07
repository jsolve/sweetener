package pl.jsolve.sweetener.converter;

import java.util.Date;

public class LongToDateConverter implements Converter<Long, Date> {

	@Override
	public Date convert(Long source) {
		return new Date(source);
	}
}