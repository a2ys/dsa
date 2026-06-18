// Given an array nums with n objects colored red,
// white, or blue, sort them in-place so that objects
// of the same color are adjacent, with the colors
// in the order red, white, and blue.

import java.util.Arrays;

public class SortColors {

    // this one is the classic counting sort
    // this just means that count the number of
    // zeros, ones and twos, store them in buckets
    // where the zero index represent the number of
    // zeroes, first index represents the number of
    // ones and second index represents the number of
    // twos.
    // deprecated just means im using something else dw
    @Deprecated
    public static void sortColors(int[] nums) {
        // this is where i am storing the count of 0, 1 and 2
        // since i have been told that only the numbers 0, 1
        // and 2 are gonna be used, i initialized the array with
        // a length of 3
        int[] colors = new int[3];

        // if i encounter a zero, increase the value at index 0
        // which is the count of zeros, same for ones, twos, etc
        // nums[i] is the current number
        // so the expression basically means, if i see a 1,
        // go to the bucket or the count array and increase the count
        // of 1 by one.
        for (int i = 0; i < nums.length; i++) {
            colors[nums[i]]++;
        }

        // this is just to keep track of the current color
        // as we have no other method to do so right now.
        // we start with zero and check validity before filling.
        int currentColor = 0;

        for (int i = 0; i < nums.length; i++) {
            // if the number of entries of the current color we chose is
            // not in the array where we store the count of colors, then
            // it is not something we should fill. move to the next one.
            // currentColor < 2 is just to guard against going to a color
            // like 3.
            while (colors[currentColor] == 0 && currentColor < 2)
                currentColor++;

            // fill the current spot with the color we need
            nums[i] = currentColor;

            // reduce the count of the current color by 1
            // so that we only fill what's needed
            colors[currentColor]--;
        }

        System.out.println(Arrays.toString(nums));
    }

    // this is the dutch national flag algorithm.
    // invented by none other than Dijkstra
    // this is specifically applicable here cuz there's sorting
    // of basically just three entries.
    public static void sortColors(int[] nums, boolean dutchNationalFlag) {
        // here low, mid and high have specific importances
        // low represent the index where a zero has to
        // mid is where a one has to go
        // high is where a two has to go

        // initially we put both low and mid to zero
        // when we start rebuilding, we will update accordingly.
        // high is set to be at the end of everything so that
        // 2s go at the end of the array
        int low = 0,
            mid = 0,
            high = nums.length - 1;

        while (mid <= high) {
            // current number is stored in num for simplicity.
            int num = nums[mid];

            if (num == 0) {
                // if we encounter a zero, it needs to go to
                // its place which is at the low index.
                // so we swap mid with low.
                // after we have swapped, we do not need the
                // same low and mid index again, so we move forward.
                // you might think, why increase mid?
                // well thats because we have already checked there was
                // nothing other than 0 till now due to the other two
                // conditions in check as well, so we know that we came here
                // after putting everything in place, so we are anyway gonna
                // get a definite answer.
                int temp = nums[low];
                nums[low] = nums[mid];
                nums[mid] = temp;

                low++;
                mid++;
            } else if (num == 1) {
                // if we get a 1, it is supposed to be at the place of mid
                // just move forward
                mid++;
            } else {
                // if we get a 2, it is supposed to go where high is,
                // so we willl just swap mid with high and decrease high.
                // we did not decrease or increase mid, cuz we might have got
                // a zero or one from the place of high. be optimistic,
                // for mid, we have always gone from left to right, we do not
                // know anything that's in front of us, so just modify high
                // to check for what's to come.
                int temp = nums[mid];
                nums[mid] = nums[high];
                nums[high] = temp;

                high--;
            }
        }

        System.out.println(Arrays.toString(nums));
    }

    public static void main(String[] args) {
        // int[] nums = { 2, 0, 2, 1, 1, 0 };
        // int[] nums = { 2, 0, 1 };
        int[] nums = { 2, 0 };
        sortColors(nums, true);
    }
}
