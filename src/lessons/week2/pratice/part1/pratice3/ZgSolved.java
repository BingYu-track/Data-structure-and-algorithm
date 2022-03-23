package lessons.week2.pratice.part1.pratice3;

import lessons.week2.ListNode;

/**
 * @version 1.0 删除排序链表中的重复元素
 * @Description:
 * @author: bingyu
 * @date: 2022/3/17
 */
public class ZgSolved {


    public static void main(String[] args) {
        ListNode node1 = new ListNode(-1);
        ListNode node2 = new ListNode(0);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(1);
        ListNode node5 = new ListNode(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        ListNode listNode = deleteDuplicates(node1);
        System.out.println(listNode);
    }

    //争哥的思路是:将链表的头节点作为一个尾节点指向null，然后遍历后面的节点，和其进行比较，不相同就插入尾部
    public static ListNode deleteDuplicates(ListNode head) {

        return head;
    }
}
