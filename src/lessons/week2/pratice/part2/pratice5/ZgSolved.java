package lessons.week2.pratice.part2.pratice5;

import lessons.week2.ListNode;

/**
 * @version 1.0
 * @Description: 相交链表
 * @author: bingyu
 * @date: 2022/3/31
 */
public class ZgSolved {

    public static void main(String[] args) {
        ListNode nodeA = new ListNode(1);
        ListNode nodeA1 = new ListNode(2);
        ListNode nodeA2 = new ListNode(3);
        ListNode nodeA3 = new ListNode(4);
        ListNode nodeA4 = new ListNode(5);
        nodeA.next = nodeA1;
        nodeA1.next = nodeA2;
        nodeA2.next = nodeA3;
        nodeA3.next = nodeA4;

        ListNode nodeB = new ListNode(6);
        ListNode nodeB1 = new ListNode(7);
        ListNode nodeB2 = new ListNode(8);
        ListNode nodeB3 = new ListNode(9);
        nodeB.next = nodeB1;
        nodeB1.next = nodeB2;
        nodeB2.next = nodeB3;
        nodeB3.next = nodeA2;
        System.out.println("A:" + nodeA);
        System.out.println("B:" + nodeB);
        ListNode intersectionNode = getIntersectionNode(nodeA, nodeB);
        System.out.println(intersectionNode);
    }

    /**
     * 争哥解法: PA、PB指针，首先分别遍历两链表，得到两链表的长度，然后将2链表长度相减，得到差值k，然后两个链表指针从头开始遍历，不同的是
     * 先让长的链表先后面移动k次，然后短链表开始遍历移动，期间2链表元素不断进行比较，相同则为相交点！
     * 1->2->3->4->5
     * 6->7->8->9->3->4->5
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 求链表A的⻓度na
        int na = 0;
        ListNode pA = headA;
        while (pA != null) {
            na++;
            pA = pA.next;
        }
        // 求链表B的⻓度nb
        int nb = 0;
        ListNode pB = headB;
        while (pB != null) {
            nb++;
            pB = pB.next;
        }
        // 先让指向⻓链表的指针多⾛na-nb或nb-na步
        pA = headA;
        pB = headB;
        if (na >= nb) {
            for (int i = 0; i < na-nb; ++i) {
                pA = pA.next;
            }
        } else {
            for (int i = 0; i < nb-na; ++i) {
                pB = pB.next;
            }
        }
        // 让pA和pB同步前进
        while (pA != null && pB != null && pA != pB) {
            pA = pA.next;
            pB = pB.next;
        }
        if (pA == null || pB == null) return null;
        else return pA;
    }

    /**
     * 1->2
     * 6->7->8->9->3->2
     */
}
