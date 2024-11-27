package christmas.config;

import christmas.controller.EventPlannerController;
import christmas.service.*;
import christmas.service.SpecialDayDiscount;
import christmas.service.WeekdayDessertDiscount;
import christmas.service.WeekendMainDiscount;
import christmas.service.discountpolicy.ChristmasCountdownDiscount;
import christmas.service.discountpolicy.DiscountPolicy;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.List;

public class AppConfig {

    public EventPlannerController eventPlannerController() {
        return new EventPlannerController(eventService(), inputView(), outputView());
    }

    public EventService eventService() {
        return new EventService(discountService(), giftService());
    }

    public DiscountService discountService() {
        return new DiscountService(discountPolicies());
    }

    public List<DiscountPolicy> discountPolicies() {
        return List.of(
                new ChristmasCountdownDiscount(),
                new WeekdayDessertDiscount(),
                new WeekendMainDiscount(),
                new SpecialDayDiscount()
        );
    }

    public GiftService giftService() {
        return new GiftService();
    }

    public InputView inputView() {
        return new InputView();
    }

    public OutputView outputView() {
        return new OutputView();
    }
}
