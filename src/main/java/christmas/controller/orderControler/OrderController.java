package christmas.controller.orderControler;

import christmas.domain.order.Orders;

import java.time.LocalDate;

public interface OrderController {
	
	LocalDate getOrderDate();
	
	Orders getOrders(LocalDate orderDate);
}
