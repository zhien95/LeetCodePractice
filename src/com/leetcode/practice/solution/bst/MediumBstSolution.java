package com.leetcode.practice.solution.bst;

import com.leetcode.practice.solution.data.TreeNode;

import java.util.*;

/**
 * [Binary Tree Level Order Traversal]
 */
//https://leetcode.com/problems/binary-tree-level-order-traversal/?envType=study-plan-v2&envId=top-interview-150
public class MediumBstSolution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            //process at each level
            int size = queue.size();
            List<Integer> levelValues = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                levelValues.add(node.val);

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(levelValues);
        }

        return result;
    }

    /**
     * [Binary Tree Right Side View]
     */
//https://leetcode.com/problems/binary-tree-right-side-view/description/?envType=study-plan-v2&envId=top-interview-150
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            //process at each level
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == 0) {
                    result.add(node.val);

                    //rightmost node at each level (i == 0 in right-to-left traversal)
                    if (node.right != null) {
                        queue.offer(node.right);
                    }

                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                }
            }

        }
        return result;
    }

    /**
     * [Binary Tree Zigzag Level Order Traversal]
     */
//https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/?envType=study-plan-v2&envId=top-interview-150
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        boolean leftToRight = true;

        while (!queue.isEmpty()) {
            //process at each level
            int size = queue.size();
            LinkedList<Integer> levelValues = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                if (leftToRight) {
                    levelValues.addLast(node.val);
                } else {
                    levelValues.addFirst(node.val);
                }


                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(levelValues);
            leftToRight = !leftToRight;
        }

        return result;
    }

    class BSTIterator {
        /**
         * In-order traversal of BST yields sorted values.
         * Use a stack to push all left nodes down to the smallest.
         */
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();

        public BSTIterator(TreeNode root) {
            pushLeftNode(root);
        }

        public int next() {
            TreeNode node = stack.pop();
            if (node.right != null) {
                pushLeftNode(node.right);
            }
            return node.val;
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        private void pushLeftNode(TreeNode node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
    }
}
