package christmas.service;

import christmas.model.domain.Order;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DiscountCalculator {

    private static final int DAILY_DISCOUNT_START = 1000; // 크리스마스 디데이 첫날 할인 금액
    private static final int DAILY_DISCOUNT_INCREMENT = 100; // 하루마다 증가하는 할인 금액
    private static final int CATEGORY_DISCOUNT_AMOUNT = 2023; // 카테고리별 할인 금액
    private static final int SPECIAL_DAY_DISCOUNT = 1000; // 특별 할인 금액
    private static final int GIFT_ELIGIBILITY_THRESHOLD = 120000; // 증정 메뉴 제공 기준 금액

    public int calculateDailyDiscount(int day) {
        if (day < 1 || day > 25) {
            return 0; // 크리스마스 디데이 할인은 12월 1~25일만 적용
        }
        return DAILY_DISCOUNT_START + ((day - 1) * DAILY_DISCOUNT_INCREMENT);
    }

    public int calculateCategoryDiscount(Order order, LocalDate visitDate) {
        DayOfWeek dayOfWeek = visitDate.getDayOfWeek();
        boolean isWeekend = (dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY);

        String targetCategory = isWeekend ? "메인" : "디저트";

        return order.getOrderDetails().entrySet().stream()
                .filter(entry -> entry.getKey().getCategory().equals(targetCategory))
                .mapToInt(entry -> CATEGORY_DISCOUNT_AMOUNT * entry.getValue())
                .sum();
    }

    public int calculateSpecialDayDiscount(LocalDate visitDate) {
        if (isSpecialDay(visitDate)) {
            return SPECIAL_DAY_DISCOUNT;
        }
        return 0;
    }

    public boolean isEligibleForGift(int totalPrice) {
        return totalPrice >= GIFT_ELIGIBILITY_THRESHOLD;
    }

    private boolean isSpecialDay(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        int dayOfMonth = date.getDayOfMonth();

        // 매주 일요일 또는 12월 25일이면 특별 할인 적용
        return dayOfWeek == DayOfWeek.SUNDAY || dayOfMonth == 25;
    }
}