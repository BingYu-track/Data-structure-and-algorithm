package lessons.week5.pratice.hashtable.pratice4;

import lessons.common.ListNode;

import java.util.HashSet;

/**
 * @version 1.0
 * @Description: 环形链表--争哥解法
 * @author: bingyu
 * @date: 2022/8/10
 */
public class ZgSolved {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(-4);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;
        boolean hasCycle = hasCycle(node1);
        System.out.println(hasCycle);
    }

    //争哥解法和我一样
    public static boolean hasCycle(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();
        ListNode p = head;
        while (p != null) {
            if (!set.contains(p)) {
                set.add(p);
            }else {
                return true;
            }
            p = p.next;
        }
        return false;
    }

}
