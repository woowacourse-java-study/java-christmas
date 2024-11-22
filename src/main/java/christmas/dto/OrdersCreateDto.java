package christmas.dto;

import java.time.LocalDate;
import java.util.List;

public record OrdersCreateDto(
		List<OrderCreateDto> orderCreateDtos,
		LocalDate orderDate
) {
}
