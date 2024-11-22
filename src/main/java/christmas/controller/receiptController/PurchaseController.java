package christmas.controller.receiptController;

import christmas.domain.badge.Badge;
import christmas.domain.event.Events;
import christmas.domain.order.Orders;
import christmas.io.output.OutputHandler;

public class PurchaseController {
	
	private final OutputHandler outputHandler;
	
	public PurchaseController(OutputHandler outputHandler) {
		this.outputHandler = outputHandler;
	}
	
	public void handlePurchase(Orders orders, Events events) {
		outputHandler.handleOrderMenus(orders.getOrderMenus(), orders.getOrderMonth(), orders.getOrderDay());
		outputHandler.handleTotalCostBeforeEvent(orders.getTotalCost());
		outputHandler.handlePromotionMenus(events.getPromotionMenus(orders));
		outputHandler.handleEvents(events.getEventList(orders));
		outputHandler.handleTotalDiscountCost(events.getEventTotalDiscount(orders));
		outputHandler.handleTotalCostAfterEvent(events.getTotalCostAfterDiscount(orders));
		outputHandler.handleBadge(Badge.from(events.getEventTotalDiscount(orders)));
	}
}
