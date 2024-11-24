package christmas.model.Event;

import christmas.model.Cart;
import christmas.model.Date;
import christmas.model.Menu;
import christmas.model.Product;
import christmas.model.Receipt;
import java.util.List;

public class WeekdayEventImpl implements Event {

    @Override
    public void apply(Date date, Cart cart, Receipt receipt) {
        final List<Integer> weekdaysRemainder = List.of(3, 4, 5, 6, 0);
        final int DISCOUNT = 2_023;

        int remainder = date.getDay() % 7;
        if (weekdaysRemainder.contains(remainder)) {
            int discountProductCnt = countDiscountProductsQuantity(cart);
            receipt.setWeekdayEventDiscount(discountProductCnt * DISCOUNT);
        }
    }

    private int countDiscountProductsQuantity(Cart cart) {
        final String DISCOUNT_CATEGORY = "디저트";
        int discountCnt = 0;
        for (Product product : cart.getProducts()) {
            if (Menu.valueOf(product.getName()).getCategory().equals(DISCOUNT_CATEGORY)) {
                discountCnt +=product.getQuantity();
            }
        }
        return discountCnt;
    }
}
