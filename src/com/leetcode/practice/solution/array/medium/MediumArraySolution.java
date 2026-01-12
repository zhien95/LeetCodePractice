package com.leetcode.practice.solution.array.medium;

import java.util.*;

public class MediumArraySolution {
    /**
     * [Remove Duplicates from Sorted Array II]
     * <p>
     * Removes duplicates from a sorted array in-place such that duplicates appeared at most twice.
     * The relative order of elements is preserved and the method modifies the input array.
     *
     * @param nums Sorted input array
     * @return The length of the array after removing extra duplicates
     */
//https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/submissions/1574069008/?envType=study-plan-v2&envId=top-interview-150
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;

        int index = 1;
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {  // Duplicate found
                count++;
            } else {
                count = 1;  // Reset count for a new number
            }

            if (count <= 2) {  // Allow at most 2 occurrences
                nums[index++] = nums[i];
            }
        }

        return index;
    }

    public int[] removeDuplicates2(int[] nums) {
        int n = nums.length;
        int index = 0;
        int count = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] == nums[i -1]){
                count++;
            } else {
                count = 1;
            }

            if (count <= 2) {
                nums[index++] = nums[i];
            }

            return nums;
        }

        return nums;
    }

    /**
     * [Rotate Array]
     * <p>
     * Rotates the array to the right by k steps, where k is non-negative.
     * Uses the reversal algorithm which involves reversing the whole array,
     * then reversing the first k elements and the remaining elements separately.
     *
     * @param nums Array to be rotated
     * @param k    Number of steps to rotate right
     */
//https://leetcode.com/problems/rotate-array/?envType=study-plan-v2&envId=top-interview-150
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        if (n < 2) {
            return;
        }

        k = k % n; // Handle cases where k > n

        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    private void reverse(int[] nums, int left, int right) {
        while (left <= right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

    /**
     * [Product of Array Except Self]
     * <p>
     * Returns an array where each element at index i is the product of all elements in the input array
     * except nums[i]. Achieves O(n) time complexity and O(1) space complexity (excluding output array).
     *
     * @param nums Input array of integers
     * @return Array where each element is the product of all other elements
     */
//https://leetcode.com/problems/product-of-array-except-self/?envType=study-plan-v2&envId=top-interview-150
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] output = new int[n];

        // First pass: store prefix products
        output[0] = 1;
        for (int i = 1; i < n; i++) {
            output[i] = output[i - 1] * nums[i - 1];
        }

        // Second pass: multiply with suffix products on the fly
        int suffix = 1;
        for (int i = n - 1; i >= 0; i--) {
            output[i] *= suffix;
            suffix *= nums[i];
        }

        return output;
    }

    /**
     * [Gas Station]
 */
//https://leetcode.com/problems/gas-station/?envType=study-plan-v2&envId=top-interview-150
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalTank = 0;
        int currTank = 0;
        int startIndex = 0;

        for (int i = 0; i < gas.length; i++) {
            int gain = gas[i] - cost[i];
            totalTank += gain;
            currTank += gain;

            // If at any point current tank is negative, we cannot start from 'startIndex'
            if (currTank < 0) {
                startIndex = i + 1;
                currTank = 0;
            }
        }

        return totalTank >= 0 ? startIndex : -1;
    }

    /**
     * [Reverse Words in a String]
 */
//https://leetcode.com/problems/reverse-words-in-a-string/?envType=study-plan-v2&envId=top-interview-150
    public String reverseWords(String s) {
        // Trim leading and trailing spaces, then split by one or more spaces
        String[] words = s.trim().split("\\s+");

        // Use StringBuilder to build the result
        StringBuilder result = new StringBuilder();

        // Append words in reverse order
        for (int i = words.length - 1; i >= 0; i--) {
            result.append(words[i]);
            if (i != 0) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    /**
     * [Zigzag Conversion]
 */
//https://leetcode.com/problems/zigzag-conversion/?envType=study-plan-v2&envId=top-interview-150
    public String convert(String s, int numRows) {
        if (numRows == 1 || s.length() <= numRows) return s;

        // Initialize an array of StringBuilder for each row
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }

        int currentRow = 0;
        boolean goingDown = false;

        // Iterate through the characters in the string
        for (int i = 0; i < s.length(); i++) {
            rows[currentRow].append(s.charAt(i));

            // Change direction when we hit the top or bottom row
            if (currentRow == 0 || currentRow == numRows - 1) {
                goingDown = !goingDown;
            }

            // Move up or down the rows
            currentRow += goingDown ? 1 : -1;
        }

        // Combine all rows into a single string
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }

        return result.toString();
    }

    /**
     * [Maximum Subarray]
     */
//https://leetcode.com/problems/maximum-subarray/description/?envType=study-plan-v2&envId=top-interview-150
    public int maxSubArray(int[] nums) {
        // Initialize the current sum and max sum as the first element
        int currentSum = nums[0];
        int maxSum = nums[0];

        // Start iterating from the second element
        for (int i = 1; i < nums.length; i++) {
            // Either start a new subarray or extend the current one
            currentSum = Math.max(nums[i], currentSum + nums[i]);

            // Update the global maximum sum if needed
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    /**
     * [Maximum Sum Circular Subarray]
     */
//https://leetcode.com/problems/maximum-sum-circular-subarray/description/?envType=study-plan-v2&envId=top-interview-150
    public int maxSubarraySumCircular(int[] nums) {
        int total = 0;

        int maxSum = nums[0];  // Max subarray sum (normal case)
        int currMax = nums[0];

        int minSum = nums[0];  // Min subarray sum (for circular case)
        int currMin = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            total += num;

            // Standard Kadane’s for max subarray sum
            currMax = Math.max(num, currMax + num);
            maxSum = Math.max(maxSum, currMax);

            // Kadane’s for min subarray sum
            currMin = Math.min(num, currMin + num);
            minSum = Math.min(minSum, currMin);
        }

        total += nums[0];  // Add the first element (missed in loop)

        // If all elements are negative, maxSum is the answer
        if (maxSum < 0) {
            return maxSum;
        }

        // Return the maximum of:
        // - non-circular max sum
        // - circular max sum = total sum - min subarray sum
        return Math.max(maxSum, total - minSum);
    }

    /**
     * [Insert Delete GetRandom O(1)]
     */
//https://leetcode.com/problems/insert-delete-getrandom-o1/description/?envType=study-plan-v2&envId=top-interview-150
    class RandomizedSet {
        List<Integer> arr;
        Map<Integer, Integer> indexMap;
        Random rand;

        public RandomizedSet() {
            arr = new ArrayList<>();
            indexMap = new HashMap<>();
            rand = new Random();
        }

        public boolean insert(int val) {
            if (indexMap.containsKey(val)) {
                return false;
            }

            arr.add(val);
            indexMap.put(val, arr.size() - 1);
            return true;
        }

        public boolean remove(int val) {
            if (!indexMap.containsKey(val)) {
                return false;
            }

            int index = indexMap.get(val);
            int lastVal = arr.get(arr.size() - 1);

            arr.set(index, lastVal); // overwrite removed value
            indexMap.put(lastVal, index); // update index of lastVal

            arr.remove(arr.size() - 1);
            indexMap.remove(val); // remove val from map

            return true;
        }

        public int getRandom() {
            return arr.get(rand.nextInt(arr.size()));
        }
    }

    public static List<Integer> getRemovableIndex(String str1, String str2) {
        if (str1.length() != str2.length() + 1) return Arrays.asList(-1);

        int i = 0, j = 0;
        int skippedIdx = 0;

        // Allow skipping one mismatched character
        while (i < str1.length() && j < str2.length()) {
            if (str1.charAt(i) == str2.charAt(j)) {
                i++;
                j++;
            } else {
                // Skip one char in str1 (at index i)
                if (str1.substring(i + 1).equals(str2.substring(j))){
                    skippedIdx = i;
                    break;
                } else {
                    return Arrays.asList(-1);
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        result.add(skippedIdx);
        while (skippedIdx + 1 < str1.length() && str1.charAt(skippedIdx) == str1.charAt(skippedIdx + 1)) {
            result.add(skippedIdx + 1);
        }

        return result;
    }

}
