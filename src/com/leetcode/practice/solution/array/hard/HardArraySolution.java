package com.leetcode.practice.solution.array.hard;

import java.util.Arrays;

public class HardArraySolution {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];
        Arrays.fill(candies, 1);  // Initialize all with 1 candy

        // Left to right
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }

        // Right to left
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }

        int sum = 0;
        for (int c : candies) {
            sum += c;
        }

        return sum;
    }

    public int trap(int[] height) {
        if (height == null || height.length == 0) return 0;

        int l = 0;
        int r = height.length - 1;

        int lMax = 0;
        int rMax = 0;

        int res = 0;

        while (l < r) {
            lMax = Math.max(lMax, height[l]);
            rMax = Math.max(rMax, height[r]);

            if (height[l] < height[r]) {
                res += lMax - height[l];
                l++;
            } else {
                res += rMax - height[r];
                r--;
            }
        }

        return res;
    }

    //https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/description/?envType=study-plan-v2&envId=top-interview-150
    public int maxProfit(int k, int[] prices) {
        int[] buy = new int[k + 1];
        int[] sell = new int[k + 1];
        Arrays.fill(buy, Integer.MIN_VALUE);

        for (int price : prices) {
            for (int t = 1; t <= k; t++) {
                buy[t] = Math.max(buy[t], sell[t - 1] - price);
                sell[t] = Math.max(sell[t], buy[t] + price);
            }
        }
        return sell[k];
    }
}
