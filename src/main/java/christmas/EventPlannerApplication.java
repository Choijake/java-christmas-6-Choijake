package christmas;

import christmas.domain.Customer;
import christmas.domain.Discount;
import christmas.view.input.InputHandler;
import christmas.domain.Manager;
import christmas.domain.OrderSheet;
import christmas.domain.Receipt;
import christmas.view.output.OutputView;

public class EventPlannerApplication {
    public static void activate(){
        show(operate(set()));
    }

    public static OrderSheet set(){
        Customer customer = Customer
                .order(InputHandler.requestDay(), InputHandler.requestOrder());
        return customer.writeOrderSheet();
    }

    public static Receipt operate(OrderSheet orderSheet){
        Manager manager = Manager.get(orderSheet);
        Discount discount = Discount.of(orderSheet);
        return manager.issueReceipt(discount);
    }

    public static void show(Receipt receipt){
        OutputView outputView = OutputView.from(receipt);
        outputView.writeEventPlan();
    }

}