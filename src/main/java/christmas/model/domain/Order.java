package christmas.model.domain;

import java.util.HashMap;
import java.util.Map;

public class Order {
    private final Map<Menu, Integer> orderDetails = new HashMap<>();

    public void addMenu(Menu menu, int quantity) {
        orderDetails.put(menu, orderDetails.getOrDefault(menu, 0) + quantity);
    }

    public Map<Menu, Integer> getOrderDetails() {
        return orderDetails;
    }

    public int calculateTotalPrice() {
        return orderDetails.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }
}
