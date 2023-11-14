package christmas;

import christmas.domain.Controller;
import christmas.domain.Customer;

public class Application {
    public static void main(String[] args) {
        Customer customer = new Customer();
        Controller.launch(customer);
    }
}
