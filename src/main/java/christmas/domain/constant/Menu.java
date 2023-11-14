package christmas.domain.constant;

public enum Menu {
    //에피타이저
    MUSHROOM_SOUP("appetizer", "양송이수프", 6000),
    TAPAS("appetizer","타파스", 5500),
    CEASAR_SELAD("appetizer","시저샐러드", 8000),

    //메인 메뉴
    T_BONE_STEAK("main","티본스테이크", 55000),
    BBQ_RIBS("main","바비큐립", 54000),
    SEAFOOD_PASTA("main", "해산물파스타", 35000),
    CHRISTMAS_PASTA("main", "크리스마스파스타", 25000),

    //디저트
    CHOCOLATE_CAKE("desert", "초코케이크", 15000),
    ICE_CREAM("desert", "아이스크림", 5000),

    //
    COKE_ZERO("beverage", "제로콜라", 3000),
    RED_WINE("beverage", "레드와인", 60000),
    CHAMPAGNE("beverage", "샴페인", 25000)
    ;

    private final String category;
    private final String name;
    private final int price;

    Menu(String category, String name, int price){
        this.category = category;
        this.name = name;
        this.price = price;
    }

    public String getCategory(){
        return category;
    }

    public String getName(){
        return name;
    }

    public int getPrice(){
        return price;
    }
}
