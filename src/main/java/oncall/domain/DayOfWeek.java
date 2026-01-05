package oncall.domain;

public enum DayOfWeek {
    MON("월", 0),
    TUE("화", 1),
    WED("수", 2),
    THU("목", 3),
    FRI("금", 4),
    SAT("토", 5),
    SUN("일", 6);

    private final String koreanName;
    private final int index;

    DayOfWeek(String koreanName, int index) {
        this.koreanName = koreanName;
        this.index = index;
    }

    public static DayOfWeek from(String name) {
        for (DayOfWeek day : values()) {
            if (day.koreanName.equals(name)) {
                return day;
            }
        }
        throw new IllegalArgumentException("[ERROR] 유효하지 않은 요일입니다.");
    }

    public DayOfWeek next() {
        return values()[(this.index + 1) % 7];
    }

    public boolean isWeekend() {
        return this == SAT || this == SUN;
    }

    public String getKoreanName() {
        return koreanName;
    }
}