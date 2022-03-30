package lessons.week2.pratice.part2.pratice6;

import lessons.week2.ListNode;

/**
 * @version 1.0 环形链表
 * @Description: 给你一个链表的头节点 head ，判断链表中是否有环。
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数
 * pos来表示链表尾连接到链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递。仅仅是为了标识链表的实际情况。
 * 如果链表中存在环，则返回 true 。 否则，返回 false 。
 *
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *
 * 示例2：
 * 输入：head = [1,2], pos = 0
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 *
 * 示例 3：
 * 输入：head = [1], pos = -1
 * 输出：false
 * 解释：链表中没有环。
 *
 * 提示：
 * 链表中节点的数目范围是 [0, 104]
 * -105 <= Node.val <= 105
 * pos 为 -1 或者链表中的一个 有效索引
 *
 * @author: bingyu
 * @date: 2022/3/30
 */
public class HasCycle {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node2;
        boolean hasCycle = hasCycle(node1);
        System.out.println(hasCycle);
    }

    /** 1->2->3->4->2
     * 思路: 快慢指针: 如果有循环，则快指针由于跨步快，循环后最终会追上慢指针
     * @param head
     * @return
     */
    public static boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode quick = head; //快指针
        ListNode slow = head; //慢指针
        while (quick != null && quick.next !=null && slow != null) { //TODO: 这里判断条件是重点需要注意的
            quick = quick.next.next;
            slow = slow.next;
            if (quick == slow) {
                return true;
            }
        }
        //指行到这里说明链表遍历完成，说明没有环
        return false;
    }
}
