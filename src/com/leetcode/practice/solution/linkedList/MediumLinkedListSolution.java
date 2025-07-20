package com.leetcode.practice.solution.linkedList;

import com.leetcode.practice.solution.data.ListNode;

import java.util.*;

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

    //https://leetcode.com/problems/copy-list-with-random-pointer/?envType=study-plan-v2&envId=top-interview-150
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

    //https://leetcode.com/problems/top-k-frequent-elements/description/
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

    //https://leetcode.com/problems/reverse-linked-list-ii/?envType=study-plan-v2&envId=top-interview-150
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left == right) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        // Move `prev` to node before the `left` position
        int pos = 1;
        while (pos < left) {
            prev = prev.next;
            pos++;
        }

        // Start reversing from `curr`
        ListNode curr = prev.next;
        ListNode reversePrev = null;

        while (pos <= right) {
            ListNode next = curr.next;
            curr.next = reversePrev;
            reversePrev = curr;
            curr = next;
            pos++;
        }

        // Reconnect the reversed part
        prev.next.next = curr;   // tail of reversed segment points to the rest
        prev.next = reversePrev; // connect prev to head of reversed part

        return dummy.next;
    }

    //https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/?envType=study-plan-v2&envId=top-interview-150
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode curr = head;

        while (curr != null) {
            if (curr.next != null && curr.val == curr.next.val) {
                while (curr.next != null && curr.val == curr.next.val) {
                    curr = curr.next;
                }
                prev.next = curr.next;
            } else {
                prev = curr;
            }
            curr = curr.next;
        }

        return dummy.next;
    }

    //https://leetcode.com/problems/rotate-list/?envType=study-plan-v2&envId=top-interview-150
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        // Step 1: Find the length
        int length = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }

        // Step 2: Connect tail to head to make it circular
        tail.next = head;

        // Step 3: Find new tail: (length - k % length - 1) steps from head
        int stepsToNewTail = length - k % length;
        ListNode newTail = head;
        for (int i = 1; i < stepsToNewTail; i++) {
            newTail = newTail.next;
        }

        // Step 4: Break the ring and return new head
        ListNode newHead = newTail.next;
        newTail.next = null;

        return newHead;
    }

    //https://leetcode.com/problems/partition-list/description/?envType=study-plan-v2&envId=top-interview-150
    public ListNode partition(ListNode head, int x) {
        ListNode leftDummy = new ListNode(0);
        ListNode rightDummy = new ListNode(0);

        ListNode left = leftDummy;
        ListNode right = rightDummy;
        ListNode current = head;
        while (current != null) {
            if (current.val < x) {
                left.next = current;
                left = left.next;
            } else {
                right.next = current;
                right = right.next;
            }
            current = current.next;
        }
        //cut off right to prevent cycle
        right.next = null;
        left.next = rightDummy.next;

        return leftDummy.next;
    }

    //https://leetcode.com/problems/lru-cache/?envType=study-plan-v2&envId=top-interview-150


    class LRUCache {
        class Node {
            int key;
            int val;
            Node prev;
            Node next;

            public Node(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }

        int capacity;
        Map<Integer, Node> map;
        Node head;
        Node tail;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            map = new HashMap<>(capacity);
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            if (!map.containsKey(key)) return -1;
            Node node = map.get(key);
            //remove node and place on front of doublyLinkedList;
            removeNode(node);
            insertToHead(node);
            return node.val;
        }

        public void put(int key, int value) {
            //if key already exist, overwrite value and place value to front
            if (map.containsKey(key)) {
                Node node = map.get(key);
                node.val = value;
                removeNode(node);
                insertToHead(node);
            }
            //else if capacity is full, evict LRU node
            else {
                if (map.size() == capacity) {
                    Node lastNode = tail.prev;
                    map.remove(lastNode.key);
                    removeNode(tail.prev);
                }
                Node newNode = new Node(key, value);
                map.put(key, newNode);
                insertToHead(newNode);
            }

        }

        private void removeNode(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private void insertToHead(Node node) {
            node.prev = head;
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
        }
    }

    //https://leetcode.com/problems/split-linked-list-in-parts/description/
    public ListNode[] splitListToParts(ListNode head, int k) {
        int length = 0;
        ListNode current = head;
        //find length of linkedlist
        while (current != null) {
            current = current.next;
            length++;
        }

        int baseSize = length / k;
        int longPart = length % k;
        ListNode[] res = new ListNode[k];

        current = head;
        for (int i = 0; i < k; i++) {
            int partSize = baseSize + (i < longPart ? 1 : 0);
            res[i] = current;
            //iterate until end of part
            for (int j = 0; j < partSize - 1; j++) {
                if (current.next != null) {
                    current = current.next;
                }
            }
            //break linked list
            if (current != null) {
                ListNode temp = current.next;
                current.next = null;
                current = temp;
            }

        }

        return res;
    }

    //https://leetcode.com/problems/sort-list/description/?envType=study-plan-v2&envId=top-interview-150
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        //split the linked list into two parts by slow and fast pointer
        ListNode slow = head, fast = head;
        ListNode pre = null;
        while(fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        //detached linked list at mid-point
        pre.next = null;
        //merge the two parts in a separate func
        ListNode left = sortList(head);
        ListNode right = sortList(slow);
        return mergeTwoLists(left, right);
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }
        current.next = l1 == null ? l2 : l1;
        return dummy.next;
    }

}
