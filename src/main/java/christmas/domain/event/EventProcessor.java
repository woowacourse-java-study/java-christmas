package christmas.domain.event;

import christmas.domain.reservation.Order;
import java.time.LocalDate;
import java.util.List;

public interface EventProcessor {
    String getEventResult(List<Order> orders, LocalDate date, int totalPrice);
}
