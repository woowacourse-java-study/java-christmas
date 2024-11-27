package christmas.domain;

import java.math.BigDecimal;
import java.util.Map;

public class EventResult {

    private final Order order;
    private final BigDecimal totalDiscount;
    private final BigDecimal discountedPrice;
    private final Menu gift;
    private final Badge badge;
    private final Map<String, BigDecimal> discountDetails;

    public EventResult(Order order, BigDecimal totalDiscount, BigDecimal discountedPrice, Menu gift, Badge badge, Map<String, BigDecimal> discountDetails) {
        this.order = order;
        this.totalDiscount = totalDiscount;
        this.discountedPrice = discountedPrice;
        this.gift = gift;
        this.badge = badge;
        this.discountDetails = discountDetails;
    }

    public Order getOrder() {
        return order;
    }

    public BigDecimal getTotalDiscount() {
        return totalDiscount;
    }

    public BigDecimal getDiscountedPrice() {
        return discountedPrice;
    }

    public Menu getGift() {
        return gift;
    }

    public Badge getBadge() {
        return badge;
    }

    public Map<String, BigDecimal> getDiscountDetails() {
        return discountDetails;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        String lineSeparator = System.lineSeparator();

        result.append("12월 이벤트 결과 미리 보기!").append(lineSeparator).append(lineSeparator);

        // 주문 메뉴 출력
        result.append("<주문 메뉴>").append(lineSeparator);
        for (OrderItem item : order.getOrderItems()) {
            result.append(item).append(lineSeparator);
        }

        // 할인 전 총 주문 금액 출력
        result.append(lineSeparator).append("<할인 전 총주문 금액>").append(lineSeparator);
        BigDecimal totalPriceBeforeDiscount = order.getTotalPriceBeforeDiscount();
        result.append(formatCurrency(totalPriceBeforeDiscount)).append("원").append(lineSeparator);

        // 증정 메뉴 출력
        result.append(lineSeparator).append("<증정 메뉴>").append(lineSeparator);
        if (gift != null) {
            result.append(gift.getName()).append(" 1개").append(lineSeparator);
        } else {
            result.append("없음").append(lineSeparator);
        }

        // 혜택 내역 출력
        result.append(lineSeparator).append("<혜택 내역>").append(lineSeparator);
        if (discountDetails.isEmpty() && gift == null) {
            result.append("없음").append(lineSeparator);
        } else {
            for (Map.Entry<String, BigDecimal> entry : discountDetails.entrySet()) {
                result.append(entry.getKey()).append(": -").append(formatCurrency(entry.getValue())).append("원").append(lineSeparator);
            }
            if (gift != null) {
                result.append("증정 이벤트: -").append(formatCurrency(gift.getPrice())).append("원").append(lineSeparator);
            }
        }

        // 총 혜택 금액 출력 (0보다 큰 경우와 0인 경우 구분)
        result.append(lineSeparator).append("<총혜택 금액>").append(lineSeparator);
        BigDecimal totalBenefit = totalDiscount;
        if (gift != null) {
            totalBenefit = totalBenefit.add(gift.getPrice());
        }
        if (totalBenefit.compareTo(BigDecimal.ZERO) > 0) {
            result.append("-").append(formatCurrency(totalBenefit)).append("원").append(lineSeparator);
        } else {
            result.append("0원").append(lineSeparator);
        }

        // 할인 후 예상 결제 금액 출력
        result.append(lineSeparator).append("<할인 후 예상 결제 금액>").append(lineSeparator);
        result.append(formatCurrency(discountedPrice)).append("원").append(lineSeparator);

        // 이벤트 배지 출력
        result.append(lineSeparator).append("<12월 이벤트 배지>").append(lineSeparator);
        if (badge != Badge.NONE) {
            result.append(badge.getDisplayName()).append(lineSeparator);
        } else {
            result.append("없음").append(lineSeparator);
        }

        return result.toString();
    }


    private String formatCurrency(BigDecimal amount) {
        return String.format("%,d", amount.longValue());
    }
}
