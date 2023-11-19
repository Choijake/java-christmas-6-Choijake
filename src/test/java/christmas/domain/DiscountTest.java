package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import christmas.domain.constant.EventConstraint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DiscountTest {

    private OrderSheet mockOrderSheet;

    @BeforeEach
    void setUp() {
        mockOrderSheet = mock(OrderSheet.class);
    }

    @DisplayName("Discount 객체를 올바르게 생성하는지 테스트")
    @Test
    void createDiscount() {
        assertThat(Discount.of(mockOrderSheet)).isNotNull();
    }

    @DisplayName("getTotalDiscountAmount 메서드 테스트")
    @Test
    void getTotalDiscountAmount() {
        when(mockOrderSheet.isBeforeDDay()).thenReturn(true);
        when(mockOrderSheet.isWeekDay()).thenReturn(true);
        when(mockOrderSheet.isWeekEnd()).thenReturn(true);
        when(mockOrderSheet.hasStar()).thenReturn(true);
        when(mockOrderSheet.getTotalPrice()).thenReturn(EventConstraint.GIFT_PROMOTION_QUALIFIER_AMOUNT.getValue() + 1);
        when(mockOrderSheet.getDDayDiscountAmount()).thenReturn(100);
        when(mockOrderSheet.getDesertCount()).thenReturn(2);
        when(mockOrderSheet.getMainCount()).thenReturn(3);

        Discount discount = Discount.of(mockOrderSheet);

        assertThat(discount.getTotalDiscountAmount())
                .isEqualTo(100 + 2 * EventConstraint.WEEK_SALE_DISCOUNT_AMOUNT.getValue()
                        + 3 * EventConstraint.WEEK_SALE_DISCOUNT_AMOUNT.getValue()
                        + EventConstraint.STAR_DAY_DISCOUNT_AMOUNT.getValue()
                        + EventConstraint.GIFT_PROMOTION_DISCOUNT_AMOUNT.getValue());
    }

    @DisplayName("isBeforeDDay 메서드 테스트 - D-Day 이전")
    @Test
    void isBeforeDDayBefore() {
        when(mockOrderSheet.isBeforeDDay()).thenReturn(true);

        Discount discount = Discount.of(mockOrderSheet);

        assertThat(discount.isBeforeDDay()).isTrue();
    }

    @DisplayName("isBeforeDDay 메서드 테스트 - D-Day 이후")
    @Test
    void isBeforeDDayAfter() {
        when(mockOrderSheet.isBeforeDDay()).thenReturn(false);

        Discount discount = Discount.of(mockOrderSheet);

        assertThat(discount.isBeforeDDay()).isFalse();
    }

    @DisplayName("getDDayDiscountAmount 메서드 테스트 - D-Day 이전")
    @Test
    void getDDayDiscountAmountBefore() {
        when(mockOrderSheet.isBeforeDDay()).thenReturn(true);
        when(mockOrderSheet.getDDayDiscountAmount()).thenReturn(100);

        Discount discount = Discount.of(mockOrderSheet);

        assertThat(discount.getDDayDiscountAmount()).isEqualTo(100);
    }

    @DisplayName("getDDayDiscountAmount 메서드 테스트 - D-Day 이후")
    @Test
    void getDDayDiscountAmountAfter() {
        when(mockOrderSheet.isBeforeDDay()).thenReturn(false);

        Discount discount = Discount.of(mockOrderSheet);

        assertThat(discount.getDDayDiscountAmount()).isEqualTo(0);
    }

    @DisplayName("getWeekDayDiscountAmount 메서드 테스트 - 주중, 디저트 주문 있음")
    @Test
    void getWeekDayDiscountAmountWeekDayWithDesert() {
        when(mockOrderSheet.isWeekDay()).thenReturn(true);
        when(mockOrderSheet.getDesertCount()).thenReturn(2);

        Discount discount = Discount.of(mockOrderSheet);

        assertThat(discount.getWeekDayDiscountAmount()).isEqualTo(2 * EventConstraint.WEEK_SALE_DISCOUNT_AMOUNT.getValue());
    }

    @DisplayName("getWeekDayDiscountAmount 메서드 테스트 - 주중, 디저트 주문 없음")
    @Test
    void getWeekDayDiscountAmountWeekDayWithoutDesert() {
        when(mockOrderSheet.isWeekDay()).thenReturn(true);
        when(mockOrderSheet.getDesertCount()).thenReturn(0);

        Discount discount = Discount.of(mockOrderSheet);

        assertThat(discount.getWeekDayDiscountAmount()).isEqualTo(0);
    }

    @DisplayName("getWeekDayDiscountAmount 메서드 테스트 - 주말, 메인 주문 있음")
    @Test
    void getWeekDayDiscountAmountWeekEndWithMain() {
        when(mockOrderSheet.isWeekDay()).thenReturn(false);
        when(mockOrderSheet.isWeekEnd()).thenReturn(true);
        when(mockOrderSheet.getMainCount()).thenReturn(3);

        Discount discount = Discount.of(mockOrderSheet);

        assertThat(discount.getWeekDayDiscountAmount()).isEqualTo(0);
    }

    @DisplayName("getWeekDayDiscountAmount 메서드 테스트 - 주말, 메인 주문 없음")
    @Test
    void getWeekDayDiscountAmountWeekEndWithoutMain() {
        when(mockOrderSheet.isWeekDay()).thenReturn(false);
        when(mockOrderSheet.isWeekEnd()).thenReturn(true);
        when(mockOrderSheet.getMainCount()).thenReturn(0);

        Discount discount = Discount.of(mockOrderSheet);

        assertThat(discount.getWeekDayDiscountAmount()).isEqualTo(0);
    }

    @DisplayName("getWeekEndDiscountAmount 메서드 테스트 - 주중, 메인 주문 있음")
    @Test
    void getWeekEndDiscountAmountWeekDayWithMain() {
        when(mockOrderSheet.isWeekDay()).thenReturn(true);
        when(mockOrderSheet.isWeekEnd()).thenReturn(false);
        when(mockOrderSheet.getMainCount()).thenReturn(3);

        Discount discount = Discount.of(mockOrderSheet);

        assertThat(discount.getWeekEndDiscountAmount()).isEqualTo(0);
    }

    @DisplayName("getWeekEndDiscountAmount 메서드 테스트 - 주말, 메인 주문 없음")
    @Test
    void getWeekEndDiscountAmountWeekEndWithoutMain() {
        when(mockOrderSheet.isWeekDay()).thenReturn(false);
        when(mockOrderSheet.isWeekEnd()).thenReturn(true);
        when(mockOrderSheet.getMainCount()).thenReturn(0);

        Discount discount = Discount.of(mockOrderSheet);

        assertThat(discount.getWeekEndDiscountAmount()).isEqualTo(0);
    }

    @DisplayName("getStarDayDiscountAmount 메서드 테스트 - 별표 날짜")
    @Test
    void getStarDayDiscountAmountStarDay() {
        when(mockOrderSheet.hasStar()).thenReturn(true);

        Discount discount = Discount.of(mockOrderSheet);

        assertThat(discount.getStarDayDiscountAmount()).isEqualTo(EventConstraint.STAR_DAY_DISCOUNT_AMOUNT.getValue());
    }

    @DisplayName("getStarDayDiscountAmount 메서드 테스트 - 별표 날짜 아님")
    @Test
    void getStarDayDiscountAmountNotStarDay() {
        when(mockOrderSheet.hasStar()).thenReturn(false);

        Discount discount = Discount.of(mockOrderSheet);

        assertThat(discount.getStarDayDiscountAmount()).isEqualTo(0);
    }

    @DisplayName("getGiftPromotionDiscountAmount 메서드 테스트 - 할인 대상")
    @Test
    void getGiftPromotionDiscountAmountQualified() {
        when(mockOrderSheet.getTotalPrice()).thenReturn(EventConstraint.GIFT_PROMOTION_QUALIFIER_AMOUNT.getValue() + 1);

        Discount discount = Discount.of(mockOrderSheet);

        assertThat(discount.getGiftPromotionDiscountAmount()).isEqualTo(EventConstraint.GIFT_PROMOTION_DISCOUNT_AMOUNT.getValue());
    }

    @DisplayName("getGiftPromotionDiscountAmount 메서드 테스트 - 할인 대상 아님")
    @Test
    void getGiftPromotionDiscountAmountNotQualified() {
        when(mockOrderSheet.getTotalPrice()).thenReturn(EventConstraint.GIFT_PROMOTION_QUALIFIER_AMOUNT.getValue() - 1);

        Discount discount = Discount.of(mockOrderSheet);

        assertThat(discount.getGiftPromotionDiscountAmount()).isEqualTo(0);
    }

    @DisplayName("toString 메서드 테스트 - 할인이 있는 경우")
    @Test
    void toStringWithDiscount() {
        when(mockOrderSheet.isBeforeDDay()).thenReturn(true);
        when(mockOrderSheet.isWeekDay()).thenReturn(true);
        when(mockOrderSheet.isWeekEnd()).thenReturn(true);
        when(mockOrderSheet.hasStar()).thenReturn(true);
        when(mockOrderSheet.getTotalPrice()).thenReturn(EventConstraint.GIFT_PROMOTION_QUALIFIER_AMOUNT.getValue() + 1);
        when(mockOrderSheet.getDDayDiscountAmount()).thenReturn(100);
        when(mockOrderSheet.getDesertCount()).thenReturn(2);
        when(mockOrderSheet.getMainCount()).thenReturn(3);

        Discount discount = Discount.of(mockOrderSheet);

        assertThat(discount.toString()).contains("크리스마스 디데이 할인: -100원"
                ,"평일 할인: -4046"
                ,"주말 할인: -6069"
                ,"특별 할인: -1000원"
                ,"증정 이벤트: -25000원"
        );
    }

    @DisplayName("toString 메서드 테스트 - 할인이 없는 경우")
    @Test
    void toStringWithoutDiscount() {
        when(mockOrderSheet.isBeforeDDay()).thenReturn(false);
        when(mockOrderSheet.isWeekDay()).thenReturn(true);
        when(mockOrderSheet.isWeekEnd()).thenReturn(false);
        when(mockOrderSheet.hasStar()).thenReturn(false);
        when(mockOrderSheet.getTotalPrice()).thenReturn(EventConstraint.GIFT_PROMOTION_QUALIFIER_AMOUNT.getValue() - 1);

        Discount discount = Discount.of(mockOrderSheet);

        String expected = "없음";

        assertThat(discount.toString()).isEqualTo(expected);
    }
}

