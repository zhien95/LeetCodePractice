package com.leetcode.practice.solution.greedy;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class MediumGreedySolution {
    /**
     * [Sell Diminishing-Valued Colored Balls]
     */
    //https://leetcode.com/problems/sell-diminishing-valued-colored-balls/description/
    private static final int MOD = 1_000_000_007;

    /**
     * [Greedy Florist]
     * <p>
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

    public int maxProfit(int[] inventory, int orders) {
        // Max heap to always sell the highest value ball
        PriorityQueue<Integer> maxHeap =
                new PriorityQueue<>(Collections.reverseOrder());

        // Add all inventory counts to heap
        for (int val : inventory) {
            maxHeap.offer(val);
        }

        long profit = 0;

        // Sell balls one by one
        while (orders > 0 && !maxHeap.isEmpty()) {
            int top = maxHeap.poll();   // highest valued ball
            profit = (profit + top) % MOD;

            // Decrease the value and reinsert if still positive
            if (top - 1 > 0) {
                maxHeap.offer(top - 1);
            }

            orders--;
        }

        return (int) profit;
    }

    //https://leetcode.com/problems/flip-string-to-monotone-increasing/
    public int minFlipsMonoIncr(String s) {
        int totalZeroCount = 0;

        for (char c : s.toCharArray()) {
            totalZeroCount += c == '0' ? 1 : 0;
        }

        int min = Integer.MAX_VALUE;
        int ones = 0;
        int zeroes = 0;

        for (char c : s.toCharArray()) {
            int currZero = c == '0' ? 1 : 0;
            int ops = ones + totalZeroCount - currZero - zeroes;
            min = Math.min(min, ops);

            if (currZero == 1) {
                zeroes++;
            } else {
                ones++;
            }
        }

        return min;
    }


}
