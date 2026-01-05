package oncall.util;

import java.util.ArrayDeque;
import oncall.model.DayOfWeek;
import oncall.model.WorkDay;
import oncall.model.Workers;

public class Parser {

    public static final String COMMA = ",";
    public static final String ERROR_INVALID_MONTH = "[ERROR] 달을 잘못 입력하셨습니다. 다시 입력해주세요.";
    public static final String ERROR_INVALID_INPUT = "[ERROR] 잘못된 입력입니다. 다시 입력해주세요.";

    public WorkDay parseMonthAndDay(String day) {
        String[] parts = day.split(COMMA);

        if (parts.length != 2) {
            throw new IllegalArgumentException(ERROR_INVALID_INPUT);
        }

        try {
            int month = Integer.parseInt(parts[0].trim());
            DayOfWeek dayOfWeek = DayOfWeek.of(parts[1].trim());
            return new WorkDay(month, dayOfWeek);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_INVALID_MONTH);
        }
    }

    public Workers parseWorkers(String raw) {
        ArrayDeque<String> workers = new ArrayDeque<>();
        String[] parts = raw.split(COMMA);

        for (String part : parts) {
            String name = part.trim();
            workers.offer(name);
        }

        return new Workers(workers);
    }
}
