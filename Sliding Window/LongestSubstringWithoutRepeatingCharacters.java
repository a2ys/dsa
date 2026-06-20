// Given a string s, find the length of the
// longest substring without duplicate characters.

import java.util.HashSet;

public class LongestSubstringWithoutRepeatingCharacters {

    public static int lengthOfLongestSubstring(String s) {
        int longestLength = 0;
        HashSet<Character> characters = new HashSet<>(); // for O(1) operations
        // start represents the start of our window,
        // end is where we currently are at.
        // both of these will help us keep track of
        // locations and what to keep and remove
        int start = 0,
            end = 0;

        for (char c : s.toCharArray()) {
            // if a character is not in a set,
            // add it.
            if (!characters.contains(c)) {
                characters.add(c);
                end++;
            } else {
                // we keep removing stuff from the beginning
                // until we reach a window small enough that
                // contains unique characters (not even the
                // current one)
                while (characters.contains(c)) {
                    characters.remove(s.charAt(start));
                    start++;
                }
                // add the current character and expand the
                // window by one
                characters.add(c);
                end++;
            }

            // comparing the previous longest length and
            // the current one
            longestLength = Math.max(longestLength, end - start);
        }

        return longestLength;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("bbbbb"));
        System.out.println(lengthOfLongestSubstring("apwwkew"));
    }
}
