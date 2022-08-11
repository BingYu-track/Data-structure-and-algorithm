package lessons.week2.pratice.linkedlist.pratice3;

import lessons.common.ListNode;

/**
 * @version 1.0
 * @Description: 剑指 Offer 22. 链表中倒数第k个节点  争哥解法
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
        ListNode node6 = new ListNode(6);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        ListNode node = getKthFromEnd(node1, 6);
        System.out.println(node);
    }

    /**
     * 争哥解法思路: 使用快慢指针，slow、fast，先让fast走到第K个节点，然后slow开始出发移动，后面两个同时向后移动，直到fast走到尾节点，此时
     *   slow指向的就是要找的倒数第k个节点
     * @param head
     * @param k
     * @return
     */
    public static ListNode getKthFromEnd(ListNode head, int k) {
        // 遍历1
        ListNode fast = head;
        int count = 0;
        while (fast != null) { //先让fast移动到第k个节点
            count++;
            if (count == k) break;
            fast = fast.next;
        }
        if (fast == null) { // 链表节点不够k
            return null;
        }
        // 遍历2
        ListNode slow = head;
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
