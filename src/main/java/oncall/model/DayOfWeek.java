package oncall.model;

public enum DayOfWeek {
    MONDAY("월"),
    TUESDAY("화"),
    WEDNESDAY("수"),
    THURSDAY("목"),
    FRIDAY("금"),
    SATURDAY("토"),
    SUNDAY("일");

    public static final String ERROR_INVALID_DAYOFWEEK = "[ERROR] 요일을 잘못 입력했습니다. 다시 시도해주세요.";
    private String name;

    DayOfWeek(String name) {
        this.name = name;
    }

    public static DayOfWeek of(String dayOfWeek) {
        for (DayOfWeek day : values()) {
            if (day.name.equals(dayOfWeek)) {
                return day;
            }
        }

        throw new IllegalArgumentException(ERROR_INVALID_DAYOFWEEK);
    }

    public static String nextDay(DayOfWeek dayOfWeek) {
        if (dayOfWeek.getName().equals("월")) {
            return "화";
        }
        if (dayOfWeek.getName().equals("화")) {
            return "수";
        }
        if (dayOfWeek.getName().equals("수")) {
            return "목";
        }
        if (dayOfWeek.getName().equals("목")) {
            return "금";
        }
        if (dayOfWeek.getName().equals("금")) {
            return "토";
        }
        if (dayOfWeek.getName().equals("토")) {
            return "일";
        }
        if (dayOfWeek.getName().equals("일")) {
            return "월";
        }

        throw new IllegalArgumentException("[ERROR]");
    }

    public String getName() {
        return name;
    }

    public boolean isWeekday() {
        for (DayOfWeek day : values()) {
            if (!day.getName().equals("토") && !day.getName().equals("일")) {
                return true;
            }
        }

        return false;
    }

    public boolean isWeekend(DayOfWeek dayOfWeek) {
        if (dayOfWeek.getName().equals("토") || dayOfWeek.getName().equals("일")) {
            return true;
        }

        return false;
    }
}
