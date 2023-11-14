package christmas.domain;

import java.util.List;

public class EventPlanner {
    private final Day day;
    private final List<Order> orders;

    private EventPlanner(final Day day, final List<Order> orders) {
        this.day = day;
        this.orders = orders;
    }

    public static EventPlanner of(final Day day, final List<Order> orders){
        return new EventPlanner(day, orders);
    }

    public void printReceipt(){
        System.out.println(getReceipt().toString());
    }

    private Receipt getReceipt(){
        return conveyOrderSheet(writeOrderSheet(day, orders)).issueReceipt();
    }

    private Manager conveyOrderSheet(OrderSheet orderSheet){
        return Manager.get(orderSheet);
    }

    private OrderSheet writeOrderSheet(final Day day, final List<Order> orders){
        return OrderSheet.of(day, orders);
    }
}
