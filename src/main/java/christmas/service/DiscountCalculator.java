package christmas.service;

import christmas.model.domain.Order;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DiscountCalculator {

    private static final int DAILY_DISCOUNT_START = 1000;
    private static final int DAILY_DISCOUNT_INCREMENT = 100;
    private static final int CATEGORY_DISCOUNT_AMOUNT = 2023;
    private static final int SPECIAL_DAY_DISCOUNT = 1000;
    private static final int GIFT_ELIGIBILITY_THRESHOLD = 120000;
    private static final String MAIN_CATEGORY = "메인";
    private static final String DESSERT_CATEGORY = "디저트";
    private static final int CHRISTMAS_DAY = 25;

    public int calculateDailyDiscount(int day) {
        if (isInvalidDiscountDay(day)) {
            return 0;
        }
        return DAILY_DISCOUNT_START + ((day - 1) * DAILY_DISCOUNT_INCREMENT);
    }

    public int calculateWeekdayDiscount(Order order, LocalDate visitDate) {
        if (isWeekday(visitDate.getDayOfWeek())) {
            return calculateCategoryDiscount(order, DESSERT_CATEGORY);
        }
        return 0;
    }

    public int calculateWeekendDiscount(Order order, LocalDate visitDate) {
        if (isWeekend(visitDate.getDayOfWeek())) {
            return calculateCategoryDiscount(order, MAIN_CATEGORY);
        }
        return 0;
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

    private boolean isInvalidDiscountDay(int day) {
        return day < 1 || day > 25;
    }

    private int calculateCategoryDiscount(Order order, String category) {
        return order.getOrderDetails().entrySet().stream()
                .filter(entry -> entry.getKey().getCategory().equals(category))
                .mapToInt(entry -> CATEGORY_DISCOUNT_AMOUNT * entry.getValue())
                .sum();
    }

    private boolean isWeekday(DayOfWeek dayOfWeek) {
        return dayOfWeek == DayOfWeek.MONDAY ||
                dayOfWeek == DayOfWeek.TUESDAY ||
                dayOfWeek == DayOfWeek.WEDNESDAY ||
                dayOfWeek == DayOfWeek.THURSDAY ||
                dayOfWeek == DayOfWeek.SUNDAY;
    }

    private boolean isWeekend(DayOfWeek dayOfWeek) {
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }

    private boolean isSpecialDay(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SUNDAY || date.getDayOfMonth() == CHRISTMAS_DAY;
    }
}