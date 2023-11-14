package christmas.domain;

public class Day {
    //TODO : 생성자에서 validate, day에 대한 할인 내역있는지 확인하는 인터페이스 만들기
    private final int day;
    public static final int D_DAY = 25;
    private static final int INITIAL_D_DAY_DISCOUNT_AMOUNT = 1000;

    private Day(int day) {
        this.day = day;
        validate();
    }

    public static Day of(int day){
        return new Day(day);
    }

    public int getDDayDiscountAmount(){
        if(isBeforeDDay()){
            return INITIAL_D_DAY_DISCOUNT_AMOUNT + (day-1) * 100;
        }
        return 0;
    }

    public boolean isBeforeDDay(){
        return day <= D_DAY;
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

    //TODO : day 1~31 사이의 수 인지 확인 후 아니면 다시 입력 하도록
    private void validateEventPeriod(){

    }


}
