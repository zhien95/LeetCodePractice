package com.leetcode.practice.solution.twoPointers;

public class EasyTwoPointersSolution {
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
