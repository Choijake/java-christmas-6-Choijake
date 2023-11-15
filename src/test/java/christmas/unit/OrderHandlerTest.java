package christmas.unit;

import christmas.domain.Customer;
import christmas.domain.Day;
import christmas.domain.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderHandlerTest {
    @Test
    @DisplayName("주문 하는 기능에 대한 테스트")
    void createCustomerWithOrder() {
        // Arrange
        Day testDay = Day.of(12);
        List<Order> testOrders = Arrays.asList(
                new Order("바비큐립", 2),
                new Order("초코케이크", 3),
                new Order("제로콜라", 1)
        );

        // Act
        Customer customer = Customer.order(testDay, testOrders);

        // Assert
        assertEquals(testDay, customer.getDay());
        assertEquals(testOrders, customer.getOrders());
    }
}
