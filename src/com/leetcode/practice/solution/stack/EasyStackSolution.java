package com.leetcode.practice.solution.stack;

import java.util.HashMap;
import java.util.Stack;

public class EasyStackSolution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(')
                stack.push(')');
            else if (c == '{')
                stack.push('}');
            else if (c == '[')
                stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c)
                return false;
        }
        return stack.isEmpty();
    }

    //https://leetcode.com/problems/next-greater-element-i/
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();

        // Build mapping from number to its next greater element in nums2
        for (int num : nums2) {
            // While stack is not empty and current num is greater than top of stack,
            // the current num is the next greater for stack.peek()
            while (!stack.isEmpty() && stack.peek() < num) {
                map.put(stack.pop(), num);
            }
            stack.push(num);
        }

        // Elements left in stack have no next greater element
        while (!stack.isEmpty()) {
            map.put(stack.pop(), -1);
        }

        // Build the result for nums1 based on the map
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = map.get(nums1[i]);
        }

        return res;
    }
}
