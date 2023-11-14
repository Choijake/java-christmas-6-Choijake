package christmas.view.output;

import christmas.view.constant.printConstant;

public class OutputWriter {
    public static void println(final Object data) {
        System.out.println(data);
    }

    public static void printNewLine() {
        System.out.println();
    }

    public static void printMessage(final printConstant message) {
        println(message.getMessage());
    }
}
