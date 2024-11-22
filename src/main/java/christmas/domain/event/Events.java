package christmas.domain.event;

import christmas.common.dto.EventResultDto;
import christmas.common.dto.PromotionDto;
import christmas.domain.order.Orders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Events {
	
	private final List<DiscountEvent> discountEvents;
	private final List<PromotionEvent> promotionEvents;
	
	private Events(List<DiscountEvent> discountEvents, List<PromotionEvent> promotionEvents) {
		this.discountEvents = discountEvents;
		this.promotionEvents = promotionEvents;
	}
	
	public static Events of(int year, int month) {
		List<DiscountEvent> discountEvents = DiscountEvents.of(year, month);
		List<PromotionEvent> promotionEvents = PromotionEvents.of(year, month);
		return new Events(discountEvents, promotionEvents);
	}
	
	public List<PromotionDto> getPromotionMenus(Orders orders) {
		List<Optional<PromotionDto>> promotionMenus = new ArrayList<>();
		for (PromotionEvent promotionEvent : promotionEvents) {
			promotionMenus.add(promotionEvent.applyPromotion(orders));
		}
		return promotionMenus.stream()
				.flatMap(Optional::stream)
				.toList();
	}
	
	public List<EventResultDto> getEventList(Orders orders) {
		List<Optional<EventResultDto>> eventResults = new ArrayList<>();
		for (DiscountEvent discountEvent : discountEvents) {
			eventResults.add(discountEvent.applyEvent(orders));
		}
		for (PromotionEvent promotionEvent : promotionEvents) {
			eventResults.add(promotionEvent.applyEvent(orders));
		}
		return eventResults.stream()
				.flatMap(Optional::stream)
				.toList();
	}
	
	public int getEventCost(Orders orders) {
		return getEventTotalDiscountCost(orders) + getEventTotalPromotionCost(orders);
	}
	
	private int getEventTotalDiscountCost(Orders orders) {
		List<Optional<EventResultDto>> eventResults = new ArrayList<>();
		for (DiscountEvent discountEvent : discountEvents) {
			eventResults.add(discountEvent.applyEvent(orders));
		}
		return eventResults.stream()
				.flatMap(Optional::stream)
				.mapToInt(EventResultDto::discountAmount)
				.sum();
	}
	
	private int getEventTotalPromotionCost(Orders orders) {
		List<Optional<EventResultDto>> eventResults = new ArrayList<>();
		for (PromotionEvent promotionEvent : promotionEvents) {
			eventResults.add(promotionEvent.applyEvent(orders));
		}
		return eventResults.stream()
				.flatMap(Optional::stream)
				.mapToInt(EventResultDto::discountAmount)
				.sum();
	}
	
	public int getTotalCostAfterDiscount(Orders orders) {
		return orders.getTotalCost() - getEventTotalDiscountCost(orders);
	}
}
