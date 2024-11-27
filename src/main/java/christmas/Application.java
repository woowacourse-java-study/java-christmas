package christmas;

import christmas.config.AppConfig;
import christmas.controller.EventController;

public class Application {
    public static void main(String[] args) {
        EventController eventController = AppConfig.getEventController();
        eventController.run();

    }
}
