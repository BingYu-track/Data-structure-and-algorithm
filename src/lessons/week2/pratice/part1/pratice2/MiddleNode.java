package lessons.week2.pratice.part1.pratice2;

import lessons.week2.ListNode;

/**
 * @version 1.0 链表的中间结点
 * @Description: 给定一个头结点为 head的非空单链表，返回链表的中间结点。如果有两个中间结点，则返回第二个中间结点。
 *
 * 示例 1：
 * 输入：[1,2,3,4,5]
 * 输出：此列表中的结点 3 (序列化形式：[3,4,5])
 * 返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
 * 注意，我们返回了一个 ListNode 类型的对象 ans，这样：
 * ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next = NULL.
 *
 * 示例2：
 * 输入：[1,2,3,4,5,6]
 * 输出：此列表中的结点 4 (序列化形式：[4,5,6])
 * 由于该列表有两个中间结点，值分别为 3 和 4，我们返回第二个结点。
 *
 * 提示：
 * 给定链表的结点数介于1和100之间。
 *
 * @author: bingyu
 * @date: 2022/3/16
 */
public class MiddleNode {


    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(1);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(3);
        ListNode node7 = new ListNode(1);
        ListNode node8 = new ListNode(8);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;
        ListNode listNode = middleNode(node1);
        System.out.println();
    }

    /**
     * 1->4->2->1->1->3->1
     * 思路，首先遍历一遍链表记录下链表节点的数目，然后根据数目去取对应的节点 推荐使用争哥的"快慢指针解法"
     */
    public static ListNode middleNode(ListNode head) {
        ListNode currentNode = head;
        int count = 0; //记录链表节点数目
        int middle = 0;
        while (currentNode != null) {
            count++;
            currentNode = currentNode.next;
        }
        middle = (count / 2) + 1; //获取中间要得到的节点所在位置
        count = 0;
        while (head != null) {
            count++;
            if (count == middle) {
                break;
            }
            head = head.next;
        }
        return head;
    }
}
