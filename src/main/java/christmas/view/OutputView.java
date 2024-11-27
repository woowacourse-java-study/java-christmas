package christmas.view;

import christmas.model.domain.Order;

import java.text.NumberFormat;
import java.util.Locale;

public class OutputView {

    private static final String ORDER_MENU_HEADER = "<주문 메뉴>";
    private static final String TOTAL_PRICE_HEADER = "\n<할인 전 총주문 금액>";
    private static final String GIFT_HEADER = "\n<증정 메뉴>";
    private static final String DISCOUNT_DETAILS_HEADER = "\n<혜택 내역>";
    private static final String TOTAL_BENEFITS_HEADER = "\n<총혜택 금액>";
    private static final String FINAL_PRICE_HEADER = "\n<할인 후 예상 결제 금액>";
    private static final String BADGE_HEADER = "\n<12월 이벤트 배지>";
    private static final String WON_SUFFIX = "원";
    private static final String NO_DISCOUNT_MESSAGE = "없음";
    private static final String DAILY_DISCOUNT_LABEL = "크리스마스 디데이 할인: ";
    private static final String CATEGORY_DISCOUNT_LABEL = "평일/주말 할인: ";
    private static final String SPECIAL_DISCOUNT_LABEL = "특별 할인: ";
    private static final String GIFT_EVENT_LABEL = "증정 이벤트: ";
    private static final String ORDER_ITEM_FORMAT = "%s %d개";
    private static final String NO_GIFT = "없음";
    private static final String NEGATIVE_PREFIX = "-";
    private static final int GIFT_DISCOUNT_AMOUNT = 25000;

    public void printOrderSummary(Order order, int totalPrice, int totalDiscount, int finalPrice, String gift, int dailyDiscount, int categoryDiscount, int specialDayDiscount, String badge) {
        printOrderDetails(order);
        printPriceDetails(totalPrice);
        printGiftDetails(gift);
        printDiscountDetails(dailyDiscount, categoryDiscount, specialDayDiscount, gift);
        printTotalBenefits(totalDiscount);
        printFinalPrice(finalPrice);
        printBadge(badge);
    }

    private void printOrderDetails(Order order) {
        System.out.println(ORDER_MENU_HEADER);
        order.getOrderDetails().forEach((menu, quantity) -> {
            System.out.println(String.format(ORDER_ITEM_FORMAT, menu.getName(), quantity));
        });
    }

    private void printPriceDetails(int totalPrice) {
        System.out.println(TOTAL_PRICE_HEADER);
        System.out.println(formatCurrency(totalPrice) + WON_SUFFIX);
    }

    private void printGiftDetails(String gift) {
        System.out.println(GIFT_HEADER);
        System.out.println(gift);
    }

    private void printDiscountDetails(int dailyDiscount, int categoryDiscount, int specialDayDiscount, String gift) {
        System.out.println(DISCOUNT_DETAILS_HEADER);
        if (hasNoDiscount(dailyDiscount, categoryDiscount, specialDayDiscount, gift)) {
            System.out.println(NO_DISCOUNT_MESSAGE);
            return;
        }
        printDailyDiscount(dailyDiscount);
        printCategoryDiscount(categoryDiscount);
        printSpecialDayDiscount(specialDayDiscount);
        printGiftEventDiscount(gift);
    }

    private boolean hasNoDiscount(int dailyDiscount, int categoryDiscount, int specialDayDiscount, String gift) {
        return dailyDiscount == 0 && categoryDiscount == 0 && specialDayDiscount == 0 && NO_GIFT.equals(gift);
    }

    private void printDailyDiscount(int dailyDiscount) {
        if (dailyDiscount > 0) {
            System.out.println(DAILY_DISCOUNT_LABEL + formatDiscount(dailyDiscount) + WON_SUFFIX);
        }
    }

    private void printCategoryDiscount(int categoryDiscount) {
        if (categoryDiscount > 0) {
            System.out.println(CATEGORY_DISCOUNT_LABEL + formatDiscount(categoryDiscount) + WON_SUFFIX);
        }
    }

    private void printSpecialDayDiscount(int specialDayDiscount) {
        if (specialDayDiscount > 0) {
            System.out.println(SPECIAL_DISCOUNT_LABEL + formatDiscount(specialDayDiscount) + WON_SUFFIX);
        }
    }

    private void printGiftEventDiscount(String gift) {
        if (!NO_GIFT.equals(gift)) {
            System.out.println(GIFT_EVENT_LABEL + formatDiscount(GIFT_DISCOUNT_AMOUNT) + WON_SUFFIX);
        }
    }

    private void printTotalBenefits(int totalDiscount) {
        System.out.println(TOTAL_BENEFITS_HEADER);
        System.out.println(formatDiscount(totalDiscount) + WON_SUFFIX);
    }

    private void printFinalPrice(int finalPrice) {
        System.out.println(FINAL_PRICE_HEADER);
        System.out.println(formatCurrency(finalPrice) + WON_SUFFIX);
    }

    private void printBadge(String badge) {
        System.out.println(BADGE_HEADER);
        System.out.println(badge);
    }

    private String formatDiscount(int amount) {
        return NEGATIVE_PREFIX + formatCurrency(amount);
    }

    private String formatCurrency(int amount) {
        return NumberFormat.getInstance(Locale.KOREA).format(amount);
    }

    public void printError(String meesage) {
        System.out.println(meesage);
    }
}