package christmas.domain.event;

import christmas.common.dto.EventResultDto;
import christmas.domain.order.Orders;

import java.util.Optional;

public interface DiscountEvent extends Event {
	Optional<EventResultDto> applyEvent(Orders orders);
}
