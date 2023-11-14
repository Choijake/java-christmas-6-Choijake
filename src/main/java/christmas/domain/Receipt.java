package christmas.domain;

import christmas.view.constant.printConstant;

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

    public String getOrderMenu(){
        return orderSheet.toString();
    }

    public int getBeforeDiscountAmount(){
        return orderSheet.getTotalPrice();
    }

    public boolean hasGiftPromotionMenu(){
        if(discount.getGiftPromotionDiscountAmount() == GIFT_PROMOTION_DISCOUNT_AMOUNT){
            return true;
        }
        return false;
    }

    public String getDiscountDetails(){
        return discount.toString();
    }

    public int getTotalDiscountAmount(){
        return discount.getTotalDiscountAmount();
    }


    public String getEventBadge(){
        String badge = printConstant.SHOW_NO_DISCOUNT.getMessage();

        if(discount.getTotalDiscountAmount() >= STAR_BADGE_STANDARD_AMOUNT
            && discount.getTotalDiscountAmount() < TREE_BADGE_STANDARD_AMOUNT){
            badge = printConstant.STAR.getMessage();
        }

        if(discount.getTotalDiscountAmount() >= TREE_BADGE_STANDARD_AMOUNT
                && discount.getTotalDiscountAmount() < SANTA_BADGE_STANDARD_AMOUNT){
            badge = printConstant.TREE.getMessage();
        }

        if(discount.getTotalDiscountAmount() >= SANTA_BADGE_STANDARD_AMOUNT){
            badge = printConstant.SANTA.getMessage();
        }

        return badge;
    }

    public int getDiscountedPrice(){
        if(discount.getGiftPromotionDiscountAmount() != 0){
            return orderSheet.getTotalPrice() - discount.getTotalDiscountAmount() + GIFT_PROMOTION_DISCOUNT_AMOUNT;
        }
        return orderSheet.getTotalPrice() - discount.getTotalDiscountAmount();
    }
}
