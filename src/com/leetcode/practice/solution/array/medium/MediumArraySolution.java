package com.leetcode.practice.solution.array.medium;

public class MediumArraySolution {
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
}
