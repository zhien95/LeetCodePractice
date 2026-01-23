package com.leetcode.practice.solution.dp;

import java.util.HashMap;
import java.util.Map;

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
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;

        // dp[col] = min falling path sum ending at column col of the previous row
        int[] dp = new int[n];

        // Initialize with the first row
        for (int col = 0; col < n; col++) {
            dp[col] = matrix[0][col];
        }

        // Build DP row by row
        for (int row = 1; row < n; row++) {
            int[] next = new int[n];

            for (int col = 0; col < n; col++) {
                int minAbove = dp[col]; // directly above

                if (col > 0) {
                    minAbove = Math.min(minAbove, dp[col - 1]); // diagonal left
                }

                if (col < n - 1) {
                    minAbove = Math.min(minAbove, dp[col + 1]); // diagonal right
                }

                next[col] = matrix[row][col] + minAbove;
            }

            dp = next; // move to next row
        }

        // Answer is the minimum value in the last DP row
        int result = Integer.MAX_VALUE;
        for (int val : dp) {
            result = Math.min(result, val);
        }

        return result;
    }

    //[1,2,3] target = 3


    //https://leetcode.com/problems/target-sum/
    public int findTargetSumWays(int[] nums, int target) {
        Map<String, Integer> memo = new HashMap<>();
        return dfs(nums, target, 0, memo);
    }

    private int dfs(int[] nums, int target, int pos, Map<String, Integer> memo) {
        if (pos == nums.length) {
            if (target == 0) {
                return 1;
            } else {
                return 0;
            }
        }

        String key = pos + "," + target;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int res = 0;
        res += dfs(nums, target + nums[pos], pos + 1, memo);
        res += dfs(nums, target - nums[pos], pos + 1, memo);

        memo.put(key, res);


        return res;
    }


}
