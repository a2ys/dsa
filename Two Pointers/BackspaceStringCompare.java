// Given two strings s and t, return true if
// they are equal when both are typed into empty
// text editors. '#' means a backspace character.

public class BackspaceStringCompare {

    public static boolean backspaceCompare(String s, String t) {
        int i = s.length() - 1,
            j = t.length() - 1;

        while (i >= 0 && j >= 0) {
            int numberOfBackspaces = 0;
            while (s.charAt(i) == '#') {
                numberOfBackspaces++;
                i--;
            }
            i -= numberOfBackspaces;

            numberOfBackspaces = 0;
            while (t.charAt(j) == '#') {
                numberOfBackspaces++;
                j--;
            }
            j -= numberOfBackspaces;

            System.out.println(i + " " + j);

            if ((i == -1 && j >= 0) || (j == -1 && i >= 0)) return false;
            System.out.println("I passed first check");
            if (s.charAt(i) != t.charAt(j)) return false;
            System.out.println("I passed second check");
            i--;
            j--;
        }

        return true;
    }

    public static void main(String[] args) {
        int testcase = 1;
        System.out.println("Test case number: " + testcase++);
        System.out.println(backspaceCompare("ab#c", "ad#c"));
        System.out.println("Test case number: " + testcase++);
        System.out.println(backspaceCompare("ab##", "c#d#"));
        System.out.println("Test case number: " + testcase++);
        System.out.println(backspaceCompare("a#c", "b"));
    }
}
