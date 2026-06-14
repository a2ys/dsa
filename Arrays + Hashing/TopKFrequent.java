import java.util.Arrays;
// Given an integer array nums and an integer k,
// return the k most frequent elements.
// You may return the answer in any order.

import java.util.HashMap;
import java.util.PriorityQueue;

public class TopKFrequent {

    public static int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // we are initializing the min heap with a comparator which is comparing with the rule:
        // map.get(a) - map.get(b)
        // map.get() returns the frequency of the element passed inside
        // thus the comparator basically says whichever frequency is lower, put it first
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(
            (a, b) -> map.get(a) - map.get(b) // <- this one is a min-heap, smallest freq elements come to the front
        );

        for (int num : map.keySet()) {
            minHeap.add(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        int[] result = new int[k];
        for (int i = k - 1; i > -1; i--) {
            // we basically puts the least freq element at the end of the array
            // the reveresed list traversal helps us put
            // the least freq element in the beginning in just one operation
            result[i] = minHeap.poll();
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = { 1, 1, 1, 2, 2, 3 };
        int k1 = 2;
        int[] result1 = topKFrequent(nums1, k1);
        System.out.println("Input: " + Arrays.toString(nums1) + ", k = " + k1);
        System.out.println(
            "Top " + k1 + " frequent elements: " + Arrays.toString(result1)
        );
    }
}
