package christmas.view.output;

import christmas.view.constant.printConstraint;

public class OutputWriter {
    public static void println(final Object data) {
        System.out.println(data);
    }

    public static void printMessage(final printConstraint message) {
        println(message.getMessage());
    }
}
