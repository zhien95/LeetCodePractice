package com.leetcode.practice.solution.math;

public class EasyMathSolution {
    //https://leetcode.com/problems/palindrome-number/submissions/1658630340/?envType=study-plan-v2&envId=top-interview-150
    public boolean isPalindrome(int x) {
        // Negative numbers and those ending in 0 (but not 0 itself) are not palindromes
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;

        int reversed = 0;

        // Reverse half of the number
        while (x > reversed) {
            reversed = reversed * 10 + x % 10;
            x = x / 10;
        }

        // For even digits: x == reversed
        // For odd digits: x == reversed / 10 (middle digit doesn't matter)
        return x == reversed || x == reversed / 10;
    }

    public int[] plusOne(int[] digits) {
        int n = digits.length;

        // Start from the end
        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                // No carry needed, just increment and return
                digits[i]++;
                return digits;
            }

            // If it's 9, set to 0 and continue loop to carry over
            digits[i] = 0;
        }

        // If we get here, all digits were 9, e.g., 999 -> 1000
        int[] result = new int[n + 1];
        result[0] = 1; // The rest are already 0 by default
        return result;
    }

    //https://leetcode.com/problems/sqrtx/description/?envType=study-plan-v2&envId=top-interview-150
    public int mySqrt(int x) {
        if (x < 2) return x;

        int left = 1, right = x / 2;
        int ans = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            long sq = (long) mid * mid;

            if (sq == x) {
                return mid;
            } else if (sq < x) {
                ans = mid;        // mid might be the answer
                left = mid + 1;   // try to find a larger one
            } else {
                right = mid - 1;
            }
        }

        return ans;
    }

    //https://leetcode.com/problems/powx-n/?envType=study-plan-v2&envId=top-interview-150
    public double myPow(double x, int n) {
        long N = n;  // Convert to long to handle Integer.MIN_VALUE safely
        double result = fastPow(x, Math.abs(N));
        return n < 0 ? 1.0 / result : result;
    }

    private double fastPow(double x, long n) {
        if (n == 0) return 1.0;
        double half = fastPow(x, n / 2);
        return (n % 2 == 0) ? half * half : half * half * x;
    }
}
