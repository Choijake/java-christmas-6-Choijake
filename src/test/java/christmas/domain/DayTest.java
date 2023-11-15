package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.constant.EventConstraint;
import christmas.exception.ErrorMessage;
import christmas.exception.OrderException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DayTest {

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

    @DisplayName("getDDayDiscountAmount 메서드 테스트")
    @Test
    void getDDayDiscountAmount() {
        assertThat(Day.of(24).getDDayDiscountAmount()).isEqualTo(
                EventConstraint.INITIAL_D_DAY_DISCOUNT_AMOUNT.getValue() + (24-1) * 100);
        assertThat(Day.of(26).getDDayDiscountAmount()).isEqualTo(0);
    }

    @DisplayName("isBeforeDDay 메서드 테스트")
    @Test
    void isBeforeDDay() {
        assertThat(Day.of(24).isBeforeDDay()).isTrue();
        assertThat(Day.of(26).isBeforeDDay()).isFalse();
    }

    @DisplayName("isWeekDay 메서드 테스트")
    @Test
    void isWeekDay() {
        assertThat(Day.of(5).isWeekDay()).isTrue();
        assertThat(Day.of(8).isWeekDay()).isFalse();
    }

    @DisplayName("isWeekEnd 메서드 테스트")
    @Test
    void isWeekEnd() {
        assertThat(Day.of(5).isWeekEnd()).isFalse();
        assertThat(Day.of(8).isWeekEnd()).isTrue();
    }

    @DisplayName("hasStar 메서드 테스트")
    @Test
    void hasStar() {
        assertThat(Day.of(25).hasStar()).isTrue();
        assertThat(Day.of(26).hasStar()).isFalse();
    }
}

