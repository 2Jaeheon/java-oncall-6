package oncall.view;

import java.util.List;
import oncall.domain.DailyWorkLog;

public class OutputView {

    public static final String HOLIDAY_TAG = "(휴일)";
    public static final String MONTH = "월 ";
    public static final String DATE = "일 ";

    public static void printError(String message) {
        System.out.println(message);
    }

    public static void printSchedule(List<DailyWorkLog> logs) {
        System.out.println();
        for (DailyWorkLog log : logs) {
            System.out.println(formatLog(log));
        }
    }

    private static String formatLog(DailyWorkLog log) {
        StringBuilder sb = new StringBuilder();
        sb.append(log.month()).append(MONTH)
                .append(log.day()).append(DATE)
                .append(log.dayOfWeek().getKoreanName());

        // 평일이면서 법정공휴일인 경우에만 (휴일) 표기
        if (shouldPrintHoliday(log)) {
            sb.append(HOLIDAY_TAG);
        }

        sb.append(" ").append(log.worker());
        return sb.toString();
    }

    private static boolean shouldPrintHoliday(DailyWorkLog log) {
        return !log.dayOfWeek().isWeekend() && log.isPublicHoliday();
    }
}