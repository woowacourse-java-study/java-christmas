package christmas.service.discountpolicy;

import christmas.domain.Order;

import java.math.BigDecimal;

public interface DiscountPolicy {
    BigDecimal applyDiscount(Order order, int date);

    String getPolicyName();
}
