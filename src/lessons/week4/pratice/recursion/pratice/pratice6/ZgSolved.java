package lessons.week4.pratice.recursion.pratice.pratice6;

import lessons.common.ListNode;

/**
 * @version 1.0
 * @Description: 合并两个排序的链表--争哥解法
 * @author: bingyu
 * @date: 2022/5/25
 */
public class ZgSolved {

    public static void main(String[] args) {
        ListNode nodeA1 = new ListNode(1);
        ListNode nodeA2 = new ListNode(3);
        ListNode nodeA4 = new ListNode(5);
        nodeA1.next = nodeA2;
        nodeA2.next = nodeA4;
        ListNode nodeB1 = new ListNode(2);
        ListNode nodeB2 = new ListNode(4);
        ListNode nodeB3 = new ListNode(6);
        ListNode nodeB4 = new ListNode(5);
        nodeB1.next = nodeB2;
        nodeB2.next = nodeB3;
//        nodeB3.next = nodeB4;
        ListNode node = mergeTwoLists(nodeA1, nodeB1);
        System.out.println(node);
    }

    /*
    TODO 推荐
    输入：1->3->5, 2->4->6
    输出：1->2->3->4->5->6
    争哥解法思路: 如果l1头节点<l2头节点,就只需要l1头节点后的子链表和l2链表合并即可，即子链表3->5和2->4->6合并得到新的链表2->3->4->5->6
    然后l1.next指向这个新的链表即可，(我还是有点不太理解，后面慢慢理解)
     因为如果l1小于l2那么l1头节点肯定是在合并后链表的前面，因此l1保持不变，只需要l1后面的子链表和l2合并后,l1头节点在next合并后的链表即可！
    */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) { //如果l1头节点<l2头节点,就只需要l1头节点后的子链表和l2链表合并得到subHead
            ListNode subHead = mergeTwoLists(l1.next, l2);
            l1.next = subHead;
            return l1;
        } else {
            ListNode subHead = mergeTwoLists(l1, l2.next);
            l2.next = subHead;
            return l2;
        }
    }


}
