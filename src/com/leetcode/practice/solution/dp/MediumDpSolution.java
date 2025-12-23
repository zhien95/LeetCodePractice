package com.leetcode.practice.solution.dp;

//https://leetcode.com/problems/house-robber/
public class MediumDpSolution {
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
}
