package lessons.week4.pratice.recursion.pratice.pratice5;

import lessons.common.ListNode;

/**
 * @version 1.0
 * @Description: 剑指 Offer 24. 反转链表 --争哥解法
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

    /*
     争哥解法--推荐
      递推公式:下面这个比较难想到，是直接反转
        head.next.next = head;
        head.next = null;
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;
        ListNode newHead = reverseList(head.next); //拿到子链表反转后的头节点，即如果原链表是1->2->3->4->5，则递到后面返回的newHead就是5，head就是4
        head.next.next = head; //注意这里head.next就是5，head.next.next=head相当于把4放到了5的next节点，后面4的指针置为空，就变成了5->4，然后不断进行归的过程
        head.next = null;
        return newHead;
    }

}
