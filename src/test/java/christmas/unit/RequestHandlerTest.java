package christmas.unit;

import christmas.domain.Day;
import christmas.domain.Order;
import christmas.exception.ErrorMessage;
import christmas.exception.OrderException;
import christmas.view.input.InputHandler;
import christmas.view.input.InputReader;
import christmas.view.input.Parser;
import christmas.view.output.OutputWriter;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class RequestHandlerTest {
    @DisplayName("parseStringToInt 메서드 테스트")
    @Test
    public void testParseStringToIntValidInput() {
        // Arrange
        String validInput = "3";

        // Act
        int result = Parser.parseStringToInt(validInput);

        // Assert
        assertEquals(3, result);
    }

    @DisplayName("유효하지 않은 입력일 때 예외처리 테스트")
    @Test
    public void testParseStringToIntInvalidInput() {
        // Arrange
        String invalidInput = "abc";

        // Act and Assert
        assertThrows(OrderException.class, () -> Parser.parseStringToInt(invalidInput));
    }

    @DisplayName("공백을 포함한 입력일 때 예외처리 테스트")
    @Test
    public void testParseStringToIntInputWithWhiteSpace() {
        // Arrange
        String inputWithWhiteSpace = "  3  ";

        // Act and Assert
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
        // Arrange
        String input = "초코케이크-2,샴페인-1";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        // Act
        String menuAndQuantity = InputReader.readLine();
        List<String> menuAndQuantityList = Parser.splitByDelimiter(menuAndQuantity);

        // Assert
        assertThat(menuAndQuantityList).containsExactly("초코케이크-2", "샴페인-1");
    }

    @DisplayName("validateFormat 메서드 테스트 - 유효한 형식")
    @Test
    void testValidateFormatValid() {
        // Arrange
        String validInput = "초코케이크-2";
        List<String> menuAndQuantityList = List.of(validInput);

        // Act & Assert
        menuAndQuantityList.forEach(Parser::validateFormat); // Should not throw an exception
    }

    @DisplayName("validateFormat 메서드 테스트 - 유효하지 않은 형식")
    @Test
    void testValidateFormatInvalid() {
        // Arrange
        String invalidInput = "초코케이크-";
        List<String> menuAndQuantityList = List.of(invalidInput);

        // Act & Assert
        assertThrows(OrderException.class, () -> menuAndQuantityList.forEach(Parser::validateFormat));
    }
}