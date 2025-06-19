package com.leetcode.practice.solution.bst;

import com.leetcode.practice.solution.data.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

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
}
