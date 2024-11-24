package christmas.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class OrderTest {
    Order order = new Order();

    @Test
    public void 이벤트_적용테스트() throws Exception {

        //given
        Cart cart = new Cart(List.of(
                new Product("타파스", 2),
                new Product("바비큐립", 2),
                new Product("아이스크림", 2)));
        Date date = new Date(1);

        Receipt receipt = new Receipt();

        //when
        order.eventApply(date, cart, receipt);
        Receipt expectedReceipt = new Receipt();
        expectedReceipt.setChristmasEventDiscount(1000);
        expectedReceipt.setWeekendEventDiscount(2_023 * 2);
        expectedReceipt.setBadge("산타");

        //then
        assertEquals(receipt.getChristmasEventDiscount(), expectedReceipt.getChristmasEventDiscount());
        assertEquals(receipt.getWeekdayEventDiscount(), expectedReceipt.getWeekdayEventDiscount());
        assertEquals(receipt.getWeekendEventDiscount(), expectedReceipt.getWeekendEventDiscount());
        assertEquals(receipt.getSpecialEventDiscount(), expectedReceipt.getSpecialEventDiscount());
        assertEquals(receipt.getBadge(), expectedReceipt.getBadge());
    }

}