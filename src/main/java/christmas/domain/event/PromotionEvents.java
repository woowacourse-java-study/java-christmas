package christmas.domain.event;

import christmas.common.dto.EventResultDto;
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
		
		@Override
		public Optional<EventResultDto> applyEvent(Orders orders) {
			if (!canApply(orders)) {
				return Optional.empty();
			}
			return Optional.of(new EventResultDto(Menu.샴페인.name(), Menu.샴페인.calculateTotalCost(1)));
		}
		
		private static boolean canApply(Orders orders) {
			return orders.getTotalCost() >= 120000
					&& EventPeriod.isInPeriod(2023, 12, orders.getDate());
		}
		
		@Override
		public boolean isEventYearMonth(int year, int month) {
			return year == 2023 && month == 12;
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
