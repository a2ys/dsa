// You are given a string s and an integer k.
// You can choose any character of the string
// and change it to any other uppercase English
// character. You can perform this operation at
// most k times.
// Return the length of the longest substring
// containing the same letter you can get after
// performing the above operations.

public class LongestRepeatingCharacterReplacement {

    public static int characterReplacement(String s, int k) {
        // required variables: left pointer, the longest length,
        // the character with the maximum frequency, a pseudo
        // map containing the counts of every character
        int left = 0,
            longestString = 0;
        int maxFreq = 0;
        int[] characterCounts = new int[26];

        for (int right = 0; right < s.length(); right++) {
            // we increase the count of the current character in
            // the pseudo map, subtracting from 'A' allows us to
            // get the index as A is 65 in integer, B is 66, etc.
            characterCounts[s.charAt(right) - 'A']++;
            // we update the maximum frequency we have out of
            // all the available characters.
            // for example, if A appears 4 times in the current window
            // while we are running, it will update it to 4. the number
            // 4 comes from the pseudo map. we updated it in last step.
            // this is done alongside updating the character count
            // just so that it is updated on the run and not take
            // extra time by using a function to get the maximum out of
            // all numbers in characterCounts
            maxFreq = Math.max(maxFreq, characterCounts[s.charAt(right) - 'A']);

            // this is an interesting condition, and in my optinion, the
            // only optimized solution. i learnt this from youtube lol.
            // right - left + 1 is the window size, call it W.
            // maxFreq is what we defined, the longest length of any
            // character in the current window.
            // what it basically means is that is the number of
            // replacable characters in the current window even
            // helping us in getting the longest substring after
            // replacement.
            // say we have 5 A's in the current window, and then
            // 1 B in the current window, so if the value of K, or
            // the total allowed replacements is 2, we are well under
            // the limit and thus we can change the B to an A.
            // now, window size is 6 and maxFreq is 5, and K is 2,
            // so 6 - 5 = 1 > 2 is false thus we are allowed to
            // use the current window.
            // now say we expand the window and now we have
            // 5 A's and 3 B's, the same thing becomes:
            // 8 - 5 = 3 > 2, it is true, so we start removing
            // characters from the left, and we change the entire
            // according to the current condition.
            // its better you try to do it yourself, you will learn
            // a lot!
            while (right - left + 1 - maxFreq > k) {
                characterCounts[s.charAt(left) - 'A']--;
                left++;
            }

            // we update the maximum window which is valid.
            longestString = Math.max(longestString, right - left + 1);
        }

        return longestString;
    }

    public static void main(String[] args) {
        System.out.println(characterReplacement("ABAB", 2));
        System.out.println(characterReplacement("AABABBA", 1));
    }
}
