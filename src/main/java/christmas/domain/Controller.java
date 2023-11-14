package christmas.domain;

public class Controller {
    private Customer customer;

    private Controller(Customer customer){
        this.customer = customer;
        start();
    }

    public static Controller launch(Customer customer){
        return new Controller(customer);
    }

    public void start(){
        EventPlanner eventPlanner = customer.placeOrder();
        eventPlanner.printReceipt();
    }

}
