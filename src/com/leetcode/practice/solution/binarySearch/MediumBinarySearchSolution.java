package com.leetcode.practice.solution.binarySearch;

import java.util.ArrayList;
import java.util.List;

public class MediumBinarySearchSolution {
    /**
     * [Find First and Last Position of Element in Sorted Array]
     * <p>
     * Finds the starting and ending position of a given target value in a sorted array.
     * If target is not found, returns [-1, -1]. Algorithm runs in O(log n) time complexity.
     *
     * @param nums   Sorted array of integers
     * @param target The value to search for
     * @return Array containing the first and last positions of the target value
     */
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
            int mid = left + (right - left) /2;
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

    /**
     * [Search in Rotated Sorted Array]
     *
     * Searches for a target value in a sorted array that has been possibly rotated at some pivot point.
     * Algorithm maintains O(log n) time complexity using modified binary search.
     *
     * @param nums Rotated sorted array of integers
     * @param target The value to search for
     * @return Index of the target value if found, otherwise -1
 */
//https://leetcode.com/problems/search-in-rotated-sorted-array/description/?envType=study-plan-v2&envId=top-interview-150
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Found target
            if (nums[mid] == target) {
                return mid;
            }

            // Left half is sorted
            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    // Target is in the left sorted half
                    right = mid - 1;
                } else {
                    // Target is in the right unsorted half
                    left = mid + 1;
                }
            }
            // Right half is sorted
            else {
                if (nums[mid] < target && target <= nums[right]) {
                    // Target is in the right sorted half
                    left = mid + 1;
                } else {
                    // Target is in the left unsorted half
                    right = mid - 1;
                }
            }
        }

        // Target not found
        return -1;
    }

    /**
     * [Search a 2D Matrix]
     * <p>
     * Searches for a target value in an m x n matrix where each row is sorted in ascending order from left to right,
     * and each column is sorted in ascending order from top to bottom.
     * Algorithm treats the 2D matrix as a 1D sorted array and performs binary search.
     *
     * @param matrix The 2D matrix to search in
     * @param target The value to search for
     * @return true if the target is found in the matrix, otherwise false
     */
//https://leetcode.com/problems/search-a-2d-matrix/?envType=study-plan-v2&envId=top-interview-150
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int m = matrix.length;      // number of rows
        int n = matrix[0].length;   // number of columns

        int left = 0;
        int right = m * n - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Convert 1D index back to 2D coordinates
            int row = mid / n;
            int col = mid % n;
            int midValue = matrix[row][col];

            if (midValue == target) {
                return true;
            } else if (midValue < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }
/**
 * [Find Peak Element]
 */
//https://leetcode.com/problems/find-peak-element/description/?envType=study-plan-v2&envId=top-interview-150
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        // Binary search for peak
        while (left < right) {
            int mid = left + (right - left) / 2;

            // If mid is greater than next element, peak is to the left (including mid)
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                // Otherwise, peak is to the right of mid
                left = mid + 1;
            }
        }

        // left == right is the peak index
        return left;
    }

    /**
     * [Find Minimum in Rotated Sorted Array]
     */
//https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/description/?envType=study-plan-v2&envId=top-interview-150
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        // If the array is not rotated (sorted normally)
        if (nums[left] <= nums[right]) {
            return nums[left];
        }

        // Binary search for the rotation point (minimum element)
        while (left < right) {
            int mid = left + (right - left) / 2;

            // If mid element is greater than right,
            // then the min must be to the right of mid
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                // Otherwise, the min is at mid or to the left of mid
                right = mid;
            }
        }

        // left == right is the index of the minimum element
        return nums[left];
    }
}
