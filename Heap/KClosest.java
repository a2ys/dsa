// Given a sorted integer array arr, two
// integers k and x, return the k closest
// integers to x in the array. The result
// should also be sorted in ascending order.

package Heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class KClosest {

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> {
            int distA = Math.abs(a - x);
            int distB = Math.abs(b - x);

            if (distA != distB) {
                return Integer.compare(distB, distA);
            }

            return Integer.compare(b, a);
        });

        for (int num : arr) {
            maxHeap.offer(num);

            if (maxHeap.size() > k) maxHeap.poll();
        }

        List<Integer> result = new ArrayList<>();
        while (!maxHeap.isEmpty()) result.add(maxHeap.poll());
        Collections.sort(result);

        return result;
    }
}
