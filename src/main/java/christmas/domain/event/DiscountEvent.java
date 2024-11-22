package christmas.domain.event;

import christmas.common.dto.EventResultDto;
import christmas.domain.order.Orders;

import java.util.Optional;

public interface DiscountEvent extends Event {
	//TODO : 어떠한 주문에 대하여 할인을 적용해야함
	Optional<EventResultDto> applyEvent(Orders orders);
}
