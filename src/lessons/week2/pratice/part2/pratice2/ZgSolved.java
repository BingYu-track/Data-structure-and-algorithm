package lessons.week2.pratice.part2.pratice2;

import lessons.week2.ListNode;

/**
 * @version 1.0
 * @Description: K个一组翻转链表 争哥解法
 * @author: bingyu
 * @date: 2022/3/30
 */
public class ZgSolved {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        ListNode node = reverseKGroup(node1, 2);
        System.out.println(node);
    }


    /** 1->2->3->4->5->6
     * 争哥解法思路: 首先使用两个快慢指针q、p来在遍历原链表时找出k个每组的尾节点和头节点，然后再翻转每组的链表并得到新的头节点和尾节点，后面继续遍历
     * 并反转，直到p、q中的快指针指向null
     * @param head
     * @param k
     * @return
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummyHead = new ListNode();
        ListNode tail = dummyHead;
        ListNode p = head;
        while (p != null) {
            int count = 0; //用来记录是否遍历了k次
            ListNode q = p;
            while (q != null) {
                count++;
                if (count == k) {
                    break;
                }
                q = q.next; //q
            }
            //执行到这里说明q是当前k组链表的尾节点,p就是当前k组链表的头节点

            if (q == null) { //如果执行到这里，说明该组链表是最后一组
                tail.next = p;
                return dummyHead.next;
            } else { //当前q不是最后一组，后面需要继续处理
                ListNode tmp = q.next; //保存下一组链表的头节点
                ListNode[] nodes = reverse(p, q); //反转当前组的链表，并得到反转当前组链表后的头节点和尾节点
                tail.next = nodes[0]; //将上一组反转的链表的尾节点，连接当前组反转后链表的头节点
                tail = nodes[1]; //更新尾节点
                p = tmp;
            }
        }
        return dummyHead.next;
    }

    //反转链表并得到新的头节点和尾节点
    private static ListNode[] reverse(ListNode head, ListNode tail) {
        ListNode newHead = null;
        ListNode p = head;
        while (p != tail) {
            ListNode tmp = p.next;
            p.next = newHead;
            newHead = p;
            p = tmp;
        }
        tail.next = newHead;
        newHead = tail;
        return new ListNode[]{tail, head};
    }


}
