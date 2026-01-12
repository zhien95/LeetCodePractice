package com.leetcode.practice.solution.linkedList;

import com.leetcode.practice.solution.data.ListNode;

import java.util.List;
import java.util.PriorityQueue;

public class HardLinkedListSolution {
    /**
     * [Merge k Sorted Lists]
     */
//https://leetcode.com/problems/merge-k-sorted-lists/?envType=study-plan-v2&envId=top-interview-150
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return mergeRange(lists, 0, lists.length - 1);
    }

    // Recursively merge the subrange of lists
    private ListNode mergeRange(ListNode[] lists, int left, int right) {
        if (left == right) return lists[left];

        int mid = left + (right - left) / 2;
        ListNode l1 = mergeRange(lists, left, mid);
        ListNode l2 = mergeRange(lists, mid + 1, right);

        return mergeTwoLists(l1, l2);
    }

    // Merge two sorted linked lists (standard Leetcode 21)
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }

        // Append the remaining nodes from l1 or l2
        curr.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }

    /**
     * [Reverse Nodes in k-Group]
     */
//https://leetcode.com/problems/reverse-nodes-in-k-group/?envType=study-plan-v2&envId=top-interview-150
    public ListNode reverseKGroupWithLength(ListNode head, int k) {
        if (head == null || k <= 1) return head;

        // Step 1: Get the length of the list
        int length = 0;
        ListNode curr = head;
        while (curr != null) {
            length++;
            curr = curr.next;
        }

        // Step 2: Use dummy node to handle head replacement
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevGroupEnd = dummy;

        // Step 3: Reverse every k nodes
        curr = head;
        while (length >= k) {
            ListNode groupStart = curr;
            ListNode prev = null;

            // Reverse k nodes
            for (int i = 0; i < k; i++) {
                ListNode next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }

            // Connect previous group to reversed
            prevGroupEnd.next = prev;
            groupStart.next = curr;

            // Move pointer to end of this group
            prevGroupEnd = groupStart;

            // Update length
            length -= k;
        }

        return dummy.next;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k <= 1) return head;
        // Step 1: Use dummy node to handle head replacement
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode groupPrev = dummy;

        while(true){
            ListNode kth = findKth(groupPrev, k);
            if (kth == null){
                break;
            }
            ListNode nextGroup = kth.next;

            //assign new prev as next group prev
            ListNode prev = nextGroup;
            ListNode curr = groupPrev.next;
            //reverse linkedList
            while (curr != nextGroup){
                ListNode temp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = temp;
            }

            ListNode temp = groupPrev.next; // new group tail
            groupPrev.next = kth; // new group head
            groupPrev = temp;
        }


        return dummy.next;
    }

    private ListNode findKth(ListNode node, int k) {
        while(node != null && k > 0){
            node = node.next;
            k--;
        }
        return node;
    }
}
