package christmas.unit;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import christmas.domain.Day;
import christmas.domain.Discount;
import christmas.domain.Manager;
import christmas.domain.Order;
import christmas.domain.OrderSheet;
import christmas.domain.Receipt;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ReceiptGenerationTest {
    @DisplayName("영수증을 발급하는 기능에 테스트")
    @Test
    void testDiscountAndIssueReceipt() {
        Day day = Day.of(3);
        List<Order> orders = List.of(
                new Order("초코케이크", 2),
                new Order("샴페인", 3)
        );
        OrderSheet orderSheet = OrderSheet.of(day, orders);
        Manager manager = Manager.get(orderSheet);


        Discount discount = Discount.of(orderSheet);
        Receipt result = manager.issueReceipt(discount);

        assertThat(result).isNotNull();
    }
}
