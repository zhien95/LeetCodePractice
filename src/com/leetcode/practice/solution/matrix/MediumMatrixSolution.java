package com.leetcode.practice.solution.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class MediumMatrixSolution {
    public boolean isValidSudoku(char[][] board) {
        // Create arrays of HashSet to track rows, columns, and 3x3 boxes
        HashSet<Character>[] rows = new HashSet[9];
        HashSet<Character>[] cols = new HashSet[9];
        HashSet<Character>[] boxes = new HashSet[9];

        // Initialize each set
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }

        // Iterate over the board
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char val = board[r][c];

                // Skip empty cells
                if (val == '.') continue;

                // Check row
                if (rows[r].contains(val)) {
                    return false;
                }
                rows[r].add(val);

                // Check column
                if (cols[c].contains(val)) {
                    return false;
                }
                cols[c].add(val);

                // Check 3x3 box
                int boxIndex = (r / 3) * 3 + (c / 3);
                if (boxes[boxIndex].contains(val)) {
                    return false;
                }
                boxes[boxIndex].add(val);
            }
        }

        // If no conflicts are found
        return true;
    }

    //https://leetcode.com/problems/spiral-matrix/submissions/1624463272/?envType=study-plan-v2&envId=top-interview-150
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int rowStart = 0;
        int rowEnd = matrix.length - 1;
        int colStart = 0;
        int colEnd = matrix[0].length - 1;

        while (rowStart <= rowEnd && colStart <= colEnd) {
            // right
            for (int j = colStart; j <= colEnd; j++) {
                res.add(matrix[rowStart][j]);
            }
            rowStart++;

            // down
            for (int j = rowStart; j <= rowEnd; j++) {
                res.add(matrix[j][colEnd]);
            }
            colEnd--;

            // left
            if (rowStart <= rowEnd) {
                for (int j = colEnd; j >= colStart; j--) {
                    res.add(matrix[rowEnd][j]);
                }
                rowEnd--;
            }

            // up
            if (colStart <= colEnd) {
                for (int j = rowEnd; j >= rowStart; j--) {
                    res.add(matrix[j][colStart]);
                }
                colStart++;
            }
        }

        return res;
    }

    //https://leetcode.com/problems/rotate-image/?envType=study-plan-v2&envId=top-interview-150
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        // Step 1: Transpose the matrix
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // swap matrix[i][j] and matrix[j][i]
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Step 2: Reverse each row
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                // swap matrix[i][j] and matrix[i][n - j - 1]
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = temp;
            }
        }
    }

    // https://leetcode.com/problems/set-matrix-zeroes/?envType=study-plan-v2&envId=top-interview-150
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        boolean firstRowZero = false;
        boolean firstColZero = false;

        // Step 1: Check if first row and column have any zeros
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                firstColZero = true;
                break;
            }
        }

        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                firstRowZero = true;
                break;
            }
        }

        // Step 2: Use first row and column to mark zeros
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // Step 3: Zero out cells based on markers
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Step 4: Zero out first row and column if needed
        if (firstRowZero) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }

        if (firstColZero) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}

