// Given an integer array nums, return all the triplets
// [nums[i], nums[j], nums[k]] such that i != j, i != k,
// and j != k, and nums[i] + nums[j] + nums[k] == 0.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

    // so this algo is just two sum II but a different fashion
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> triplets = new ArrayList<>();
        // to use two sum II, you are doing sorting
        // this is mainly to first, prevent any duplicates
        // next, is to help you use two sum II using the
        // two pointer approach, allowing you to conditionally
        // increase or decrease the pointers.
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            // these two conditions define the algorithm on
            // edge cases and a little smart optimization

            // this condition allows us to check for duplicate numbers
            // from the second position onwards, otherwise we will
            // get repeating pairs, which we absolutely do not want.
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            // this below condition is an optimistic condition which is based
            // on the fact that, if you have reached so far in the sorted
            // array, that after a certain point, you cross 0, then there
            // will be no element and no combination of elements that will
            // allow you to ever reach a sum of zero. so this allows you an
            // early exit from a set of dead lookups. you will understand
            // this better after you understand the full code.
            if (nums[i] > 0) break;

            // the fixed is the number we first fix, like an anvil. the two
            // pointers j and k will be moving across the sorted array starting
            // right after the fixed position i. this would then become just a
            // replica of two sum ii.
            // fixed gets updated after every iteration to the next element, until
            // we hit the third last element which actually contains the final
            // triplet to exist.
            int fixed = nums[i];
            int j = i + 1,
                k = nums.length - 1;

            // condition to prevent pointers from crossing each other
            while (j < k) {
                // a nice and clean sum variable to make our comparisons easy
                int sum = fixed + nums[j] + nums[k];

                // if our target condition of acheiving a sum of 0 is fulfilled,
                // we add it to the array and do some other magic inside.
                if (sum == 0) {
                    // syntax buff lol
                    triplets.add(Arrays.asList(fixed, nums[j], nums[k]));

                    // we move both pointers from where they originally were
                    // to prevent any duplicate pairs. left pointer moves right
                    // and right pointer moves left.
                    j++;
                    k--;

                    // now we are skipping duplicates. say the last position we
                    // were on contained a 2 in nums[j] and when we moved forward,
                    // we again encounter a j, we move forward again and keep moving
                    // forward until we get a unique number, or we cross the k pointer.
                    // the same explanation goes for the motion of the k pointer
                    // with the while loop.
                    while (j < k && nums[j] == nums[j - 1]) j++;
                    while (j < k && nums[k] == nums[k + 1]) k--;
                } else if (sum < 0) {
                    // if the sum is less than zero, then the pointer containing
                    // the smaller number is moved forward optimistically to
                    // get a greater number and increase our chance to getting
                    // a solution.
                    j++;
                } else {
                    // same with moving our pointer containing a larger
                    // number backward. move towards the solution.
                    k--;
                }
            }
        }

        // at the end, we return all the triplets we found.
        // our cool conditions helped us remove any duplicates and staying
        // safe from weird edge cases.
        // also we cut a little time if you understood the condition of
        // breaking at a number greater than zero itself.
        return triplets;
    }

    public static void main(String[] args) {
        ThreeSum ts = new ThreeSum();

        System.out.println(ts.threeSum(new int[] { -1, 0, 1, 2, -1, -4 }));
        System.out.println(ts.threeSum(new int[] { 0, 1, 1 }));
        System.out.println(ts.threeSum(new int[] { 0, 0, 0 }));
        System.out.println(ts.threeSum(new int[] { -2, 0, 1, 1, 2 }));
    }
}
