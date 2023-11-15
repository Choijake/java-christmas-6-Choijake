package christmas.unit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import christmas.domain.Day;
import christmas.domain.Manager;
import christmas.domain.Order;
import christmas.domain.OrderSheet;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ManagerOrderHandlerTest {
    @DisplayName("Test the process of creating Discount and issuing Receipt")
    @Test
    void testDiscountAndIssueReceipt() {
        Day day = Day.of(3);
        List<Order> orders = List.of(
                new Order("초코케이크", 2),
                new Order("샴페인", 3)
        );
        OrderSheet orderSheet = OrderSheet.of(day, orders);
        Manager manager = Manager.get(orderSheet);

        assertThat(manager).isNotNull();
    }
}
