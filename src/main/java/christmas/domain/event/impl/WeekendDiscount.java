package christmas.domain.event.impl;

import christmas.domain.event.DiscountProcessor;
import christmas.domain.menu.impl.Menu;
import christmas.domain.reservation.Order;
import java.util.List;

public class WeekendDiscount implements DiscountProcessor<List<Order>> {
    @Override
    public int processDiscount(List<Order> orders) {
        int discount = 0;

        for (Order order : orders) {
            String dishName = order.getDishName();

            if (Menu.valueOf(dishName).getCategory().equals("MainDish")){
                discount += 2023;
            }
        }

        return discount;
    }
}
