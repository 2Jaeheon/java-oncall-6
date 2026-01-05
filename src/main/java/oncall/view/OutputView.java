package oncall.view;

import java.util.Map;
import oncall.model.DayOfWeek;
import oncall.model.Holidays;
import oncall.model.Month;

public class OutputView {
    public void printError(String message) {
        System.out.println(message);
    }

    public void printWorkTable(Map<Month, DayOfWeek> startDate, Map<Integer, String> history) {
        Month currentMonth = null;
        DayOfWeek currentDayOfWeek = null;
        for (Map.Entry<Month, DayOfWeek> entry : startDate.entrySet()) {
            currentMonth = entry.getKey();
            currentDayOfWeek = entry.getValue();
        }

        int month = currentMonth.getMonth();
        int date = 1;
        int endDate = currentMonth.getLastDate();
        while (date <= endDate) {
            printTable(history, month, date, currentDayOfWeek);

            date++;
            String nextDayOfWeek = DayOfWeek.nextDay(currentDayOfWeek);
            currentDayOfWeek = DayOfWeek.of(nextDayOfWeek);
        }
    }

    private void printTable(Map<Integer, String> history, int month, int date, DayOfWeek currentDayOfWeek) {
        String holidaySuffix = "";

        for (Holidays holidays : Holidays.values()) {
            if (holidays.getMonth() == month && holidays.getDate() == date) {
                holidaySuffix = "(휴일)";
                break;
            }
        }

        System.out.println(
                month + "월 " + date + "일 " + currentDayOfWeek.getName() + holidaySuffix + " " + history.get(date));
    }
}
