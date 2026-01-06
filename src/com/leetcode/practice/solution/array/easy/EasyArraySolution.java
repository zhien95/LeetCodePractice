package com.leetcode.practice.solution.array.easy;

import java.util.HashMap;
import java.util.Map;

public class EasyArraySolution {
    //https://leetcode.com/problems/merge-sorted-array/description/?envType=study-plan-v2&envId=top-interview-150
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;

        while (j >= 0) {
            if (i >= 0 && nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }
    }

    //https://leetcode.com/problems/remove-element/submissions/1563777174/?envType=study-plan-v2&envId=top-interview-150
    public int removeElement(int[] nums, int val) {
        int count = 0;
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[index++] = nums[i];
                count++;
            }
        }


        return count;

    }

    //https://leetcode.com/problems/remove-duplicates-from-sorted-array/submissions/1563850688/?envType=study-plan-v2&envId=top-interview-150
    public int removeDuplicates(int[] nums) {
        int index = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {  // Found a unique element
                nums[index++] = nums[i];    // Move it to the front
            }
        }

        return index;
    }

    //https://leetcode.com/problems/majority-element/?envType=study-plan-v2&envId=top-interview-150
    public int majorityElement(int[] nums) {
        int candidate = nums[0];
        int count = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == candidate) {
                count++;
            } else {
                count--;
                if (count == 0) {
                    candidate = nums[i];
                    count = 1;
                }
            }
        }
        return candidate;
    }

    //https://leetcode.com/problems/roman-to-integer/?envType=study-plan-v2&envId=top-interview-150
    public int romanToInt(String s) {
        if (s.isEmpty()) {
            return 0;
        }
        Map<Character, Integer> romanMap = new HashMap<>();
        /**
         * I             1
         * V             5
         * X             10
         * L             50
         * C             100
         * D             500
         * M             1000
         */
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);

        int sum = romanMap.get(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            char prevChar = s.charAt(i - 1);
            char currChar = s.charAt(i);
            int prevValue = romanMap.get(prevChar);
            int curValue = romanMap.get(currChar);

            if (prevValue < curValue) {
                sum = sum - 2 * prevValue + curValue;
            } else {
                sum += curValue;
            }
        }

        return sum;
    }

    //https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/?envType=study-plan-v2&envId=top-interview-150
    public int strStr(String haystack, String needle) {
        int needleLength = needle.length();
        int haystackLength = haystack.length();
        if (needleLength > haystackLength) {
            return -1;
        }

        int i = 0;

        while (i <= haystackLength - needleLength) {
            int j = 0;
            while (j < needleLength && haystack.charAt(i + j) == needle.charAt(j)) {
                j++;
            }
            if (j == needleLength) {
                return i;
            }
            i++;
        }

        /**
        @developer asdaa
         */

        return -1;

    }

}
