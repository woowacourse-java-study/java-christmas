package christmas.model.Event;

import christmas.model.Cart;
import christmas.model.Date;
import christmas.model.Receipt;

public class BadgeEventImpl implements Event {


    private final int STAR_LIMIT = 5_000;
    private final String STAR_APPLY = "별";
    private final int TREE_LIMIT = 10_000;
    private final String TREE_APPLY = "트리";
    private final int SANTA_LIMIT = 20_000;
    private final String SANTA_APPLY = "산타";

    @Override
    public void apply(Date date, Cart cart, Receipt receipt) {
        int totalPrice = cart.getTotalPrice();
        if (totalPrice >= SANTA_LIMIT) {
            receipt.setBadge(SANTA_APPLY);
        } else if (totalPrice >= TREE_LIMIT) {
            receipt.setBadge(TREE_APPLY);
        } else if (totalPrice >= STAR_LIMIT) {
            receipt.setBadge(STAR_APPLY);
        }
    }
}
