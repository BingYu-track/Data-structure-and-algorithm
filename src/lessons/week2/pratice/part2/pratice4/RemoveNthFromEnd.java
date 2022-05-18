package lessons.week2.pratice.part2.pratice4;

import lessons.common.ListNode;

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
//        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;
//        node5.next = node6;
        ListNode node = removeNthFromEnd(node1,1);
        System.out.println(node);
    }

    /** TODO: 推荐解法！--快慢指针解法，这次是让快指针走n+1步，得到要删除节点的前驱节点，然后删除指定节点即可,要注意的是这里我们需要虚拟头节点，为了方便删除头节点
     * 1->2->3->4->5->6
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        //if (head == null || head.next == null) return head; TODO:注意不能这样写，因为如果是要删除头节点的话，这段代码会直接返回
        if (head == null) return head;
        ListNode dummy = new ListNode(); //创建虚拟头节点，为了方便处理头节点
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        int count = 0;
        while (fast != null) {
            fast = fast.next;
            if (count >= n + 1) { //让快指针先走n+1步，然后slow指针开始移动
                slow = slow.next;
            }
            count++;
        }
        if (slow.next != null) { //指行到这里说明快指针已经指向null
            slow.next = slow.next.next;
        }
        return dummy.next;
    }

    /** 我的解法
     * 1->2->3->4->5->6
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd2(ListNode head, int n) {
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
