package christmas.domain;

import christmas.domain.constant.EventConstraint;
import christmas.view.constant.printConstraint;

public class Discount {
   private final OrderSheet orderSheet;
   
    //TODO : 이벤트 있는 날에, 메뉴 당 할인이 적용 된다, 메뉴 판을 설정 해줘야 한다
   private Discount(OrderSheet orderSheet){
       this.orderSheet = orderSheet;
   }

   public static Discount of(OrderSheet orderSheet){
       return new Discount(orderSheet);
   }

   public int getTotalDiscountAmount(){
       return getDDayDiscountAmount()
               + getGiftPromotionDiscountAmount()
               + getStarDayDiscountAmount()
               + getWeekDayDiscountAmount()
               + getWeekEndDiscountAmount();
   }

   //크리스마스 디데이 할인
   public boolean isBeforeDDay(){
       return orderSheet.isBeforeDDay();
   }

   public int getDDayDiscountAmount(){
       if(isBeforeDDay()){
           return orderSheet.getDDayDiscountAmount();
       }
       return 0;
   }

   //평일 할인
   public int getWeekDayDiscountAmount(){
       if(orderSheet.isWeekDay() && orderSheet.getDesertCount() > 0){
           return orderSheet.getDesertCount() * EventConstraint.WEEK_SALE_DISCOUNT_AMOUNT.getValue();
       }
       return 0;
   }

   //주말 할인
   public int getWeekEndDiscountAmount(){
       if(orderSheet.isWeekEnd() && orderSheet.getMainCount() > 0){
           return orderSheet.getMainCount() * EventConstraint.WEEK_SALE_DISCOUNT_AMOUNT.getValue();
       }
       return 0;
   }

   //특별 할인
   public int getStarDayDiscountAmount(){
       if(orderSheet.hasStar()){
           return EventConstraint.STAR_DAY_DISCOUNT_AMOUNT.getValue();
       }
       return 0;
   }

   //증정 이벤트
   public int getGiftPromotionDiscountAmount(){
       if(orderSheet.getTotalPrice() >= EventConstraint.GIFT_PROMOTION_QUALIFIER_AMOUNT.getValue()){
           return EventConstraint.GIFT_PROMOTION_DISCOUNT_AMOUNT.getValue();
       }
       return 0;
   }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        boolean isDiscountExist = false;

        if(orderSheet.isBeforeDDay()){
            isDiscountExist = true;
            sb.append(printConstraint.SHOW_D_DAY_DISCOUNT.getMessage())
                    .append(getDDayDiscountAmount())
                    .append(printConstraint.WON.getMessage())
                    .append(printConstraint.LINE_SEPARATOR.getMessage());
        }

        if(orderSheet.isWeekDay() && orderSheet.getDesertCount() > 0){
            isDiscountExist = true;
            sb.append(printConstraint.SHOW_WEEK_DAY_DISCOUNT.getMessage())
                    .append(getWeekDayDiscountAmount())
                    .append(printConstraint.WON.getMessage())
                    .append(printConstraint.LINE_SEPARATOR.getMessage());
        }

        if(orderSheet.isWeekEnd() && orderSheet.getMainCount() > 0){
            isDiscountExist = true;
            sb.append(printConstraint.SHOW_WEEK_END_DISCOUNT.getMessage())
                    .append(getWeekEndDiscountAmount())
                    .append(printConstraint.WON.getMessage())
                    .append(printConstraint.LINE_SEPARATOR.getMessage());
        }

        if(orderSheet.hasStar()){
            isDiscountExist = true;
            sb.append(printConstraint.SHOW_STAR_DAY_DISCOUNT.getMessage())
                    .append(getStarDayDiscountAmount())
                    .append(printConstraint.WON.getMessage())
                    .append(printConstraint.LINE_SEPARATOR.getMessage());
        }

        if(orderSheet.getTotalPrice() >= EventConstraint.GIFT_PROMOTION_QUALIFIER_AMOUNT.getValue()){
            isDiscountExist = true;
            sb.append(printConstraint.SHOW_GIFT_PROMOTION_DISCOUNT.getMessage())
                    .append(getGiftPromotionDiscountAmount())
                    .append(printConstraint.WON.getMessage())
                    .append(printConstraint.LINE_SEPARATOR.getMessage());
        }

        if(!isDiscountExist){
            sb.append(printConstraint.SHOW_NO_DISCOUNT.getMessage());
        }

        return sb.toString();
    }
}
