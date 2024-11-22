package christmas.domain.event;

import java.time.LocalDate;
import java.time.YearMonth;

public class EventPeriod {
	
	private static final int COMPARE_OFFSET = 1;

	public static boolean isInPeriod(int year, int month, LocalDate orderDate) {
		LocalDate startDate = LocalDate.of(year, month, 1);
		LocalDate endDate = YearMonth.of(year, month).atEndOfMonth();
		return isInPeriod(startDate, endDate, orderDate);
	}
	
	public static boolean isInPeriod(int year, int month, int startDay, int endDay, LocalDate orderDate) {
		LocalDate startDate = LocalDate.of(year, month, startDay);
		LocalDate endDate = LocalDate.of(year, month, endDay);
		return isInPeriod(startDate, endDate, orderDate);
	}
	
	public static boolean isInPeriod(LocalDate startDate, LocalDate endDate, LocalDate orderDate) {
		return startDate.isBefore(orderDate.plusDays(COMPARE_OFFSET))
				&& endDate.isAfter(orderDate.minusDays(COMPARE_OFFSET));
	}
}
