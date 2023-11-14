package christmas.domain;

import christmas.domain.constant.Menu;
import java.util.Objects;

public class Order {
    private final String menuName;
    private final int quantity;

    public Order(String menuName, int quantity) {
        this.menuName = menuName;
        this.quantity = quantity;
    }

    public int getPrice(){
        int price = 0;
        for(Menu menu : Menu.values()){
            if(Objects.equals(menu.getName(), menuName)){
                price = menu.getPrice();
            }
        }
        return price;
    }

    public String getCategory(){
        String category = "Null";
        for(Menu menu : Menu.values()){
            if(Objects.equals(menu.getName(), menuName)){
                category = menu.getCategory();
            }
        }
        return category;
    }

    public int getTotalPriceForMenu(){
        return getPrice() * quantity;
    }

    public int getQuantity(){
        return quantity;
    }

    @Override
    public String toString() {
        return menuName + " " + quantity + "개";
    }
}