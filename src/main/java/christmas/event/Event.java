package christmas.event;

import christmas.calendar.EventPeriod;
import christmas.menu.Menu;

import java.time.LocalDate;
import java.util.Optional;

public abstract class Event {
	
	private final String name;
	private final Menu targetMenu;
	private final EventType eventType;
	private final EventPeriod eventPeriod;
	
	public Event(String name, Menu targetMenu, EventType eventType, EventPeriod eventPeriod) {
		this.name = name;
		this.targetMenu = targetMenu;
		this.eventType = eventType;
		this.eventPeriod = eventPeriod;
	}
	
	public boolean isPromotionEvent() {
		return eventType == EventType.PROMOTION;
	}
	
	public Optional<Menu> applyPromotion(Menu menu, LocalDate date) {
		if (!isPromotionEvent() || targetMenu != menu || !eventPeriod.isInPeriod(date)) {
			return Optional.empty();
		}
		return Optional.of(targetMenu);
	}
	
	//TODO : 어떠한 주문에 대하여 할인을 적용해야함
	//TODO : 어떠한 주문에 대하여 프로모션 상품을 제공해야함
}
