// You are given an n x n 2D matrix representing an
// image, rotate the image by 90 degrees (clockwise).
// You have to rotate the image in-place, which means
// you have to modify the input 2D matrix directly.
// DO NOT allocate another 2D matrix and do the rotation.

public class RotateImage {

    // for my debugging lol
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    // so something cool we have encountered.
    // when i first looked at the question and the examples,
    // i just looked at them and tried to find some patterns.
    // i tried to do whatever i could.
    // no direct matrix thing was applicable.
    // then i tried deriving a solution, like getting some formula,
    // i got this, M[i][j] <=> M[j][N - 1 - i].
    // buddy try to implement and i bet youll break your laptop.
    // this looks easy when you look at it once, but it is impossible.
    // sometimes, math formulas are not the time savers.
    // they do help in most of the questions, but this one swap
    // relation i got, this is four operations!!!
    // do not go this way please.
    // i looked inside my brain. i did what i knew, i thought
    // matrix transpose does it lol
    // it doesnt
    // but it does something which makes it easy af
    // i tried matrix transpose and the answer was not correct
    // cuz it does it the other way, i never knew.
    // but when i saw the correct answer, it was basically a
    // mirror image when you place a mirror vertically, but
    // put it in the center of the answer of transpose.
    // voila
    // so rotation of 90 degrees CW is just take the
    // matrix transpose and then just take a mirror image.
    // well, maths was not my friend in this question.

    public static void rotate(int[][] matrix) {
        int n = matrix.length;

        // look at how i started my j at i + 1
        // it is just to prevent myself from swapping
        // the same element twice cuz the loop will visit
        // the swapped element and will swap it again.
        // i + 1 means we will skip the diagonal, diagonals
        // dont need a swap in transpose.
        // so its just the upper right triangle that we need
        // to swap with the lower right triange.
        // basic matrix operation.

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        printMatrix(matrix); // debugging baby

        // now, when i take the reflection, i need to keep the
        // mirror in the center of the array vertically.
        // so my i again goes from 0 to n, all rows covered,
        // but j stops at n / 2 (exclusive of the center element)
        // this is to again make sure i do not swap the same
        // element twice, otherwise everything comes back and
        // we get the same matrix.
        // this gives us a mirror image of the matrix taken from
        // the center of the matrix.

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = temp;
            }
        }

        printMatrix(matrix); // for the love of the game
    }

    public static void main(String[] args) {
        // int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        int[][] matrix = {
            { 5, 1, 9, 11 },
            { 2, 4, 8, 10 },
            { 13, 3, 6, 7 },
            { 15, 14, 12, 16 },
        };
        rotate(matrix);
    }
}
