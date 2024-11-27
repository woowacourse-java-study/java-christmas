package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.repository.MenuRepository;

import java.util.HashSet;
import java.util.Set;

public class InputView {

    private static final String VISIT_DATE_PROMPT = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String ORDER_DETAILS_PROMPT = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    private static final String ERROR_INVALID_DATE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final String ERROR_INVALID_ORDER = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private static final String ORDER_DELIMITER = ",";
    private static final String DETAIL_DELIMITER = "-";

    public int readVisitDate() {
        System.out.println(VISIT_DATE_PROMPT);
        String input = Console.readLine();
        return parseVisitDate(input);
    }

    private int parseVisitDate(String input) {
        try {
            int date = Integer.parseInt(input);
            validateDate(date);
            return date;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_INVALID_DATE);
        }
    }

    private void validateDate(int date) {
        if (date < 1 || date > 31) {
            throw new IllegalArgumentException(ERROR_INVALID_DATE);
        }
    }

    public String readOrderDetails() {
        System.out.println(ORDER_DETAILS_PROMPT);
        String input = Console.readLine();
        validateOrderInput(input);
        return input;
    }

    private void validateOrderInput(String input) {
        validateEmptyInput(input);

        Set<String> uniqueMenus = new HashSet<>();
        String[] orders = input.split(ORDER_DELIMITER);

        for (String order : orders) {
            validateOrder(order, uniqueMenus);
        }
    }

    private void validateEmptyInput(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(ERROR_INVALID_ORDER);
        }
    }

    private void validateOrder(String order, Set<String> uniqueMenus) {
        String[] details = order.split(DETAIL_DELIMITER);
        validateOrderFormat(details);
        String menuName = details[0].trim();
        validateMenuName(menuName, uniqueMenus);
        String quantityString = details[1].trim();
        validateQuantity(quantityString);
    }

    private void validateOrderFormat(String[] details) {
        if (details.length != 2) {
            throw new IllegalArgumentException(ERROR_INVALID_ORDER);
        }
    }

    private void validateMenuName(String menuName, Set<String> uniqueMenus) {
        if (MenuRepository.findByName(menuName) == null) {
            throw new IllegalArgumentException(ERROR_INVALID_ORDER);
        }
        if (!uniqueMenus.add(menuName)) {
            throw new IllegalArgumentException(ERROR_INVALID_ORDER);
        }
    }

    private void validateQuantity(String quantityString) {
        try {
            int quantity = Integer.parseInt(quantityString);
            if (quantity < 1) {
                throw new IllegalArgumentException(ERROR_INVALID_ORDER);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_INVALID_ORDER);
        }
    }
}