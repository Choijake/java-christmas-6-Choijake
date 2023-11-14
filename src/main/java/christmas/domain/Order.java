package christmas.domain;

import static christmas.exception.ErrorMessage.NOT_EXIST_MENU;
import static christmas.exception.ErrorMessage.ZERO_MENU_QUANTITY;

import christmas.exception.OrderException;
import java.util.Objects;

public class Order {
    private final String menuName;
    private final int quantity;

    public Order(String menuName, int quantity) {
        this.menuName = menuName;
        this.quantity = quantity;
        validate();
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

    public void validate(){
        validateMenuName();
        validateQuantity();
    }

    public void validateMenuName(){
        boolean isMenuNameValid = false;

        for (Menu menu : Menu.values()) {
            if (menu.getName().equals(menuName)) {
                isMenuNameValid = true;
                break;
            }
        }
        if (!isMenuNameValid) {
            throw OrderException.from(NOT_EXIST_MENU);
        }
    }

    public void validateQuantity(){
        if(quantity <= 0){
            throw OrderException.from(ZERO_MENU_QUANTITY);
        }
    }
}
