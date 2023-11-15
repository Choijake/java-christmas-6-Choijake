package christmas.view.input;

import static christmas.exception.ErrorMessage.CONTAIN_WHITESPACE;
import static christmas.exception.ErrorMessage.ENDS_WITH_DELIMITER;
import static christmas.exception.ErrorMessage.NOT_FIT_FORMAT;
import static christmas.exception.ErrorMessage.REQUEST_NOT_INTEGER;

import christmas.exception.OrderException;
import java.util.Arrays;
import java.util.List;

public class Parser {
    private static final String DELIMITER = ",";

    private Parser() {
    }

    public static int parseStringToInt(String input) {
        try {
            validateContainWhiteSpace(input);
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw OrderException.of(REQUEST_NOT_INTEGER, exception);
        }
    }

    public static void validateFormat(String str) {
        if (!isValidFormat(str)) {
            throw OrderException.from(NOT_FIT_FORMAT);
        }
    }

    private static boolean isValidFormat(String str) {
        String[] parts = str.split("-");
        return parts.length == 2 && isNumeric(parts[1]);
    }

    private static boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

    public static List<String> splitByDelimiter(String input) {
        try {
            validateContainWhiteSpace(input);
            validateEndsWithDelimiter(input);
            return Arrays
                    .asList(input.split(DELIMITER));
        } catch (NumberFormatException exception) {
            throw OrderException.of(REQUEST_NOT_INTEGER, exception);
        }
    }


    private static void validateContainWhiteSpace(String input) {
        if (hasWhiteSpace(input)) {
            throw OrderException.from(CONTAIN_WHITESPACE);
        }
    }

    private static void validateEndsWithDelimiter(String input) {
        if (isEndsWithDelimiter(input)) {
            throw OrderException.from(ENDS_WITH_DELIMITER);
        }
    }

    private static boolean hasWhiteSpace(String input) {
        return input.chars().anyMatch(Character::isWhitespace);
    }

    private static boolean isEndsWithDelimiter(String input) {
        return input.endsWith(DELIMITER);
    }
}
