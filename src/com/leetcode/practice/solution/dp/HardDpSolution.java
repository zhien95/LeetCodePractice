package com.leetcode.practice.solution.dp;

import java.util.Arrays;

public class HardDpSolution {
    /**
     * [Best Time to Buy and Sell Stock IV]
     * <p>
     * Finds the maximum profit that can be achieved with at most k transactions.
     * Uses dynamic programming with buy and sell arrays to track optimal states.
     *
     * @param k      Maximum number of transactions allowed
     * @param prices Array of stock prices on each day
     * @return Maximum profit achievable
     */
//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/description/?envType=study-plan-v2&envId=top-interview-150
    public int maxProfit(int k, int[] prices) {
        int[] buy = new int[k + 1];
        int[] sell = new int[k + 1];
        Arrays.fill(buy, Integer.MIN_VALUE);

        for (int price : prices) {
            for (int t = 1; t <= k; t++) {
                buy[t] = Math.max(buy[t], sell[t - 1] - price);
                sell[t] = Math.max(sell[t], buy[t] + price);
            }
        }
        return sell[k];
    }


    /***
     * Calculates the minimum sum of a falling path through a grid with the constraint that adjacent rows cannot select the same column.
     *
     * @param grid A 2D grid array where grid[i][j] represents the value at position (i,j)
     * @return Returns the minimum falling path sum from top to bottom
     */
    //https://leetcode.com/problems/minimum-falling-path-sum-ii/
    public int minFallingPathSum(int[][] grid) {
        // Minimum and Second Minimum Column Index
        int nextMin1C = -1;
        int nextMin2C = -1;

        // Minimum and Second Minimum Value
        int nextMin1 = Integer.MAX_VALUE;
        int nextMin2 = Integer.MAX_VALUE;

        // Find the minimum and second minimum from the last row
        for (int col = 0; col < grid.length; col++) {
            if (grid[grid.length - 1][col] <= nextMin1) {
                nextMin2 = nextMin1;
                nextMin2C = nextMin1C;
                nextMin1 = grid[grid.length - 1][col];
                nextMin1C = col;
            } else if (grid[grid.length - 1][col] <= nextMin2) {
                nextMin2 = grid[grid.length - 1][col];
                nextMin2C = col;
            }
        }

        // Fill the recursive cases
        for (int row = grid.length - 2; row >= 0; row--) {
            // Minimum and Second Minimum Column Index of the current row
            int min1C = -1;
            int min2C = -1;

            // Minimum and Second Minimum Value of current row
            int min1 = Integer.MAX_VALUE;
            int min2 = Integer.MAX_VALUE;

            for (int col = 0; col < grid.length; col++) {
                // Select minimum from valid cells of the next row
                int value;
                if (col != nextMin1C) {
                    value = grid[row][col] + nextMin1;
                } else {
                    value = grid[row][col] + nextMin2;
                }

                // Save minimum and second minimum
                if (value <= min1) {
                    min2 = min1;
                    min2C = min1C;
                    min1 = value;
                    min1C = col;
                } else if (value <= min2) {
                    min2 = value;
                    min2C = col;
                }
            }

            // Change of row. Update nextMin1C, nextMin2C, nextMin1, nextMin2
            nextMin1C = min1C;
            nextMin2C = min2C;
            nextMin1 = min1;
            nextMin2 = min2;
        }

        // Return the minimum from the first row
        return nextMin1;
    }


}