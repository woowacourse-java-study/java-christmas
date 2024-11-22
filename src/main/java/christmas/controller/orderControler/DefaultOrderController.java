package christmas.controller.orderControler;

import christmas.common.dto.OrdersCreateDto;
import christmas.domain.order.Orders;
import christmas.io.input.InputHandler;
import christmas.service.dateProvider.DateProvider;

public class DefaultOrderController implements OrderController {
	
	private final InputHandler inputHandler;
	private final DateProvider dateProvider;
	
	public DefaultOrderController(InputHandler inputHandler, DateProvider dateProvider) {
		this.inputHandler = inputHandler;
		this.dateProvider = dateProvider;
	}
	
	@Override
	public Orders getOrders() {
		OrdersCreateDto ordersCreateDto = inputHandler.getOrders(dateProvider.getYear(), dateProvider.getMonth());
		return Orders.from(ordersCreateDto);
	}
}
