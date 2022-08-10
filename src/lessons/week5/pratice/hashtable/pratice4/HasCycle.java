package lessons.week5.pratice.hashtable.pratice4;

import lessons.common.ListNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @version 1.0 环形链表
 * @Description: 给你一个链表的头节点head，判断链表中是否有环。
 * 如果链表中有某个节点，可以通过连续跟踪next指针再次到达，则链表中存在环。为了表示给定链表中的环，评测系统内部使用整数pos来表示链表尾连接
 * 到链表中的位置（索引从0开始）。注意：pos不作为参数进行传递。仅仅是为了标识链表的实际情况。
 * 如果链表中存在环，则返回true。否则，返回 false 。
 *
 * 示例 1：
 * 3-->2-->0-->-4
 *     |________|
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *
 * 示例2：
 *
 * 输入：head = [1,2], pos = 0
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 *
 * 示例 3：
 *
 * 输入：head = [1], pos = -1
 * 输出：false
 * 解释：链表中没有环。
 *
 *
 * 提示：
 * 链表中节点的数目范围是 [0, 10^4]
 * -10^5 <= Node.val <= 10^5
 * pos 为 -1 或者链表中的一个 有效索引
 *
 *
 * @author: bingyu
 * @date: 2022/8/10
 */
public class HasCycle {

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

    /*
     我的思路: 使用哈希表
     执行用时：4 ms, 在所有 Java 提交中击败了14.03%的用户
     内存消耗：42.4 MB, 在所有 Java 提交中击败了66.66%的用户
    */
    public static boolean hasCycle(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();
        ListNode p = head;
        while (p != null) {
            if (!set.contains(p)) { //如果在遍历期间，没有在哈希表里找到对应节点，则将其放入哈希表，有说明重复了即出现环
                set.add(p);
            }else {
                return true;
            }
            p = p.next;
        }
        return false;
    }


}
