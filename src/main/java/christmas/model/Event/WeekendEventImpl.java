package christmas.model.Event;

import christmas.model.Cart;
import christmas.model.Date;
import christmas.model.Menu;
import christmas.model.Product;
import christmas.model.Receipt;
import java.util.List;

public class WeekendEventImpl implements Event {

    @Override
    public void apply(Date date, Cart cart, Receipt receipt) {
        final List<Integer> weekendsRemainder = List.of(1, 2);
        final int DISCOUNT = 2_023;

        int remainder = date.getDay() % 7;
        if (weekendsRemainder.contains(remainder)) {
            int discountProductCnt = countDiscountProductsQuantity(cart);
            receipt.setWeekendEventDiscount(discountProductCnt * DISCOUNT);
        }
    }

    private int countDiscountProductsQuantity(Cart cart) {
        final String DISCOUNT_CATEGORY = "메인";
        int discountCnt = 0;
        for (Product product : cart.getProducts()) {
            if (Menu.valueOf(product.getName()).getCategory().equals(DISCOUNT_CATEGORY)) {
                discountCnt += product.getQuantity();
            }
        }
        return discountCnt;
    }
}
