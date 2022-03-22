package lessons.week2.pratice.part1.pratice4;

import lessons.week2.ListNode;

/**
 * @version 1.0 剑指 Offer 25. 合并两个排序的链表
 * @Description: 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
 *
 * 示例1：
 * 输入：1->2->2, 1->3->4
 * 输出：1->1->2->2->3->4
 *
 * 限制：
 * 0 <= 链表长度 <= 1000
 *
 * @author: bingyu
 * @date: 2022/3/17
 */
public class MergeTwoLists {

    public static void main(String[] args) {
        ListNode nodeA1 = new ListNode(1);
        ListNode nodeA2 = new ListNode(2);
        ListNode nodeA3 = new ListNode(2);
        ListNode nodeB1 = new ListNode(1);
        ListNode nodeB2 = new ListNode(3);
        ListNode nodeB3 = new ListNode(4);
        nodeA1.next = nodeA2;
        nodeA2.next = nodeA3;
        nodeB1.next = nodeB2;
        nodeB2.next = nodeB3;
        ListNode listNode = mergeTwoLists(nodeA1, nodeB1);
        System.out.println(listNode);
    }

    /**
     * 思路: p1和p2进行比较，把小的插入p链表，继续遍历小的那一个链表，例如:如果p1<p2将p1放到p的后面继续遍历p1后面的直到p1的大于p2，就换成遍历p2的链表
     * 这道题，争哥的解法和我一摸一样，但是细节处理稍微比我好一点
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode p1 = l1; //链表1的头节点
        ListNode p2 = l2; //链表2的头节点
        ListNode p = new ListNode(); //虚拟头节点
        ListNode tail = p;  //必须使用这个tail指针
        while (p1 != null || p2 != null) {
            if (p1 != null && p2 != null) { //两链表都有值，取小的插入tail后面，并且小的所在链表向后移动一位
                if (p1.val <= p2.val) {
                    tail.next = p1;
                    p1 = p1.next;
                }else {
                    tail.next = p2;
                    p2 = p2.next;
                }
            } else if (p1 == null) {
                tail.next = p2;
                p2 = p2.next;
            } else  { //执行到这里说明p2肯定为null，则只需遍历另外一个链表即可
                tail.next = p1;
                p1 = p1.next;
            }
            tail = tail.next;
        }
        return p.next;
    }
}
