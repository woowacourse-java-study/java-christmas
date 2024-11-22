package christmas.event;

import christmas.menu.Menu;
import christmas.order.Orders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Events {
	
	private final List<DiscountEvent> discountEvents;
	private final List<PromotionEvent> promotionEvents;
	
	public Events(List<DiscountEvent> discountEvents, List<PromotionEvent> promotionEvents) {
		this.discountEvents = discountEvents;
		this.promotionEvents = promotionEvents;
	}
	
	//TODO : 스스로 생성되어야 함
	
	//TODO : 증정메뉴 반환
	public List<Menu> getPromotionMenus(Orders orders) {
		List<Optional<Menu>> promotionMenus = new ArrayList<>();
		for (PromotionEvent promotionEvent : promotionEvents) {
			promotionMenus.add(promotionEvent.applyPromotion(orders));
		}
		return promotionMenus.stream()
				.flatMap(Optional::stream)
				.toList();
	}
	
	//TODO : 혜택내역 반환
	public List<EventResult> getEventList(Orders orders) {
		List<Optional<EventResult>> eventResults = new ArrayList<>();
		for (DiscountEvent discountEvent : discountEvents) {
			eventResults.add(discountEvent.applyEvent(orders));
		}
		return eventResults.stream()
				.flatMap(Optional::stream)
				.toList();
	}
	
	//TODO : 총 혜택금액 반환
	public int getEventTotalDiscount(Orders orders) {
		List<Optional<EventResult>> eventResults = new ArrayList<>();
		for (DiscountEvent discountEvent : discountEvents) {
			eventResults.add(discountEvent.applyEvent(orders));
		}
		return eventResults.stream()
				.flatMap(Optional::stream)
				.mapToInt(EventResult::discountAmount)
				.sum();
	}
	
}
