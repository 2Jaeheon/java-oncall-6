package oncall.domain;

import java.util.ArrayList;
import java.util.List;

public class WorkMonth {
    private final int month;
    private final DayOfWeek startDay;

    public WorkMonth(int month, DayOfWeek startDay) {
        validateMonth(month);
        this.month = month;
        this.startDay = startDay;
    }

    private void validateMonth(int month) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("[ERROR] 월은 1~12 사이의 숫자여야 합니다.");
        }
    }

    public List<String> generateSchedule(Employees employees) {
        List<String> scheduleResult = new ArrayList<>();
        int maxDays = getMaxDays();
        DayOfWeek currentDayOfWeek = startDay;
        String previousWorker = "";

        for (int day = 1; day <= maxDays; day++) {
            WorkDay workDay = new WorkDay(month, day, currentDayOfWeek);
            String worker = employees.assignWorker(workDay.isHoliday(), previousWorker);

            scheduleResult.add(workDay.toStringFormat(worker));

            previousWorker = worker;
            currentDayOfWeek = currentDayOfWeek.next();
        }
        return scheduleResult;
    }

    private int getMaxDays() {
        if (month == 2) {
            return 28;
        }
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        }
        return 31;
    }
}