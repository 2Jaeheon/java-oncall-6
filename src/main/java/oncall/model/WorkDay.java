package oncall.model;

import java.util.Arrays;

public record WorkDay(int month, DayOfWeek dayOfWeek) {
    public static final String ERROR_INVALID_INPUT = "[ERROR] 잘못된 입력입니다.";
    public static final String ERROR_INVALID_MONTH = "[ERROR] 월을 잘못 입력하셨습니다. 다시 입력해주세요.";

    public WorkDay {
        validate(month, dayOfWeek);
    }

    private void validate(int month, DayOfWeek dayOfWeek) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException(ERROR_INVALID_MONTH);
        }

    }

    public int getLastDay() {
        if (Arrays.asList(1, 3, 5, 7, 8, 10, 12).contains(month)) {
            return 31;
        }

        if (Arrays.asList(4, 6, 9, 11).contains(month)) {
            return 30;
        }

        if (month == 2) {
            return 28;
        }

        throw new IllegalArgumentException(ERROR_INVALID_INPUT);
    }
}
