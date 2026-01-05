package oncall.model;

public record Work(int month, int day, String dayOfWeek, boolean isPublicHoliday, String name) {
}
