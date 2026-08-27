import java.util.Arrays;

public class BoatsToSavePeople {
    public static int numRescueBoats(int[] people, int limit) {
        int result = 0;
        int left = 0, right = people.length - 1;

        Arrays.sort(people);

        while (left <= right) {
            int remaining = limit - people[right];
            right--;
            result++;

            if (left <= right && people[left] <= remaining)
                left++;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] people = { 3, 2, 2, 1 };
        int limit = 3;

        System.out.println(numRescueBoats(people, limit));
    }
}
