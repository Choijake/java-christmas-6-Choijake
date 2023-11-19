package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ManagerTest {

    private OrderSheet mockOrderSheet;
    private Discount mockDiscount;

    @BeforeEach
    void setUp() {
        mockOrderSheet = mock(OrderSheet.class);
        mockDiscount = mock(Discount.class);
    }

    @DisplayName("Manager 객체를 올바르게 생성하는지 테스트")
    @Test
    void createManager() {
        assertThat(Manager.get(mockOrderSheet)).isNotNull();
    }

    @DisplayName("issueReceipt 메서드 테스트")
    @Test
    void issueReceipt() {
        when(mockOrderSheet.toString()).thenReturn("Mock OrderSheet");
        when(mockDiscount.toString()).thenReturn("Mock Discount");

        Manager manager = Manager.get(mockOrderSheet);
        Receipt receipt = manager.issueReceipt(mockDiscount);

        assertThat(receipt).isNotNull();
        assertThat(receipt.getDiscountDetails()).contains("Mock Discount");
    }
}

