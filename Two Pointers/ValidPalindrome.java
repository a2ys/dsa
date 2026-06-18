// A phrase is a palindrome if, after converting all uppercase
// letters into lowercase letters and removing all non-alphanumeric
// characters, it reads the same forward and backward. Alphanumeric
// characters include letters and numbers. Given a string s,
// return true if it is a palindrome, or false otherwise.

public class ValidPalindrome {

    public static boolean isPalindrome(String s) {
        // we have two pointers, one at the beginning of the
        // string, one at the end
        int i = 0,
            j = s.length() - 1;

        while (i < j) {
            // we are supposed to ignore non-alphanumeric chars
            // so, if a character is not a letter or not a digit,
            // then we ignore it. the left pointer moves forward
            // and the right pointer moves backward.
            if (
                !Character.isLetter(s.charAt(i)) &&
                !Character.isDigit(s.charAt(i))
            ) {
                i++;
                continue;
            }
            if (
                !Character.isLetter(s.charAt(j)) &&
                !Character.isDigit(s.charAt(j))
            ) {
                j--;
                continue;
            }

            // here we are comparing lowercase because
            // we are supposed to do so lol
            // if they do not match, it is not a palindrome
            if (
                Character.toLowerCase(s.charAt(i)) !=
                Character.toLowerCase(s.charAt(j))
            ) return false;

            // move left pointer right, move right pointer left
            i++;
            j--;
        }

        // if it didn't return in the loop execution,
        // it was a palindrome! return true.
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(isPalindrome("race a car"));
        System.out.println(isPalindrome(" "));
        System.out.println(isPalindrome(".A"));
        System.out.println(isPalindrome("0P"));
        System.out.println(isPalindrome("0P0"));
    }
}
