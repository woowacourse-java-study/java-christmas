package christmas.utils.impl;

import christmas.domain.reservation.EventOrder;
import christmas.domain.reservation.Order;
import christmas.utils.InputParser;
import christmas.utils.InputValidator;
import java.util.ArrayList;
import java.util.List;

public class OrdersParser implements InputParser<List<Order>> {
    private final InputValidator orderValidator;

    public OrdersParser(InputValidator orderValidator) {
        this.orderValidator = orderValidator;
    }

    @Override
    public List<Order> parse(String rawOrders) {
        orderValidator.validate(rawOrders);

        List<Order> orders = new ArrayList<>();
        for (String rawOrder : rawOrders.split(",")) {
            String[] parts = rawOrder.split("-");

            String dishName = parts[0];
            int quantity = Integer.parseInt(parts[1]);
            Order order = new EventOrder(dishName, quantity);
            orders.add(order);
        }
        return orders;
    }
}