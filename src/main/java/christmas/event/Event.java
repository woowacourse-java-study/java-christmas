package christmas.event;

import christmas.menu.Menu;
import christmas.order.Orders;

import java.util.Optional;

public interface Event {
	boolean isPromotionEvent();
	
	//TODO : 어떠한 주문에 대하여 프로모션 상품을 제공해야함
	Optional<Menu> applyPromotion(Orders orders);
	
	//TODO : 어떠한 주문에 대하여 할인을 적용해야함
	Optional<EventResult> applyEvent(Orders orders);
}
