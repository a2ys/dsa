// You are given an array of CPU tasks, each
// labeled with a letter from A to Z, and a
// number n. Each CPU interval can be idle or
// allow the completion of one task. Tasks can
// be completed in any order, but there's a
// constraint: there has to be a gap of at least
// n intervals between two tasks with the same label.
// Return the minimum number of CPU intervals
// required to complete all tasks.

package Heap;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

class Task {

    char task;
    int remainingCount;
    int readyTime;

    Task(char task, int remainingCount, int readyTime) {
        this.task = task;
        this.remainingCount = remainingCount;
        this.readyTime = readyTime;
    }
}

public class TaskScheduler {

    public int leastInterval(char[] tasks, int n) {
        HashMap<Character, Integer> taskMap = new HashMap<>();
        PriorityQueue<Task> taskToPick = new PriorityQueue<>((a, b) ->
            Integer.compare(b.remainingCount, a.remainingCount)
        );
        Queue<Task> waiting = new LinkedList<>();
        int currentTime = 0;

        for (char task : tasks) {
            taskMap.put(task, taskMap.getOrDefault(task, 0) + 1);
        }

        for (char task : taskMap.keySet()) {
            Task taskToAdd = new Task(task, taskMap.get(task), 0);
            taskToPick.offer(taskToAdd);
        }

        while (!(taskToPick.isEmpty() && waiting.isEmpty())) {
            while (
                !waiting.isEmpty() && waiting.peek().readyTime <= currentTime
            ) {
                taskToPick.offer(waiting.poll());
            }

            if (!taskToPick.isEmpty()) {
                Task currentTask = taskToPick.poll();
                currentTask.remainingCount--;
                currentTask.readyTime = currentTime + n + 1;
                if (currentTask.remainingCount != 0) waiting.offer(currentTask);
            }

            currentTime++;
        }

        return currentTime;
    }
}
