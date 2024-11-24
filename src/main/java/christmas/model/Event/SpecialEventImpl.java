package christmas.model.Event;

import christmas.model.Cart;
import christmas.model.Date;
import christmas.model.Receipt;
import java.util.List;

public class SpecialEventImpl implements Event {

    @Override
    public void apply(Date date, Cart cart, Receipt receipt) {
        final List<Integer> specialDays = List.of(3, 10, 17, 24, 25, 31);
        final int DISCOUNT = 1_000;
        if (specialDays.contains(date.getDay())) {
            receipt.setSpecialEventDiscount(DISCOUNT);
        }
    }
}
