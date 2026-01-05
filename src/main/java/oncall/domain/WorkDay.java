package oncall.domain;

public class WorkDay {
    private final int month;
    private final int day;
    private final DayOfWeek dayOfWeek;
    private final boolean isPublicHoliday;

    public WorkDay(int month, int day, DayOfWeek dayOfWeek) {
        this.month = month;
        this.day = day;
        this.dayOfWeek = dayOfWeek;
        this.isPublicHoliday = PublicHoliday.isHoliday(month, day);
    }

    public boolean isHoliday() {
        return dayOfWeek.isWeekend() || isPublicHoliday;
    }

    public boolean shouldPrintHolidayInfo() {
        return !dayOfWeek.isWeekend() && isPublicHoliday;
    }

    public boolean isPublicHoliday() {
        return isPublicHoliday;
    }

    public String toStringFormat(String workerName) {
        StringBuilder sb = new StringBuilder();
        sb.append(month).append("월 ").append(day).append("일 ").append(dayOfWeek.getKoreanName());
        if (shouldPrintHolidayInfo()) {
            sb.append("(휴일)");
        }
        sb.append(" ").append(workerName);
        return sb.toString();
    }
}