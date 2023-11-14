package christmas.exception;

public enum ErrorMessage {
    REQUEST_NOT_INTEGER("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    CONTAIN_WHITESPACE("공백을 제외 후 입력해 주세요"),
    ENDS_WITH_DELIMITER("마지막 쉼표를 제외 후 입력해 주세요"),
    DAY_NOT_IN_RANGE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    NOT_FIT_FORMAT("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    NOT_EXIST_MENU("판매 하지 않는 메뉴 입니다"),
    ZERO_MENU_QUANTITY("메뉴를 1개 이상 부터 구매할 수 있습니다"),
    OVER_LIMIT_QUANTITY("메뉴는 총 20개 까지 주문 가능 합니다"),
    ALL_MENU_BEVERAGE("음료만 주문 할 수 없습니다")
    ;

    private static final String ERROR_PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_PREFIX + message;
    }

}
