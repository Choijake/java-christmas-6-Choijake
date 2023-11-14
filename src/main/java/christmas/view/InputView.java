package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.view.constant.printConstant;

public class InputView {
    public static int inputVisitDate(){
        System.out.println(printConstant.ASK_VISIT_DATE_MESSAGE);
        return Integer.parseInt(Console.readLine());
    }

    public static String inputMenuAndQuantity(){
        System.out.println(printConstant.ASK_MENU_AND_QUANTITY_MESSAGE);
        return Console.readLine();
    }
}
