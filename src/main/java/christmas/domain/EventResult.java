package christmas.domain;

import java.math.BigDecimal;

public class EventResult {

    private final Order order;
    private final BigDecimal totalDiscount;
    private final BigDecimal discountedPrice;
    private final String gift;
    private final Badge badge;

    public EventResult(Order order, BigDecimal totalDiscount, BigDecimal discountedPrice, String gift, Badge badge) {
        this.order = order;
        this.totalDiscount = totalDiscount;
        this.discountedPrice = discountedPrice;
        this.gift = gift;
        this.badge = badge;
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

    public String getGift() {
        return gift;
    }

    public Badge getBadge() {
        return badge;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(order.toString());
        sb.append("\n<할인 전 총주문 금액>\n").append(order.getTotalPriceBeforeDiscount()).append("원\n");
        sb.append("<할인 금액>\n").append(totalDiscount).append("원\n");
        sb.append("<할인 후 예상 결제 금액>\n").append(discountedPrice).append("원\n");
        sb.append("<증정 메뉴>\n").append(gift).append("\n");
        sb.append("<12월 이벤트 배지>\n").append(badge.getDisplayName()).append("\n");
        return sb.toString();
    }
}
