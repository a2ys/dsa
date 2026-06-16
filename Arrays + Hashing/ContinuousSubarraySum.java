// Given an integer array nums and an integer k,
// return true if nums has a good subarray or false otherwise.
// A good subarray is a subarray where:
// 1. its length is at least two, and
// 2. the sum of the elements of the subarray is a multiple of k.

// this question is one of the examples of building up
// if you have not solved subarray sum equals K, then i would like you
// to not attempt this question at all. the amount of similarity this
// question has with that one is insane, so please solve that one before
// attempting this question.
// i would be assuming you (and in fact, I myself) know a lot of info
// from that question once i attempt this one.

import java.util.HashMap;

public class ContinuousSubarraySum {

    public static boolean checkSubarraySum(int[] nums, int k) {
        // once again, following the pattern of the subarray sum equals k,
        // we create a hashmap and a prefix sum variable which stores the
        // cumulative sum of all the elements seen till now including the
        // current one.

        HashMap<Integer, Integer> sumIndexMap = new HashMap<>();
        int prefixSum = 0;

        // once again, we need to store the empty, or imaginary value here
        // that is, at index -1, when there is nothing in the prefix and nothing in the array,
        // or from the big bang of the problem, what was the sum in the first place
        // so before we even started, at an imaginary index of -1, the sum was 0
        // only after that, say on reaching index 0, we started to put our own
        // prefix sums.
        sumIndexMap.put(0, -1);

        // interestingly, we do not store the same { cumulative sum : count }
        // the idea of the hashmap and everything is the same this time,
        // but in the hashmap we are going to store the cumulative sum and
        // index of till where the cumulative sum has been.
        // S is any old sum in the hashmap and P is the cumulative sum
        // till now. since we know K and P already, we wanna find out the
        // value of S from one of the major relation they have given in the question:
        // the sum of the elements of the subarray is a multiple of K
        // that would give us a relation: (P - S) % K == 0
        // we cannot derive a deterministic value for S.
        // earlier we had arithmetic operators, but since we have a modulus operator
        // we need to utilize that to form something. now since the difference modulus K
        // is zero, that means the difference is a multiple of K, that means
        // (P - S) = nK, where n is any integer, it can be 1, 2, 3, and so on.
        // in our code, we cant just find everything. it will not be deterministic, so
        // instead, we can use algebra and modular arithmetic together in the following steps:
        // P = S + nK, when we add S on both sides. Now, when we take modulus K on both sides,
        // what we get is this: P % K = (S + nK) % K, now we know that nK % K is zero, so we
        // can remove it from the equation and what we are left with is P % K = S % K, this is
        // extremely useful and one of the easiest ways to attempt the question.
        // now this is something which will actually stumble upon your mind, that is, what do we
        // store in the hashmap now? from the powerful relation we have, instead of looking up for S,
        // which we do not know and cannot find a deterministic value of, we can store: S % K!!!
        // this value is the remainder of any old previous cumulative sum modulus K, this would mean
        // when we send a lookup into the hashmap, we do not lookup for any cumulative sum out of the blue,
        // we specifically send a lookup onto a cumulative sum whose modulus K was equal to the current
        // cumulative sum modulus K, which we know will definitely hold true and give us an answer
        // due to the relation we found, P % K = S % K, which is just a direct mathematical representation
        // of the statement we wrote.
        // now, adding to it, we know that we have to store the index till where
        // the cumulative sum was calculated. even though the cumulative sum has now changed
        // to cumulative sum modulus K, we still need the exact index due to the first condition
        // that is, the length of the subarray should be more than or equal to 2.
        // to enforce this, we will use the basic way to find the length. the length of a
        // subarray is just the difference of the index it ends minus the index it starts, no matter
        // what the indexing is, the difference never changes. the array is 0-indexed, but doesn't
        // really matter. the index it ends is basically the current index we are standing at
        // and the index it started was the value corresponding to the key (P % K) which was
        // proved to be equal to (S % K).
        // There is just one last issue we need to take care of, that is the fact that the problem
        // does not guarantee that there exists just one solution, there can be multiple,
        // we just need to show that there is at least one. this is a relief, but gives us another
        // problem that is: what index do I store? if a key already appeared before, that is
        // a remainder appears again, do I overwrite the previous value associated to that equation?
        // take the array [5, 0, 0] for example and the value of K to be 5, the remainders of the
        // prefixes come out to be 0, 0, 0. so how do we solve this new and hopefully the last
        // question? narrowing down, do we store the latest one or the earliest one?
        // to answer this, i had to look in the sky for about 5 minutes and it clicked. take a deep breath.
        // we will take the earliest one. i am unsure if you would understand my reasoning,
        // so form up your own for my answer, but here's mine. since we need length >= 2, if we store the
        // latest one, we would end up ignoring a correct answer that might have existed.
        // to be correct, we need an optimistic solution, that would say,
        // if there ever existed a subarray, let it sit at a distance of 2 or more than 2.
        // if there does not exist a subarray that does not satisfy the latter,
        // then it would have never qualified for an answer in the first place.
        // even though that would get stored in the array, but given our
        // condition of >= 2, it would not pass our program condition and
        // anyway our program will be correct
        // now, finally after solving everything, we can begin programming.

        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            int numberToFind = prefixSum % k; // P % K
            if (numberToFind < 0) {
                // java's modulus operator is not modulus, it is just a remainder operator
                // if the remainder is negative, we just make it positive by adding the divisor
                // (modular arithmetic rules lol)
                numberToFind += k;
            }

            // check if P % K, eventually S % K exists in the HashMap
            if (sumIndexMap.get(numberToFind) != null) {
                // if it exists, check if the length of the candidate subarray
                // is thus greater than or equal to two, if true, the function returns true
                if (i - sumIndexMap.get(numberToFind) >= 2) return true;
            } else {
                // in all other cases, that is not found, or found but didn't satisfy the length
                // condition, overwrite only if P % K did not exist from the beginning.
                sumIndexMap.put(numberToFind, i);
            }
        }

        // now, we can better explain why we put 0 : -1 in the first place.
        // say, we have [5, 0] as the array and K as 5, prefix sums are 5 and 5
        // and the remainders are 0 and 0. thus the entire array is the subarray
        // with the sum 5 and length >= 2, answer should be true
        // but if we do not store 0 : -1, at index 0, remainder is 0, so
        // not found, put 0 : 0 (sum : current index)
        // at index 1, remainder is 0, found, but length which we got is 1 - 0 = 1,
        // but does not satisfy, we return false, but answer should be true (?)
        // now if we store the imaginary index -1, magic happens. at index 0, remainder is 0,
        // found, but 0 - (-1) = 1, does not satisfy. at index 1, remainder is still 0,
        // found, 1 - (-1) = 2, which satisfies 2 >= 2, thus subarray found.
        // 0 : -1, the initial value, it appears everywhere and everywhere it means a different thing.
        // here it just means, before we processed any element, the prefix sum was 0.
        // it is just the empty prefix before index 0. without it, our algorithm will think
        // everything is starting at index 1. we need that index to participate in the math in the first place.

        // return false if there was never a subarray in the first case
        // to complete the function
        return false;
    }

    public static void main(String[] args) {
        // int[] nums = { 23, 2, 6, 4, 7 };
        int[] nums = { 5, 0, 0, 0 };
        boolean result = checkSubarraySum(nums, 3);
        System.out.println(result);
    }
}
