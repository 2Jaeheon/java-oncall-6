package oncall.controller;

import java.util.List;
import oncall.domain.DayOfWeek;
import oncall.domain.Employees;
import oncall.domain.WorkMonth;
import oncall.utils.InputParser;
import oncall.view.InputView;
import oncall.view.OutputView;

public class Controller {

    public void run() {
        WorkMonth workMonth = getValidWorkMonth();
        Employees employees = getValidEmployees();

        List<String> result = workMonth.generateSchedule(employees);
        OutputView.printSchedule(result);
    }

    private WorkMonth getValidWorkMonth() {
        while (true) {
            try {
                String input = InputView.readMonthAndDay();
                int month = InputParser.parseMonth(input);
                String dayStr = InputParser.parseDayOfWeek(input);
                return new WorkMonth(month, DayOfWeek.from(dayStr));
            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }

    private Employees getValidEmployees() {
        while (true) {
            try {
                String weekdayInput = InputView.readWeekdayWorkers();
                List<String> weekdayWorkers = InputParser.parseWorkers(weekdayInput);

                String holidayInput = InputView.readHolidayWorkers();
                List<String> holidayWorkers = InputParser.parseWorkers(holidayInput);

                return new Employees(weekdayWorkers, holidayWorkers);
            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }
}