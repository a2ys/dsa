// Given an integer array nums, move all
// 0's to the end of it while maintaining
// the relative order of the non-zero elements.

import java.util.Arrays;

public class MoveZeroes {

    public static void moveZeroes(int[] nums) {
        // ah well so easy, try to understand by looking at my code
        // no time remaining lol
        // just remember that free square is a place where
        // zero was there already and i is just free which
        // helps you check if the current element is
        // zero or non-zero
        int freeSquare = 0;
        int i = 0;

        while (i < nums.length) {
            if (nums[i] == 0) i++;
            else nums[freeSquare++] = nums[i++];
        }

        while (freeSquare < nums.length) {
            nums[freeSquare++] = 0;
        }
    }

    public static void main(String[] args) {
        // int[] nums = { 0, 1, 0, 3, 12 };
        int[] nums = { 1, 3, 0, 12 };
        moveZeroes(nums);

        System.out.println(Arrays.toString(nums));
    }
}
