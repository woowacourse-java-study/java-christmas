package christmas.controller;

import christmas.config.MenuConfig;
import christmas.model.domain.*;
import christmas.repository.MenuRepository;
import christmas.service.DiscountCalculator;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class EventController {

    private static final String GIFT_CHAMPAGNE = "샴페인 1개"; // 증정 메뉴 상수
    private static final String NO_GIFT = "없음";            // 증정 메뉴가 없을 때
    private static final String ORDER_DELIMITER = ",";      // 주문 항목 구분자
    private static final String DETAIL_DELIMITER = "-";     // 메뉴명-개수 구분자

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
        try {
            int visitDay = readValidVisitDate();
            LocalDate visitDate = LocalDate.of(2023, 12, visitDay);

            Order order = createOrderFromInput();
            processOrder(order, visitDay, visitDate); // LocalDate 전달
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
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
        int categoryDiscount = discountCalculator.calculateCategoryDiscount(order, visitDate); // LocalDate 사용
        int specialDayDiscount = discountCalculator.calculateSpecialDayDiscount(visitDate);

        boolean eligibleForGift = discountCalculator.isEligibleForGift(totalPrice);
        String gift = getGiftBasedOnEligibility(eligibleForGift);

        int totalDiscount = calculateTotalDiscount(dailyDiscount, categoryDiscount, specialDayDiscount, eligibleForGift);
        int discountPrice = calculateDiscount(dailyDiscount, categoryDiscount, specialDayDiscount);
        int finalPrice = totalPrice - discountPrice;

        String badge = Badge.getBadgeByBenefit(totalDiscount).getName();

        outputView.printOrderSummary(order, totalPrice, totalDiscount, finalPrice, gift, dailyDiscount, categoryDiscount, specialDayDiscount, badge);
    }

    private String getGiftBasedOnEligibility(boolean eligibleForGift) {
        if (eligibleForGift) {
            return GIFT_CHAMPAGNE;
        }
        return NO_GIFT;
    }

    private int calculateDiscount(int dailyDiscount, int categoryDiscount, int specialDayDiscount) {
        return dailyDiscount + categoryDiscount + specialDayDiscount;
    }

    private int calculateTotalDiscount(int dailyDiscount, int categoryDiscount, int specialDayDiscount, boolean eligibleForGift) {
        int giftDiscount = 0;
        if (eligibleForGift) {
            giftDiscount = 25000;
        }
        return dailyDiscount + categoryDiscount + specialDayDiscount + giftDiscount;
    }
}