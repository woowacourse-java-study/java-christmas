package christmas.controller;

import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.OrderItem;
import christmas.domain.EventResult;
import christmas.service.EventService;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.validator.InputValidator;

import java.util.ArrayList;
import java.util.List;

public class EventPlannerController {

    private final EventService eventService;
    private final OutputView outputView;
    private final InputView inputView;





    public EventPlannerController(EventService eventService, InputView inputView, OutputView outputView) {
        this.eventService = eventService;
        this.outputView = outputView;
        this.inputView = inputView;
    }

    public void run() {

        int date = readDateWithRetry(inputView);
        List<OrderItem> orderItems = readOrderWithRetry(inputView);

        Order order = new Order(orderItems);
        EventResult eventResult = eventService.planEvent(order, date);

        outputView.printEventResult(eventResult);
    }

    private int readDateWithRetry(InputView inputView) {
        while (true) {
            try {

                int date = InputValidator.validateDate(inputView.readDate());
                return date;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private List<OrderItem> readOrderWithRetry(InputView inputView) {
        while (true) {
            try {
                String input = inputView.readOrder();
                return parseOrder(input);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private List<OrderItem> parseOrder(String input) {
        List<OrderItem> orderItems = new ArrayList<>();
        String[] items = input.split(",");

        for (String item : items) {
            String[] details = item.split("-");
            InputValidator.validateMenuFormat(details);

            String menuName = details[0].trim();
            String quantityStr = details[1].trim();

            InputValidator.validateMenuName(menuName);
            int quantity = parseQuantity(quantityStr);

            InputValidator.validateQuantity(quantity);

            orderItems.add(new OrderItem(Menu.fromName(menuName), quantity));
        }

        return orderItems;
    }

    private int parseQuantity(String quantityStr) {
        try {
            return Integer.parseInt(quantityStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}
