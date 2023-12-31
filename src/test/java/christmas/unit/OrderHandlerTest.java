package christmas.unit;

import christmas.domain.Customer;
import christmas.domain.Day;
import christmas.domain.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class OrderHandlerTest {
    @Test
    @DisplayName("주문 하는 기능에 대한 테스트")
    void createCustomerWithOrder() {
        Day testDay = Day.of(12);
        List<Order> testOrders = Arrays.asList(
                new Order("바비큐립", 2),
                new Order("초코케이크", 3),
                new Order("제로콜라", 1)
        );

        Customer customer = Customer.order(testDay, testOrders);

        assertThat(customer).isNotNull();
    }

    @DisplayName("writeOrderSheet 메서드 테스트")
    @Test
    void testWriteOrderSheet() {
        Day day = Day.of(3);
        List<Order> orders = List.of(
                new Order("초코케이크", 2),
                new Order("샴페인", 3)
        );
        Customer customer = Customer.order(day,orders);

        customer.writeOrderSheet();
    }
}
