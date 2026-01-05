package oncall.domain;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Worker {
    private final Deque<String> workers;

    public Worker(List<String> workerList) {
        validate(workerList);
        this.workers = new ArrayDeque<>(workerList);
    }

    private void validate(List<String> workerList) {
        if (workerList.size() < 5 || workerList.size() > 35) {
            throw new IllegalArgumentException("[ERROR] 근무자는 5명 이상 35명 이하여야 합니다.");
        }
    }

    public String getNextWorker(String prevWorker) {
        String candidate = workers.peekFirst();

        if (candidate.equals(prevWorker)) {
            return getSwappedWorker();
        }

        String worker = workers.pollFirst();
        workers.addLast(worker);
        return worker;
    }

    private String getSwappedWorker() {
        String first = workers.pollFirst();
        String second = workers.pollFirst();

        workers.addFirst(first);
        workers.addLast(second);

        return second;
    }
}