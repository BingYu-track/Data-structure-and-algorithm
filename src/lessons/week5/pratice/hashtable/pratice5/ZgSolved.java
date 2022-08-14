package lessons.week5.pratice.hashtable.pratice5;

import lessons.common.ListNode;

import java.util.HashSet;
import java.util.Set;

import static lessons.week5.pratice.hashtable.pratice5.RemoveDuplicateNodes.removeDuplicateNodes2;

/**
 * @version 1.0
 * @Description: 移出重复节点--争哥解法
 * @author: bingyu
 * @date: 2022/8/11
 */
public class ZgSolved {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(2);
        ListNode node6 = new ListNode(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        ListNode node = removeDuplicateNodes2(node1);
        System.out.println(node);
    }

    /*
     争哥思路: 和我思路是一样的
    */
    public static ListNode removeDuplicateNodes(ListNode head) {
        Set<Integer> set = new HashSet<>();
        ListNode newHead = new ListNode();
        ListNode tail = newHead;
        ListNode p = head;
        while (p != null) {
            ListNode tmp = p.next;
            if (!set.contains(p.val)) {
                set.add(p.val);
                tail.next = p;
                tail = p;
                tail.next = null;
            }
            p = tmp;
        }
        return newHead.next;
    }

}
