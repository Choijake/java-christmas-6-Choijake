package christmas.domain;

import static christmas.exception.ErrorMessage.ALL_MENU_BEVERAGE;
import static christmas.exception.ErrorMessage.OVER_LIMIT_QUANTITY;
import static christmas.exception.ErrorMessage.ZERO_MENU_QUANTITY;

import christmas.domain.constant.EventConstraint;
import christmas.domain.constant.MenuConstraint;
import christmas.exception.OrderException;
import java.util.List;
import java.util.Objects;

public class OrderSheet {
    private final Day day;
    private final List<Order> orders;

    private OrderSheet(Day day, List<Order> orders) {
        this.day = day;
        this.orders = orders;
        validate();
    }

    public static OrderSheet of(Day day, List<Order> orders){
        return new OrderSheet(day, orders);
    }

    public int getTotalPrice(){
        int totalPrice = 0;
        for (Order order : orders) {
            totalPrice += order.getTotalPriceForMenu();
        }
        return totalPrice;
    }

    public int getTotalQuantity(){
        int totalQuantity = 0;
        for(Order order : orders){
            totalQuantity += order.getQuantity();
        }
        return totalQuantity;
    }

    public boolean areAllMenuBeverage() {
        for (Order order : orders) {
            if (!Objects.equals(order.getCategory(), MenuConstraint.BEVERAGE.getValue())) {
                return false;
            }
        }
        return true;
    }

    //평일 할인용
    public Integer getDesertCount(){
        int desertCount = 0;
        for(Order order : orders){
            if(Objects.equals(order.getCategory(), MenuConstraint.DESERT.getValue())){
                desertCount += order.getQuantity();
            }
        }
        return desertCount;
    }

    //주말 할인용
    public Integer getMainCount(){
        int mainCount = 0;
        for(Order order : orders){
            if(Objects.equals(order.getCategory(), MenuConstraint.MAIN.getValue())){
                mainCount += order.getQuantity();
            }
        }
        return mainCount;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        orders.forEach(order -> sb.append(order.toString()).append("\n"));
        return sb.toString();
    }

    public boolean isWeekDay(){
        return day.isWeekDay();
    }

    public boolean isWeekEnd(){
        return day.isWeekEnd();
    }

    public boolean hasStar(){
        return day.hasStar();
    }

    public boolean isBeforeDDay(){
        return day.isBeforeDDay();
    }

    public int getDDayDiscountAmount(){
        return day.getDDayDiscountAmount();
    }

    public void validate(){
        validateTotalQuantity();
        validateMenuCategory();
    }

    private void validateTotalQuantity(){
        if(getTotalQuantity() > EventConstraint.MAX_ORDER_AMOUNT.getValue()){
            throw OrderException.from(OVER_LIMIT_QUANTITY);
        }
    }

    private void validateMenuCategory(){
        if(areAllMenuBeverage()){
            throw OrderException.from(ALL_MENU_BEVERAGE);
        }
    }
}
