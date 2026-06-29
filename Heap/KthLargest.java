// Given an integer array nums and an integer
// k, return the kth largest element in the array.

import java.util.PriorityQueue;

public class KthLargest {

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) ->
            Integer.compare(a, b)
        );

        for (int num : nums) {
            minHeap.offer(num);

            if (minHeap.size() > k) minHeap.poll();
        }

        return minHeap.peek();
    }
}
