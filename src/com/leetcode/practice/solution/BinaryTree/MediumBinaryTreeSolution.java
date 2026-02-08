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

    //https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/?envType=study-plan-v2&envId=top-interview-150
    public class PostOrderSolution {
        private int postIndex;
        private Map<Integer, Integer> inorderIndexMap;

        /**
         * Constructs a binary tree from inorder and postorder traversal arrays.
         * Uses the fact that the last element in postorder is the root, and inorder
         * helps determine left and right subtrees.
         *
         * @param inorder Inorder traversal of the binary tree
         * @param postorder Postorder traversal of the binary tree
         * @return Root of the constructed binary tree
         */
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

    //https://leetcode.com/problems/flatten-binary-tree-to-linked-list/?envType=study-plan-v2&envId=top-interview-150
    /**
     * [Flatten Binary Tree to Linked List]
     * <p>
     * Flattens a binary tree to a linked list in-place such that the linked list follows the pre-order traversal
     * of the binary tree. Each node's left pointer becomes null and right pointer points to the next node
     * in the pre-order traversal.
     *
     * @param root Root of the binary tree to flatten
     */
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

    //https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/?envType=study-plan-v2&envId=top-interview-150
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
            prev.next = null;
        }
        return root;
    }

    //connect without using Queue, memory complexity = O(1)
    public Node connectWithoutQ(Node root) {
        // Pointer to traverse the current level using next pointers
        Node curr = root;

        // Dummy node to build the next level's linked list
        Node dummy = new Node(0);

        // Tail pointer to append children to the next level
        Node tail = dummy;

        // Traverse level by level
        while (curr != null) {

            // If left child exists, append it to next level list
            if (curr.left != null) {
                tail.next = curr.left;
                tail = tail.next;
            }

            // If right child exists, append it to next level list
            if (curr.right != null) {
                tail.next = curr.right;
                tail = tail.next;
            }

            // Move to the next node in the current level
            curr = curr.next;

            // If end of current level is reached
            if (curr == null) {
                // Move to the first node of the next level
                curr = dummy.next;

                // Reset dummy and tail for building the following level
                dummy.next = null;
                tail = dummy;
            }
        }

        // Root now has all next pointers connected
        return root;
    }

    //https://leetcode.com/problems/sum-root-to-leaf-numbers/?envType=study-plan-v2&envId=top-interview-150
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode node, int sum) {
        // Base case: no node contributes 0 to the total sum
        if (node == null) {
            return 0;
        }

        // Build the current number
        sum = sum * 10 + node.val;

        // If leaf node, return the number formed
        if (node.left == null && node.right == null) {
            return sum;
        }

        // Recurse on left and right subtree
        return  dfs(node.left, sum) + dfs(node.right, sum);
    }


    /**
     * [Lowest Common Ancestor of a Binary Tree]
     * <p>
     * Finds the lowest common ancestor (LCA) of two given nodes in a binary tree.
     * The LCA is the lowest node that has both p and q as descendants.
     * Uses recursive approach to search in left and right subtrees.
     *
     * @param root Root of the binary tree
     * @param p    First target node
     * @param q    Second target node
     * @return The lowest common ancestor of nodes p and q
     */
//https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/?envType=study-plan-v2&envId=top-interview-150
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //base case: root = null | found p | found q
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        //Both left and right found, root is LCA
        if (left != null && right != null) {
            return root;
        }

        //LCA in let subtree or right subtree
        return left == null ? right : left;
    }


    public static void preorderDfs(TreeNode node) {
        if (node == null) {
            return;
        }

        System.out.println(node.val);
        preorderDfs(node.left);
        preorderDfs(node.right);

    }

    public static TreeNode createTree() {
        // 构建一个更复杂的树结构
        TreeNode root = new TreeNode(1);

        // 第二层
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        // 第三层
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        return root;
    }

    //https://leetcode.com/problems/count-good-nodes-in-binary-tree/description/?envType=problem-list-v2&envId=plakya4j&
    class GoodNodeSolution {
        int res = 0;

        public int goodNodes(TreeNode root) {
            dfs(root, root.val);
            return res;
        }

        private void dfs(TreeNode node, int currentMax) {
            if (node == null) return;

            if (node.val >= currentMax) {
                res++;
            }

            currentMax = Math.max(currentMax, node.val);

            dfs(node.left, currentMax);
            dfs(node.right, currentMax);
        }
    }




    public static void main(String[] args) {
        TreeNode root = createTree();

        BinaryTreePrinter.print(root);
        BinaryTreePrinter.printByLevel(root);
        MediumBinaryTreeSolution.preorderDfs(root);
    }

}
