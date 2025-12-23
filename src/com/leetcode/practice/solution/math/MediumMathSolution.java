package com.leetcode.practice.solution.math;

public class MediumMathSolution {
    //https://leetcode.com/problems/factorial-trailing-zeroes/?envType=study-plan-v2&envId=top-interview-150
    public int trailingZeroes(int n) {
        //n factorial: n * n-1 ... 5 * 4 * 3 * 2 * 1
        //calculating N! will lead to overflow -> so find a pattern
        //the tail gains a 0 when there's a 5 * 2 and there's plenty of 2 in n factorial
        int count = 0;

        while (n > 0) {
            n /= 5;
            count += n;
        }

        return count;
    }
}
