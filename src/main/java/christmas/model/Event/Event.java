package christmas.model.Event;

import christmas.model.Cart;
import christmas.model.Date;
import christmas.model.Receipt;

public interface Event {
    void apply(Date date, Cart cart, Receipt receipt);
}
