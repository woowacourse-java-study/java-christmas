package christmas.domain.event.impl;
import christmas.domain.event.DiscountProcessor;
import christmas.domain.event.EventProcessor;
import christmas.domain.event.Gift;
import christmas.domain.menu.impl.Menu;
import christmas.domain.reservation.Order;
import java.time.LocalDate;
import java.util.List;

public class TotalEventProcessor implements EventProcessor {
    private static final List<Integer> weekends = List.of(1,2,8,9,15,16,22,33,29,30);

    private final DiscountProcessor<LocalDate> dDayDiscount;
    private final DiscountProcessor<LocalDate> specialDiscount;
    private final DiscountProcessor<List<Order>> weekdaysDiscount;
    private final DiscountProcessor<List<Order>> weekendDiscount;
    private final Gift<Boolean> champagneGift;
    private final Gift<String> badgeGift;

    public TotalEventProcessor(DiscountProcessor<LocalDate> dDayDiscount,
                               DiscountProcessor<LocalDate> specialDiscount,
                               DiscountProcessor<List<Order>> weekdaysDiscount,
                               DiscountProcessor<List<Order>> weekendDiscount,
                               Gift<Boolean>  champagneGift,
                               Gift<String> badgeGift
                               ) {
        this.dDayDiscount = dDayDiscount;
        this.specialDiscount = specialDiscount;
        this.weekdaysDiscount = weekdaysDiscount;
        this.weekendDiscount = weekendDiscount;
        this.champagneGift = champagneGift;
        this.badgeGift = badgeGift;
    }


    @Override
    public String getEventResult(List<Order> orders, LocalDate date, int totalPrice) {
        StringBuilder builder = new StringBuilder();

        builder.append("\n<증정메뉴>\n");

        int champagneDiscount = getChampagneDiscount(totalPrice, builder);
        int totalDiscountAmount = champagneDiscount;

        builder.append("\n혜택 내역\n");
        totalDiscountAmount += getDDayDiscount(date,builder);
        if(weekends.contains(date.getDayOfWeek()) ) {
            totalDiscountAmount += getWeekendDiscount(orders,builder);
        }
        if (!weekends.contains(date.getDayOfWeek())){
            totalDiscountAmount += getWeekdaysDiscount(orders,builder);
        }
        totalDiscountAmount += getSpecialDiscount(date,builder);
        if(champagneDiscount != 0) {
            builder.append("증정 이벤트: -").append(champagneDiscount).append("원").append("\n");
        }

        builder.append("\n<총혜택 금액>\n-").append(totalDiscountAmount).append("원").append("\n");
        builder.append("\n<할인 후 예상 결제 금액>\n").append(totalPrice - totalDiscountAmount).append("원").append("\n");
        builder.append("\n<12월 이벤트 배지>\n").append(badgeGift.getGiftOrNot(totalDiscountAmount));

        return builder.toString();
    }

    private int getChampagneDiscount(int totalPrice, StringBuilder result) {
        int champagneDiscountAmount = 0;
        if(champagneGift.getGiftOrNot(totalPrice)){
            champagneDiscountAmount += Menu.valueOf("샴페인").getPrice();
            result.append("샴페인 1개\n");
            return champagneDiscountAmount;
        }
        result.append("없음\n");
        return champagneDiscountAmount;
    }

    private int getDDayDiscount(LocalDate date, StringBuilder result) {
        int dDayDiscountAmount = dDayDiscount.processDiscount(date);
        result.append("크리스마스 디데이 할인: -").append(dDayDiscountAmount).append("원").append("\n");
        return dDayDiscountAmount;
    }

    private int getWeekendDiscount(List<Order> orders, StringBuilder result) {
        int weekendDiscountAmount =  weekendDiscount.processDiscount(orders);
        result.append("주말 할인: -").append(weekendDiscountAmount).append("원").append("\n");
        return weekendDiscountAmount;
    }

    private int getWeekdaysDiscount(List<Order>  orders, StringBuilder result) {
        int weekdaysDiscountAmount = weekdaysDiscount.processDiscount(orders);
        result.append("평일 할인: -").append(weekdaysDiscountAmount).append("원").append("\n");
        return weekdaysDiscountAmount;
    }

    private int getSpecialDiscount(LocalDate date, StringBuilder result) {
        int specialDiscountAmount = specialDiscount.processDiscount(date);
        result.append("특별 할인: -").append(specialDiscountAmount).append("원").append("\n");
        return specialDiscountAmount;

    }



}
