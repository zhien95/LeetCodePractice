package com.leetcode.practice.solution.binarySearch;

public class EasyBinarySearchSolution {
    /**
     * [Search Insert Position]
     */
//https://leetcode.com/problems/search-insert-position/?envType=study-plan-v2&envId=top-interview-150
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            //target found return index
            if (nums[mid] == target) {
                return mid;
            }

            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        //target not found -> return next closest index left
        return left;
    }
}
