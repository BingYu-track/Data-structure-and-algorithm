package lessons.week2.pratice.part2.pratice4;

import lessons.common.ListNode;

/**
 * @version 1.0 删除链表的倒数第 N 个结点
 * @Description: 争哥解法
 * @author: bingyu
 * @date: 2022/3/30
 */
public class ZgSolved {

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
        ListNode node = removeNthFromEnd(node1,2);
        System.out.println(node);
    }

    /**
     * 争哥解法思路: 使用快慢指针法，一个指针p1先移动到正数n位的位置，然后p2从头节点出发，p1,p2一起向后移动，直到p1到达尾节点，
     *              此时p2指向的就是我们要找的目标节点！
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode n1 = dummy;
        ListNode n2 = dummy;
        for (int i=0;i<n;i++){ //移动到正数第n个位置的节点
            n2 = n2.next;
        } //1-2-3-4-5

        while (n2.next != null) { //快慢指针移动
            n1 = n1.next;
            n2 = n2.next;
        }
        n1.next = n1.next.next;
        return dummy.next;
    }
}
