package christmas.domain.reservation;

import christmas.domain.event.EventProcessor;
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

    public static EventReservation create(List<Order> orders,LocalDate eventDate){
        return new EventReservation(orders,eventDate);
    }

    public String makeReceipt(EventProcessor totalEventProcessor){
        StringBuilder builder = new StringBuilder();
        builder.append("12월 ").append(eventDate.getDayOfWeek()).append("일에 우테코 식당에서 받을 혜택 미리 보기!\n");

        builder.append("\n<주문 메뉴>\n");
        for(Order order : orders){
            builder.append(order.getDishName()).append(" ").append(order.getQuantity()).append("개\n");
        }
        builder.append("<할인 전 총주문 금액>\n").append(totalPrice).append("원\n");

        builder.append(totalEventProcessor.getEventResult(orders,eventDate,totalPrice));

        return builder.toString();
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
