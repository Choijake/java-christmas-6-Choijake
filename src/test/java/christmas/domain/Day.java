package christmas.domain;

public class Day {
    //TODO : 생성자에서 validate, day에 대한 할인 내역있는지 확인하는 인터페이스 만들기
    private final int day;

    private Day(int day) {
        this.day = day;
    }

    public static Day of(int day){
        return new Day(day);
    }
}
