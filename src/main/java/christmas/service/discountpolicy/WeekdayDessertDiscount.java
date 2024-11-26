package christmas.service;

import christmas.domain.Order;
import christmas.domain.OrderItem;
import christmas.service.discountpolicy.DiscountPolicy;
import christmas.util.DateUtils;

import java.math.BigDecimal;

public class WeekdayDessertDiscount implements DiscountPolicy {

    @Override
    public BigDecimal applyDiscount(Order order, int date) {
        if (DateUtils.isWeekday(date)) {
            BigDecimal discount = BigDecimal.ZERO;
            for (OrderItem item : order.getOrderItems()) {
                if (item.getMenu().getCategory().equals("디저트")) {
                    discount = discount.add(BigDecimal.valueOf(2023).multiply(BigDecimal.valueOf(item.getQuantity())));
                }
            }
            return discount;
        }
        return BigDecimal.ZERO;
    }
}
