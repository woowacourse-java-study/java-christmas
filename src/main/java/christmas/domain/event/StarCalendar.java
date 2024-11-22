package christmas.domain.event;

import java.time.LocalDate;
import java.util.Arrays;

public enum StarCalendar {

	STAR_2023_12_3(LocalDate.of(2023, 12, 3)),
	STAR_2023_12_10(LocalDate.of(2023, 12, 10)),
	STAR_2023_12_17(LocalDate.of(2023, 12, 17)),
	STAR_2023_12_24(LocalDate.of(2023, 12, 24)),
	STAR_2023_12_25(LocalDate.of(2023, 12, 25)),
	STAR_2023_12_31(LocalDate.of(2023, 12, 31)),
	;
	
	private final LocalDate localDate;
	
	StarCalendar(LocalDate localDate) {
		this.localDate = localDate;
	}
	
	public static boolean isStarDate(LocalDate localDate) {
		return Arrays.stream(StarCalendar.values())
				.anyMatch(starCalendar -> starCalendar.localDate == localDate);
	}
}
