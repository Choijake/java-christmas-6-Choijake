package christmas.domain;

public class Manager {
    // TODO : 혜택 내역, 혜택 금액, 적용 후 금액, 배지 종류, 증정품 여부 계산 시키기
    private final OrderSheet orderSheet;

    private Manager(OrderSheet orderSheet) {
        this.orderSheet = orderSheet;
    }

    public static Manager get(OrderSheet orderSheet){
        return new Manager(orderSheet);
    }

    public Receipt issueReceipt(Discount discount){
        return Receipt.of(orderSheet, discount);
    }
}
