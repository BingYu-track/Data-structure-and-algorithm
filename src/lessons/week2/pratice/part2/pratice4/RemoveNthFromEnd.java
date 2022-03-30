package lessons.week2.pratice.part2.pratice4;

import lessons.week2.ListNode;

/**
 * @version 1.0 删除链表的倒数第 N 个结点
 * @Description: 给你一个链表，删除链表的倒数第n个结点，并且返回链表的头结点。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 *
 * 示例 2：
 * 输入：head = [1], n = 1
 * 输出：[]
 *
 * 示例 3：
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 *
 * 提示：
 * 链表中结点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 *
 * @author: bingyu
 * @date: 2022/3/30
 */
public class RemoveNthFromEnd {

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
        ListNode node = removeNthFromEnd(node1,1);
        System.out.println(node);
    }

    /**
     * 1->2->3->4->5->6
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode current = head;
        ListNode prev = null; //前驱节点
        int count = 0;
        while (current != null) {
            count++;
            current = current.next;
        }
        int targetIndex = count - n + 1; //获取目标值索引
        count = 0;
        current = head;
        while (current != null) {
            count++;
            if (count == targetIndex) { //到达目标值
                if (prev != null) {
                    prev.next = current.next;
                }else {
                    head = head.next; //执行到这里说明指定要删除的是第一个元素，将第一个元素删除，直接结束循环
                    break;
                }
            }
            prev = current; //记录作为下一个节点的前驱节点
            current = current.next;
        }
        return head;
    }
}
