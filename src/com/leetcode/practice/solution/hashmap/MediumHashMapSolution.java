package com.leetcode.practice.solution.hashmap;

import java.util.*;
import java.util.stream.Collectors;

public class MediumHashMapSolution {
    //https://leetcode.com/problems/group-anagrams/description/?envType=study-plan-v2&envId=top-interview-150
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            int[] count = new int[26];  // assuming only lowercase letters
            for (char c : str.toCharArray()) {
                count[c - 'a']++;  // count frequency of each character
            }

            // Build a key based on character counts
            StringBuilder keyBuilder = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                keyBuilder.append((char) ('a' + i));  // e.g., 'a'
                keyBuilder.append(count[i]);
            }
            String key = keyBuilder.toString();

            // Group by the count-based key
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }

        return new ArrayList<>(map.values());
    }

    //https://leetcode.com/problems/longest-consecutive-sequence/submissions/1630704575/?envType=study-plan-v2&envId=top-interview-150
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Set<Integer> numSet = Arrays.stream(nums)
                .boxed()
                .collect(Collectors.toSet());

        Set<Integer> start = new HashSet<>();
        for (int i =0; i< nums.length;i++){
            int num = nums[i];
            if (!numSet.contains(num-1)){
                start.add(num);
            }
        }

        List<Integer> startList = new ArrayList<>(start);

        int max = 0;
        for (Integer num : startList){
            int curr = num;
            int count = 1;
            while(numSet.contains(curr+1)){
                count++;
                curr++;
            }
            max = Math.max(max, count);
        }

        return max;
    }
}
