package pl.jsolve.sweetener.converter;

import java.util.Calendar;

public class CalendarToLongConverter implements Converter<Calendar, Long> {

	@Override
	public Long convert(Calendar source) {
		return source.getTimeInMillis();
	}
}