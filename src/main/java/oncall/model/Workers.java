package oncall.model;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Workers {
    public static final String ERROR_WORKER_SIZE = "[ERROR] 비상근무자는 최소 5명에서 최대 35명 이여야 합니다.";
    public static final String ERROR_NICKNAME_DUPLICATE = "[ERROR] 닉네임은 중복될 수 없습니다.";

    private final ArrayDeque<String> workers;

    public Workers(ArrayDeque<String> workers) {
        validate(workers);
        this.workers = workers;
    }

    private void validate(ArrayDeque<String> workers) {
        if (workers.size() < 5 || workers.size() > 35) {
            throw new IllegalArgumentException(ERROR_WORKER_SIZE);
        }

        Set<String> set = new HashSet<>(workers);
        if (set.size() != workers.size()) {
            throw new IllegalArgumentException(ERROR_NICKNAME_DUPLICATE);
        }
    }

    public String getNextWorker(String prevWorker) {
        boolean isSameMember = prevWorker.equals(workers.peek());
        return pickMember(isSameMember);
    }

    private String pickMember(boolean isSameMember) {
        if (isSameMember) {
            String savedMember = workers.poll();
            String polledMember = workers.poll();
            workers.addLast(polledMember);
            workers.addFirst(savedMember);
            return polledMember;
        }

        String polledMember = workers.poll();
        workers.offer(polledMember);
        return polledMember;
    }

    private ArrayDeque<String> getWorkers() {
        return workers;
    }
}
