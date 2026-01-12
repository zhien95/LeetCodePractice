package com.leetcode.practice.solution.interval;

import java.util.ArrayList;
import java.util.List;

/**
 * [Summary Ranges]
 *
 * Given a sorted unique integer array nums, returns the smallest sorted list of ranges that cover all the numbers in the array.
 * Each range [a,b] is represented as "a->b" if a != b, or "a" if a == b.
 *
 * @param nums Sorted array of unique integers
 * @return List of string representations of ranges
 */
//https://leetcode.com/problems/summary-ranges/?envType=study-plan-v2&envId=top-interview-150
public class EasyIntervalSolution {
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        int n = nums.length;
        int start = 0;

        while (start < n) {
            int end = start;
            // Expand end while numbers are consecutive
            while (end + 1 < n && nums[end + 1] == nums[end] + 1) {
                end++;
            }

            if (start == end) {
                res.add(String.valueOf(nums[start]));
            } else {
                res.add(nums[start] + "->" + nums[end]);
            }

            start = end + 1;
        }

        return res;
    }
}
