package lessons.week2.pratice.linkedlist.pratice1;

import lessons.common.ListNode;

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
     * @param head 争哥的解法和我一模一样！
     * @return
     */
    public static ListNode oddEvenList(ListNode head) {
        if (head == null) return null;
        ListNode oddHead = new ListNode();
        ListNode oddTail = oddHead;
        ListNode evenHead = new ListNode();
        ListNode evenTail = evenHead;
        ListNode p = head;
        int count = 1;
        while (p != null) {
            ListNode tmp = p.next;
            if (count % 2 == 1) { // 奇数
                p.next = null;
                oddTail.next = p;
                oddTail = p;
            } else { // 偶数
                p.next = null;
                evenTail.next = p;
                evenTail = p;
            }
            count++;
            p = tmp;
        }
        oddTail.next = evenHead.next;
        return oddHead.next;
    }
}
