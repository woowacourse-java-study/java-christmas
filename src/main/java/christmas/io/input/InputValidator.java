package christmas.io.input;

import christmas.common.exception.CustomExceptions;

import java.util.regex.Pattern;

public class InputValidator {
	
	private static final Pattern ORDER_DAY_PATTERN = Pattern.compile("^\\d(\\d)?$");
	private static final Pattern ORDERS_PATTERN = Pattern.compile("^(.+-\\d+)(,.+-\\d+)*$");
	
	public void validateOrderDay(String input) {
		if (!ORDER_DAY_PATTERN.matcher(input).matches()) {
			throw CustomExceptions.ILLEGAL_DATE_FORMAT.get();
		}
	}
	
	public void validateOrders(String input) {
		if (!ORDERS_PATTERN.matcher(input).matches()) {
			throw CustomExceptions.ILLEGAL_ORDER_FORMAT.get();
		}
	}
}
