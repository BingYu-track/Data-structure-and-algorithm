package lessons.week5.pratice.hashtable.pratice3;

import lessons.common.ListNode;

import java.util.HashSet;

/**
 * @version 1.0 相交链表
 * @Description: 给你两个单链表的头节点headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
 *
 * 图示两个链表在节点 c1 开始相交：
 * A:      a1-->a2
 *                \
 *                 c1-->c2-->c3
 *                 |
 * B: b1-->b2-->b3/
 * 题目数据 保证 整个链式结构中不存在环。
 * 注意，函数返回结果后，链表必须 保持其原始结构 。
 * 自定义评测：
 * 评测系统 的输入如下（你设计的程序 不适用 此输入）：
 *
 *
 * intersectVal - 相交的起始节点的值。如果不存在相交节点，这一值为 0
 * listA - 第一个链表
 * listB - 第二个链表
 * skipA - 在 listA 中（从头节点开始）跳到交叉节点的节点数
 * skipB - 在 listB 中（从头节点开始）跳到交叉节点的节点数
 * 评测系统将根据这些输入创建链式数据结构，并将两个头节点 headA 和 headB 传递给你的程序。如果程序能够正确返回相交节点，那么你的解决方案将被视作正确答案 。
 *
 *
 * 示例 1：
 * A:     4-->1
 *            |
 *            8-->4-->5
 *            |
 * B: 5-->6-->1
 *
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
 * 输出：Intersected at '8'
 * 解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。
 * 从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,6,1,8,4,5]。
 * 在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 *
 * 示例2：
 * A: 1-->9-->1
 *            |
 *            2-->4
 *            |
 * B:         3
 *
 * 输入：intersectVal= 2, listA = [1,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
 * 输出：Intersected at '2'
 * 解释：相交节点的值为 2 （注意，如果两个链表相交则不能为 0）。
 * 从各自的表头开始算起，链表 A 为 [1,9,1,2,4]，链表 B 为 [3,2,4]。
 * 在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
 *
 * 示例3：
 * A: 2-->6-->4
 *
 * B: 1-->5
 *
 * 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 * 输出：null
 * 解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。
 * 由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
 * 这两个链表不相交，因此返回 null 。
 *
 *
 * 提示：
 * listA 中节点数目为 m
 * listB 中节点数目为 n
 * 1 <= m, n <= 3 * 10^4
 * 1 <= Node.val <= 10^5
 * 0 <= skipA <= m
 * 0 <= skipB <= n
 * 如果 listA 和 listB 没有交点，intersectVal 为 0
 * 如果 listA 和 listB 有交点，intersectVal == listA[skipA] == listB[skipB]
 *
 * @author: bingyu
 * @date: 2022/8/9
 */
public class GetIntersectionNode {

    public static void main(String[] args) {
        ListNode nodeA1 = new ListNode(4);
        ListNode nodeA2 = new ListNode(1);
        nodeA1.next = nodeA2;

        ListNode nodeB1 = new ListNode(5);
        ListNode nodeB2 = new ListNode(6);
        ListNode nodeB3 = new ListNode(1);
        nodeB1.next = nodeB2;
        nodeB2.next = nodeB3;

        ListNode nodeC1 = new ListNode(8);
        ListNode nodeC2 = new ListNode(4);
        ListNode nodeC3 = new ListNode(5);
        nodeC1.next = nodeC2;
        nodeC2.next = nodeC3;

        nodeA2.next = nodeC1;
        nodeB3.next = nodeC1;

        ListNode listNode = getIntersectionNode(nodeA1, nodeB1);
        System.out.println(listNode);
    }


    /**
     * 我的思路: 找出交点,也就是当前驱节点不同，后继节点一样，就说明找到了交点;用哈希表
     * TODO 注意 使用containValue会很慢，尽量使用containsKey方法，争哥解法和我的一模一样
     *
     * 1.使用containValue方法的执行时间
     * 执行用时：1655 ms, 在所有 Java 提交中击败了5.46%的用户
     * 内存消耗：45 MB, 在所有 Java 提交中击败了5.08%的用户
     *
     * 2.使用containsKey方法的执行时间
     * 执行用时：5 ms, 在所有 Java 提交中击败了23.05%%的用户
     * 内存消耗：44.2 MB, 在所有 Java 提交中击败了69.20%的用户
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode A = headA;
        ListNode B = headB;
        HashSet<ListNode> nodeSet = new HashSet<>();
        while (A!=null) {
            nodeSet.add(A);
            A = A.next;
        }
        while (B!=null) {
            if (nodeSet.contains(B)) {
                return B;
            }else {
                B = B.next;
            }
        }
        return null;
    }

}
