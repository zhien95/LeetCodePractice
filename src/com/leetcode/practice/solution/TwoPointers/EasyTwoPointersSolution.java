package com.leetcode.practice.solution.TwoPointers;

public class EasyTwoPointersSolution {
    /**
     * [Valid Palindrome]
     * <p>
     * Checks if a given string is a palindrome. A palindrome is a word, phrase, number,
     * or other sequence of characters which reads the same backward as forward.
     *
     * @param s The input string to check
     * @return true if the input string is a palindrome, false otherwise
     */
//https://leetcode.com/problems/valid-palindrome/?envType=study-plan-v2&envId=top-interview-150
    public boolean isPalindrome(String s) {
        s = s.toLowerCase().replaceAll("[^a-z0-9]", "");
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) return false;
            start++;
            end--;
        }

        return true;

    }

    /**
     * [Is Subsequence]
     * <p>
     * Checks if string s is a subsequence of string t. A subsequence is a sequence derived from another
     * sequence by deleting some elements without changing the order of the remaining elements.
     *
     * @param s The subsequence to check for
     * @param t The string to check in
     * @return true if s is a subsequence of t, false otherwise
     */
//https://leetcode.com/problems/is-subsequence/solutions/?envType=study-plan-v2&envId=top-interview-150
    public boolean isSubsequence(String s, String t) {
        if (s.length() < 1) {
            return true;
        }
        int i = 0;
        int j = 0;
        int count = 0;

        while (i < t.length()) {
            if (t.charAt(i) == s.charAt(j)) {
                j++;
                count++;
            }

            if (count == s.length()) {
                return true;
            }
            i++;
        }

        return false;

    }
}
