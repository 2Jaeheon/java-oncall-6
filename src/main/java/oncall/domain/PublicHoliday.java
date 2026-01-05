package oncall.domain;

public enum PublicHoliday {
    NEW_YEAR(1, 1),
    SAMIL(3, 1),
    CHILDREN(5, 5),
    MEMORIAL(6, 6),
    LIBERATION(8, 15),
    FOUNDATION(10, 3),
    HANGUL(10, 9),
    CHRISTMAS(12, 25);

    private final int month;
    private final int day;

    PublicHoliday(int month, int day) {
        this.month = month;
        this.day = day;
    }

    public static boolean isHoliday(int month, int day) {
        for (PublicHoliday holiday : values()) {
            if (holiday.month == month && holiday.day == day) {
                return true;
            }
        }
        return false;
    }
}