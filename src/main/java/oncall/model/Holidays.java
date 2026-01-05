package oncall.model;

public enum Holidays {

    신정(1, 1, "1월 1일", "신정"),
    삼일절(3, 1, "3월 1일", "삼일절"),
    어린이날(5, 5, "5월 5일", "어린이날"),
    현충일(6, 6, "6월 6일", "현충일"),
    광복절(8, 15, "8월 15일", "광복절"),
    개천절(10, 3, "10월 3일", "개천절"),
    한글날(10, 9, "10월 9일", "한글날"),
    성탄절(12, 5, "12월 25일", "성탄절");

    private final int month;
    private final int date;
    private final String label;
    private final String name;

    Holidays(int month, int date, String label, String name) {
        this.month = month;
        this.date = date;
        this.label = label;
        this.name = name;
    }

    public int getMonth() {
        return month;
    }

    public int getDate() {
        return date;
    }

    public String getLabel() {
        return label;
    }

    public String getName() {
        return name;
    }
}
