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

    private Manager conveyOrderSheet(OrderSheet orderSheet){
        return Manager.get(orderSheet);
    }

    private OrderSheet writeOrderSheet(final Day day, final List<Order> orders){
        return OrderSheet.of(day, orders);
    }
}
