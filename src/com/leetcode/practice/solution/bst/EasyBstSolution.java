package com.leetcode.practice.solution.bst;

import com.leetcode.practice.solution.data.TreeNode;

import java.util.*;

public class EasyBstSolution {
    //https://leetcode.com/problems/average-of-levels-in-binary-tree/?envType=study-plan-v2&envId=top-interview-150
    public List<Double> averageOfLevels(TreeNode root) {
        if (root == null){
            return new ArrayList<>();
        }
        List<Double> result = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            double sum = 0;
            for (int i =0; i < size;i++){
                TreeNode node = queue.poll();
                sum += node.val;

                if (node.left != null){
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer(node.right);
                }
            }

            result.add(sum / size);
        }

        return result;
    }

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
}
