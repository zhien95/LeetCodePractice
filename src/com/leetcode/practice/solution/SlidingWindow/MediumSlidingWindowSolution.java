package com.leetcode.practice.solution.SlidingWindow;

public class MediumSlidingWindowSolution {
    //https://leetcode.com/problems/minimum-size-subarray-sum/description/?envType=study-plan-v2&envId=top-interview-150
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int start = 0, sum = 0;
        int minLength = Integer.MAX_VALUE;

        for (int end = 0; end < n; end++) {
            sum += nums[end];

            while (sum >= target) {
                minLength = Math.min(minLength, end - start + 1);
                sum -= nums[start++];
            }
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}
