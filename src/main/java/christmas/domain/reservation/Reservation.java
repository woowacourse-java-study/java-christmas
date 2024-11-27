package christmas.domain.reservation;

import christmas.domain.event.EventProcessor;

public interface Reservation {
    String makeReceipt(EventProcessor eventProcessor);
}
