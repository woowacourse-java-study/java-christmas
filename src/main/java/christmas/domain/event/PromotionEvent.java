package christmas.domain.event;

import christmas.common.dto.PromotionDto;
import christmas.domain.order.Orders;

import java.util.Optional;

public interface PromotionEvent extends DiscountEvent {
	Optional<PromotionDto> applyPromotion(Orders orders);
}
