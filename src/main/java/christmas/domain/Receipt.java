package christmas.domain;

import christmas.domain.constant.EventConstraint;
import christmas.view.constant.printConstraint;

public class Receipt {
    //TODO : 주문 메뉴, 할인 전 금액, 증정 메뉴, 해택 내역, 총 혜택 금액, 할인 후 에상 금액, 이벤트 배지 정보 담는 객체
    private final OrderSheet orderSheet;
    private final Discount discount;

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
        if(discount.getGiftPromotionDiscountAmount() == EventConstraint.GIFT_PROMOTION_DISCOUNT_AMOUNT.getValue()){
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
        String badge = printConstraint.SHOW_NO_DISCOUNT.getMessage();

        if(discount.getTotalDiscountAmount() >= EventConstraint.STAR_BADGE_STANDARD_AMOUNT.getValue()
            && discount.getTotalDiscountAmount() < EventConstraint.TREE_BADGE_STANDARD_AMOUNT.getValue()){
            badge = printConstraint.STAR.getMessage();
        }

        if(discount.getTotalDiscountAmount() >= EventConstraint.TREE_BADGE_STANDARD_AMOUNT.getValue()
                && discount.getTotalDiscountAmount() < EventConstraint.SANTA_BADGE_STANDARD_AMOUNT.getValue()){
            badge = printConstraint.TREE.getMessage();
        }

        if(discount.getTotalDiscountAmount() >= EventConstraint.SANTA_BADGE_STANDARD_AMOUNT.getValue()){
            badge = printConstraint.SANTA.getMessage();
        }

        return badge;
    }

    public int getDiscountedPrice(){
        if(discount.getGiftPromotionDiscountAmount() != 0){
            return orderSheet.getTotalPrice() - discount.getTotalDiscountAmount() + EventConstraint.GIFT_PROMOTION_DISCOUNT_AMOUNT.getValue();
        }
        return orderSheet.getTotalPrice() - discount.getTotalDiscountAmount();
    }
}
