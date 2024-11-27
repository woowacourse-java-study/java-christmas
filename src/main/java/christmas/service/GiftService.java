package christmas.service;

import christmas.domain.Menu;
import christmas.domain.Order;

public class GiftService {

    private static final int GIFT_THRESHOLD = 120_000;

    public Menu checkGiftEligibility(Order order) {
        if (order.getTotalPriceBeforeDiscount().compareTo(new java.math.BigDecimal(GIFT_THRESHOLD)) >= 0) {
            return Menu.CHAMPAGNE;
        }
        return null;
    }
}
