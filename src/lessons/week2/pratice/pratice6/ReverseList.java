package lessons.week2.pratice.pratice6;

import lessons.week2.ListNode;

/**
 * @version 1.0 反转链表
 * @Description: 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5]
 * 输出：[5,4,3,2,1]
 *
 * 示例 2：
 * 输入：head = [1,2]
 * 输出：[2,1]
 *
 * 示例 3：
 * 输入：head = []
 * 输出：[]
 *
 * 提示：
 * 链表中节点的数目范围是 [0, 5000]
 * -5000 <= Node.val <= 5000
 *
 * 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？
 *
 * @author: bingyu
 * @date: 2022/3/18
 */
public class ReverseList {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        ListNode listNode = reverseList2(node1);
        System.out.println(listNode);
    }

    //1.迭代法 1->2->3->4->5
    public static ListNode reverseList(ListNode head) {
        ListNode newHeand = null; //该节点作为反正链表的头节点
        ListNode pre = null; //存储遍历时的上一个节点
        ListNode current = head;
        while (current != null) {
            ListNode temp = current.next; //用来存储当前节点的下一个节点
            newHeand = current;
            newHeand.next = pre; //将上一个节点存到当前反转链表的头节点后面
            pre = newHeand;
            current = temp;
        }
        return newHeand;
    }

    //2.递归法 1->2->3->4->5
    public static ListNode reverseList2(ListNode head) {
        if (head == null) return head;
        ListNode newHeand = head; //该节点作为反转链表的头节点
        if (newHeand.next != null) {
            newHeand = reverseList2(newHeand.next); //5 4
            head.next = null;
            ListNode tmp = newHeand;
            while (tmp.next != null) { //有什么办法能规避掉在递归中的while循环？
                tmp = tmp.next;
            }
            tmp.next = head;
        }
        return newHeand;
    }


}
