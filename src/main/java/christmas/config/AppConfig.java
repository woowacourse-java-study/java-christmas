package christmas.config;

import christmas.controller.EventController;
import christmas.view.InputView;
import christmas.view.OutputView;

public class AppConfig {
    private final EventController eventController;

    public AppConfig() {
        this.eventController = new EventController(new InputView(), new OutputView());
    }

    public void run() {
        eventController.init();
        eventController.run();
    }
}
