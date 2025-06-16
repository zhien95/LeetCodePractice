package com.leetcode.practice.solution.linkedList;

import com.leetcode.practice.solution.data.ListNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MediumLinkedListSolution {
    class Node {
        public int val;
        public List<Node> neighbors;
        public Node next;
        public Node random;

        public Node() {
            neighbors = new ArrayList<>();
        }

        public Node(int val) {
            this.val = val;
            neighbors = new ArrayList<>();
        }

        public Node(int val, List<Node> neighbors) {
            this.val = val;
            this.neighbors = neighbors;
        }
    }

    // Recursive helper function to clone nodes using DFS
    private Node clone(Node root, Map<Integer, Node> map) {
        // If the node has not been cloned yet
        if (!map.containsKey(root.val)) {
            // Create a new node with the same value
            Node newNode = new Node(root.val);

            // Store it in the map to avoid re-cloning
            map.put(root.val, newNode);

            // Recursively clone and add all neighbors
            for (Node neighbor : root.neighbors) {
                newNode.neighbors.add(clone(neighbor, map));
            }
        }

        // Return the cloned node from the map
        return map.get(root.val);
    }

    // Main method to clone the graph starting from the given node
    public Node cloneGraph(Node node) {
        if (node == null) {
            // If the input node is null, return null (empty graph)
            return null;
        }

        // A map to keep track of already copied nodes using their values
        Map<Integer, Node> map = new HashMap<>();

        // Start DFS cloning from the given node
        return clone(node, map);
    }

    Map<Node, Node> map = new HashMap<>();
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        if (map.containsKey(head)) {
            return map.get(head);
        }

        Node newNode = new Node(head.val);
        map.put(head, newNode);

        newNode.next = copyRandomList(head.next);
        newNode.random = copyRandomList(head.random);
        return newNode;
    }



    //https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/?envType=study-plan-v2&envId=top-interview-150
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode first = dummy;
        ListNode second = dummy;

        // Advance first pointer by n+1 to maintain a gap
        for (int i = 0; i <= n; i++) {
            first = first.next;
        }

        // Move both pointers
        while (first != null) {
            first = first.next;
            second = second.next;
        }

        // Delete the n-th node from end
        second.next = second.next.next;

        return dummy.next;
    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // Buckets: index is frequency, value is list of numbers with that frequency
        List<Integer>[] buckets = new ArrayList[nums.length + 1];
        for (int num : freqMap.keySet()) {
            int freq = freqMap.get(num);
            if (buckets[freq] == null) {
                buckets[freq] = new ArrayList<>();
            }
            buckets[freq].add(num);
        }

        List<Integer> result = new ArrayList<>();
        for (int i = buckets.length - 1; i >= 0 && result.size() < k; i--) {
            if (buckets[i] != null) {
                result.addAll(buckets[i]);
            }
        }

        return result.stream().limit(k).mapToInt(Integer::intValue).toArray();
    }
}
