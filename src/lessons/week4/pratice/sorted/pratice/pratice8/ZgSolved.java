package lessons.week4.pratice.sorted.pratice.pratice8;

import lessons.common.ListNode;

/**
 * @version 1.0
 * @Description:
 * @author: bingyu
 * @date: 2022/6/24
 */
public class ZgSolved {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(-1);
        ListNode node2 = new ListNode(5);
        ListNode node3 = new ListNode(-2);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(0);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        ListNode node = insertionSortList(node1);
        System.out.println(node);
    }

    /*
    争哥思路:
    */
    public static ListNode insertionSortList(ListNode head) {
        if (head == null) return null;
        // 存储已经排好序的节点
        ListNode newHead = new ListNode(Integer.MIN_VALUE, null);
        // 遍历节点
        ListNode p = head;
        while (p != null) {
            ListNode tmp = p.next;
            // 寻找p节点插⼊的位置,插⼊到哪个节点后⾯
            ListNode q = newHead; // 初始化值
            while (q.next != null && q.next.val <= p.val) { // 循环结束条件
                q = q.next;
            }
            // 将p节点插⼊
            p.next = q.next;
            q.next = p;
            p = tmp;
        }
        return newHead.next;
    }


}
