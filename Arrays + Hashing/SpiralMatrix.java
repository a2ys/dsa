// Given an m x n matrix, return all
// elements of the matrix in spiral order.

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {

    public static List<Integer> spiralOrder(int[][] matrix) {
        // sometimes, you don't need explaining.
        // do what you're told to you.
        // this is one of those questions.
        // we just travel, make your own rules.
        // fuck around and find out yourself, this one is
        // just my solution archive, no explanation how
        // this happened.

        int top = 0;
        int left = 0;
        int right = matrix[0].length - 1;
        int bottom = matrix.length - 1;

        List<Integer> spiral = new ArrayList<>();

        // condition: never go out of bounds
        while (top <= bottom && left <= right) {
            // lets move left to right.
            for (int i = left; i <= right; i++) {
                spiral.add(matrix[top][i]);
            }
            top++;

            // reached the right end?
            // lets take a downward turn.
            for (int i = top; i <= bottom; i++) {
                spiral.add(matrix[i][right]);
            }
            right--;

            // lets move right to left.
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    spiral.add(matrix[bottom][i]);
                }
                bottom--;
            }

            // now move bottom to top.
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    spiral.add(matrix[i][left]);
                }
                left++;
            }

            // the while loop helps here be in bounds.
            // btw we make sure that we are always choosing
            // unexplored elements because we are storing the
            // top, bottom, left and right "to explore" indices
            // and keep changing them as we explore the matrix in
            // the way we are told to do.
            // its not that serious buddy, do it yourself.
        }

        return spiral;
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
        System.out.println(spiralOrder(matrix));
    }
}
