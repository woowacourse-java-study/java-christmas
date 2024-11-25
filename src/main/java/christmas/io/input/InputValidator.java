package christmas.io.input;

import christmas.common.exception.CustomExceptions;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class InputValidator {
	
	private static final Pattern ORDERS_PATTERN = Pattern.compile("^(.+-[1-9][0-9]*)(,.+-[1-9][0-9]*)*$");
	
	public void validateOrderDay(int year, int month, String day) {
		try {
			LocalDate.of(year, month, Integer.parseInt(day));
		} catch (Exception e) {
			throw CustomExceptions.ILLEGAL_DATE.get();
		}
	}
	
	public void validateOrders(String input) {
		if (!ORDERS_PATTERN.matcher(input).matches()) {
			throw CustomExceptions.INVALID_ORDER.get();
		}
	}
}
