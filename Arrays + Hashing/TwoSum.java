// the entry point basically, everyone starts and restarts here lol
// Given an array of integers nums and an integer target,
// return indices of the two numbers such that they add up to target.

import java.util.HashMap;

public class TwoSum {

    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> complement = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (complement.getOrDefault(nums[i], -1) != -1) {
                return new int[] { complement.get(nums[i]), i };
            }

            complement.put(target - nums[i], i);
        }

        return new int[] {};
    }

    public static void main(String[] args) {
        int[] nums = { 2, 7, 11, 15 };
        int target = 9;

        int[] result = twoSum(nums, target);

        System.out.println(result[0]);
        System.out.println(result[1]);
    }
}
