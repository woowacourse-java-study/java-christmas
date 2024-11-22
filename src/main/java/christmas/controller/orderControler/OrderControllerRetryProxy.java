package christmas.controller.orderControler;

import christmas.common.retryHandler.RetryHandler;
import christmas.domain.order.Orders;

public class OrderControllerRetryProxy implements OrderController {
	
	private final OrderController orderController;
	private final RetryHandler retryHandler;
	
	public OrderControllerRetryProxy(OrderController orderController, RetryHandler retryHandler) {
		this.orderController = orderController;
		this.retryHandler = retryHandler;
	}
	
	@Override
	public Orders getOrders() {
		return retryHandler.tryUntilSuccess(orderController::getOrders);
	}
}
