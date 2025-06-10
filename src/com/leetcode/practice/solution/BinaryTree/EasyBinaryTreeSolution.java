package com.leetcode.practice.solution.BinaryTree;


import com.leetcode.practice.solution.data.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

public class EasyBinaryTreeSolution {
    //https://leetcode.com/problems/maximum-depth-of-binary-tree/?envType=study-plan-v2&envId=top-interview-150
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

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

    //https://leetcode.com/problems/same-tree/?envType=study-plan-v2&envId=top-interview-150
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p != null && q != null && p.val == q.val){
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }

       return false;
    }

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
