package christmas.controller.orderControler;

import christmas.common.retryHandler.RetryHandler;
import christmas.domain.order.Orders;

import java.time.LocalDate;

public class OrderControllerRetryProxy implements OrderController {
	
	private final OrderController orderController;
	private final RetryHandler retryHandler;
	
	public OrderControllerRetryProxy(OrderController orderController, RetryHandler retryHandler) {
		this.orderController = orderController;
		this.retryHandler = retryHandler;
	}
	
	@Override
	public LocalDate getOrderDate() {
		return retryHandler.tryUntilSuccess(orderController::getOrderDate);
	}
	
	@Override
	public Orders getOrders(LocalDate orderDate) {
		return retryHandler.tryUntilSuccess(() -> orderController.getOrders(orderDate));
	}
}
