// You are given an integer array height of length n.
// There are n vertical lines drawn such that the two
// endpoints of the ith line are (i, 0) and (i, height[i]).

public class ContainerWithMostWater {

    //  this is where the question is just really badly
    // worded. you just need to give the maximum area of
    // any rectangle that can be formed.
    public static int maxArea(int[] height) {
        // we start with the absolute minimum, the golden
        // rule. think of this as the negative infinity
        // but represented in an integer value.
        // although the question guarantees that we will
        // always encounter positive heights, for maximizing
        // problems, you can take this as the golden rule
        // here, area = 0 can also work, but just for learning
        // sake, take area = Integer.MIN_VALUE
        int area = Integer.MIN_VALUE;

        // the first pointer is at 0 and
        // last pointer is at length - 1
        int i = 0,
            j = height.length - 1;

        // once again, no overlap
        while (i < j) {
            // since we basically want the highest area of any rectangle
            // we need to pick the lowest height among these two. what
            // it basically means is if you pick a larger tower, water
            // will definitely overflow, but if you pick the smaller
            // tower, the smaller tower will hold the water and the larger
            // one does it anyway
            int lesserHeight = Math.min(height[i], height[j]);

            // maximize the area, we choose the larger area out of the current
            // one and the older one. whichever one is greater, we pick that
            // and overwrite the area variable.
            int candidateArea = lesserHeight * (j - i);
            area = Math.max(area, candidateArea);

            // the pointer with the smaller height tower is promoted, we
            // do this optimistically. we do not know if we will get a
            // larger area if we do so, but if the tower at position i
            // is smaller than the one at j, we increase i looking for
            // a better area, and vice versa.
            if (height[i] < height[j]) i++;
            else j--;
        }

        return area;
    }

    public static void main(String[] args) {
        // int[] area = { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
        int[] area = { 1, 1 };
        System.out.println(maxArea(area));
    }
}
