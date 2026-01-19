package com.leetcode.practice.solution.matrix;

import java.util.Arrays;
import java.util.List;

public class MatrixManipulator {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 10},
                {4, 5, 6, 11},
                {7, 8, 9, 12}
        };

        List<String> commands = Arrays.asList(
                "rotate90Clockwise"
        );

        matrix = processCommands(matrix, commands);
        printMatrix(matrix);
    }

    public static int[][] processCommands(int[][] matrix, List<String> commands) {
        for (String command : commands) {
            String[] parts = command.split(" ");
            if (parts[0].equals("swapRow")) {
                swapRow(matrix, Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
            } else if (parts[0].equals("swapCol")) {
                swapCol(matrix, Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
            } else if (parts[0].equals("reverseRow")) {
                reverseRow(matrix, Integer.parseInt(parts[1]));
            } else if (parts[0].equals("reverseCol")) {
                reverseCol(matrix, Integer.parseInt(parts[1]));
            } else if (parts[0].equals("rotate90Clockwise")) {
                matrix = rotate90Clockwise(matrix);
            }
        }
        return matrix;
    }

    private static void swapRow(int[][] matrix, int r1, int r2) {
        int[] temp = matrix[r1];
        matrix[r1] = matrix[r2];
        matrix[r2] = temp;
    }

    private static void swapCol(int[][] matrix, int c1, int c2) {
        for (int i = 0; i < matrix.length; i++) {
            int temp = matrix[i][c1];
            matrix[i][c1] = matrix[i][c2];
            matrix[i][c2] = temp;
        }
    }

    private static void reverseRow(int[][] matrix, int r) {
        int left = 0, right = matrix[0].length - 1;
        while (left < right) {
            int temp = matrix[r][left];
            matrix[r][left] = matrix[r][right];
            matrix[r][right] = temp;
            left++;
            right--;
        }
    }

    private static void reverseCol(int[][] matrix, int c) {
        int top = 0, bottom = matrix.length - 1;
        while (top < bottom) {
            int temp = matrix[top][c];
            matrix[top][c] = matrix[bottom][c];
            matrix[bottom][c] = temp;
            top++;
            bottom--;
        }
    }

    private static int[][] rotate90Clockwise(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] rotated = new int[m][n]; // swapped dimensions for rotation

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                rotated[j][n - 1 - i] = matrix[i][j];
            }
        }
        return rotated;
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}
