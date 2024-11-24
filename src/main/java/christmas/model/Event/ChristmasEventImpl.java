package christmas.model.Event;

import christmas.model.Cart;
import christmas.model.Date;
import christmas.model.Receipt;

public class ChristmasEventImpl implements Event {

    @Override
    public void apply(Date date, Cart cart, Receipt receipt) {
        final int CHRISTMAS_D_DAY = 25;
        final int DISCOUNT_START = 1_000;
        final int DISCOUNT_STACK = 100;

        if (date.getDay() > CHRISTMAS_D_DAY) {
            return;
        }
        receipt.setChristmasEventDiscount(DISCOUNT_START + DISCOUNT_STACK * (date.getDay() - 1));
    }
}
