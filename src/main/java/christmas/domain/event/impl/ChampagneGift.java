package christmas.domain.event.impl;

import christmas.domain.event.Gift;

public class ChampagneGift implements Gift<Boolean> {
    @Override
    public Boolean getGiftOrNot(int totalAmount) {
        if (totalAmount >= 120000) {
            return true;
        }
        return false;

    }
}
