package christmas.service.discountpolicy;

import christmas.domain.Order;

import java.math.BigDecimal;

public class ChristmasCountdownDiscount implements DiscountPolicy {

    @Override
    public BigDecimal applyDiscount(Order order, int date) {
        if (date >= 1 && date <= 25) {
            BigDecimal discountAmount = BigDecimal.valueOf(1000 + (date - 1) * 100);
            return discountAmount.min(order.getTotalPriceBeforeDiscount());
        }
        return BigDecimal.ZERO;
    }
}
