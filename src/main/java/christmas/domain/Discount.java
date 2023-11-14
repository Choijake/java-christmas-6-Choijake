package christmas.domain;

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

   public int getDDayDiscountAmount(){
       return orderSheet.getDDayDiscountAmount();
   }

   public int getWeekDayDiscountAmount(){
       if(orderSheet.isWeekDay()){
           return orderSheet.getDesertCount() * WEEK_SALE_DISCOUNT_AMOUNT;
       }
       return 0;
   }

   public int getWeekEndDiscountAmount(){
       if(orderSheet.isWeekEnd()){
           return orderSheet.getMainCount() * WEEK_SALE_DISCOUNT_AMOUNT;
       }
       return 0;
   }

   public int getStarDayDiscountAmount(){
       if(orderSheet.hasStar()){
           return STAR_DAY_DISCOUNT_AMOUNT;
       }
       return 0;
   }

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
            sb.append("크리스마스 디데이 할인: -").append(getDDayDiscountAmount()).append("원\n");
        }

        if(orderSheet.isWeekDay() && orderSheet.getDesertCount() > 0){
            sb.append("평일 할인: -").append(getWeekDayDiscountAmount()).append("원\n");
        }

        if(orderSheet.isWeekEnd() && orderSheet.getMainCount() > 0){
            sb.append("주말 할인: -").append(getWeekEndDiscountAmount()).append("원\n");
        }

        if(orderSheet.hasStar()){
            sb.append("특별 할인: -").append(getStarDayDiscountAmount()).append("원\n");
        }

        if(orderSheet.getTotalPrice() >= GIFT_PROMOTION_QUALIFIER_AMOUNT){
            sb.append("증정 이벤트: -").append(getGiftPromotionDiscountAmount()).append("원\n");
        }

        sb.append("없음");

        return sb.toString();
    }
}
