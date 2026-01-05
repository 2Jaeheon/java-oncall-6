package oncall.model;

import java.util.ArrayList;
import java.util.List;

public class Employees {
    public static final String ERROR_WORKER_NOT_SAME = "[ERROR] 비상 근무자는 평일과 휴일 각각 1회씩 있어야 합니다.";
    private final Workers weekdayWorkers;
    private final Workers holidayWorkers;

    public Employees(Workers weekdayWorkers, Workers holidayWorkers) {
        this.weekdayWorkers = weekdayWorkers;
        this.holidayWorkers = holidayWorkers;
    }

    public List<Work> createWorkTable(WorkDay workDay) {
        List<Work> table = new ArrayList<>();
        int currentMonth = workDay.getMonth();
        DayOfWeek currentDayOfWeek = workDay.getDayOfWeek();
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
