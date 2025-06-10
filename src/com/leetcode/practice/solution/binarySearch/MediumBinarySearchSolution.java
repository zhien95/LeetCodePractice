package com.leetcode.practice.solution.binarySearch;

import java.util.ArrayList;
import java.util.List;

public class MediumBinarySearchSolution {
    //https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/?envType=study-plan-v2&envId=top-interview-150
    public int[] searchRange(int[] nums, int target) {
        List<Integer> result = new ArrayList<>();
        int first = findFirst(nums, target);
        if (first == -1) return new int[]{-1,-1}; // Target not found

        // Scan forward to collect all equal elements
        for (int i = first; i < nums.length && nums[i] == target; i++) {
            result.add(i);
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    // Standard binary search to find first occurrence
    private int findFirst(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int result = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                result = mid;
                right = mid - 1; // keep going left
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    public int binarySearch(int[] nums, int target){
        int left = 0;
        int right = nums.length-1;

        while (left <= right){
            int mid = left + (left + right) /2;
            if (nums[mid] == target){
                return mid;
            }
            if (nums[mid] < target){
                //target is in right part, update left
                right = mid -1;
            } else {
                //target is in left part, update right
                left = mid +1;
            }
        }

        return -1;
    }
}
