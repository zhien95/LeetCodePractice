package com.leetcode.practice.solution.BinaryTree;

import com.leetcode.practice.solution.data.TreeNode;

public class HardBinaryTreeSolution {
    /**
     * [Binary Tree Maximum Path Sum]
     */
//https://leetcode.com/problems/binary-tree-maximum-path-sum/?envType=study-plan-v2&envId=top-interview-150
    int pathMaxSum = Integer.MIN_VALUE;

    /**
     * Calculates the maximum path sum in a binary tree.
     * A path is defined as any sequence of nodes from some starting node to any node in the tree
     * along parent-child connections. The path must contain at least one node and does not need
     * to go through the root.
     *
     * @param root Root of the binary tree
     * @return Maximum path sum in the binary tree
     */
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return pathMaxSum;
    }

    public int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        //only consider positive gain
        int leftGain = Math.max(dfs(node.left), 0);
        int rightGain = Math.max(dfs(node.right), 0);

        int pathSum = node.val + leftGain + rightGain;
        pathMaxSum = Math.max(pathMaxSum, pathSum);

        return node.val + Math.max(leftGain, rightGain);
    }
}
