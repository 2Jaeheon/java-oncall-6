package oncall.util;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import oncall.model.DayOfWeek;
import oncall.model.Member;
import oncall.model.Month;

public class Parser {

    public static final String ERROR_INVALID_INPUT = "[ERROR] 잘못된 입력입니다. 다시 시도해주세요.";
    public static final String ERROR_DUPLICATED_NICKNAME = "[ERROR] 근무자 닉네임은 중복될 수 없습니다.";
    public static final String ERROR_NICKNAME_SIZE_OVER = "[ERROR] 근무자 닉네임은 최대 5자 이하여야 합니다.";
    public static final String ERROR_WORKER_COUNT = "[ERROR] 근무자는 최소 5명 이상이여야 합니다.";

    public Map<Month, DayOfWeek> parseDay(String monthAndDate) throws IllegalArgumentException {
        Map<Month, DayOfWeek> start = new LinkedHashMap<>();
        String[] parts = monthAndDate.split(",");

        int parsedMonth;
        try {
            parsedMonth = Integer.parseInt(parts[0].trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_INVALID_INPUT);
        }

        Month month = Month.of(parsedMonth);
        DayOfWeek dayOfWeek = DayOfWeek.of(parts[1].trim());
        start.put(month, dayOfWeek);
        return start;
    }

    public ArrayDeque<Member> parseNames(String raw) {
        ArrayDeque<Member> members = new ArrayDeque<>();

        String[] parts = raw.split(",");
        for (String part : parts) {
            members.add(new Member(part));
            validateName(part);
        }

        if (members.size() < 5) {
            throw new IllegalArgumentException(ERROR_WORKER_COUNT);
        }

        Set<Member> duplicatedSet = new HashSet<>(members);
        if (duplicatedSet.size() != members.size()) {
            throw new IllegalArgumentException(ERROR_DUPLICATED_NICKNAME);
        }

        return members;
    }

    private void validateName(String part) {
        if (part.length() > 5) {
            throw new IllegalArgumentException(ERROR_NICKNAME_SIZE_OVER);
        }
    }
}
