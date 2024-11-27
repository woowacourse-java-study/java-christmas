package christmas.domain.event.impl;

import christmas.domain.event.DiscountProcessor;
import java.time.LocalDate;
import java.util.List;

public class SpecialDiscount implements DiscountProcessor<LocalDate> {
    private static final List<Integer> specialDays = List.of(3,10,17,24,25,31);

    @Override
    public int processDiscount(LocalDate reservationDate) {
        int discount = 0;
        if (specialDays.contains(reservationDate.getDayOfWeek())) {
            discount +=1000;
        }
        return discount;
    }
}
