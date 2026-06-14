// Given an unsorted array of integers nums, return
// the length of the longest consecutive elements sequence.

import java.util.HashSet;

public class LongestConsecutiveSequence {

    public static int longestConsecutive(int[] nums) {
        // we maintain a hashset for fast lookups, O(1)
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);

        int longestConsecutiveLength = 0;

        // we do not iterate over the nums parameter again
        // this is done to prevent duplicates
        // cuz set already put the duplicates into one array
        for (int num : set) {
            // we are only checking the beginning of the sequence
            // we do not care about the middle of a sequence
            if (!set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                // when we have found a beginning of a sequence
                // we check for how long the sequence is carried on
                while (set.contains(currentNum + 1)) {
                    currentNum++;
                    currentStreak++;
                }

                // is the current recorded sequence bigger, or was the older one bigger
                longestConsecutiveLength = Math.max(
                    longestConsecutiveLength,
                    currentStreak
                );
            }
        }

        return longestConsecutiveLength;
    }

    public static void main(String[] args) {
        int[] nums = { 100, 4, 200, 1, 3, 2 };
        System.out.println("Length: " + longestConsecutive(nums));
    }
}
