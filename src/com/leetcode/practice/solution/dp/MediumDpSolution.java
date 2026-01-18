package com.leetcode.practice.solution.dp;

/**
 * [House Robber]
 */
//https://leetcode.com/problems/house-robber/
public class MediumDpSolution {
    /**
     * [House Robber]
     * <p>
     * Solves the House Robber problem where a robber plans to rob houses along a street.
     * Each house has a certain amount of money, but adjacent houses cannot be robbed.
     * Returns the maximum amount that can be robbed without alerting the police.
     *
     * @param nums Array representing the amount of money in each house
     * @return The maximum amount that can be robbed
     */
    public int rob(int[] nums) {
        //At each house i, there are 2 choices
        //1: to rob, skip dp[i-1] so total = dp[i-2] + nums[i]
        //2: to skip, so total = dp[i-1]
        int n =  nums.length;
        if(n == 0)
            return 0;
        if(n == 1)
            return nums[0];

        int prev2 = 0; //dp[i-2]
        int prev = nums[0]; //dp[i-1]

        for (int i =1; i <n ; i++) {
            int rob = prev2 + nums[i];
            int skip = prev;
            int current = Math.max(rob, skip);
            prev2 = prev;
            prev = current;
        }

        return prev;
    }

    //https://leetcode.com/problems/minimum-falling-path-sum/
    // ... existing code ...
    //https://leetcode.com/problems/minimum-falling-path-sum/
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int[][] dp = new int[n][n];

        for (int col = 0; col < n; col++){
            dp[0][col] = matrix[0][col];
        }

        for (int row = 1; row < n; row++){
            for (int col = 0; col < n; col++){
                // Start with directly above (center)
                int bestAbove = dp[row-1][col];

                // Check diagonal left
                if (col > 0){
                    bestAbove = Math.min(bestAbove, dp[row-1][col-1]);
                }

                // Check diagonal right
                if (col < n - 1){
                    bestAbove = Math.min(bestAbove, dp[row-1][col + 1]);
                }

                dp[row][col] = matrix[row][col] + bestAbove;
            }
        }

        int minPath = Integer.MAX_VALUE;

        //last row stores final minPath based on final position
        for (int col = 0; col < n; col++){
            minPath = Math.min(minPath, dp[n-1][col]);
        }

        return minPath;
    }

}
