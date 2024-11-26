package christmas.domain.reservation;

import christmas.domain.event.DiscountProcessor;
import christmas.domain.menu.impl.Menu;
import java.time.LocalDate;
import java.util.List;

public class EventReservation implements Reservation {
    private final List<Order> orders;
    private final LocalDate eventDate;
    private final int totalPrice;

    private EventReservation(List<Order> orders,LocalDate eventDate) {
        this.orders = orders;
        this.eventDate = eventDate;
        this.totalPrice = getTotalPrice();

    }

    public static void create(List<Order> orders,LocalDate eventDate){
        new EventReservation(orders,eventDate);
    }

    public int applyDDayDiscount(DiscountProcessor dDayDiscount){
        int discountAmount = 0;
        return discountAmount;
    }

    public int applySpecialDiscount(DiscountProcessor specialDiscount){
        int discountAmount = 0;
        return discountAmount;
    }

    public int applyWeekdaysDiscount(DiscountProcessor weekdaysDiscount){
        int discountAmount = 0;
        return discountAmount;
    }

    public int applyWeekendDiscount(DiscountProcessor weekendDiscount){
        int discountAmount = 0;
        return discountAmount;
    }

    private int getTotalPrice(){
        int priceSum = 0;

        for (Order order : orders) {
            String dishName = order.getDishName();
            int quantity = order.getQuantity();

            priceSum += quantity *  Menu.valueOf(dishName).getPrice();
        }
        return priceSum;
    }


}
