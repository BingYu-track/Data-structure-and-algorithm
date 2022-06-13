package lessons.week4.pratice.recursion.pratice.pratice6;

import lessons.common.ListNode;

/**
 * @version 1.0 合并两个排序的链表
 * @Description: 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
 *
 * 示例1：
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 *
 * 限制：
 * 0 <= 链表长度 <= 1000
 *
 * @author: bingyu
 * @date: 2022/5/24
 */
public class MergeTwoLists {

    public static void main(String[] args) {
        ListNode nodeA1 = new ListNode(-9);
        ListNode nodeA2 = new ListNode(3);
        ListNode nodeA4 = new ListNode(4);
        nodeA1.next = nodeA2;
//      nodeA2.next = nodeA4;
        ListNode nodeB1 = new ListNode(5);
        ListNode nodeB2 = new ListNode(7);
        ListNode nodeB3 = new ListNode(4);
        ListNode nodeB4 = new ListNode(5);
        nodeB1.next = nodeB2;
//        nodeB2.next = nodeB3;
//        nodeB3.next = nodeB4;
        ListNode node = MergeTwoLists.mergeTwoLists(nodeA1, nodeB1);
        System.out.println(node);
    }

    /*
    我的解法
    递归解法: 安照普通链表解法翻译成的递归
     输入: -9->3, 5->7
     输出: -9->3->5->7
    */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode vnode = new ListNode(); //虚拟头节点
        ListNode p = vnode;
        merge(l1,l2,p);
        return vnode.next;
    }

    private static void merge(ListNode l1, ListNode l2, ListNode p) {
        if (l1 == null || l2 == null) { //终止条件:l1或者l2链表遍历完，级将未遍历完的链表遍历完，返回即可
            while (l1 != null) { //l2遍历完成了，l1未遍历完，继续遍历
                p.next = new ListNode(l1.val);
                l1 = l1.next;
                p = p.next;
            }
            while (l2 !=null) { //l1遍历完成了，l2未遍历完，继续便利
                p.next = new ListNode(l2.val);
                l2 = l2.next;
                p = p.next;
            }
            return;
        }
        if (l1.val < l2.val) {
            p.next = l1;
            l1 = l1.next;
        }else {
            p.next = l2;
            l2 = l2.next;
        }
        p = p.next;
        merge(l1,l2,p);
    }


    /*
    普通链表解法:同时遍历2个链表，然后比较两节点的值，小的放前面新的头节点，小的链表向后遍历，大的链表不变

    输入：1->2->4, 5
    输出：1->2->4->5
    */
    public static ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode vnode = new ListNode(); //虚节点
        ListNode p = vnode;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                p.next = new ListNode(l1.val); //小的链表向后遍历
                l1 = l1.next;
            }else {
                p.next = new ListNode(l2.val);
                l2 = l2.next;
            }
            p = p.next;
        }
        while (l1 != null) {
            p.next = new ListNode(l1.val);
            l1 = l1.next;
            p = p.next;
        }
        while (l2 !=null) {
            p.next = new ListNode(l2.val);
            l2 = l2.next;
            p = p.next;
        }
        return vnode.next;
    }

    
}
