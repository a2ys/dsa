// Given a 1-indexed array of integers numbers that
// is already sorted in non-decreasing order, find
// two numbers such that they add up to a specific
// target number.

import java.util.Arrays;

public class TwoSumTwo {

    public static int[] twoSum(int[] numbers, int target) {
        // put the first pointer at the beginning, second one at the end
        int i = 0,
            j = numbers.length - 1;

        while (i < j) {
            // declare a sum variable to avoid multiple calculations
            // we can use long instead of int to avoid overflows
            // in a real sum calculation scenario, but since leetcode
            // never asked for it, we would not scratch our head to it.
            // it is basically this: long sum = (long) numbers[i] + numbers[j]
            // this would convert it to long and prevent Integer overflow.
            int sum = numbers[i] + numbers[j];

            // if sum is equal to target, we found the pair.
            // since it is 1-indexed, we just return both added with one.

            if (sum == target) {
                return new int[] { i + 1, j + 1 };
            } else if (sum < target) {
                // remember that the array is already sorted in
                // ascending order.
                // if sum is less than target, then we move the left
                // pointer forward, because the left element is lesser
                // than the right, that means optimistically, we will
                // increase the left pointer once to just make sure
                // that we are moving towards the sum.
                // when we increase the left pointer, we increase the sum.

                i++;
            } else {
                // when the sum is more than the target, the right
                // element is the culprit, so we move the right element
                // backwards to again pull the sum closer towards the
                // actual target. again, we are being optimistic here.
                j--;
            }
        }

        // since the question said we will have a unique solution always,
        // we do not care about this part basically. this would never run.
        // but just for convention, we assume -1, -1 means no pair found.
        return new int[] { -1, -1 };
    }

    public static void main(String[] args) {
        System.out.println(
            Arrays.toString(twoSum(new int[] { 2, 7, 11, 15 }, 9))
        );
        System.out.println(Arrays.toString(twoSum(new int[] { 2, 3, 4 }, 6)));
        System.out.println(Arrays.toString(twoSum(new int[] { -1, 0 }, -1)));
    }
}
