package christmas.exception;

public enum ErrorMessage {
    ERROR_HEADER("[ERROR] "),
    NOT_VALID_ORDER_FORMAT("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    NOT_INTEGER("입력값이 숫자가 아닙니다. 다시 입력해주세요"),
    NOT_VALID_DATE("유효하지 않은 날짜입니다. 다시 입력해주세요.");
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    private String getMessage() {
        return message;
    }

    public String getErrorMessage() {
        return ERROR_HEADER.getMessage() +  message;
    }
}
