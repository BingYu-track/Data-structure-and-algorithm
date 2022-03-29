package lessons.week2.pratice.part2.pratice1;

import lessons.week2.ListNode;

/**
 * @version 1.0
 * @Description: 奇偶链表 争哥解法
 * @author: bingyu
 * @date: 2022/3/29
 */
public class ZgSolved {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(7);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        ListNode node = oddEvenList(node1);
        System.out.println(node);
    }

    /**
     * [4,4,2,6,3,7]
     * @param head
     * @return
     */
    public static ListNode oddEvenList(ListNode head) {

        return null;
    }
}
