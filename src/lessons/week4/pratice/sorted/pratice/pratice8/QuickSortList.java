package lessons.week4.pratice.sorted.pratice.pratice8;

import lessons.common.ListNode;

/**
 * @version 1.0
 * @Description: 对链表进行插快速排序
 * @author: bingyu
 * @date: 2022/7/11
 */
public class QuickSortList {

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
        ListNode node = quickSortList(node1);
        System.out.println(node);
    }

    private static ListNode quickSortList(ListNode node) {

        return null;
    }

}
