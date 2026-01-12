package com.leetcode.practice.solution.hashmap;

import java.util.*;
import java.util.stream.Collectors;

public class MediumHashMapSolution {
    /**
     * [Group Anagrams]
     */
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

    /**
     * [Longest Consecutive Sequence]
     */
//https://leetcode.com/problems/longest-consecutive-sequence/submissions/1630704575/?envType=study-plan-v2&envId=top-interview-150
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Set<Integer> numSet = Arrays.stream(nums)
                .boxed()
                .collect(Collectors.toSet());

        Set<Integer> start = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (!numSet.contains(num - 1)) {
                start.add(num);
            }
        }

        List<Integer> startList = new ArrayList<>(start);

        int max = 0;
        for (Integer num : startList) {
            int curr = num;
            int count = 1;
            while (numSet.contains(curr + 1)) {
                count++;
                curr++;
            }
            max = Math.max(max, count);
        }

        return max;
    }

    /**
     * [Subarray Sum Equals K]
     *
     * Counts the number of continuous subarrays whose sum equals k.
     * Uses a prefix sum approach with a hash map to store the frequency of each prefix sum.
     * For each position, checks if (current_sum - k) exists in the map to find subarrays ending at current position.
     *
     * @param nums Array of integers
     * @param k Target sum
     * @return Number of subarrays with sum equal to k
     */
//https://leetcode.com/problems/subarray-sum-equals-k/
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixSumCount = new HashMap<>();
        prefixSumCount.put(nums[1], 1);
        int count = 0, sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            if (prefixSumCount.containsKey(nums[i])) {
                count += prefixSumCount.get(nums[i]);
            }

            prefixSumCount.put(sum, prefixSumCount.getOrDefault(sum, 0) + 1);
        }

        return count;
    }

    /**
     * [Top K Frequent Elements]
     */
//https://leetcode.com/problems/top-k-frequent-elements/
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // Buckets: index is frequency, value is list of numbers with that frequency
        List<Integer>[] buckets = new ArrayList[nums.length + 1];
        for (int num : freqMap.keySet()) {
            int freq = freqMap.get(num);
            if (buckets[freq] == null) {
                buckets[freq] = new ArrayList<>();
            }
            buckets[freq].add(num);
        }

        List<Integer> result = new ArrayList<>();
        for (int i = buckets.length - 1; i >= 0 && result.size() < k; i--) {
            if (buckets[i] != null) {
                for (int num : buckets[i]) {
                    if (result.size() < k) {
                        result.add(num);
                    } else {
                        break;
                    }
                }
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        MediumHashMapSolution solution = new MediumHashMapSolution();
        System.out.println(Arrays.toString(solution.topKFrequent(new int[] {1,1,1,2,2,3}, 2)));
    }
}
