package christmas.view;

import christmas.domain.EventResult;
import christmas.domain.OrderItem;

import java.util.List;

public class OutputView {

    public void printEventResult(EventResult result) {
        System.out.println(result);
    }

    public void printOrderItems(List<OrderItem> orderItems) {
        System.out.println("<주문 메뉴>");
        for (OrderItem item : orderItems) {
            System.out.println(item);
        }
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }
}
