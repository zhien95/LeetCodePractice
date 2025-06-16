package com.leetcode.practice.solution.stack;

import java.util.Stack;

public class MediumStackSolution {
    //https://leetcode.com/problems/basic-calculator-ii/
    public int calculate(String s) {
        // stack to store intermediate results
        Stack<Integer> stack = new Stack<>();

        // Remove spaces for easier parsing
        s = s.trim().replaceAll(" ", "");

        int num = 0;
        // Initialize operator to '+' to handle the first number
        char op = '+';

        // Iterate through the string, including the last character
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (Character.isDigit(ch)) {
                // Build the current number
                num = num * 10 + (ch - '0');
            }

            // If it's an operator or the last character of the string
            if (!Character.isDigit(ch) || i == s.length() - 1) {
                if (op == '+') {
                    stack.push(num);
                } else if (op == '-') {
                    stack.push(-num);
                } else if (op == '*') {
                    stack.push(stack.pop() * num);
                } else if (op == '/') {
                    stack.push(stack.pop() / num);
                }
                // Reset num for the next number
                num = 0;
                // Update the current operator
                op = ch;
            }
        }

        // Sum all the values remaining in the stack
        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }

    //https://leetcode.com/problems/simplify-path/description/?envType=study-plan-v2&envId=top-interview-150
    public String simplifyPath(String path) {
        String[] parts = path.split("/");
        Stack<String> stack = new Stack<>();

        for (String part : parts) {
            //invalid part
            if (part.isEmpty() || part.equals(".")) {
                continue;
            } else if (part.equals("..")) {
                //move up parent;
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(part);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (String dir : stack) {
            sb.append("/").append(dir);
        }

        return (!sb.isEmpty()) ? sb.toString() : "/";
    }
}
