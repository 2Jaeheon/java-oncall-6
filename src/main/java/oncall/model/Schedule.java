package oncall.model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Schedule {
    public static final String ERROR_WORKER_SIZE = "[ERROR] 비상 근무자는 최대 35명까지만 가능합니다.";
    public static final String ERROR_NOT_SAME_NAMES = "[ERORR] 평일 비상 근무 순번의 사원과 주말 비상 근무 순번의 사원 이름이 다릅니다.";

    private final ArrayDeque<Member> weekdayMembers;
    private final ArrayDeque<Member> holidayMembers;
    private final Map<Integer, String> history;

    public Schedule(ArrayDeque<Member> weekdayMembers, ArrayDeque<Member> holidayMembers) {
        validate(weekdayMembers, holidayMembers);
        this.weekdayMembers = weekdayMembers;
        this.holidayMembers = holidayMembers;
        history = new LinkedHashMap<>();
    }

    private void validate(ArrayDeque<Member> weekdayMembers, ArrayDeque<Member> holidayMembers) {
        int totalWorkersSize = weekdayMembers.size();

        if (totalWorkersSize > 35) {
            throw new IllegalArgumentException(ERROR_WORKER_SIZE);
        }

        validateExistSameName(weekdayMembers, holidayMembers);
    }

    public Map<Integer, String> calculate(Month month, DayOfWeek dayOfWeek) {
        int date = 1;
        int endDate = month.getLastDate();
        initHistory(endDate);
        List<Integer> holidays = calculateHolidays(month, dayOfWeek);
        extractHistory(dayOfWeek, date, endDate, holidays);

        return history;
    }

    private void extractHistory(DayOfWeek dayOfWeek, int date, int endDate, List<Integer> holidays) {
        while (date <= endDate) {
            boolean isHoliday = holidays.contains(date);
            String yesterdayMember = history.get(date - 1);

            // 오늘이 휴일일 때
            process(isHoliday, yesterdayMember, holidayMembers, date);
            process(!isHoliday, yesterdayMember, weekdayMembers, date);

            // 다음 날로 이동
            date++;
            String nextDayOfWeek = DayOfWeek.nextDay(dayOfWeek);
            dayOfWeek = DayOfWeek.of(nextDayOfWeek);
        }
    }

    private void initHistory(int endDate) {
        for (int i = 0; i <= endDate; i++) {
            history.put(i, "");
        }
    }

    private void process(boolean isHoliday, String yesterdayMember, ArrayDeque<Member> holidayMembers, int date) {
        if (isHoliday) {
            if (yesterdayMember.equals(holidayMembers.peek().name())) {
                Member savedMember = holidayMembers.poll();
                Member polledMember = holidayMembers.poll();
                history.put(date, polledMember.name());
                holidayMembers.addLast(polledMember);
                holidayMembers.addFirst(savedMember);
            }

            if (!yesterdayMember.equals(holidayMembers.peek().name())) {
                Member polledMember = holidayMembers.poll();
                history.put(date, polledMember.name());
                holidayMembers.addLast(polledMember);
            }
        }
    }

    private List<Integer> calculateHolidays(Month target, DayOfWeek dayOfWeek) {
        List<Integer> holidays = new ArrayList<>();
        int month = target.getMonth();
        int date = 1;

        extractHolidays(target, dayOfWeek, date, month, holidays);

        return holidays;
    }

    private void extractHolidays(Month target, DayOfWeek dayOfWeek, int date, int month, List<Integer> holidays) {
        while (date <= target.getLastDate()) {
            for (Holidays holiday : Holidays.values()) {
                if (month == holiday.getMonth() && holiday.getDate() == date && dayOfWeek.isWeekday()) {
                    holidays.add(date);
                }
            }
            if (dayOfWeek.isWeekend(dayOfWeek)) {
                holidays.add(date);
            }
            date++;
            String nextDayOfWeek = DayOfWeek.nextDay(dayOfWeek);
            dayOfWeek = DayOfWeek.of(nextDayOfWeek);
        }
    }

    private void validateExistSameName(ArrayDeque<Member> weekdayMembers, ArrayDeque<Member> holidayMembers) {
        List<String> weekdayWorkers = new ArrayList<>();
        List<String> holidayWorkers = new ArrayList<>();

        for (int i = 0; i < weekdayMembers.size(); i++) {
            Member weekdayMember = weekdayMembers.poll();
            weekdayWorkers.add(weekdayMember.name());
            weekdayMembers.addLast(weekdayMember);

            Member holidayMember = holidayMembers.poll();
            holidayWorkers.add(holidayMember.name());
            holidayMembers.addLast(holidayMember);
        }

        Collections.sort(weekdayWorkers);
        Collections.sort(holidayWorkers);

        if (!weekdayWorkers.equals(holidayWorkers)) {
            throw new IllegalArgumentException(ERROR_NOT_SAME_NAMES);
        }
    }
}
