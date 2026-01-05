package oncall.model;

public enum Month {
    JANUARY(1, "1월", 31),
    FEBRUARY(2, "2월", 28),
    MARCH(3, "3월", 31),
    APRIL(4, "4월", 30),
    MAY(5, "5월", 31),
    JUNE(6, "6월", 30),
    JULY(7, "7월", 31),
    AUGUST(8, "8월", 31),
    SEPTEMBER(9, "9월", 30),
    OCTOBER(10, "10월", 31),
    NOVEMBER(11, "11월", 30),
    DECEMBER(12, "12월", 31);

    public static final String ERROR_INVALID_MONTH = "[ERROR] 월을 잘못 입력하셨습니다. 다시 시도해주세요";
    private final int month;
    private final String name;
    private final int lastDate;

    Month(int month, String name, int lastDate) {
        this.month = month;
        this.name = name;
        this.lastDate = lastDate;
    }

    public static Month of(int input) throws IllegalArgumentException {
        for (Month month : values()) {
            if (month.month == input) {
                return month;
            }
        }

        throw new IllegalArgumentException(ERROR_INVALID_MONTH);
    }

    public int getMonth() {
        return month;
    }

    public String getName() {
        return name;
    }

    public int getLastDate() {
        return lastDate;
    }
}
