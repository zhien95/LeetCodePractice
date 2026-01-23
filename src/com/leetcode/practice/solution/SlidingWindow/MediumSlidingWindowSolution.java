package com.leetcode.practice.solution.SlidingWindow;

import java.util.HashMap;
import java.util.Map;

public class MediumSlidingWindowSolution {
    /**
     * [Minimum Size Subarray Sum]
     * <p>
     * Finds the minimal length of a contiguous subarray for which the sum is greater than or equal to the target.
     * Uses the sliding window technique to achieve O(n) time complexity.
     *
     * @param target The target sum to achieve
     * @param nums   Array of positive integers
     * @return The minimal length of a subarray withsum >= target, or 0 if no such subarray exists
     */
//https://leetcode.com/problems/minimum-size-subarray-sum/description/?envType=study-plan-v2&envId=top-interview-150
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int start = 0, sum = 0;
        int minLength = Integer.MAX_VALUE;

        for (int end = 0; end < n; end++) {
            sum += nums[end];

            while (sum >= target) {
                minLength = Math.min(minLength, end - start + 1);
                sum -= nums[start++];
            }
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    /**
     * [Longest Substring Without Repeating Characters]
     * <p>
     * Finds the length of the longest substring without repeating characters.
     *Uses the sliding window technique with a hash map to track character positions.
     *
     * @param s Input string
     * @return Length of the longest substring without repeating characters
     */
//https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
    public int lengthOfLongestSubstring(String s) {
        int start =0;
        Map<Character, Integer> lastSeen = new HashMap<>();
        int maxLen = 0;

        for (int end = 0; end < s.length();end ++){
            char ch = s.charAt(end);

            //if char seen, move startto last seen index +1
            if (lastSeen.containsKey(ch) && lastSeen.get(ch) >= start){
                start = lastSeen.get(ch) +1;
            }

            maxLen = Math.max(maxLen, end -start +1);
            lastSeen.put(ch, end);
        }

        return maxLen;
    }

    /**
     * [Longest Repeating Character Replacement]
     ** maxCount represents the frequency of the most frequent character in the current window [left, right].
     * It tracks the highest character frequency seen so far in any window during the algorithm execution.
     *
     * The algorithm uses a sliding window approach where:
     * - We expand the window by moving theright pointer
     * - We update the frequency of the current character and adjust maxCount accordingly
     * - If the number of characters that need to be replaced ((right-left+1) - maxCount) exceeds k,
     *   we shrink the window from the left until the condition is satisfied
     * -We track the maximum window size achieved
     *
     * Note: maxCount doesn't necessarily reflect the exact max frequency in the current window at all times
     * since we never decrease it when shrinking the window. But this is OK because we only care about finding
     * the maximum possible window size, anda larger window would only be possible if maxCount increases.
     */
//https://leetcode.com/problems/longest-repeating-character-replacement/description/
    public int characterReplacement(String s, int k) {
        Map<Character, Integer> freqMap = new HashMap<>();
        int left = 0, maxCount = 0, maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
            maxCount = Math.max(maxCount, freqMap.get(c));

            while ((right - left + 1) - maxCount > k) {
                char leftChar = s.charAt(left);
                freqMap.put(leftChar, freqMap.get(leftChar) - 1);
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    public static void main(String[] args) {
        MediumSlidingWindowSolution solution = new MediumSlidingWindowSolution();
        solution.characterReplacement("AABBBAA", 2);
    }

}
