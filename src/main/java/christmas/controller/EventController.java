package christmas.controller;

import christmas.config.MenuConfig;
import christmas.model.domain.*;
import christmas.repository.MenuRepository;
import christmas.service.DiscountCalculator;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.time.LocalDate;

public class EventController {

    private static final String GIFT_CHAMPAGNE = "샴페인 1개";
    private static final String NO_GIFT = "없음";
    private static final String ORDER_DELIMITER = ",";
    private static final String DETAIL_DELIMITER = "-";
    private static final int GIFT_DISCOUNT_AMOUNT = 25000;
    private static final int EVENT_YEAR = 2023;
    private static final int EVENT_MONTH = 12;

    private final InputView inputView;
    private final OutputView outputView;
    private final DiscountCalculator discountCalculator;

    public EventController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.discountCalculator = new DiscountCalculator();
    }

    public void init() {
        MenuConfig.initMenus();
    }

    public void run() {
        while (true) {
            try {
                int visitDay = readValidVisitDate();
                LocalDate visitDate = LocalDate.of(EVENT_YEAR, EVENT_MONTH, visitDay);

                Order order = createOrderFromInput();
                processOrder(order, visitDay, visitDate);
                break;
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private int readValidVisitDate() {
        while (true) {
            try {
                return inputView.readVisitDate();
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private Order createOrderFromInput() {
        Order order = new Order();
        String[] orderDetails = readValidOrderDetails();
        for (String detail : orderDetails) {
            String[] parts = detail.split(DETAIL_DELIMITER);
            Menu menu = MenuRepository.findByName(parts[0].trim());
            int quantity = Integer.parseInt(parts[1].trim());
            order.addMenu(menu, quantity);
        }
        return order;
    }

    private String[] readValidOrderDetails() {
        while (true) {
            try {
                return inputView.readOrderDetails().split(ORDER_DELIMITER);
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private void processOrder(Order order, int visitDay, LocalDate visitDate) {
        int totalPrice = order.calculateTotalPrice();

        int dailyDiscount = discountCalculator.calculateDailyDiscount(visitDay);
        int weekdayDiscount = discountCalculator.calculateWeekdayDiscount(order, visitDate);
        int weekendDiscount = discountCalculator.calculateWeekendDiscount(order, visitDate);
        int specialDayDiscount = discountCalculator.calculateSpecialDayDiscount(visitDate);

        boolean eligibleForGift = discountCalculator.isEligibleForGift(totalPrice);
        String gift = getGiftBasedOnEligibility(eligibleForGift);

        int totalDiscount = calculateTotalDiscount(dailyDiscount, weekdayDiscount, weekendDiscount, specialDayDiscount, eligibleForGift);
        int finalPrice = totalPrice - (dailyDiscount + weekdayDiscount + weekendDiscount + specialDayDiscount);

        String badge = Badge.getBadgeByBenefit(totalDiscount).getName();

        outputView.printOrderSummary(order, totalPrice, totalDiscount, finalPrice, gift, dailyDiscount, weekdayDiscount, weekendDiscount, specialDayDiscount, badge);
    }

    private String getGiftBasedOnEligibility(boolean eligibleForGift) {
        if (eligibleForGift) {
            return GIFT_CHAMPAGNE;
        }
        return NO_GIFT;
    }

    private int calculateTotalDiscount(int dailyDiscount, int weekdayDiscount, int weekendDiscount, int specialDayDiscount, boolean eligibleForGift) {
        int giftDiscount = 0;
        if (eligibleForGift) {
            giftDiscount = GIFT_DISCOUNT_AMOUNT;
        }
        return dailyDiscount + weekdayDiscount + weekendDiscount + specialDayDiscount + giftDiscount;
    }
}