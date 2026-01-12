package com.leetcode.practice.solution.linkedList;

import com.leetcode.practice.solution.data.ListNode;

public class EasyLinkedListSolution {
    /**
     * Detects if there is a cycle in the linked list.
     * Uses Floyd's Cycle Detection Algorithm (also known as the tortoise and hare algorithm).
     * Two pointers move at different speeds; if there's a cycle, they will eventually meet.
     *
     * @param head Head of the linked list
     * @return true if there is a cycle, false otherwise
     */
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;

        while (fast != null && head != null) {
            head = head.next;

            if (fast.next != null) {
                fast = fast.next.next;
            }

            if (head == fast) {
                return true;
            }
        }
        return false;
    }

    /**
     * [Reverse Linked List]
     */
//https://leetcode.com/problems/reverse-linked-list/
    public ListNode reverseList(ListNode head) {
        ListNode current = head;

        ListNode prev = null;
        while (current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }
}
