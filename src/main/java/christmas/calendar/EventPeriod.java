package christmas.calendar;

import java.time.LocalDate;

public class EventPeriod {
	
	private static final int COMPARE_OFFSET = 1;
	
	private final LocalDate startDate;
	private final LocalDate endDate;
	
	public EventPeriod(LocalDate startDate, LocalDate endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public boolean isInPeriod(LocalDate date) {
		return startDate.minusDays(COMPARE_OFFSET).isBefore(date)
				&& date.minusDays(COMPARE_OFFSET).isBefore(endDate);
	}
}
