package com.leetcode.practice.solution.SlidingWindow;

import java.util.HashMap;
import java.util.Map;

public class HardSlidingWindowSolution {
    //https://leetcode.com/problems/minimum-window-substring/description/?envType=study-plan-v2&envId=top-interview-150
    public String minWindow(String s, String t) {
        if (t.length() > s.length()) return "";

        // Count characters needed from string t
        Map<Character, Integer> targetCounts = new HashMap<>();
        for (char c : t.toCharArray()) {
            targetCounts.put(c, targetCounts.getOrDefault(c, 0) + 1);
        }

        // This map keeps track of character frequencies in the current window
        Map<Character, Integer> windowCounts = new HashMap<>();

        // Number of unique characters in t that must match in the window
        int required = targetCounts.size();

        // Number of unique characters in the current window that match the required count
        int formed = 0;

        // Pointers for the sliding window
        int left = 0, right = 0;

        // Track the best window
        int minLen = Integer.MAX_VALUE;
        int minLeft = 0;

        while (right < s.length()) {
            // Add the character at right to the window
            char c = s.charAt(right);
            windowCounts.put(c, windowCounts.getOrDefault(c, 0) + 1);

            // If this character is needed and now satisfied, increment 'formed'
            if (targetCounts.containsKey(c) &&
                    windowCounts.get(c).intValue() == targetCounts.get(c).intValue()) {
                formed++;
            }

            // Try to contract the window from the left while it's valid
            while (formed == required) {
                // Update the result if this window is smaller
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minLeft = left;
                }

                // Remove the leftmost character from the window
                char lc = s.charAt(left);
                windowCounts.put(lc, windowCounts.get(lc) - 1);

                // If this character is required and is now below the required count, reduce 'formed'
                if (targetCounts.containsKey(lc) &&
                        windowCounts.get(lc).intValue() < targetCounts.get(lc).intValue()) {
                    formed--;
                }

                // Move left pointer forward to try smaller window
                left++;
            }

            // Expand the window by moving right pointer
            right++;
        }

        // Return the smallest window found, or empty string if no valid window
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + minLen);
    }
}
