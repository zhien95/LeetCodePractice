package com.leetcode.practice.solution.bst;

import com.leetcode.practice.solution.data.TreeNode;

import java.util.*;

public class EasyBstSolution {
    /**
     * [Average of Levels in Binary Tree]
     *
     * Calculates the average value of nodes on each level of a binary tree.
     * Uses level-order traversal (BFS) to process nodes level by level.
     *
     * @param root Root of the binary tree
     * @return List of average values for each level
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
     *
     * Converts a sorted array into a height-balanced binary search tree.
     * A height-balanced BST is defined as a binary tree in which the depth of the two subtrees
     * of every node never differs by more than one.
     *
     * @param nums Sorted array of integers
     * @return Root of the height-balanced BST
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
     *
     * Finds the minimum absolute difference between values of any two different nodes in a BST.
     * Uses in-order traversal to visit nodes in ascending order, allowing comparison of consecutive values.
     *
     * @param root Root of the binary search tree
     * @return Minimum absolute difference between any two different node values
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

    public class BstDfs {
        private TreeNode prev = null;
        private int minDiff = Integer.MAX_VALUE;
        public int getMinimumDifferenceDFS(TreeNode root){
            inorder(root);
            return minDiff;
        }

        public void inorder(TreeNode node) {
            if (node == null ){
                return;
            }

            getMinimumDifference(node.left);

            if (prev != null) {
                minDiff = Math.min(minDiff, node.val - prev.val);
            }

            prev = node;

            getMinimumDifference(node.right);
        }
    }

    //https://leetcode.com/problems/kth-smallest-element-in-a-bst/?envType=study-plan-v2&envId=top-interview-150
    /**
     * [Kth Smallest Element in a BST]
     *
     * Finds the kth smallest element in a binary search tree.
     * Uses in-order traversal to visit nodes in ascending order until the kth element is found.
     *
     * @param root Root of the binary search tree
     * @param k Position of the desired smallest element (1-indexed)
     * @return Value of the kth smallest node
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
     *
     * Determines if a binary tree is a valid binary search tree.
     * A valid BST satisfies the following conditions:
     * - Left subtree contains only nodes with keys less than the node's key
     * - Right subtree contains only nodes with keys greater than the node's key
     * - Both left and right subtrees are also BSTs
     *
     * @param root Root of the binary tree
     * @return true if the tree is a valid BST, false otherwise
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
