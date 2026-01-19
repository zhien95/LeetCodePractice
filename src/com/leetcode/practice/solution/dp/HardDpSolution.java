package com.leetcode.practice.solution.dp;

import java.util.Arrays;

public class HardDpSolution {
    /**
     * [Best Time to Buy and Sell Stock IV]
     * <p>
     * Finds the maximum profit that can be achieved with at most k transactions.
     * Uses dynamic programming with buy and sell arrays to track optimal states.
     *
     * @param k      Maximum number of transactions allowed
     * @param prices Array of stock prices on each day
     * @return Maximum profit achievable
     */
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
