package christmas.controller;

import christmas.common.dto.OrdersCreateDto;
import christmas.domain.badge.Badge;
import christmas.domain.event.Events;
import christmas.domain.order.Orders;
import christmas.io.input.InputHandler;
import christmas.io.output.OutputHandler;
import christmas.service.dateProvider.DateProvider;

public class ChristmasController {
	
	private final InputHandler inputHandler;
	private final OutputHandler outputHandler;
	private final DateProvider dateProvider;
	
	public ChristmasController(InputHandler inputHandler, OutputHandler outputHandler, DateProvider dateProvider) {
		this.inputHandler = inputHandler;
		this.outputHandler = outputHandler;
		this.dateProvider = dateProvider;
	}
	
	public void start() {
		OrdersCreateDto ordersCreateDto = inputHandler.getOrders(dateProvider.getYear(), dateProvider.getYear());
		Orders orders = Orders.from(ordersCreateDto);
		Events events = Events.of(dateProvider.getYear(), dateProvider.getMonth());
		
		outputHandler.handleOrderMenus(orders.getOrderMenus());
		outputHandler.handleTotalCostBeforeEvent(orders.getTotalCost());
		outputHandler.handlePromotionMenus(events.getPromotionMenus(orders));
		outputHandler.handleEvents(events.getEventList(orders));
		outputHandler.handleTotalDiscountCost(events.getEventTotalDiscount(orders));
		outputHandler.handleTotalCostAfterEvent(events.getTotalCostAfterDiscount(orders));
		outputHandler.handleBadge(Badge.from(events.getEventTotalDiscount(orders)));
	}
}
