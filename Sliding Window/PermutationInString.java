// Given two strings s1 and s2, return true
// if s2 contains a permutation of s1, or
// false otherwise.
// In other words, return true if one of s1's
// permutations is the substring of s2.

import java.util.Arrays;

public class PermutationInString {

    public static boolean checkInclusion(String s1, String s2) {
        if (s2.length() < s1.length()) return false;

        int[] s1Seq = new int[26];
        int[] s2Seq = new int[26];
        int left = 0;

        for (char c : s1.toCharArray()) {
            s1Seq[c - 'a']++;
        }

        // warm up
        int right = 0;
        while (right < s1.length()) {
            s2Seq[s2.charAt(right++) - 'a']++;
        }

        if (Arrays.equals(s1Seq, s2Seq)) return true;

        while (right < s2.length()) {
            s2Seq[s2.charAt(left) - 'a']--;
            left++;

            s2Seq[s2.charAt(right) - 'a']++;
            right++;

            if (Arrays.equals(s1Seq, s2Seq)) return true;
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(checkInclusion("ab", "eidbaooo"));
        System.out.println(checkInclusion("ab", "eidboaoo"));
    }
}
