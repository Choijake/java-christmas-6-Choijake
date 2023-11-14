package christmas.domain;

public class Receipt {
    //TODO : 주문 메뉴, 할인 전 금액, 증정 메뉴, 해택 내역, 총 혜택 금액, 할인 후 에상 금액, 이벤트 배지 정보 담는 객체
    private final OrderSheet orderSheet;
    private final Discount discount;
    private static final int STAR_BADGE_STANDARD_AMOUNT = 5000;
    public static final int TREE_BADGE_STANDARD_AMOUNT = 10000;
    public static final int SANTA_BADGE_STANDARD_AMOUNT = 20000;
    public static final int GIFT_PROMOTION_DISCOUNT_AMOUNT = 25_000;

    private Receipt(OrderSheet orderSheet, Discount discount) {
        this.orderSheet = orderSheet;
        this.discount = discount;
    }

    public static Receipt of(OrderSheet orderSheet, Discount discount){
        return new Receipt(orderSheet, discount);
    }

    @Override
    public String toString() {
        return getOrderMenu()
                + getBeforeDiscountAmount()
                + getGiftPromotionMenu()
                + getDiscountDetails()
                + getTotalDiscountAmount()
                + getAfterDiscountAmount()
                + getEventBadge();
    }

    private String getOrderMenu(){
        return "<주문 메뉴>" + System.lineSeparator() + orderSheet.toString() + System.lineSeparator();
    }

    private String getBeforeDiscountAmount(){
        return "<할인 전 총주문 금액>" + System.lineSeparator() + orderSheet.getTotalPrice() + "원" + System.lineSeparator();
    }

    private String getGiftPromotionMenu(){
        if(discount.getGiftPromotionDiscountAmount() != 0){
            
            return "<증정 메뉴>" + System.lineSeparator() + "샴페인 1개" + System.lineSeparator();
        }
        return "<증정 메뉴>" + System.lineSeparator() + "없음" + System.lineSeparator();
    }

    private String getDiscountDetails(){
        return "<혜택 내역>" + System.lineSeparator() + discount.toString() + System.lineSeparator();
    }

    private String getTotalDiscountAmount(){
        if(discount.getTotalDiscountAmount()>0){
            return "<총혜택 금액>" + System.lineSeparator() + "-" + discount.getTotalDiscountAmount() + "원" + System.lineSeparator();
        }
        return "<총혜택 금액>" + System.lineSeparator() + "0원" + System.lineSeparator();
    }

    private String getAfterDiscountAmount(){
        return "<할인 후 예상 결제 금액>" + System.lineSeparator() + getDiscountedPrice() + "원" + System.lineSeparator();
    }

    private String getEventBadge(){
        StringBuilder sb = new StringBuilder();
        sb.append("<12월 이벤트 배지>").append(System.lineSeparator());

        if(discount.getTotalDiscountAmount() < STAR_BADGE_STANDARD_AMOUNT){
            sb.append("없음");
        }

        if(discount.getTotalDiscountAmount() >= STAR_BADGE_STANDARD_AMOUNT
            && discount.getTotalDiscountAmount() < TREE_BADGE_STANDARD_AMOUNT){
            sb.append("별");
        }

        if(discount.getTotalDiscountAmount() >= TREE_BADGE_STANDARD_AMOUNT
                && discount.getTotalDiscountAmount() < SANTA_BADGE_STANDARD_AMOUNT){
            sb.append("트리");
        }

        if(discount.getTotalDiscountAmount() >= SANTA_BADGE_STANDARD_AMOUNT){
            sb.append("산타");
        }

        return sb.toString();
    }

    private int getDiscountedPrice(){
        if(discount.getGiftPromotionDiscountAmount() != 0){
            return orderSheet.getTotalPrice() - discount.getTotalDiscountAmount() + GIFT_PROMOTION_DISCOUNT_AMOUNT;
        }
        return orderSheet.getTotalPrice() - discount.getTotalDiscountAmount();
    }
}
