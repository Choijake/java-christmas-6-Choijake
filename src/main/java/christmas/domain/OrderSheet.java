package christmas.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class OrderSheet {
    private final static String DESERT_CATEGORY = "desert";
    private final static String MAIN_CATEGORY = "main";
    private final Day day;
    private final List<Order> orders;

    private OrderSheet(Day day, List<Order> orders) {
        this.day = day;
        this.orders = orders;
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

    //평일 할인용
    public Integer getDesertCount(){
        int desertCount = 0;
        for(Order order : orders){
            if(Objects.equals(order.getCategory(), DESERT_CATEGORY)){
                desertCount += order.getQuantity();
            }
        }
        return desertCount;
    }

    //주말 할인용
    public Integer getMainCount(){
        int mainCount = 0;
        for(Order order : orders){
            if(Objects.equals(order.getCategory(), MAIN_CATEGORY)){
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
}
