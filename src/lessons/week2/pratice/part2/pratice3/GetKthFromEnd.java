package lessons.week2.pratice.part2.pratice3;

import lessons.week2.ListNode;

/**
 * @version 1.0 剑指 Offer 22. 链表中倒数第k个节点
 * @Description: 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
 * 例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。
 *
 * 示例：
 * 给定一个链表: 1->2->3->4->5, 和 k = 2.
 * 返回链表 4->5.
 *
 * @author: bingyu
 * @date: 2022/3/30
 */
public class GetKthFromEnd {

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
     * 1->2->3->4->5->6
     * 思路: 循环遍历得到链表的长度，再根据长度计数得到倒数指定的节点
     * 问：有没有更好的方法，使其只用一次循环得到节点呢？
     * @param head
     * @param k
     * @return
     */
    public static ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        ListNode current = head;
        ListNode target = null;
        int count = 0; //链表长度
        while (current != null) {
            count++;
            current = current.next;
        }
        int targetIndex = count - k + 1;
        count = 0;
        current = head;
        while (current != null) {
            count++;
            if (count == targetIndex) {
                target = current;
                break;
            }
            current = current.next;
        }
        return target;
    }
}
