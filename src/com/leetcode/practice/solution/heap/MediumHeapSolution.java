package com.leetcode.practice.solution.heap;

import com.leetcode.practice.solution.data.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class MediumHeapSolution {
    /**
     * [Kth Largest Element in an Array]
     */
//https://leetcode.com/problems/kth-largest-element-in-an-array/description/?envType=study-plan-v2&envId=top-interview-150
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int num : nums){
            minHeap.offer(num);
            if (minHeap.size() > k){
                minHeap.poll(); // remove smallest of the top k + 1 element
            }
        }

        return minHeap.peek();
    }

    /**
     * [Find K Pairs with Smallest Sums]
     */
//https://leetcode.com/problems/find-k-pairs-with-smallest-sums/description/?envType=study-plan-v2&envId=top-interview-150
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0 || k == 0) return result;

        // Min-heap storing int[]{i, j}, where i is index in nums1 and j in nums2
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
                (a, b) -> nums1[a[0]] + nums2[a[1]] - nums1[b[0]] - nums2[b[1]]
        );

        // Initialize heap with first element in nums2 combined with first k elements in nums1
        for (int i = 0; i < Math.min(k, nums1.length); i++) {
            minHeap.offer(new int[]{i, 0});  // nums1[i] + nums2[0]
        }

        // Extract the smallest k pairs
        while (k-- > 0 && !minHeap.isEmpty()) {
            int[] pair = minHeap.poll();
            int i = pair[0], j = pair[1];
            result.add(Arrays.asList(nums1[i], nums2[j]));

            // If possible, move to next element in nums2
            if (j + 1 < nums2.length) {
                minHeap.offer(new int[]{i, j + 1});
            }
        }

        return result;
    }
}
