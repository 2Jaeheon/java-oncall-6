package oncall.model;

public enum DayOfWeek {
    MON("월", 0),
    TUE("화", 1),
    WED("수", 2),
    THU("목", 3),
    FRI("금", 4),
    SAT("토", 5),
    SUN("일", 6);

    public static final String ERROR_INVALID_DAYOFWEEK = "[ERROR] 잘못된 날짜를 입력하셨습니다. 다시 시도해주세요.";
    public static final String ERROR_INVALID_INPUT = "[ERROR] 잘못된 입력입니다.";
    private final String name;
    private final int index;

    DayOfWeek(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public static DayOfWeek of(String inputMonth) {
        for (DayOfWeek current : values()) {
            if (current.name.equals(inputMonth)) {
                return current;
            }
        }
        throw new IllegalArgumentException(ERROR_INVALID_DAYOFWEEK);
    }

    public boolean isWeekend() {
        return name.equals("토") || name.equals("일");
    }

    public String getName() {
        return name;
    }

    public DayOfWeek next() {
        int nextIndex = (this.index + 1) % 7;
        return values()[nextIndex];
    }
}
