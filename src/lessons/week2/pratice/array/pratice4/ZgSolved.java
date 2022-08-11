package lessons.week2.pratice.array.pratice4;

import lessons.common.ListNode;

/**
 * @version 1.0
 * @Description: 剑指 Offer 25. 合并两个排序的链表
 * @author: bingyu
 * @date: 2022/3/17
 */
public class ZgSolved {

    public static void main(String[] args) {
        ListNode nodeA1 = new ListNode(1);
        ListNode nodeA2 = new ListNode(2);
        ListNode nodeA3 = new ListNode(2);
        ListNode nodeB1 = new ListNode(1);
        ListNode nodeB2 = new ListNode(3);
        ListNode nodeB3 = new ListNode(4);
        nodeA1.next = nodeA2;
        nodeA2.next = nodeA3;
        nodeB1.next = nodeB2;
        nodeB2.next = nodeB3;
        ListNode listNode = mergeTwoLists(nodeA1, nodeB1);
        System.out.println();
    }

    //争哥的解法
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode head = new ListNode();//虚拟头节点
        ListNode tail = head;
        while (p1 != null && p2 != null) {
            if (p1.val <= p2.val) {
                tail.next = p1;
                tail = p1;
                p1 = p1.next;
            } else {
                tail.next = p2;
                tail = p2;
                p2 = p2.next;
            }
        }
        // 如果p1还没处理完，就把剩下的直接接到tail后⾯(这里处理的细节就比我好)
        if (p1 != null) tail.next = p1;
        // 同理
        if (p2 != null) tail.next = p2;
        return head.next;
    }


}
