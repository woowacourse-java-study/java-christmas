package christmas.validator;

import christmas.domain.Menu;

public class InputValidator {

    public static int validateDate(String input) {
        try {
            int date = Integer.parseInt(input);
            if (date < 1 || date > 31) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
            }
            return date;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public static void validateMenuFormat(String[] details) {
        if (details.length != 2) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문 형식입니다. 다시 입력해 주세요.");
        }
    }

    public static void validateQuantity(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("[ERROR] 수량은 1개 이상이어야 합니다.");
        }
    }

    public static void validateMenuName(String menuName) {
        try {
            Menu.fromName(menuName);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 메뉴입니다. 다시 입력해 주세요.");
        }
    }
}
