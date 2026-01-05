package oncall.view;

import java.util.List;

public class OutputView {
    public static void printError(String message) {
        System.out.println(message);
    }

    public static void printSchedule(List<String> scheduleLines) {
        System.out.println();
        for (String line : scheduleLines) {
            System.out.println(line);
        }
    }
}