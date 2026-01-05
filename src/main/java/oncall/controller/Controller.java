package oncall.controller;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.Map.Entry;
import oncall.model.DayOfWeek;
import oncall.model.Member;
import oncall.model.Month;
import oncall.model.Schedule;
import oncall.util.Parser;
import oncall.view.InputView;
import oncall.view.OutputView;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;
    private final Parser parser;

    public Controller(InputView inputView, OutputView outputView, Parser parser) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.parser = parser;
    }

    public void run() {
        Map<Month, DayOfWeek> startDate = readDate();
        Schedule schedule = readMembersUntilValid();

        Map<Integer, String> workTable = calculateWorkTable(startDate, schedule);

        outputView.printWorkTable(startDate, workTable);
    }

    private Map<Integer, String> calculateWorkTable(Map<Month, DayOfWeek> startDate, Schedule schedule) {

        Month month = null;
        DayOfWeek dayOfWeek = null;
        for (Entry<Month, DayOfWeek> entry : startDate.entrySet()) {
            month = entry.getKey();
            dayOfWeek = entry.getValue();
        }

        return schedule.calculate(month, dayOfWeek);
    }

    private Map<Month, DayOfWeek> readDate() {
        while (true) {
            try {
                String monthAndDate = inputView.readMonthAndDate();
                return parser.parseDay(monthAndDate);
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private Schedule readMembersUntilValid() {
        while (true) {
            try {
                ArrayDeque<Member> weekdayMembers = parser.parseNames(inputView.readWeekdayWorkers());
                ArrayDeque<Member> holidayMembers = parser.parseNames(inputView.readHolidayWorkers());
                return new Schedule(weekdayMembers, holidayMembers);
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }
}
