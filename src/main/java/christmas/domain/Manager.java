package christmas.domain;

public class Manager {
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
