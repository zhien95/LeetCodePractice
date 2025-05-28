package com.leetcode.practice.solution.interval;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.*;

public class MediumIntervalSolution {
    //https://leetcode.com/problems/longest-substring-without-repeating-characters/description/?envType=study-plan-v2&envId=top-interview-150
    public int lengthOfLongestSubstring(String s) {
        int start = 0;
        int max = 0;
        Map<Character, Integer> lastSeen = new HashMap<>();

        for (int end = 0; end < s.length(); end++) {
            char currentChar = s.charAt(end);

            if (lastSeen.containsKey(currentChar) && lastSeen.get(currentChar) >= start) {
                start = lastSeen.get(currentChar) + 1;
            }

            lastSeen.put(currentChar, end);
            max = Math.max(max, end - start + 1);
        }

        return max;
    }

    //https://leetcode.com/problems/merge-intervals/description/?envType=study-plan-v2&envId=top-interview-150
    public int[][] merge(int[][] intervals) {
        //sort array by start
        Arrays.sort(intervals, (a,b) -> Integer.compare(a[0],b[0]));

        List<int[]> merged = new ArrayList<>();
        int[] current = intervals[0];
        merged.add(current);

        for (int i =1; i < intervals.length; i++){
            int[] next = intervals[i];
            //there is overlap
            if (current[1] >= next[0]){
                current[1] = Math.max(current[1], next[1]);
            } else {
                //no more overlap
                current = next;
                merged.add(current);
            }
        }

        return merged.toArray(new int[merged.size()][2]);
    }

    //https://leetcode.com/problems/insert-interval/description/?envType=study-plan-v2&envId=top-interview-150
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        int n = intervals.length;

        // Step 1: Add intervals before newInterval
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        // Step 2: Merge overlapping intervals
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval);  // Add the merged interval

        // Step 3: Add remaining intervals
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[result.size()][2]);
    }

    //https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/description/?envType=study-plan-v2&envId=top-interview-150
    public int findMinArrowShots(int[][] points) {
        // Sort the balloons based on their end coordinates
        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));

        int arrows = 1;
        int prevEnd = points[0][1];

        // Count the number of non-overlapping intervals
        for (int i = 1; i < points.length; ++i) {
            if (points[i][0] > prevEnd) {
                arrows++;
                prevEnd = points[i][1];
            }
        }

        return arrows;
    }

}
