package christmas.domain.event.impl;

import christmas.domain.event.Gift;

public class BadgeGift implements Gift<String> {
    @Override
    public String getGiftOrNot(int discountAmount) {
        if (discountAmount >= 20000) {
            return "산타";
        }
        if (discountAmount >= 10000) {
            return "트리";
        }
        if (discountAmount >= 5000) {
            return "별";
        }
        return "없음";
    }
}
