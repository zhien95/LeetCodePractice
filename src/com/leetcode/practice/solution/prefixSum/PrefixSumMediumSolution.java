package com.leetcode.practice.solution.prefixSum;

import java.util.Arrays;

public class PrefixSumMediumSolution {
    //https://leetcode.com/problems/continuous-subarray-sum/description/?envType=problem-list-v2&envId=prefix-sum
    public static boolean checkSubarraySum(int[] nums, int k) {
        int[] output = new int[nums.length];
        int n = nums.length;

        for (int i = n - 2; i >= 1; i--) {
            for (int j = i; j >= 1; j--) {
                output[j] = output[j - 1] + nums[j - 1];
                System.out.println(Arrays.toString(output));
                if (output[i] % k == 0) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        checkSubarraySum(new int[]{23, 2, 4, 6, 7}, 6);
    }

}
