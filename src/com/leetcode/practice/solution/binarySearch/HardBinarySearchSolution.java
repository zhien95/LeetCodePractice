package com.leetcode.practice.solution.binarySearch;

public class HardBinarySearchSolution {
    /**
     * [Median of Two Sorted Arrays]
     *
     * Finds the median of two sorted arrays of sizes m and n respectively.
     * The overall run time complexity should be O(log (m+n)).
     * Uses binary search approach to partition both arrays appropriately.
     *
     * @param nums1 First sorted array
     * @param nums2 Second sorted array
     * @return Median of the two sorted arrays
     */
//https://leetcode.com/problems/median-of-two-sorted-arrays/?envType=study-plan-v2&envId=top-interview-150
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        // Always binary search the smaller array
        if (m > n) return findMedianSortedArrays(nums2, nums1);

        int totalLeft = (m + n + 1) / 2;
        int left = 0, right = m;

        while (left <= right) {
            int partitionA = left + (right - left) / 2;
            int partitionB = totalLeft - partitionA;

            int aLeftMax = partitionA == 0 ? Integer.MIN_VALUE : nums1[partitionA - 1];
            int aRightMin = partitionA == m ? Integer.MAX_VALUE : nums1[partitionA];

            int bLeftMax = partitionB == 0 ? Integer.MIN_VALUE : nums2[partitionB - 1];
            int bRightMin = partitionB == n ? Integer.MAX_VALUE : nums2[partitionB];

            if (aLeftMax > bRightMin) {
                // too many elements from nums1
                right = partitionA - 1;
            } else if (bLeftMax > aRightMin) {
                // too few elements from nums1
                left = partitionA + 1;
            } else {
                // correct partition
                if ((m + n) % 2 == 1) {
                    return Math.max(aLeftMax, bLeftMax);
                }
                return (Math.max(aLeftMax, bLeftMax) +
                        Math.min(aRightMin, bRightMin)) / 2.0;
            }
        }
        throw new IllegalArgumentException();
    }
}
