package oncall.utils;

import java.util.ArrayList;
import java.util.List;

public class InputParser {

    public static int parseMonth(String input) {
        try {
            String[] parts = input.split(",");
            return Integer.parseInt(parts[0].trim());
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
        }
    }

    public static String parseDayOfWeek(String input) {
        try {
            String[] parts = input.split(",");
            return parts[1].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
        }
    }

    public static List<String> parseWorkers(String input) {
        String[] parts = input.split(",");
        List<String> workers = new ArrayList<>();
        for (String name : parts) {
            workers.add(name.trim());
        }
        validateUnique(workers);
        return workers;
    }

    private static void validateUnique(List<String> workers) {
        long uniqueCount = workers.stream().distinct().count();
        if (uniqueCount != workers.size()) {
            throw new IllegalArgumentException("[ERROR] 근무자는 중복될 수 없습니다.");
        }
    }
}