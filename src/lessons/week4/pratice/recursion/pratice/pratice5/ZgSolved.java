package lessons.week4.pratice.recursion.pratice.pratice5;

import lessons.common.ListNode;

/**
 * @version 1.0
 * @Description:
 * @author: bingyu
 * @date: 2022/5/19
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
        node4.next = node5;
        ListNode listNode = reverseList(node1);
        System.out.println(listNode);
    }

    public static ListNode reverseList(ListNode head) {

        return null;
    }
}
