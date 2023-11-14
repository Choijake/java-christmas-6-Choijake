package christmas.exception;

public enum ErrorMessage {
    REQUEST_NOT_INTEGER("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    CONTAIN_WHITESPACE("공백을 제외 후 입력해 주세요"),
    ENDS_WITH_DELIMITER("마지막 쉼표를 제외 후 입력해 주세요"),
    DAY_NOT_IN_RANGE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    NOT_FIT_FORMAT("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    NOT_EXIST_MENU("메뉴판에 없는 메뉴입니다")
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
