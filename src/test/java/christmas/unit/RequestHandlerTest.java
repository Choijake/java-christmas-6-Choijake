package christmas.unit;

import christmas.domain.Day;
import christmas.exception.ErrorMessage;
import christmas.exception.OrderException;
import christmas.view.input.InputReader;
import christmas.view.input.Parser;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RequestHandlerTest {
    @DisplayName("parseStringToInt 메서드 테스트")
    @Test
    public void testParseStringToIntValidInput() {
        String validInput = "3";

        int result = Parser.parseStringToInt(validInput);

        assertEquals(3, result);
    }

    @DisplayName("유효하지 않은 입력일 때 예외처리 테스트")
    @Test
    public void testParseStringToIntInvalidInput() {
        // Arrange
        String invalidInput = "abc";

        assertThrows(OrderException.class, () -> Parser.parseStringToInt(invalidInput));
    }

    @DisplayName("공백을 포함한 입력일 때 예외처리 테스트")
    @Test
    public void testParseStringToIntInputWithWhiteSpace() {
        // Arrange
        String inputWithWhiteSpace = "  3  ";

        assertThrows(OrderException.class, () -> Parser.parseStringToInt(inputWithWhiteSpace));
    }

    @DisplayName("Day 객체를 올바르게 생성하는지 테스트")
    @Test
    void createDay() {
        assertThat(Day.of(5)).isNotNull();
    }

    @DisplayName("Day 객체를 생성할 때 day가 유효한 범위 내인지 확인")
    @Test
    void createDayWithInvalidDay() {
        assertThatThrownBy(() -> Day.of(40))
                .isInstanceOf(OrderException.class)
                .hasMessageContaining(ErrorMessage.DAY_NOT_IN_RANGE.getMessage());
    }

    @DisplayName("readLine 메서드 테스트 - splitByDelimiter가 올바르게 호출되는지 테스트")
    @Test
    void testReadLineSplitByDelimiter() {
        String input = "초코케이크-2,샴페인-1";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        String menuAndQuantity = InputReader.readLine();
        List<String> menuAndQuantityList = Parser.splitByDelimiter(menuAndQuantity);

        assertThat(menuAndQuantityList).containsExactly("초코케이크-2", "샴페인-1");
    }

    @DisplayName("validateFormat 메서드 테스트 - 유효한 형식")
    @Test
    void testValidateFormatValid() {
        String validInput = "초코케이크-2";
        List<String> menuAndQuantityList = List.of(validInput);
        menuAndQuantityList.forEach(Parser::validateFormat);
    }

    @DisplayName("validateFormat 메서드 테스트 - 유효하지 않은 형식")
    @Test
    void testValidateFormatInvalid() {
        String invalidInput = "초코케이크-";
        List<String> menuAndQuantityList = List.of(invalidInput);
        assertThrows(OrderException.class, () -> menuAndQuantityList.forEach(Parser::validateFormat));
    }
}