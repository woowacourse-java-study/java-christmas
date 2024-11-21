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
		for (Order order : orders.getOrders()) {
			for (Event event : getPromotionEvents()) {
				promotionMenus.add(event.applyPromotion(order.getMenu(), orders.getDate()));
			}
		}
		return promotionMenus.stream()
				.filter(Optional::isPresent)
				.map(Optional::get)
				.toList();
	}
	
	private List<Event> getPromotionEvents() {
		return events.stream()
				.filter(Event::isPromotionEvent)
				.toList();
	}
	
	//TODO : 혜택내역 반환
	//TODO : 총 혜택금액 반환
}
