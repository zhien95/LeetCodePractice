package com.leetcode.practice.solution.interval;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HardIntervalSolution {
    /**
     * [Substring with Concatenation of All Words]
     */
//https://leetcode.com/problems/substring-with-concatenation-of-all-words/description/?envType=study-plan-v2&envId=top-interview-150
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || words == null || words.length == 0) return result;

        int wordLength = words[0].length();
        int wordCount = words.length;
        int totalLength = wordLength * wordCount;

        Map<String, Integer> wordMap = new HashMap<>();
        for (String word : words) {
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }

        // Loop over wordLength to cover all starting offsets
        for (int i = 0; i < wordLength; i++) {
            int left = i, right = i;
            int count = 0;
            Map<String, Integer> currentMap = new HashMap<>();

            while (right + wordLength <= s.length()) {
                String word = s.substring(right, right + wordLength);
                right += wordLength;

                if (wordMap.containsKey(word)) {
                    currentMap.put(word, currentMap.getOrDefault(word, 0) + 1);
                    count++;

                    // If there are more occurrences than expected, shift left
                    while (currentMap.get(word) > wordMap.get(word)) {
                        String leftWord = s.substring(left, left + wordLength);
                        currentMap.put(leftWord, currentMap.get(leftWord) - 1);
                        count--;
                        left += wordLength;
                    }

                    if (count == wordCount) {
                        result.add(left);
                    }
                } else {
                    // Reset if word is not in wordMap
                    currentMap.clear();
                    count = 0;
                    left = right;
                }
            }
        }

        return result;
    }
}
