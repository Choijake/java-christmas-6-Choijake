package christmas.view.constant;

public enum printConstant {
    LINE_SEPARATOR(System.lineSeparator()),
    WELCOME_MESSAGE("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    ASK_VISIT_DATE_MESSAGE("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    ASK_MENU_AND_QUANTITY_MESSAGE("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1"),
    SHOW_RECEIPT_MESSAGE("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    SHOW_ORDER_MENU_HEADER("<주문 메뉴>" + LINE_SEPARATOR.getMessage()),
    SHOW_DISCOUNT_HEADER("<혜택 내역>" + LINE_SEPARATOR.getMessage()),
    SHOW_BEFORE_DISCOUNT_HEADER("<할인 전 총주문 금액>" + LINE_SEPARATOR.getMessage()),
    SHOW_GIFT_PROMOTION_HEADER("<증정 메뉴>" + LINE_SEPARATOR.getMessage()),
    SHOW_TOTAL_DISCOUNT_HEADER("<총혜택 금액>" + LINE_SEPARATOR.getMessage()),
    SHOW_AFTER_DISCOUNT_HEADER("<할인 후 예상 결제 금액>" + LINE_SEPARATOR.getMessage()),
    SHOW_EVENT_BADGE_HEADER("<12월 이벤트 배지>" + LINE_SEPARATOR.getMessage()),
    SHOW_D_DAY_DISCOUNT("크리스마스 디데이 할인: -"),
    SHOW_WEEK_DAY_DISCOUNT("평일 할인: -"),
    SHOW_WEEK_END_DISCOUNT("주말 할인: -"),
    SHOW_STAR_DAY_DISCOUNT("특별 할인: -"),
    SHOW_GIFT_PROMOTION_DISCOUNT("증정 이벤트: -"),
    SHOW_GIFT("샴페인 1개"),
    SHOW_NO_DISCOUNT("없음"),
    STAR("별"),
    TREE("트리"),
    SANTA("산타"),
    DASH("-"),
    WON("원")

    ;

    private String message;
    printConstant(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getMessageWithAmount(int amount){
        if (amount != 0) {
            return message + amount + "원";
        } else {
            return message;
        }
    }
}
