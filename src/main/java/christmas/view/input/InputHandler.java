package christmas.view.input;

import static christmas.view.constant.printConstant.ASK_MENU_AND_QUANTITY_MESSAGE;
import static christmas.view.constant.printConstant.ASK_VISIT_DATE_MESSAGE;
import static christmas.view.input.Parser.validateFormat;

import christmas.domain.Day;
import christmas.domain.Order;
import christmas.exception.OrderException;
import christmas.view.input.Parser;
import christmas.view.output.OutputWriter;
import christmas.view.input.InputReader;
import java.util.ArrayList;
import java.util.List;

public class InputHandler {
    public static Day requestDay(){
        OutputWriter.printMessage(ASK_VISIT_DATE_MESSAGE);
        return readDateInformation();
    }

    public static List<Order> requestOrder(){
        OutputWriter.printMessage(ASK_MENU_AND_QUANTITY_MESSAGE);
        return readMenuAndQuantity();
    }

    private static Day readDateInformation(){
        try {
            final String dateInput = InputReader.readLine();
            final int date = Parser.parseStringToInt(dateInput);
            return Day.of(date);
        } catch (OrderException exception) {
            OutputWriter.println(exception.getMessage());
            return readDateInformation();
        }
    }

    private static List<Order> readMenuAndQuantity(){
        try {
            final String menuAndQuantity = InputReader.readLine();
            List<String> menuAndQuantityList = Parser.splitByDelimiter(menuAndQuantity);
            menuAndQuantityList.forEach(Parser::validateFormat);

            List<Order> orders = new ArrayList<>();
            menuAndQuantityList.stream()
                    .map(m -> m.split("-"))
                    .forEach(part -> orders.add(new Order(part[0], Integer.parseInt(part[1]))));

            return orders;
        } catch (OrderException exception) {
            OutputWriter.println(exception.getMessage());
            return readMenuAndQuantity();
        }
    }

}
