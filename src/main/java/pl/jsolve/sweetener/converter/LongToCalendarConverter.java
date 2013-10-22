package pl.jsolve.sweetener.converter;

import java.util.Calendar;

public class LongToCalendarConverter implements Converter<Long, Calendar> {

	@Override
	public Calendar convert(Long source) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(source);
		return calendar;
	}
}