package lessons.week2.pratice.array.pratice3;

import lessons.common.ListNode;

/**
 * @version 1.0 删除排序链表中的重复元素
 * @Description:
 * @author: bingyu
 * @date: 2022/3/17
 */
public class ZgSolved {

    //-1->0->0->1->1
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

    //争哥的思路是:使用新的头节点作为一个尾节点指向null，并作为结果链表，然后遍历所给的链表后面的节点，和结果链表尾部进行比较，不相同就插入尾部
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode newHead = new ListNode(-101); //虚拟头节点
        ListNode result = newHead; //结果链表的尾部指针
        ListNode p = head;
        while (p != null) {
            if (p.val != result.val) {
                result.next = p;
                result = result.next;
            }
            p = p.next;
            result.next = null; //这行代码是为了解决无法去除尾部重复的元素问题，
        }
        return newHead.next;
    }
}
