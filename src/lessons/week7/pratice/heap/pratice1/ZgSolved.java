package lessons.week7.pratice.heap.pratice1;

import lessons.common.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @version 1.0 合并K个升序链表 --争哥解法
 * @Description: 给你一个链表数组，每个链表都已经按升序排列。请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * @author: bingyu
 * @date: 2022/12/9
 */
public class ZgSolved {

    public static void main(String[] args) {
        ListNode nodeA1 = new ListNode(1);
        ListNode nodeA2 = new ListNode(4);
        ListNode nodeA3 = new ListNode(5);
        nodeA1.next = nodeA2;
        nodeA2.next = nodeA3;

        ListNode nodeB1 = new ListNode(1);
        ListNode nodeB2 = new ListNode(3);
        ListNode nodeB3 = new ListNode(4);
        nodeB1.next = nodeB2;
        nodeB2.next = nodeB3;

        ListNode nodeC1 = new ListNode(2);
        ListNode nodeC2 = new ListNode(6);
        nodeC1.next = nodeC2;
        ListNode[] lists = new ListNode[] {nodeA1,nodeB1,nodeC1};
        ListNode result = mergeKLists(lists);
        System.out.println(result);
    }

    /*
     争哥解法解题思路: 每个链表取头节点存入小顶堆中，然后进行堆化，得到的堆顶元素就是最小元素的链表头节点，然后该最小元素节点指针向后移动一位，然后继续堆化
    */
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        int k = lists.length;
        PriorityQueue<ListNode> minQ = new PriorityQueue<>(new Comparator<ListNode>() { //小顶堆优先队列
            @Override
            public int compare(ListNode q1, ListNode q2) {
                return q1.val - q2.val;
            }
        });
        for (int i = 0; i < k; ++i) {
            if (lists[i] != null) { //将所有链表的头节点放入小顶堆
                minQ.offer(lists[i]);
            }
        }
        //此时小顶堆的堆顶元素就是最小的
        ListNode head = new ListNode();
        ListNode tail = head;
        while (!minQ.isEmpty()) {
            ListNode curNode = minQ.poll();//将最小的节点从堆顶移出，并将将问指针指向她，然后想堆中插入之前最小节点的下额节点
            tail.next = curNode;
            tail = tail.next;
            if (curNode.next != null) {
                minQ.offer(curNode.next);
            }
        }
        return head.next;
    }

}
