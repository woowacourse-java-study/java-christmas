package christmas.domain.event.impl;

import christmas.domain.event.DiscountProcessor;
import java.time.LocalDate;

public class DDayDiscount implements DiscountProcessor<LocalDate> {
    private static final LocalDate CHRISTMAS =LocalDate.of(2023,12,25);
    @Override
    public int processDiscount(LocalDate reservationDate) {
        if (reservationDate.isAfter(CHRISTMAS)) {
            return 0;
        }
        int discount = 1000;
       discount += 100 * (reservationDate.getDayOfMonth() - 1);

        return discount;
    }
}
