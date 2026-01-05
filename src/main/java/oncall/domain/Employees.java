package oncall.domain;

import java.util.List;

public class Employees {
    private final Worker weekdayWorkers;
    private final Worker holidayWorkers;

    public Employees(List<String> weekdayWorkers, List<String> holidayWorkers) {
        this.weekdayWorkers = new Worker(weekdayWorkers);
        this.holidayWorkers = new Worker(holidayWorkers);
    }

    public String assignWorker(boolean isHoliday, String previousWorker) {
        if (isHoliday) {
            return holidayWorkers.getNextWorker(previousWorker);
        }
        return weekdayWorkers.getNextWorker(previousWorker);
    }
}