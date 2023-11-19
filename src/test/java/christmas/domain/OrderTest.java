package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import christmas.exception.ErrorMessage;
import christmas.exception.OrderException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderTest {

    @DisplayName("Order 객체를 올바르게 생성하는지 테스트")
    @Test
    void createOrder() {
        assertThat(new Order("초코케이크", 2)).isNotNull();
    }

    @DisplayName("getPrice 메서드 테스트")
    @Test
    void getPrice() {
        Order order = new Order("초코케이크", 2);

        assertThat(order.getPrice()).isEqualTo(15000);
    }

    @DisplayName("getCategory 메서드 테스트")
    @Test
    void getCategory() {
        Order order = new Order("초코케이크", 2);

        assertThat(order.getCategory()).isEqualTo("desert");
    }

    @DisplayName("getTotalPriceForMenu 메서드 테스트")
    @Test
    void getTotalPriceForMenu() {
        Order order = new Order("초코케이크", 2);

        assertThat(order.getTotalPriceForMenu()).isEqualTo(15000 * 2);
    }

    @DisplayName("getQuantity 메서드 테스트")
    @Test
    void getQuantity() {
        Order order = new Order("초코케이크", 2);

        assertThat(order.getQuantity()).isEqualTo(2);
    }

    @DisplayName("toString 메서드 테스트")
    @Test
    void toStringTest() {
        Order order = new Order("초코케이크", 2);

        assertThat(order.toString()).isEqualTo("초코케이크 2개");
    }

    @DisplayName("validateMenuName 메서드 테스트 - 유효한 메뉴명")
    @Test
    void validateMenuNameValid() {
        assertThatCode(() -> new Order("초코케이크", 2)).doesNotThrowAnyException();
    }

    @DisplayName("validateMenuName 메서드 테스트 - 유효하지 않은 메뉴명")
    @Test
    void validateMenuNameInvalid() {
        assertThatThrownBy(() -> new Order("피자", 2))
                .isInstanceOf(OrderException.class)
                .hasMessageContaining(ErrorMessage.NOT_EXIST_MENU.getMessage());
    }

    @DisplayName("validateQuantity 메서드 테스트 - 양수인 경우")
    @Test
    void validateQuantityPositive() {
        assertThatCode(() -> new Order("초코케이크", 2)).doesNotThrowAnyException();
    }

    @DisplayName("validateQuantity 메서드 테스트 - 0인 경우")
    @Test
    void validateQuantityZero() {
        assertThatThrownBy(() -> new Order("초코케이크", 0))
                .isInstanceOf(OrderException.class)
                .hasMessageContaining(ErrorMessage.ZERO_MENU_QUANTITY.getMessage());
    }

    @DisplayName("validateQuantity 메서드 테스트 - 음수인 경우")
    @Test
    void validateQuantityNegative() {
        assertThatThrownBy(() -> new Order("초코케이크", -2))
                .isInstanceOf(OrderException.class)
                .hasMessageContaining(ErrorMessage.ZERO_MENU_QUANTITY.getMessage());
    }
}
