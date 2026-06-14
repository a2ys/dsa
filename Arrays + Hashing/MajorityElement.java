// Given an array nums of size n, return the majority element.

public class MajorityElement {

    // very interesing approach Boyer-Moore algorithm
    // this solution is built on top of a big assumption
    // that holds true only for the current question:
    // there will always be a majority element
    //
    // if in a case where we are not sure that there will definitely
    // be a majority element, we can make a second function which just
    // counts the majority element we got from the function
    // and check if the amount of times it appeared is more than the
    // floor value of the number of elements in the nums array halved
    //
    // this could have been easily solved with sorting and checking
    // what the middle element is very easily, but that would have
    // given us O(n log n) time due to sorting
    // so we are doing this mathematics magic for the love of the game :)
    public static int majorityElement(int[] nums) {
        // 1. we are assuming the majority element is the first one
        // count will not store the actual count, but the count of
        // the majority element we are getting after we have cancelled
        // out all the elements that are distinct to the majority element.
        int majElem = nums[0],
            count = 0;

        for (int i = 0; i < nums.length; i++) {
            // 5. first read comment 2 before reading this one
            // when the count becomes zero after cancelling out the assumed
            // majority element with another element, we assume the new majority
            // element is the one we are standing at.
            // since we were left with [2] after the example in the comment 3,
            // we assume that [2] is the majorith and assign it to the maj element.
            // later count will be updated in the section covered by comment 4
            // and we will get to the correct result.
            if (count == 0) {
                majElem = nums[i];
            }

            // 2. basically we are counting the element which we assumed is majority
            // read comment 3 first.
            if (nums[i] == majElem) {
                // 4. if we are on a majority element, we just increase its count
                // this would help us make sure that the count of the element that
                // we assumed is the majority is recorded and we get an actual
                // count of the amount of times it has appeared where it was not cancelled out
                // or overwritten by an element which is not same as it.
                count++;
            } else {
                // 3. if we are not on a majority element but encountered two distinct
                // elements, we basically cancel it out, or decrease the amount of "unique counts"
                // this basically means: if we have [2, 1, 2]
                // we will be cancelling the 2 and the 1 since they are distinct
                // and then we will just be left with [2] with the value inside count being 0.
                // this special case of count = 0 will be handled in comment 5.
                count--;
            }
        }

        return majElem;
    }

    public static void main(String[] args) {
        int[] nums = { 2, 2, 1, 1, 1, 2, 2 };
        System.out.println(majorityElement(nums));
    }
}
