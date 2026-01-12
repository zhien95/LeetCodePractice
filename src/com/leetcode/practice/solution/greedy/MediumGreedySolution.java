package com.leetcode.practice.solution.greedy;

import java.util.Arrays;

public class MediumGreedySolution {
    /**
     * [Greedy Florist]
     *
     * Calculates the minimum cost to purchase all flowers when the price of each flower
     * increases based on how many flowers the same person has already bought.
     * Implements a greedy approach by sorting flowers in descending order and assigning
     * purchases to minimize cost.
     *
     * @param k Number of people buying flowers
     * @param c Array of initial prices for each flower
     * @return Minimum total cost to purchase all flowers
     */
    //https://www.hackerrank.com/challenges/greedy-florist/problem?isFullScreen=true
    public static long getMinimumCost(int k, int[] c) {
        //sort cost in ascending order
        Arrays.sort(c);

        long total = 0;
        int n = c.length;

        //for each flower find the multiplier for each person (i+1)
        for (int i = 0; i < n; i++) {
            int multiplier = (i / k) + 1;
            total += (long) c[n - 1 - i] * multiplier;
        }
        return total;
    }


    /**
     * [Sell Diminishing-Valued Colored Balls]
     */
//https://leetcode.com/problems/sell-diminishing-valued-colored-balls/description/
    public int maxProfit(int[] inventory, int orders) {
        final int MOD = 1_000_000_007;
        Arrays.sort(inventory);
        int n = inventory.length;
        long res = 0;
        int k = 1;

        for (int i = n - 1; i >= 0 && orders > 0; --i, ++k) {
            int cur = inventory[i];
            int next = (i > 0 ? inventory[i - 1] : 0);

            if (cur > next) {
                long diff = cur - next;
                long totalBalls = diff * k;
                if (orders >= totalBalls) {
                    long sum = (long) (cur + next + 1) * diff / 2;
                    res = (res + sum * k) % MOD;
                    orders -= totalBalls;
                } else {
                    long full = orders / k;
                    long rem = orders % k;
                    long low = cur - full;
                    long sum = (cur + low + 1) * full / 2;
                    res = (res + sum * k) % MOD;
                    res = (res + rem * low) % MOD;
                    orders = 0;
                }
            }
        }

        return (int) (res % MOD);
    }


}
