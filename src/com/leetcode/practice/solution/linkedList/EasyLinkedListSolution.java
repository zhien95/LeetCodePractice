package com.leetcode.practice.solution.linkedList;

import com.leetcode.practice.solution.data.ListNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
