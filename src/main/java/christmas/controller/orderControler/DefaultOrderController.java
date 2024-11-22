package christmas.controller.orderControler;

import christmas.common.dto.OrdersCreateDto;
import christmas.domain.order.Orders;
import christmas.io.input.InputHandler;
import christmas.service.dateProvider.DateProvider;

import java.time.LocalDate;

public class DefaultOrderController implements OrderController {
	
	private final InputHandler inputHandler;
	private final DateProvider dateProvider;
	
	public DefaultOrderController(InputHandler inputHandler, DateProvider dateProvider) {
		this.inputHandler = inputHandler;
		this.dateProvider = dateProvider;
	}
	
	@Override
	public LocalDate getOrderDate() {
		return inputHandler.getOrderDate(dateProvider.getYear(), dateProvider.getMonth());
	}
	
	@Override
	public Orders getOrders(LocalDate orderDate) {
		return Orders.from(new OrdersCreateDto(inputHandler.getOrders(), orderDate));
	}
}
