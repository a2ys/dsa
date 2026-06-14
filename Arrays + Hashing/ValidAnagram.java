// Given two strings s and t
// return true if t is an anagram of s
// and false otherwise.

public class ValidAnagram {

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] s_characters = new int[26];
        int[] t_characters = new int[26];

        for (char ch : s.toCharArray()) {
            s_characters[(int) ch - (int) 'a']++;
        }

        for (char ch : t.toCharArray()) {
            t_characters[(int) ch - (int) 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (s_characters[i] != t_characters[i]) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(isAnagram("anagram", "nagaram"));
        System.out.println(isAnagram("rat", "car"));
    }
}
