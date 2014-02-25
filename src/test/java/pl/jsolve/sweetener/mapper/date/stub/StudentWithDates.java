package pl.jsolve.sweetener.mapper.date.stub;

import java.util.Date;

import pl.jsolve.sweetener.mapper.annotationdriven.annotation.Map;
import pl.jsolve.sweetener.mapper.annotationdriven.annotation.MappableTo;

@MappableTo(StudentWithDatesSnapshot.class)
public class StudentWithDates {

	@Map(to = "date")
	private Long dateAsLong;

	@Map(to = "calendar")
	private Date date;

	public Long getDateAsLong() {
		return dateAsLong;
	}

	public void setDateAsLong(Long dateAsLong) {
		this.dateAsLong = dateAsLong;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
