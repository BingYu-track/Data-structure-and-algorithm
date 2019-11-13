package leetcode.linkedlist;

/**
 * @version 1.0
 * @Description: 反转链表
 * @author: bingyu
 * @date: 2019/11/12 22:59
 */
public class ReverseLinkedList {

    public static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }

    //方法一：递归
    public static ListNode reverseList(ListNode head) {
        //1.终止条件是当前为空，或者下一个节点为空
        if(head==null || head.next==null) {
            return head;
        }
        //这里的cur就是最后一个节点
        ListNode cur = reverseList(head.next);
        //这里请配合动画演示理解
        //如果链表是 1->2->3->4->5，那么此时的cur就是5
        //而head是4，head的下一个是5，下下一个是空
        //所以head.next.next 就是5->4
        head.next.next = head; //当第一次执行到这时，说明cur已经是尾结点，head就是倒数第二个节点，head.next就是尾结点，head.next.next=head就是尾结点的指针指向它前面的结点
        //防止链表循环，需要将head.next设置为空
        head.next = null;
        //每层递归函数都返回cur，也就是最后一个节点
        return cur;
    }

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
    }
}
