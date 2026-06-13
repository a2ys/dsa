// Given an integer array nums
// return true if any value appears at least twice in the array
// and return false if every element is distinct.

import java.util.HashSet;

public class ContainsDuplicate {

    public static boolean containsDuplicate(int[] nums) {
        HashSet<Integer> appearedNums = new HashSet<>();

        for (int num : nums) {
            if (appearedNums.contains(num)) {
                return true;
            }

            appearedNums.add(num);
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3 };
        boolean result = containsDuplicate(nums);

        System.out.println("Contains Duplicate: " + result);
    }
}
