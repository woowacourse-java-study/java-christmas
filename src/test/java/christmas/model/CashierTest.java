package christmas.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CashierTest {
    Cashier cashier = new Cashier();
    @Test
    public void 음료만_주문_예외테스트() throws Exception {
        //given
        String input = "레드와인";

        //then
        assertThrows(IllegalArgumentException.class, () -> {
            cashier.menuCashier(input);
        });
    }

    @Test
    public void 최대_주문_갯수_초과_예외테스트() throws Exception {
        //given
        String input = "타파스-21";

        //then
        assertThrows(IllegalArgumentException.class, () -> {
            cashier.menuCashier(input);
        });
    }

    @Test
    public void 존재하지_않는_메뉴_주문_예외테스트() throws Exception {

        //given
        String input = "사탕-10";

        //then
        assertThrows(IllegalArgumentException.class, () -> {
            cashier.menuCashier(input);
        });
    }
    @Test
    public void 중복_메뉴_주문_예외테스트() throws Exception {

        //given
        String input = "초코케이크-3,초코케이크-2";

        //then
        assertThrows(IllegalArgumentException.class, () -> {
            cashier.menuCashier(input);
        });
    }
    @Test
    public void 한개_미만_주문_예외테스트() throws Exception {

        //given
        String input = "초코케이크-0";

        //then
        assertThrows(IllegalArgumentException.class, () -> {
            cashier.menuCashier(input);
        });
    }
}