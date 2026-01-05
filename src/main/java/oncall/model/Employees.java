package oncall.model;

import java.util.ArrayList;
import java.util.List;

public class Employees {
    private final Workers weekdayWorkers;
    private final Workers holidayWorkers;

    public Employees(Workers weekdayWorkers, Workers holidayWorkers) {
        this.weekdayWorkers = weekdayWorkers;
        this.holidayWorkers = holidayWorkers;
    }

    public List<Work> createWorkTable(WorkDay workDay) {
        List<Work> table = new ArrayList<>();
        int currentMonth = workDay.month();
        DayOfWeek currentDayOfWeek = workDay.dayOfWeek();
        String prevWorker = "";

        extractWorkTable(workDay, currentMonth, currentDayOfWeek, prevWorker, table);

        return table;
    }

    private void extractWorkTable(WorkDay workDay, int currentMonth, DayOfWeek currentDayOfWeek, String prevWorker,
                                  List<Work> table) {
        for (int day = 1; day <= workDay.getLastDay(); day++) {
            boolean isPublicHoliday = Holiday.isHoliday(currentMonth, day);
            boolean isWeekend = currentDayOfWeek.isWeekend();
            boolean isHoliday = isPublicHoliday || isWeekend;

            String nextWorker = applyWorker(isHoliday, prevWorker);
            Work work = new Work(currentMonth, day, currentDayOfWeek.getName(), isPublicHoliday, nextWorker);
            table.add(work);

            prevWorker = nextWorker;
            currentDayOfWeek = currentDayOfWeek.next();
        }
    }

    private String applyWorker(boolean isHoliday, String prevWorker) {
        if (isHoliday) {
            return holidayWorkers.getNextWorker(prevWorker);
        }

        return weekdayWorkers.getNextWorker(prevWorker);
    }
}
