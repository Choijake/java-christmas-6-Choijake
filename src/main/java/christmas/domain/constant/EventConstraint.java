package christmas.domain.constant;

public enum EventConstraint {
    WEEK_SALE_DISCOUNT_AMOUNT(2023),
    STAR_DAY_DISCOUNT_AMOUNT(1000),
    GIFT_PROMOTION_QUALIFIER_AMOUNT(120000),
    GIFT_PROMOTION_DISCOUNT_AMOUNT(25000),

    D_DAY(25),
    INITIAL_D_DAY_DISCOUNT_AMOUNT(1000),
    MIN_DAY(1),
    MAX_DAY(31),

    MAX_ORDER_AMOUNT(20),
    STAR_BADGE_STANDARD_AMOUNT(5000),
    TREE_BADGE_STANDARD_AMOUNT(10000),
    SANTA_BADGE_STANDARD_AMOUNT(20000),;
    private final int value;

    EventConstraint(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
