package christmas.controller;

import christmas.controller.eventController.EventController;
import christmas.controller.orderControler.OrderController;
import christmas.controller.receiptController.PurchaseController;
import christmas.domain.event.Events;
import christmas.domain.order.Orders;

public class ControllerFacade {
	
	private final OrderController orderController;
	private final EventController eventController;
	private final PurchaseController purchaseController;
	
	public ControllerFacade(OrderController orderController, EventController eventController, PurchaseController purchaseController) {
		this.orderController = orderController;
		this.eventController = eventController;
		this.purchaseController = purchaseController;
	}
	
	public void run() {
		Orders orders = orderController.getOrders();
		Events events = eventController.getEvents();
		purchaseController.handlePurchase(orders, events);
	}
}
