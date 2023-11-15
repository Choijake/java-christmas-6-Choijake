package christmas.domain.constant;

public enum MenuConstraint {
    //카테고리
    DESERT("desert"),
    MAIN("main"),
    BEVERAGE("beverage"),
    APPETIZER("appetizer")
    ;

    private final String category;

    MenuConstraint(String category) {
        this.category = category;
    }

    public String getValue() {
        return category;
    }

}
