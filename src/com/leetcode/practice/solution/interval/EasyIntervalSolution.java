package com.leetcode.practice.solution.interval;

import java.util.ArrayList;
import java.util.List;

/**
 * [Summary Ranges]
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
