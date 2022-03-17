package lessons.week2.pratice.pratice2;

import lessons.week2.ListNode;

/**
 * @version 1.0
 * @Description: 链表的中间结点
 * @author: bingyu
 * @date: 2022/3/16
 */
public class ZgSolved {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(1);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(3);
        ListNode node7 = new ListNode(1);
        ListNode node8 = new ListNode(8);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;
        ListNode listNode = middleNode(node1);
        System.out.println();
    }
    //1 2 3 4 5
    //1->4->2->1->5->3->1->8
    //争哥的解法:使用快慢指针发，快指针一次走2步，慢指针一次走1步，当快指针到达尾部后，慢指针所在的位置就是中间节点
    public static ListNode middleNode(ListNode head) {
        ListNode quick = head; //快指针
        ListNode slow = head; //慢指针
        while (quick!=null && quick.next != null) {
            quick = quick.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
