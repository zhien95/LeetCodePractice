package com.leetcode.practice.solution.backtracking;

/**
 * [N-Queens II]
 * <p>
 * Counts the number of distinct solutions to the n-queens puzzle.
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard
 * such that no two queens attack each other.
 *
 * @param n Size of the chessboard and number of queens
 * @return Number of distinct solutions
 */
//https://leetcode.com/problems/n-queens-ii/
public class HardBackTrackingSolution {
    public int totalNQueens(int n) {
        return countBacktrack(0, n, new boolean[n], new boolean[2 * n], new boolean[2 * n]);
    }

    private int countBacktrack(int row, int n, boolean[] cols, boolean[] diag1, boolean[] diag2) {
        if (row == n) return 1;

        int count = 0;
        for (int col = 0; col < n; col++) {
            int d1 = row - col + n; // main diagonal
            int d2 = row + col;     // anti-diagonal

            if (cols[col] || diag1[d1] || diag2[d2]) continue;

            cols[col] = diag1[d1] = diag2[d2] = true;
            count += countBacktrack(row + 1, n, cols, diag1, diag2);
            cols[col] = diag1[d1] = diag2[d2] = false; // backtrack
        }
        return count;
    }
}
