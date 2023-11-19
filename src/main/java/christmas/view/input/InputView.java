package christmas.view.input;

import static christmas.view.constant.printConstraint.WELCOME_MESSAGE;

import christmas.domain.Day;
import christmas.domain.Order;
import christmas.view.output.OutputWriter;
import java.util.List;

public class InputView {
    public static Day inputVisitDate(){
        OutputWriter.printMessage(WELCOME_MESSAGE);
        return InputHandler.requestDay();
    }

    public static List<Order> inputOrder(){
        return InputHandler.requestOrder();
    }
}