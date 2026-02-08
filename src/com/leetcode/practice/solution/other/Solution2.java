package com.leetcode.practice.solution.other;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Solution2 {
    public static ExecutorService executor = new ThreadPoolExecutor(1, 2, 3, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(5000), new ThreadPoolExecutor.CallerRunsPolicy());


    /**
     * using sliding window
     *
     */

    //space complexity O(1)
    //time complexity O(N)
    public static int minRange(int[] nums, int target) {

        int start = 0;
        int sum = 0;
        int len = Integer.MAX_VALUE;

        for (int end = 0; end < nums.length; end++) {
            sum += nums[end];

            while (sum >= target) {
                len = Math.min(len, end - start + 1);
                sum -= nums[start];
                start++;
            }

        }

        return len == Integer.MAX_VALUE ? 0 : len;
    }

    public static void async() {
        executor.submit(() -> minRange(new int[]{1, 23}, 5));
    }


    public static void main(String[] args) {
        System.out.println(minRange(new int[]{2, 3, 1, 2, 4, 3}, 7));
        System.out.println(minRange(new int[]{1, 4, 4}, 4));
        System.out.println(minRange(new int[]{1, 1, 1, 1, 1, 1, 1, 1}, 11));
    }

}
