package oncall.domain;


public record DailyWorkLog(
        int month,
        int day,
        DayOfWeek dayOfWeek,
        boolean isPublicHoliday,
        String worker
) {
}