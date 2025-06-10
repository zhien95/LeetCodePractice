package com.leetcode.practice.solution.BinaryTree;

import com.leetcode.practice.solution.data.TreeNode;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import com.leetcode.practice.solution.data.Node;

public class MediumBinaryTreeSolution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, 0, preorder.length, inorder, 0, inorder.length);
    }

    private TreeNode build(int[] preorder, int preStart, int preEnd,
                           int[] inorder, int inStart, int inEnd) {
        if (preStart >= preEnd || inStart >= inEnd) {
            return null;
        }

        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);

        // Find root in inorder
        int mid = inStart;
        while (mid < inEnd && inorder[mid] != rootVal) {
            mid++;
        }

        int leftSize = mid - inStart;

        root.left = build(preorder, preStart + 1, preStart + 1 + leftSize,
                inorder, inStart, mid);
        root.right = build(preorder, preStart + 1 + leftSize, preEnd,
                inorder, mid + 1, inEnd);

        return root;
    }

    //https://leetcode.com/problems/flatten-binary-tree-to-linked-list/?envType=study-plan-v2&envId=top-interview-150
    TreeNode prevFlattened;
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        flatten(root.right);
        flatten(root.left);

        root.left = null;
        root.right = prevFlattened;
        prevFlattened = root;
    }

    public Node connect(Node root) {
        if (root == null) return null;

        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            Node prev = null;

            for (int i = 0; i < size; i++) {
                Node node = queue.poll();

                if (prev != null) {
                    prev.next = node;
                }
                prev = node;

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            // Last node in this level points to null by default
        }
        return  root;
    }
}
