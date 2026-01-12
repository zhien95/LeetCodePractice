package com.leetcode.practice.solution.BinaryTree;


import com.leetcode.practice.solution.data.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

public class EasyBinaryTreeSolution {
    /**
     * [Maximum Depth of Binary Tree]
     * <p>
     * Calculates the maximum depth of a binary tree, which is the number of nodes along the longest path
     * from the root node down to the farthest leaf node.
     * Uses recursive depth-first search approach.
     *
     * @param root Root node of the binary tree
     * @return Maximum depth of the tree
     */
//https://leetcode.com/problems/maximum-depth-of-binary-tree/?envType=study-plan-v2&envId=top-interview-150
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * [Invert Binary Tree]
     * <p>
     * Inverts a binary tree by swapping all left and right children recursively.
     * Uses BFS (breadth-first search) approach to traverse and invert the tree.
     *
     * @param root Root node of the binary tree
     * @return Root of the inverted binary tree
     */
//https://leetcode.com/problems/invert-binary-tree/description/?envType=study-plan-v2&envId=top-interview-150
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }

                TreeNode temp = node.left;
                node.left = node.right;
                node.right = temp;
            }
        }

        return root;

    }

    /**
     * [Same Tree]
     * <p>
     * Checks if two binary trees are structurally identical and have the same values.
     * Uses recursive depth-first search to compare nodes in both trees simultaneously.
     *
     * @param p Root of the first binary tree
     * @param q Root of the second binary tree
     * @return true if both trees are identical, false otherwise
     */
//https://leetcode.com/problems/same-tree/?envType=study-plan-v2&envId=top-interview-150
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p != null && q != null && p.val == q.val){
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }

        return false;
    }

    /**
     * [Symmetric Tree]
     *
     * Checks if a binary tree is symmetric (a mirror of itself around its center).
     * Compares left and right subtrees recursively to determine symmetry.
     *
     * @param root Root node of the binary tree
     * @return true if the tree is symmetric, false otherwise
 */
//https://leetcode.com/problems/symmetric-tree/?envType=study-plan-v2&envId=top-interview-150
    public boolean isSymmetric(TreeNode root) {
        return isMirror(root,root);
    }

    public boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        } else if (t1 == null ||t2 == null) {
            return false;
        }

        return t1.val == t2.val && isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
    }

    /**
     * [Path Sum]
     *
     * Determines if the tree has a root-to-leaf path such that adding up all the values
     * along the path equals the target sum.
     * Uses depth-first search to explore all possible root-to-leaf paths.
     *
     * @param root Root node of the binary tree
     * @param targetSum Target sum to find in a root-to-leaf path
     * @return true if such a path exists, false otherwise
 */
//https://leetcode.com/problems/path-sum/description/?envType=study-plan-v2&envId=top-interview-150
    public boolean hasPathSum(TreeNode root, int targetSum) {
        return hasPathSumDfs(0, root, targetSum);
    }

    public boolean hasPathSumDfs(int sum, TreeNode node, int targetSum) {
        if (node == null){
            return false;
        }
        sum += node.val;
        if (node.left == null && node.right == null){
            return sum == targetSum;
        }

        return hasPathSumDfs(sum, node.left, targetSum) || hasPathSumDfs(sum, node.right, targetSum);
    }



}
