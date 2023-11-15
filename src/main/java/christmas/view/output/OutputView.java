package christmas.view.output;

import christmas.domain.Receipt;
import christmas.view.constant.printConstant;

public class OutputView {
    private final Receipt receipt;

    private OutputView(Receipt receipt) {
        this.receipt = receipt;
    }

    public static OutputView from(Receipt receipt){
        return new OutputView(receipt);
    }

    //이벤트 플래너
    public void writeEventPlan(){
        OutputWriter.println(printConstant.SHOW_RECEIPT_MESSAGE.getMessage());
        writeOrderMenu();
        writeBeforeDiscountAmount();
        writeGiftPromotionMenu();
        writeDiscountDetails();
        writeTotalDiscountAmount();
        writeAfterDiscountAmount();
        writeEventBadge();
    }

    //주문 메뉴
    public void writeOrderMenu(){
        OutputWriter.println(printConstant.SHOW_ORDER_MENU_HEADER.getMessage()
                + receipt.getOrderMenu()) ;
    }

    //할인 전 총 주문 금액
    public void writeBeforeDiscountAmount(){
        OutputWriter.println(printConstant.SHOW_BEFORE_DISCOUNT_HEADER
                .getMessageWithAmount(receipt.getBeforeDiscountAmount()));
    }

    //증정 메뉴
    public void writeGiftPromotionMenu(){
        if(receipt.hasGiftPromotionMenu()){
            OutputWriter.println(printConstant.SHOW_GIFT_PROMOTION_HEADER.getMessage()
                    + printConstant.SHOW_GIFT.getMessage());
        }

        if(!receipt.hasGiftPromotionMenu()){
            OutputWriter.println(printConstant.SHOW_GIFT_PROMOTION_HEADER.getMessage()
                    + printConstant.SHOW_NO_DISCOUNT.getMessage());
        }
    }

    //해택 내역
    public void writeDiscountDetails(){
        OutputWriter.println(printConstant.SHOW_DISCOUNT_HEADER.getMessage()
                + receipt.getDiscountDetails());
    }

    //총 혜택 금액
    public void writeTotalDiscountAmount(){
        if(receipt.getTotalDiscountAmount() > 0){
            OutputWriter.println(printConstant.SHOW_TOTAL_DISCOUNT_HEADER.getMessage()
                    + printConstant.DASH.getMessage()
                    + receipt.getTotalDiscountAmount()
                    + printConstant.WON.getMessage());
        }

        if(receipt.getTotalDiscountAmount() == 0){
            OutputWriter.println(printConstant.SHOW_TOTAL_DISCOUNT_HEADER.getMessage()
                    + receipt.getTotalDiscountAmount()
                    + printConstant.WON.getMessage());
        }
    }

    //할인 후 예상 결제 금액
    public void writeAfterDiscountAmount(){
        OutputWriter.println(printConstant.SHOW_AFTER_DISCOUNT_HEADER
                .getMessageWithAmount(receipt.getDiscountedPrice()));
    }

    //이벤트 배지
    public void writeEventBadge(){
        OutputWriter.println(printConstant.SHOW_EVENT_BADGE_HEADER.getMessage()
                + receipt.getEventBadge());
    }

}
