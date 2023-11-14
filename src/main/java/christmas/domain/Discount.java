package christmas.domain;

import christmas.view.constant.printConstant;

public class Discount {
   private static final int WEEK_SALE_DISCOUNT_AMOUNT = 2023;
   public static final int STAR_DAY_DISCOUNT_AMOUNT = 1000;
   public static final int GIFT_PROMOTION_QUALIFIER_AMOUNT = 120_000;
   public static final int GIFT_PROMOTION_DISCOUNT_AMOUNT = 25_000;
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
           return orderSheet.getDesertCount() * WEEK_SALE_DISCOUNT_AMOUNT;
       }
       return 0;
   }

   //주말 할인
   public int getWeekEndDiscountAmount(){
       if(orderSheet.isWeekEnd() && orderSheet.getMainCount() > 0){
           return orderSheet.getMainCount() * WEEK_SALE_DISCOUNT_AMOUNT;
       }
       return 0;
   }

   //특별 할인
   public int getStarDayDiscountAmount(){
       if(orderSheet.hasStar()){
           return STAR_DAY_DISCOUNT_AMOUNT;
       }
       return 0;
   }

   //증정 이벤트
   public int getGiftPromotionDiscountAmount(){
       if(orderSheet.getTotalPrice() >= GIFT_PROMOTION_QUALIFIER_AMOUNT){
           return GIFT_PROMOTION_DISCOUNT_AMOUNT;
       }
       return 0;
   }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if(orderSheet.isBeforeDDay()){
            sb.append(printConstant.SHOW_D_DAY_DISCOUNT.getMessage()).append(getDDayDiscountAmount()).append(printConstant.WON.getMessage());
        }

        if(orderSheet.isWeekDay() && orderSheet.getDesertCount() > 0){
            sb.append(printConstant.SHOW_WEEK_DAY_DISCOUNT.getMessage()).append(getWeekDayDiscountAmount()).append(printConstant.WON.getMessage());
        }

        if(orderSheet.isWeekEnd() && orderSheet.getMainCount() > 0){
            sb.append(printConstant.SHOW_WEEK_END_DISCOUNT.getMessage()).append(getWeekEndDiscountAmount()).append(printConstant.WON.getMessage());
        }

        if(orderSheet.hasStar()){
            sb.append(printConstant.SHOW_STAR_DAY_DISCOUNT.getMessage()).append(getStarDayDiscountAmount()).append(printConstant.WON.getMessage());
        }

        if(orderSheet.getTotalPrice() >= GIFT_PROMOTION_QUALIFIER_AMOUNT){
            sb.append(printConstant.SHOW_GIFT_PROMOTION_DISCOUNT.getMessage()).append(getGiftPromotionDiscountAmount()).append(printConstant.WON.getMessage());
        }

        sb.append(printConstant.SHOW_NO_DISCOUNT.getMessage());

        return sb.toString();
    }
}
