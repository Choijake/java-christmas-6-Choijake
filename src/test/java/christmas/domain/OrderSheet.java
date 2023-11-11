package christmas.domain;

import java.util.List;

public class OrderSheet {
    private final Day day;
    private final List<Order> orders;


    private OrderSheet(Day day, List<Order> orders) {
        this.day = day;
        this.orders = orders;
    }

    public static OrderSheet of(Day day, List<Order> orders){
        return new OrderSheet(day, orders);
    }
}
