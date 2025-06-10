package com.leetcode.practice.solution.TwoPointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MediumTwoPointersSolution {
    //https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/?envType=study-plan-v2&envId=top-interview-150
    public int[] twoSum(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;

        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum > target) {
                right--;
            } else if (sum < target) {
                left++;
            } else {
                return new int[]{left + 1, right + 1}; // 1-based indices
            }
        }

        return new int[0]; // or throw an exception
    }

    //https://leetcode.com/problems/container-with-most-water/?envType=study-plan-v2&envId=top-interview-150
    public int maxArea(int[] height) {
        int n = height.length;
        int left  =0;
        int right = n-1;
        int maxArea = 0;

        while (left < right) {
            int width = right - left;
            int h = Math.min(height[left], height[right]);
            int area = width * h;
            maxArea = Math.max(area, maxArea);

            if (height[left] <= height[right]){
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

    //https://leetcode.com/problems/3sum/?envType=study-plan-v2&envId=top-interview-150
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            // Skip duplicate values for i
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // Skip duplicates for left and right
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;

                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return res;
    }
}
