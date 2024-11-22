package christmas.event;

import christmas.menu.Menu;
import christmas.order.Orders;

import java.util.Optional;

public interface DiscountEvent {
	//TODO : 어떠한 주문에 대하여 할인을 적용해야함
	Optional<EventResult> applyEvent(Orders orders);
}
