package lessons.week4.pratice.recursion.pratice.pratice5;

import lessons.common.ListNode;

/**
 * @version 1.0 剑指 Offer 24. 反转链表
 * @Description: 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
 *
 * 示例:
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 *
 * 限制：
 * 0 <= 节点个数 <= 5000
 *
 * @author: bingyu
 * @date: 2022/5/19
 */
public class ReverseList {

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
    }

    public static ListNode reverseList(ListNode head) {

        return null;
    }

    /*
     普通链表解法:
     */
    public static ListNode reverseList2(ListNode head) {
        ListNode vnode = new ListNode(); //哨兵节点
        ListNode p = head;

        return null;
    }

}
