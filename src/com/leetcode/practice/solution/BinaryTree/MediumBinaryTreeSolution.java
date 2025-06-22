package com.leetcode.practice.solution.BinaryTree;

import com.leetcode.practice.solution.data.Node;
import com.leetcode.practice.solution.data.TreeNode;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class MediumBinaryTreeSolution {
    //summarise methods to build tree (preorder/postorder + inorder)
    //1. find root in preorder/postorder
    //2. find index of root in inorder
    //3. split inorder array into left and right partitions
    //4. build children nodes
    //  4a. if preorder -> build left node first
    //  4b. if postorder -> build right node first

    public class PreOrderSolution {
        private int preIndex = 0;
        private Map<Integer, Integer> inorderIndexMap;

        public TreeNode buildTree(int[] preorder, int[] inorder) {
            // Map each value in inorder to its index
            inorderIndexMap = new HashMap<>();
            for (int i = 0; i < inorder.length; i++) {
                inorderIndexMap.put(inorder[i], i);
            }

            return helper(preorder, 0, inorder.length - 1);
        }

        private TreeNode helper(int[] preorder, int inLeft, int inRight) {
            if (inLeft > inRight) return null;

            // Take current root from preorder
            int rootVal = preorder[preIndex++];
            TreeNode root = new TreeNode(rootVal);

            // Find the index of the root in inorder
            int index = inorderIndexMap.get(rootVal);

            // Recursively build left and right subtrees
            root.left = helper(preorder, inLeft, index - 1);
            root.right = helper(preorder, index + 1, inRight);

            return root;
        }
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
        return root;
    }

    public class PostOrderSolution {


        //https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/?envType=study-plan-v2&envId=top-interview-150
        private int postIndex;
        private Map<Integer, Integer> inorderIndexMap;

        public TreeNode buildTree(int[] inorder, int[] postorder) {
            postIndex = postorder.length - 1;

            // Build value->index map for inorder traversal
            inorderIndexMap = new HashMap<>();
            for (int i = 0; i < inorder.length; i++) {
                inorderIndexMap.put(inorder[i], i);
            }

            return helper(inorder, postorder, 0, inorder.length - 1);
        }

        private TreeNode helper(int[] inorder, int[] postorder, int inLeft, int inRight) {
            if (inLeft > inRight) return null;

            // Get root value and move postIndex backward
            int rootVal = postorder[postIndex--];
            TreeNode root = new TreeNode(rootVal);

            // Find index of root in inorder array
            int index = inorderIndexMap.get(rootVal);

            // Important: build right first
            root.right = helper(inorder, postorder, index + 1, inRight);
            root.left = helper(inorder, postorder, inLeft, index - 1);

            return root;
        }
    }
}
