package christmas.model;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private int christmasEventDiscount;
    private int weekdayEventDiscount;
    private int weekendEventDiscount;
    private int specialEventDiscount;
    private final List<Product> giftEvent;
    private String badge;

    public Receipt() {
        this.christmasEventDiscount = 0;
        this.weekdayEventDiscount = 0;
        this.weekendEventDiscount = 0;
        this.specialEventDiscount = 0;
        this.giftEvent = new ArrayList<>();
        this.badge = "";

    }

    public int getChristmasEventDiscount() {
        return christmasEventDiscount;
    }

    public int getWeekdayEventDiscount() {
        return weekdayEventDiscount;
    }

    public int getWeekendEventDiscount() {
        return weekendEventDiscount;
    }

    public int getSpecialEventDiscount() {
        return specialEventDiscount;
    }

    public List<Product> getGiftEvent() {
        return giftEvent;
    }

    public int getGiftEventDiscount() {
        int giftDiscount = 0;
        for (Product gift : giftEvent) {
            giftDiscount += Menu.valueOf(gift.getName()).getPrice();
        }
        return giftDiscount;
    }
    public int getTotalDiscount() {
        int totalDiscount = 0;
        totalDiscount += getChristmasEventDiscount();
        totalDiscount += getWeekdayEventDiscount();
        totalDiscount += getWeekendEventDiscount();
        totalDiscount += getSpecialEventDiscount();
        return totalDiscount;
    }

    public String getBadge() {
        return badge;
    }

    public void setChristmasEventDiscount(int christmasEventDiscount) {
        this.christmasEventDiscount = christmasEventDiscount;
    }

    public void setWeekdayEventDiscount(int weekdayEventDiscount) {
        this.weekdayEventDiscount = weekdayEventDiscount;
    }

    public void setWeekendEventDiscount(int weekendEventDiscount) {
        this.weekendEventDiscount = weekendEventDiscount;
    }

    public void setSpecialEventDiscount(int specialEventDiscount) {
        this.specialEventDiscount = specialEventDiscount;
    }

    public void addGiftEvent(Product product) {
        this.giftEvent.add(product);
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }
}
