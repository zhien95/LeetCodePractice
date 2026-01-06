package com.leetcode.practice.solution.BinaryTree;

import com.leetcode.practice.solution.data.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author limzhien
 * @date 2/1/26
 */
public class BinaryTreePrinter {

    public static void print(TreeNode root) {
        if (root == null) {
            System.out.println("Empty tree");
            return;
        }
        printTree(root, "", true);
    }

    private static void printTree(TreeNode node, String prefix, boolean isLast) {
        if (node != null) {
            System.out.println(prefix + (isLast ? "└── " : "├── ") + node.val);

            if (node.left != null || node.right != null) {
                String childPrefix = prefix + (isLast ? "    " : "│   ");

                if (node.right != null) {
                    printTree(node.right, childPrefix, node.left == null);
                }
                if (node.left != null) {
                    printTree(node.left, childPrefix, true);
                }
            }
        }
    }

    // 按层打印（更适合宽树）
    public static void printByLevel(TreeNode root) {
        if (root == null) {
            System.out.println("Empty tree");
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            System.out.print("Level " + level + ": ");

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                System.out.print(node.val + " ");

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            System.out.println();
            level++;
        }
    }
}

