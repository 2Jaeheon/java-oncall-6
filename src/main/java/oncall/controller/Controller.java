package oncall.controller;

import java.util.List;
import oncall.model.Employees;
import oncall.model.WorkDay;
import oncall.model.Work;
import oncall.model.Workers;
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
        WorkDay workDay = readValidWorkDay();
        Employees employees = readValidEmployees();

        List<Work> workTable = employees.createWorkTable(workDay);
        outputView.printWorkTable(workTable);
    }

    private WorkDay readValidWorkDay() {
        while (true) {
            try {
                String day = inputView.readWorkDay();
                return parser.parseMonthAndDay(day);
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private Employees readValidEmployees() {
        while (true) {
            try {
                Workers weekdayWorkers = parser.parseWorkers(inputView.readWeekdayWorkers());
                Workers holidayWorkers = parser.parseWorkers(inputView.readHolidayWorkers());
                return new Employees(weekdayWorkers, holidayWorkers);
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }
}
