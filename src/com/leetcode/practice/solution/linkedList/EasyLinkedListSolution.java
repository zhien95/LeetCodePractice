package com.leetcode.practice.solution.linkedList;

import com.leetcode.practice.solution.data.ListNode;

public class EasyLinkedListSolution {
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
}
