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
        if (n < 2){
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
}
