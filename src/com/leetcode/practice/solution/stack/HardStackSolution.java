package com.leetcode.practice.solution.stack;

import java.util.Stack;

public class HardStackSolution {
    /**
     * [Basic Calculator]
     *
     * Evaluates a mathematical expression given as a string.
     * Supports addition, subtraction, parentheses, and handles spaces.
     * Does not use any built-in function like eval().
     *
     * @param s Mathematical expression string containing digits, '+', '-', '(', ')', and ' '
     * @return Result of evaluating the expression
     */
//https://leetcode.com/problems/basic-calculator/?envType=study-plan-v2&envId=top-interview-150
    public int calculate(String s) {
        int result = 0;
        int sign = 1; // 1 for '+', -1 for '-'
        Stack<Integer> stack = new Stack<>();

        int i = 0;
        while (i < s.length()) {
            char ch = s.charAt(i);

            if (Character.isDigit(ch)) {
                int num = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + (s.charAt(i) - '0');
                    i++;
                }
                result += sign * num;
                continue; // skip i++ because we already moved i
            } else if (ch == '+') {
                sign = 1;
            } else if (ch == '-') {
                sign = -1;
            } else if (ch == '(') {
                // Push current result and sign onto stack
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            } else if (ch == ')') {
                int prevSign = stack.pop();
                int prevResult = stack.pop();
                result = prevResult + prevSign * result;
            }
            i++;
        }

        return result;
    }
}
