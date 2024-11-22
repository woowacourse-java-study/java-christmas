package christmas.event;

import christmas.menu.Menu;
import christmas.order.Orders;

import java.util.Optional;

public enum PromotionEventList implements PromotionEvent {
	CHAMPAGNE_PROMOTION {
		@Override
		public Optional<Menu> applyPromotion(Orders orders) {
			if (!canApply(orders)) {
				return Optional.empty();
			}
			return Optional.of(Menu.샴페인);
		}
		
		private static boolean canApply(Orders orders) {
			return orders.getTotalCost() >= 120000;
		}
	},
	;
	
}
