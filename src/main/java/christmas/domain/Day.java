package christmas.domain;

import static christmas.exception.ErrorMessage.DAY_NOT_IN_RANGE;
import static christmas.exception.ErrorMessage.ENDS_WITH_DELIMITER;

import christmas.domain.constant.EventConstraint;
import christmas.exception.OrderException;

public class Day {
    private final int day;

    private Day(int day) {
        this.day = day;
        validate();
    }

    public static Day of(int day){
        return new Day(day);
    }

    public int getDDayDiscountAmount(){
        if(isBeforeDDay()){
            return EventConstraint.INITIAL_D_DAY_DISCOUNT_AMOUNT.getValue() + (day-1) * 100;
        }
        return 0;
    }

    public boolean isBeforeDDay(){
        return day <= EventConstraint.D_DAY.getValue();
    }

    public boolean isWeekDay(){
        for (int i = 1; i <= 30; i += 7) {
            if (day == i || day == i + 1) {
                return false;
            }
        }
        return true;
    }

    public boolean isWeekEnd(){
        for (int i = 1; i <= 30; i += 7) {
            if (day == i || day == i + 1) {
                return true;
            }
        }
        return false;
    }

    public boolean hasStar(){
        if(day == 25)return true;
        for(int i = 3; i <= 31; i += 7){
            if(day == i)return true;
        }
        return false;
    }

    public void validate(){
        validateEventPeriod();
    }
    private void validateEventPeriod(){
        if (day < EventConstraint.MIN_DAY.getValue() || day > EventConstraint.MAX_DAY.getValue()) {
            throw OrderException.from(DAY_NOT_IN_RANGE);
        }
    }
}
