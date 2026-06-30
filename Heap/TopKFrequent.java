package Heap;

import java.util.HashMap;
import java.util.PriorityQueue;

public class TopKFrequent {

    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> frequency = new HashMap<>();
        for (int num : nums) {
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) ->
            Integer.compare(frequency.get(a), frequency.get(b))
        );

        for (int key : frequency.keySet()) {
            pq.offer(key);

            if (pq.size() > k) pq.poll();
        }

        int[] result = new int[k];
        for (int i = k - 1; i > -1; i--) {
            result[i] = pq.poll();
        }

        return result;
    }
}
