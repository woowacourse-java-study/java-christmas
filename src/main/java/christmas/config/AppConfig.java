package christmas.config;

import christmas.controller.ChristmasController;
import christmas.model.Cashier;
import christmas.model.Order;
import christmas.view.InputView;
import christmas.view.OutputView;

public class AppConfig {
    public ChristmasController christmasController() {
        return new ChristmasController(inputView(), outputView(), cashier(), order());
    }
    public InputView inputView() {
        return new InputView();
    }
    public OutputView outputView() {
        return new OutputView();
    }
    public Cashier cashier() {
        return new Cashier();
    }
    public Order order() {
        return new Order();
    }
}
