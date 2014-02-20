package pl.jsolve.sweetener.mapper.date;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import pl.jsolve.sweetener.mapper.annotationDriven.AnnotationDrivenMapper;
import pl.jsolve.sweetener.mapper.date.stub.StudentWithDates;
import pl.jsolve.sweetener.mapper.date.stub.StudentWithDatesSnapshot;

public class AnnotationDrivenMapperDateTest {

	private static final Long FIRST_SEMPTEMBER_2008_TIMESTAMP = 1220227200100L;
	private static final Date FIRST_SEMPTEMBER_2008_DATE = new Date(FIRST_SEMPTEMBER_2008_TIMESTAMP);

	@Test
	public void shouldMapStudentWithDatesToItsSnapshot() {
		StudentWithDates studentWithDates = new StudentWithDates();
		studentWithDates.setDateAsLong(FIRST_SEMPTEMBER_2008_TIMESTAMP);
		studentWithDates.setDate(FIRST_SEMPTEMBER_2008_DATE);

		// when
		StudentWithDatesSnapshot studentWithDatesSnapshot = AnnotationDrivenMapper.map(studentWithDates, StudentWithDatesSnapshot.class);

		// then
		assertThat(studentWithDatesSnapshot.getCalendar().getTime()).isEqualTo(studentWithDates.getDate());
		assertThat(studentWithDatesSnapshot.getDate().getTime()).isEqualTo(studentWithDates.getDateAsLong());
	}

	@Test
	public void shouldConvertStudentWithDatesSnapshotToStudentWithDates() {
		// given
		Calendar calendar = Calendar.getInstance();
		StudentWithDatesSnapshot studentWithDatesSnapshot = new StudentWithDatesSnapshot();
		studentWithDatesSnapshot.setCalendar(calendar);
		studentWithDatesSnapshot.setDate(calendar.getTime());

		// when
		StudentWithDates studentWithDates = AnnotationDrivenMapper.map(studentWithDatesSnapshot, StudentWithDates.class);

		// then
		assertThat(studentWithDates.getDate()).isEqualTo(studentWithDatesSnapshot.getCalendar().getTime());
		assertThat(studentWithDates.getDateAsLong()).isEqualTo(studentWithDatesSnapshot.getDate().getTime());
	}
}
