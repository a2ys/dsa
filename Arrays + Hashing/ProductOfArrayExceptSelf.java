// Given an integer array nums,
// return an array answer such that answer[i] is equal
// to the product of all the elements of nums except nums[i].
// we are not allowed to use the division operator btw

import java.util.Arrays;

public class ProductOfArrayExceptSelf {

    public static int[] productExceptSelf(int[] nums) {
        // three arrays to store three products
        int[] productArray = new int[nums.length];
        int[] leftProduct = new int[nums.length];
        int[] rightProduct = new int[nums.length];

        // this is the prefix array
        // basically we use this to store the product of
        // all the numbers to the left of the current index we are standing at
        int startProduct = 1;
        for (int i = 0; i < nums.length; i++) {
            leftProduct[i] = startProduct;
            startProduct = startProduct * nums[i];
        }

        // here we store the product of all the numbers
        // to the right of the current index we are standing at
        startProduct = 1;
        for (int i = nums.length - 1; i > -1; i--) {
            rightProduct[i] = startProduct;
            startProduct = startProduct * nums[i];
        }

        // product except self = product of all elements to the left * product of all elements to the right
        for (int i = 0; i < nums.length; i++) {
            productArray[i] = leftProduct[i] * rightProduct[i];
        }

        return productArray;
    }

    public static void main(String[] args) {
        int[] testNums = { 1, 2, 3, 4 };

        int[] result = productExceptSelf(testNums);
        System.out.println(Arrays.toString(result));
    }
}
