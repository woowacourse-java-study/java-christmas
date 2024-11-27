package christmas;

import christmas.config.AppConfig;
import christmas.controller.EventPlannerController;

public class Application {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        EventPlannerController controller = appConfig.eventPlannerController();
        controller.run();
    }
}
