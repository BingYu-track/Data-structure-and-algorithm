package lessons.week2.pratice.part2.pratice6;

import lessons.week2.ListNode;

/**
 * @version 1.0
 * @Description: 环形链表--争哥解法
 * @author: bingyu
 * @date: 2022/3/30
 */
public class ZgSolved {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;
        boolean hasCycle = hasCycle(node1);
        System.out.println(hasCycle);
    }

    /**
     * 争哥解法思路:
     * @param head
     * @return
     */
    public static boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null && slow != fast) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (slow == fast) return true;
        return false;
    }

}
