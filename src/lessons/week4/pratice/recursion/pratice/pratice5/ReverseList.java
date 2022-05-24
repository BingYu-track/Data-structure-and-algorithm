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
        ListNode listNode = reverseList(node1);
        System.out.println(listNode);
    }

    /*
     递归解法:
     1->2->3->4->5->NULL
     p.next = newHead
     递推公式:
    */
    public static ListNode v = null; //TODO: 用来存储反转的头节点，这里是重点
    public static ListNode reverseList(ListNode head) {
        if (head == null) return v;
        ListNode p = head;
        reselve(p,p.next);
        return v;
    }

    private static ListNode reselve(ListNode p,ListNode next) {
        if (p == null || p.next == null) {
            v = p; //在递归的终止条件时，此时遇到的节点就是反转链表的头节点，直接赋给成员变量
            return p;
        }
        ListNode n = reselve(next,next.next);
        p.next = null;
        n.next = p;
        return p;
    }

    /*
     普通链表解法:
     1->2->3->4->5->NULL
     */
    public static ListNode reverseList2(ListNode head) {
        ListNode temp = null; //临时节点
        ListNode p = head;
        ListNode newHead = null; //表示上一个节点
        while (p != null) {
            temp = p.next; //保存下一个节点，后面要赋值给p做遍历
            p.next = newHead; //作为反转链表的后继节点
            newHead = p;
            p = temp;
        }
        return newHead;
    }

}
