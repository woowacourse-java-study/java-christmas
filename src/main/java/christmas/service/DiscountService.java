package christmas.service;

import christmas.domain.Order;
import christmas.domain.OrderItem;
import christmas.service.discountpolicy.DiscountPolicy;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DiscountService {

    private final List<DiscountPolicy> discountPolicies;

    public DiscountService(List<DiscountPolicy> discountPolicies) {
        this.discountPolicies = discountPolicies;
    }

    public BigDecimal calculateDiscount(Order order, int date) {
        BigDecimal totalDiscount = BigDecimal.ZERO;

        for (DiscountPolicy policy : discountPolicies) {
            BigDecimal discount = policy.applyDiscount(order, date);
            totalDiscount = totalDiscount.add(discount);
        }

        return totalDiscount;
    }

    public Map<String, BigDecimal> calculateDiscountDetails(Order order, int date) {

        Map<String, BigDecimal> discountDetails = new LinkedHashMap<>();

        for (DiscountPolicy policy : discountPolicies) {
            BigDecimal discount = policy.applyDiscount(order, date);
            if (discount.compareTo(BigDecimal.ZERO) > 0) {
                discountDetails.put(policy.getPolicyName(), discount);
            }
        }

        return discountDetails;
    }
}
