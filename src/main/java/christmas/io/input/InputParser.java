package christmas.io.input;

import christmas.common.dto.OrderCreateDto;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class InputParser {
	public LocalDate parseOrderDay(int year, int month, String input) {
		return LocalDate.of(year, month, Integer.parseInt(input));
	}
	
	public List<OrderCreateDto> parseOrders(String input) {
		return Arrays.stream(input.split(","))
				.map(this::parseOrder)
				.toList();
	}
	
	public OrderCreateDto parseOrder(String input) {
		String[] split = input.split("-");
		return new OrderCreateDto(split[0], Integer.parseInt(split[1]));
	}
}
