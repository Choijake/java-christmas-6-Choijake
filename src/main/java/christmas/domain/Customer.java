package christmas.domain;

import christmas.view.InputView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Customer {
    public EventPlanner placeOrder(){
        return EventPlanner.of(decideVisitDay(), decideMenu());
    }

    private Day decideVisitDay(){
        int visitDate = InputView.inputVisitDate();
        return Day.of(visitDate);
    }

    private List<Order> decideMenu(){
        List<String> menuAndQuantityList = Arrays
                .asList(InputView.inputMenuAndQuantity().split(","));
        List<Order> orders = new ArrayList<>();

        menuAndQuantityList.stream()
                .map(menuAndQuantity -> menuAndQuantity.split("-"))
                .forEach(part -> orders.add(new Order(part[0], Integer.parseInt(part[1]))));

        return orders;
    }
}
