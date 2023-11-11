package christmas.domain;

import java.awt.Menu;

public class Order {
    private final String menu;
    private final int quantity;

    public Order(String menu, int quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }
}
