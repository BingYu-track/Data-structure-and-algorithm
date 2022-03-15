package lessons.week2.pratice;

/**
 * @version 1.0
 * @Description: 移除链表元素
 * @author: bingyu
 * @date: 2022/3/15
 */
public class ZgSolved {

    //题目定义的单向链表
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) {
            this.val = val; this.next = next;
        }
    }


    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(1);
        ListNode node5 = new ListNode(1);
        ListNode node6 = new ListNode(3);
        ListNode node7 = new ListNode(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        ListNode listNode = removeElements(node1, 1);
        System.out.println();
    }

    /**
     * 争哥解法
     */
    public static ListNode removeElements(ListNode head, int val) {

        return head;
    }
}
