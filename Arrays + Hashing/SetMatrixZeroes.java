// Given an m x n integer matrix matrix, if an
// element is 0, set its entire row and column to 0's.
// You must do it in place.

// i cant explain this
// otherwise this would become a book.

public class SetMatrixZeroes {

    // debug, debug, debug
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void setZeroes(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        boolean isFirstRowZero = false;
        boolean isFirstColumnZero = false;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0; // row marker
                    matrix[0][j] = 0; // column marker

                    if (i == 0) isFirstRowZero = true;
                    if (j == 0) isFirstColumnZero = true;
                }
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (isFirstRowZero) {
            for (int j = 0; j < m; j++) {
                matrix[0][j] = 0;
            }
        }

        if (isFirstColumnZero) {
            for (int i = 0; i < n; i++) {
                matrix[i][0] = 0;
            }
        }

        printMatrix(matrix);
    }

    public static void main(String[] args) {
        // int[][] matrix = { { 1, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } };
        int[][] matrix = { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 0, 1 } };
        // int[][] matrix = { { 0, 1, 2, 0 }, { 3, 4, 5, 2 }, { 1, 3, 1, 5 } };
        setZeroes(matrix);
    }
}
