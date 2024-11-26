package christmas.util;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DateUtils {

    public static DayOfWeek getDayOfWeek(int dayOfDecember) {
        if (dayOfDecember < 1 || dayOfDecember > 31) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 1에서 31 사이의 숫자를 입력해 주세요.");
        }
        LocalDate date = LocalDate.of(2023, 12, dayOfDecember);
        return date.getDayOfWeek();
    }

    public static boolean isWeekday(int dayOfDecember) {
        DayOfWeek dayOfWeek = getDayOfWeek(dayOfDecember);
        return dayOfWeek != DayOfWeek.FRIDAY && dayOfWeek != DayOfWeek.SATURDAY;
    }

    public static boolean isWeekend(int dayOfDecember) {
        DayOfWeek dayOfWeek = getDayOfWeek(dayOfDecember);
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }
}
