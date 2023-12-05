package christmas.constant;

public enum ErrorMessages {
    INVALID_DAY_OF_MONTH("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_MENU_AND_COUNT("유효하지 않은 주문입니다. 다시 입력해 주세요.");

    private static final String ERROR_PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_PREFIX + message;
    }
}
