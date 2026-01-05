package oncall.view;

import java.util.List;
import oncall.model.Work;

public class OutputView {
    public void printError(String message) {
        System.out.println(message);
    }

    public void printWorkTable(List<Work> workTable) {
        System.out.println();
        StringBuilder sb = new StringBuilder();
        for (Work work : workTable) {
            sb.append(work.month()).append("월 ")
                    .append(work.day()).append("일 ")
                    .append(work.dayOfWeek())
                    .append(" ");

            if (work.isPublicHoliday()) {
                sb.append("(휴일) ");
            }

            sb.append(work.name());
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
