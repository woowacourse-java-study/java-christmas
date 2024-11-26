package christmas.service;

import christmas.domain.Order;
import christmas.service.discountpolicy.DiscountPolicy;

import java.math.BigDecimal;
import java.util.Set;

public class SpecialDayDiscount implements DiscountPolicy {

    private final Set<Integer> specialDays;

    public SpecialDayDiscount() {
        this.specialDays = Set.of(3, 10, 17, 24, 25, 31);
    }

    @Override
    public BigDecimal applyDiscount(Order order, int date) {
        if (specialDays.contains(date)) {
            return BigDecimal.valueOf(1000);
        }
        return BigDecimal.ZERO;
    }
}
