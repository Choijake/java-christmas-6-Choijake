package christmas.domain;

import java.util.List;

public class Customer {
    private final Day day;
    private final List<Order> orders;

    private Customer(final Day day, final List<Order> orders) {
        this.day = day;
        this.orders = orders;
    }

    public static Customer order(final Day day, final List<Order> orders){
        return new Customer(day, orders);
    }

    public OrderSheet writeOrderSheet(){
        return OrderSheet.of(day, orders);
    }
}
