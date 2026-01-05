package oncall.model;

public enum Holiday {
    신정(1, 1),
    삼일절(3, 1),
    어린이날(5, 5),
    현충일(6, 6),
    광복절(8, 15),
    개천절(10, 3),
    한글날(10, 9),
    성탄절(12, 25);

    private final int month;
    private final int day;

    Holiday(int month, int day) {
        this.month = month;
        this.day = day;
    }

    public static boolean isHoliday(int month, int day) {
        for (Holiday holiday : values()) {
            int currentMonth = holiday.getMonth();
            int currentDay = holiday.getDay();

            if (currentMonth == month && currentDay == day) {
                return true;
            }
        }

        return false;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }
}
