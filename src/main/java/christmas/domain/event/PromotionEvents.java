package christmas.domain.event;

import christmas.common.dto.PromotionDto;
import christmas.domain.menu.Menu;
import christmas.domain.order.Orders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum PromotionEvents implements PromotionEvent {
	CHAMPAGNE_PROMOTION {
		@Override
		public Optional<PromotionDto> applyPromotion(Orders orders) {
			if (!canApply(orders)) {
				return Optional.empty();
			}
			return Optional.of(new PromotionDto(Menu.샴페인, 1));
		}
		
		private static boolean canApply(Orders orders) {
			return orders.getTotalCost() >= 120000;
		}
		
		@Override
		public boolean isEventYearMonth(int year, int month) {
			return year == 2024 && month == 12;
		}
	},
	;
	
	public static List<PromotionEvent> of(int year, int month) {
		return Arrays.stream(PromotionEvents.values())
				.filter(promotionEvent -> promotionEvent.isEventYearMonth(year, month))
				.map(promotionEvent -> (PromotionEvent) promotionEvent)
				.toList();
	}
	
}
