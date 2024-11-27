package christmas.service;

import christmas.domain.Badge;
import christmas.domain.EventResult;
import christmas.domain.Menu;
import christmas.domain.Order;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class EventService {

    private final DiscountService discountService;
    private final GiftService giftService;

    public EventService(DiscountService discountService, GiftService giftService) {
        this.discountService = discountService;
        this.giftService = giftService;
    }

    public EventResult planEvent(Order order, int date) {
        // 할인 계산 및 할인 내역 세부 정보 저장
        Map<String, BigDecimal> discountDetails = discountService.calculateDiscountDetails(order, date);
        BigDecimal totalDiscount = discountDetails.values().stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 증정 품목 확인
        Menu gift = giftService.checkGiftEligibility(order);

        // 최종 결제 금액 계산
        BigDecimal finalPrice = order.getTotalPriceBeforeDiscount().subtract(totalDiscount);

        // 배지 부여
        Badge badge = assignBadge(totalDiscount, gift);

        return new EventResult(order, totalDiscount, finalPrice, gift, badge, discountDetails);
    }

    private Badge assignBadge(BigDecimal totalDiscount, Menu gift) {
        BigDecimal totalBenefit = totalDiscount;
        if (gift != null) {
            totalBenefit = totalBenefit.add(gift.getPrice());
        }

        if (totalBenefit.compareTo(new BigDecimal("20000")) >= 0) {
            return Badge.SANTA;
        }
        if (totalBenefit.compareTo(new BigDecimal("10000")) >= 0) {
            return Badge.TREE;
        }
        if (totalBenefit.compareTo(new BigDecimal("5000")) >= 0) {
            return Badge.STAR;
        }
        return Badge.NONE;
    }
}
