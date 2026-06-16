// Given an array of integers nums and an integer k,
// return the total number of subarrays whose sum equals to k.
// interestingly, it is also contiguous subarrays.
// that takes the path of thinking to another level.

import java.util.HashMap;

public class SubarraySumEqualsK {

    public static int subarraySum(int[] nums, int k) {
        // This hashmap is used to store the point where we
        // are putting the sum upto a certain index
        // for example, if the sum we have in hand is thirty
        // and the number of subarrays that sum upto thrity is
        // three, then we will be basically storing the pair: {30 : 3}
        // this would signify that the number of subarrays that we know
        // summing upto thirty is three that we know of
        HashMap<Integer, Integer> indexSumMap = new HashMap<>();
        // we stored {0 : 1} because we know that the number of subarrays
        // that sum upto zero are 1. basically, there always exist one
        // subarray which sums upto zero.
        indexSumMap.put(0, 1);

        // these two counter variables are pretty important
        int count = 0; // the number of solutions we have encountered, this is the final answer btw
        int prefixSum = 0; // the sum of every element till now, cumulative sum

        for (int num : nums) {
            // we add the current number we are on into the cumulative sum
            prefixSum += num;
            // interesting maths fact.
            // first, our goal which is: is this subarray summing upto K
            // if there were some cumulative sum till now which had sum as 50
            // and the current sum that we have in our cumulative sum is 70
            // and the K that was given to us was 20, then logically
            // we know that the subarray which was between 50 and 70 must be summing
            // upto 20. we do not really care about the elements we encountered in between
            // or the amount of elements we encountered in between
            // we just care about the sum of the elements we encountered in between
            // that means, if a previous cumulative sum was lets say S, and the current
            // cumulative sum is P, and the k value is K
            // then, if P - S = K, this would mean that between the range of elements
            // summin upto S and the range of elements summing upto P, there was a
            // series of elements summing upto K.
            // Now since we do not know S, but we know P and K, we can use the relation
            // P - K = S, which would mean we should search for P - K, if there is a P - K
            // in our hashmap, that would mean that the subarray was there.
            // now, fetching from the hashmap, we would get the number of subarrays we encountered
            // which is what we need
            // so the getOrDefault() function would give us the number of subarrays summing upto P - K
            // or if it doesn't exist, it gives us zero.
            // this is added to count which is basically the number of subarrays summing upto P - K
            count += indexSumMap.getOrDefault(prefixSum - k, 0);

            // here, another interesting fact is, now since we have the sum uptill the current location
            // we store it in our hashmap, with the key being the cumulative sum prefixSum
            // and the value being the current value inside the hashmap if it exists other wise zero,
            // and we increase that with one, so that we have a real count to add to the count variable later.
            indexSumMap.put(
                prefixSum,
                indexSumMap.getOrDefault(prefixSum, 0) + 1
            );
        }

        // we return the count of subarrays
        return count;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3 };
        System.out.println("Number of subarrays: " + subarraySum(nums, 3));
    }
}
