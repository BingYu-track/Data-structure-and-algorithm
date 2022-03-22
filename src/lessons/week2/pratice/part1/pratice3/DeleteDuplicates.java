package lessons.week2.pratice.part1.pratice3;

import lessons.week2.ListNode;

/**
 * @version 1.0 删除排序链表中的重复元素
 * @Description: 给定一个已排序的链表的头head，删除所有重复的元素，使每个元素只出现一次。返回 已排序的链表。
 *
 * 示例 1：
 * 输入：head = [1,1,2]
 * 输出：[1,2]
 *
 * 示例 2：
 * 输入：head = [1,1,2,3,3]
 * 输出：[1,2,3]
 *
 * 提示：
 * 链表中节点数目在范围 [0, 300] 内
 * -100 <= Node.val <= 100
 * 题目数据保证链表已经按升序 排列
 *
 * @author: bingyu
 * @date: 2022/3/17
 */
public class DeleteDuplicates {

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
    }

    /**
     *  [1,1,2]
     * @param head
     * @return
     */
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode currentNode = head;
        ListNode prev = null;
        while (currentNode != null) {
            if (prev != null && prev.val == currentNode.val) { //前一个和目前节点相同
                prev.next = currentNode.next;
            } else {
                prev = currentNode; // 不同，前节点位置后移
            }
            currentNode = currentNode.next;
        }
        return head;
    }
}
