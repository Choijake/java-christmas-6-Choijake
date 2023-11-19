package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import christmas.domain.constant.EventConstraint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class ReceiptTest {

    private OrderSheet mockOrderSheet;
    private Discount mockDiscount;

    @BeforeEach
    void setUp() {
        mockOrderSheet = mock(OrderSheet.class);
        mockDiscount = mock(Discount.class);
    }

    @DisplayName("Receipt 객체를 올바르게 생성하는지 테스트")
    @Test
    void createReceipt() {
        when(mockOrderSheet.getTotalPrice()).thenReturn(50000);
        when(mockDiscount.getTotalDiscountAmount()).thenReturn(10000);

        assertThat(Receipt.of(mockOrderSheet, mockDiscount)).isNotNull();
    }

    @DisplayName("getOrderMenu 메서드 테스트")
    @Test
    void getOrderMenu() {
        when(mockOrderSheet.toString()).thenReturn("초코케이크 2개\n");
        Receipt receipt = Receipt.of(mockOrderSheet, mockDiscount);

        assertThat(receipt.getOrderMenu()).isEqualTo("초코케이크 2개\n");
    }

    @DisplayName("getBeforeDiscountAmount 메서드 테스트")
    @Test
    void getBeforeDiscountAmount() {
        when(mockOrderSheet.getTotalPrice()).thenReturn(50000);
        Receipt receipt = Receipt.of(mockOrderSheet, mockDiscount);

        assertThat(receipt.getBeforeDiscountAmount()).isEqualTo(50000);
    }

    @DisplayName("hasGiftPromotionMenu 메서드 테스트 - 증정 메뉴가 있는 경우")
    @Test
    void hasGiftPromotionMenuTrue() {
        when(mockDiscount.getGiftPromotionDiscountAmount()).thenReturn(EventConstraint.GIFT_PROMOTION_DISCOUNT_AMOUNT.getValue());
        Receipt receipt = Receipt.of(mockOrderSheet, mockDiscount);

        assertThat(receipt.hasGiftPromotionMenu()).isTrue();
    }

    @DisplayName("hasGiftPromotionMenu 메서드 테스트 - 증정 메뉴가 없는 경우")
    @Test
    void hasGiftPromotionMenuFalse() {
        when(mockDiscount.getGiftPromotionDiscountAmount()).thenReturn(0);
        Receipt receipt = Receipt.of(mockOrderSheet, mockDiscount);

        assertThat(receipt.hasGiftPromotionMenu()).isFalse();
    }

    @DisplayName("getDiscountDetails 메서드 테스트")
    @Test
    void getDiscountDetails() {
        when(mockDiscount.toString()).thenReturn("할인 내역: 10,000원 할인\n");
        Receipt receipt = Receipt.of(mockOrderSheet, mockDiscount);

        assertThat(receipt.getDiscountDetails()).isEqualTo("할인 내역: 10,000원 할인\n");
    }

    @DisplayName("getTotalDiscountAmount 메서드 테스트")
    @Test
    void getTotalDiscountAmount() {
        when(mockDiscount.getTotalDiscountAmount()).thenReturn(10000);
        Receipt receipt = Receipt.of(mockOrderSheet, mockDiscount);

        assertThat(receipt.getTotalDiscountAmount()).isEqualTo(10000);
    }

    @DisplayName("getEventBadge 메서드 테스트")
    @Test
    void getEventBadge() {
        when(mockDiscount.getTotalDiscountAmount()).thenReturn(4000);

        // 배지 조건을 충족하지 않는 경우
        Receipt receipt1 = Receipt.of(mockOrderSheet, mockDiscount);
        assertThat(receipt1.getEventBadge()).isEqualTo("없음");

        // 별 배지 조건을 충족하는 경우
        when(mockDiscount.getTotalDiscountAmount()).thenReturn(5500);
        Receipt receipt2 = Receipt.of(mockOrderSheet, mockDiscount);
        assertThat(receipt2.getEventBadge()).isEqualTo("별");

        // 트리 배지 조건을 충족하는 경우
        when(mockDiscount.getTotalDiscountAmount()).thenReturn(10000);
        Receipt receipt3 = Receipt.of(mockOrderSheet, mockDiscount);
        assertThat(receipt3.getEventBadge()).isEqualTo("트리");

        // 산타 배지 조건을 충족하는 경우
        when(mockDiscount.getTotalDiscountAmount()).thenReturn(20000);
        Receipt receipt4 = Receipt.of(mockOrderSheet, mockDiscount);
        assertThat(receipt4.getEventBadge()).isEqualTo("산타");
    }

    @DisplayName("getDiscountedPrice 메서드 테스트 - 증정 메뉴가 있는 경우")
    @Test
    void getDiscountedPriceWithGiftPromotion() {
        when(mockOrderSheet.getTotalPrice()).thenReturn(50000);
        when(mockDiscount.getTotalDiscountAmount()).thenReturn(10000);
        when(mockDiscount.getGiftPromotionDiscountAmount()).thenReturn(EventConstraint.GIFT_PROMOTION_DISCOUNT_AMOUNT.getValue());

        Receipt receipt = Receipt.of(mockOrderSheet, mockDiscount);

        assertThat(receipt.getDiscountedPrice()).isEqualTo(40000 + EventConstraint.GIFT_PROMOTION_DISCOUNT_AMOUNT.getValue());
    }

    @DisplayName("getDiscountedPrice 메서드 테스트 - 증정 메뉴가 없는 경우")
    @Test
    void getDiscountedPriceWithoutGiftPromotion() {
        when(mockOrderSheet.getTotalPrice()).thenReturn(50000);
        when(mockDiscount.getTotalDiscountAmount()).thenReturn(10000);
        when(mockDiscount.getGiftPromotionDiscountAmount()).thenReturn(0);

        Receipt receipt = Receipt.of(mockOrderSheet, mockDiscount);

        assertThat(receipt.getDiscountedPrice()).isEqualTo(40000);
    }
}
