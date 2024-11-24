package christmas.model;

import christmas.model.Event.BadgeEventImpl;
import christmas.model.Event.ChristmasEventImpl;
import christmas.model.Event.Event;
import christmas.model.Event.GiftEventImpl;
import christmas.model.Event.SpecialEventImpl;
import christmas.model.Event.WeekdayEventImpl;
import christmas.model.Event.WeekendEventImpl;
import java.util.List;

public class Order {

    public void eventApply(Date date, Cart cart, Receipt receipt) {
        final int MIN_EVENT_APPLY_MARGIN = 10_000;

        List<Event> events = List.of(new ChristmasEventImpl(), new WeekendEventImpl(), new WeekdayEventImpl(), new SpecialEventImpl(),
                new GiftEventImpl(), new BadgeEventImpl());

        if (cart.getTotalPrice() > MIN_EVENT_APPLY_MARGIN) {
            for (Event event : events) {
                event.apply(date, cart, receipt);
            }
        }
    }
}
