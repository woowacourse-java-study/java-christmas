package christmas.service;

import christmas.domain.Order;
import christmas.domain.OrderItem;
import christmas.service.discountpolicy.DiscountPolicy;
import christmas.util.DateUtils;

import java.math.BigDecimal;

public class WeekendMainDiscount implements DiscountPolicy {

    @Override
    public BigDecimal applyDiscount(Order order, int date) {
        // 주말인지 확인 (금요일, 토요일)
        if (DateUtils.isWeekend(date)) {
            BigDecimal discount = BigDecimal.ZERO;
            for (OrderItem item : order.getOrderItems()) {
                if (item.getMenu().getCategory().equals("메인")) {
                    discount = discount.add(BigDecimal.valueOf(2023).multiply(BigDecimal.valueOf(item.getQuantity())));
                }
            }
            return discount;
        }
        return BigDecimal.ZERO;
    }

    @Override
    public String getPolicyName() {
        return "주말할인";
    }
}
