package christmas.domain;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import christmas.domain.constant.MenuConstraint;
import christmas.exception.ErrorMessage;
import christmas.exception.OrderException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

public class OrderSheetTest {

    private Day mockDay;
    private Order mockOrder;

    @BeforeEach
    void setUp() {
        mockDay = mock(Day.class);
        mockOrder = mock(Order.class);
    }

    @DisplayName("OrderSheet 객체를 올바르게 생성하는지 테스트")
    @Test
    void createOrderSheet() {
        when(mockDay.isWeekDay()).thenReturn(true);
        when(mockDay.isWeekEnd()).thenReturn(false);
        when(mockDay.hasStar()).thenReturn(false);
        when(mockDay.isBeforeDDay()).thenReturn(true);
        when(mockDay.getDDayDiscountAmount()).thenReturn(100);

        assertThat(OrderSheet.of(mockDay, Collections.singletonList(mockOrder))).isNotNull();
    }

    @DisplayName("getTotalPrice 메서드 테스트")
    @Test
    void getTotalPrice() {
        when(mockOrder.getTotalPriceForMenu()).thenReturn(5000);
        OrderSheet orderSheet = OrderSheet.of(mockDay, Collections.singletonList(mockOrder));

        assertThat(orderSheet.getTotalPrice()).isEqualTo(5000);
    }

    @DisplayName("getTotalQuantity 메서드 테스트")
    @Test
    void getTotalQuantity() {
        when(mockOrder.getQuantity()).thenReturn(3);
        OrderSheet orderSheet = OrderSheet.of(mockDay, Collections.singletonList(mockOrder));

        assertThat(orderSheet.getTotalQuantity()).isEqualTo(3);
    }

    @DisplayName("areAllMenuBeverage 메서드 테스트 - 모든 메뉴가 음료인 경우")
    @Test
    void areAllMenuBeverageAllBeverage() {
        when(mockOrder.getCategory()).thenReturn(MenuConstraint.BEVERAGE.getValue());

        OrderException exception = assertThrows(OrderException.class, () -> {
            OrderSheet.of(mockDay, Collections.singletonList(mockOrder));
        });

        assertEquals(ErrorMessage.ALL_MENU_BEVERAGE.getMessage(), exception.getMessage());

    }

    @DisplayName("areAllMenuBeverage 메서드 테스트 - 음료가 아닌 메뉴가 있는 경우")
    @Test
    void areAllMenuBeverageNotAllBeverage() {
        when(mockOrder.getCategory()).thenReturn(MenuConstraint.DESERT.getValue());
        OrderSheet orderSheet = OrderSheet.of(mockDay, Collections.singletonList(mockOrder));

        assertThat(orderSheet.areAllMenuBeverage()).isFalse();
    }

    @DisplayName("toString 메서드 테스트")
    @Test
    void toStringTest() {
        when(mockOrder.toString()).thenReturn("초코케이크 2개");
        OrderSheet orderSheet = OrderSheet.of(mockDay, Collections.singletonList(mockOrder));

        assertThat(orderSheet.toString()).isEqualTo("초코케이크 2개\n");
    }

    @DisplayName("validateTotalQuantity 메서드 테스트 - 주문 수가 제한을 넘는 경우")
    @Test
    void validateTotalQuantityExceedLimit() {
        when(mockOrder.getQuantity()).thenReturn(25);

        OrderException exception = assertThrows(OrderException.class, () -> {
            OrderSheet.of(mockDay, Collections.singletonList(mockOrder)).validate();
        });

        assertEquals(ErrorMessage.OVER_LIMIT_QUANTITY.getMessage(), exception.getMessage());
    }

    @DisplayName("validateMenuCategory 메서드 테스트 - 모든 메뉴가 음료인 경우")
    @Test
    void validateMenuCategoryAllBeverage() {
        when(mockOrder.getCategory()).thenReturn(MenuConstraint.BEVERAGE.getValue());

        OrderException exception = assertThrows(OrderException.class, () -> {
            OrderSheet.of(mockDay, Collections.singletonList(mockOrder)).validate();
        });

        assertEquals(ErrorMessage.ALL_MENU_BEVERAGE.getMessage(), exception.getMessage());
    }
}
