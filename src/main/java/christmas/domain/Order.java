package christmas.domain;

import java.math.BigDecimal;
import java.util.List;

public class Order {

    private final List<OrderItem> orderItems;

    public Order(List<OrderItem> orderItems) {
        validateOrderItems(orderItems);
        this.orderItems = orderItems;
    }

    private void validateOrderItems(List<OrderItem> orderItems) {
        if (orderItems == null || orderItems.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 주문 항목이 비어 있습니다.");
        }

        if (orderItems.stream().anyMatch(item -> item.getMenu().getCategory().equals("음료") && orderItems.size() == 1)) {
            throw new IllegalArgumentException("[ERROR] 음료만 주문할 수 없습니다.");
        }

        int totalQuantity = orderItems.stream().mapToInt(OrderItem::getQuantity).sum();
        if (totalQuantity > 20) {
            throw new IllegalArgumentException("[ERROR] 주문 수량은 20개 이하로 해주세요.");
        }
    }

    public BigDecimal getTotalPriceBeforeDiscount() {
        return orderItems.stream()
                .map(item -> item.getMenu().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    @Override
    public String toString() {
        StringBuilder orderDetails = new StringBuilder("<주문 메뉴>\n");
        for (OrderItem item : orderItems) {
            orderDetails.append(item.toString()).append("\n");
        }
        return orderDetails.toString();
    }
}
