package christmas.event;

import christmas.ParamsValidator;
import christmas.menu.Menu;
import christmas.order.Order;
import christmas.order.Orders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Events {
	
	private final List<Event> events;
	
	public Events(List<Event> events) {
		this.events = events;
	}
	
	//TODO : 스스로 생성되어야 함
	
	//TODO : 증정메뉴 반환
	public List<Menu> getPromotionMenus(Orders orders) {
		List<Optional<Menu>> promotionMenus = new ArrayList<>();
		for (Event event : events) {
			promotionMenus.add(event.applyPromotion(orders));
		}
		return promotionMenus.stream()
				.flatMap(Optional::stream)
				.toList();
	}
	
	//TODO : 혜택내역 반환
	public List<EventResult> getEventList(Orders orders) {
		List<Optional<EventResult>> eventResults = new ArrayList<>();
		for (Event event : events) {
			eventResults.add(event.applyEvent(orders));
		}
		return eventResults.stream()
				.flatMap(Optional::stream)
				.toList();
	}
	
	//TODO : 총 혜택금액 반환
	public int getEventTotalDiscount(Orders orders) {
		List<Optional<EventResult>> eventResults = new ArrayList<>();
		for (Event event : events) {
			eventResults.add(event.applyEvent(orders));
		}
		return eventResults.stream()
				.flatMap(Optional::stream)
				.mapToInt(EventResult::discountAmount)
				.sum();
	}
	
}
