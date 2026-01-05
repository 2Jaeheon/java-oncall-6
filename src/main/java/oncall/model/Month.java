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

    private final int month;
    private final String name;
    private final int lastDate;

    Month(int month, String name, int lastDate) {
        this.month = month;
        this.name = name;
        this.lastDate = lastDate;
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
