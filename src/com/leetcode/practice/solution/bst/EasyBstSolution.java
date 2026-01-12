package com.leetcode.practice.solution.bst;

import com.leetcode.practice.solution.data.TreeNode;

import java.util.*;

public class EasyBstSolution {
    /**
     * [Average of Levels in Binary Tree]
     */
//https://leetcode.com/problems/average-of-levels-in-binary-tree/?envType=study-plan-v2&envId=top-interview-150
    public List<Double> averageOfLevels(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Double> result = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            double sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            result.add(sum / size);
        }

        return result;
    }

    /**
     * [Convert Sorted Array to Binary Search Tree]
     */
//https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/?envType=study-plan-v2&envId=top-interview-150
    public TreeNode sortedArrayToBST(int[] nums) {
        //base case
        if (nums == null || nums.length == 0) return null;

        return helper(nums, 0, nums.length - 1);
    }

    private TreeNode helper(int[] nums, int left, int right){
        //end recursive
        if (left > right) return null;

        int mid =  left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, left, mid - 1);
        root.right = helper(nums, mid + 1, right);

        return root;
    }


    /**
     * [Minimum Absolute Difference in BST]
     */
//https://leetcode.com/problems/minimum-absolute-difference-in-bst/description/?envType=study-plan-v2&envId=top-interview-150
    public int getMinimumDifference(TreeNode root) {
        //use stack to prevent stack call overflow
        Stack<TreeNode> stack = new Stack<>();
        Integer prev = null;
        int minDiff = Integer.MAX_VALUE;
        TreeNode curr = root;
        //in order traversal
        while (curr != null || !stack.isEmpty()) {
            //go as left as possible
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            //process node
            curr = stack.pop();
            if (prev != null) {
                minDiff = Math.min(minDiff, curr.val - prev);
            }
            prev = curr.val;

            //point to right node
            curr = curr.right;
        }

        return minDiff;
    }

    /**
     * [Kth Smallest Element in a BST]
     */
//https://leetcode.com/problems/kth-smallest-element-in-a-bst/?envType=study-plan-v2&envId=top-interview-150
    public int kthSmallest(TreeNode root, int k) {
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()) {
            //go as far left as possible
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            //process node
            curr = stack.pop();
            if (--k == 0) {
                return curr.val;
            }
            curr = curr.right;
        }

        return -1;
    }

    /**
     * [Validate Binary Search Tree]
     */
//https://leetcode.com/problems/validate-binary-search-tree/description/?envType=study-plan-v2&envId=top-interview-150
    public boolean isValidBST(TreeNode root) {
        //use stack to prevent stack call overflow
        Stack<TreeNode> stack = new Stack<>();
        Integer prev = null;
        TreeNode curr = root;
        //in order traversal
        while (curr != null || !stack.isEmpty()) {
            //go as left as possible
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            //process node
            curr = stack.pop();
            if (prev != null && curr.val <= prev) {
                return false;

            }
            prev = curr.val;
            //point to right node
            curr = curr.right;
        }

        return true;
    }

}
