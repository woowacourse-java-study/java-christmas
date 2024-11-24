package christmas.model.Event;

import christmas.model.Cart;
import christmas.model.Date;
import christmas.model.Product;
import christmas.model.Receipt;

public class GiftEventImpl implements Event {

    @Override
    public void apply(Date date, Cart cart, Receipt receipt) {
        final int DISCOUNT_CONDITION = 120_000;
        final Product GIFT = new Product("샴페인", 1);

        if (cart.getTotalPrice() >= DISCOUNT_CONDITION) {
            receipt.addGiftEvent(GIFT);
        }
    }
}
