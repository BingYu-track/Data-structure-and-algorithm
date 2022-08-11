package lessons.week2.pratice.linkedlist.pratice5;

import lessons.common.ListNode;

/**
 * @version 1.0 相交链表
 * @Description: 给你两个单链表的头节点headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null。 。
 *
 * 图示两个链表在节点 c1 开始相交：
 * 题目数据 保证 整个链式结构中不存在环。
 * 注意，函数返回结果后，链表必须保持其原始结构 。
 *
 * 自定义评测：
 * 评测系统 的输入如下（你设计的程序 不适用 此输入）：
 *
 * intersectVal - 相交的起始节点的值。如果不存在相交节点，这一值为 0
 * listA - 第一个链表
 * listB - 第二个链表
 * skipA - 在 listA 中（从头节点开始）跳到交叉节点的节点数
 * skipB - 在 listB 中（从头节点开始）跳到交叉节点的节点数
 * 评测系统将根据这些输入创建链式数据结构，并将两个头节点 headA 和 headB 传递给你的程序。如果程序能够正确返回相交节点，那么你的解决方案将被 视作正确答案 。
 *
 *
 *
 * 示例 1：
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
 * 输出：Intersected at '8'
 * 解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。
 * 从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,6,1,8,4,5]。
 * 在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 *
 * 示例2：
 * 输入：intersectVal= 2, listA = [1,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
 * 输出：Intersected at '2'
 * 解释：相交节点的值为 2 （注意，如果两个链表相交则不能为 0）。
 * 从各自的表头开始算起，链表 A 为 [1,9,1,2,4]，链表 B 为 [3,2,4]。
 * 在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
 *
 * 示例3：
 * 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 * 输出：null
 *
 * 解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。
 * 由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
 * 这两个链表不相交，因此返回 null 。
 *
 * 提示：
 * listA 中节点数目为 m
 * listB 中节点数目为 n
 * 1 <= m, n <= 3 * 104
 * 1 <= Node.val <= 105
 * 0 <= skipA <= m
 * 0 <= skipB <= n
 * 如果 listA 和 listB 没有交点，intersectVal 为 0
 * 如果 listA 和 listB 有交点，intersectVal == listA[skipA] == listB[skipB]
 *
 * @author: bingyu
 * @date: 2022/3/30
 */
public class GetIntersectionNode {

    public static void main(String[] args) {
        ListNode nodeA = new ListNode(1);
        ListNode nodeA1 = new ListNode(2);
        ListNode nodeA2 = new ListNode(4);
        ListNode nodeA3 = new ListNode(5);
        ListNode nodeA4 = new ListNode(4);
//        nodeA.next = nodeA1;
//        nodeA1.next = nodeA2;
//        nodeA2.next = nodeA3;
//        nodeA3.next = nodeA4;

        ListNode nodeB = new ListNode(6);
        ListNode nodeB1 = new ListNode(7);
        ListNode nodeB2 = new ListNode(8);
        ListNode nodeB3 = new ListNode(9);
        nodeB.next = nodeB1;
        nodeB1.next = nodeB2;
        nodeB2.next = nodeB3;
        nodeB3.next = nodeA;
        System.out.println("A:" + nodeA);
        System.out.println("B:" + nodeB);
        ListNode intersectionNode = getIntersectionNode(nodeA, nodeB);
        System.out.println(intersectionNode);
    }


    /** TODO: 推荐使用争哥的解法！快慢指针 特殊情况:1.相交的两盒链表长度相同。 2.其中一个相交链表就一个元素
     * 1->2->3->4->5
     * 6->7->8->9->3->4->5
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode currentA = headA;
        ListNode currentB = headB;
        ListNode slow = null;
        ListNode fast = null;
        int lA = 0;
        int lB = 0;
        //分别遍历两链表，得到两链表的长度
        while (currentA != null) {
            currentA = currentA.next;
            lA++;
        }
        while (currentB != null) {
            currentB = currentB.next;
            lB++;
        }
        int k = Math.abs(lA - lB); //得到两链表的长度差值
        //得到快、慢指针
        if (lA < lB) {
            slow = headA;
            fast = headB;
        } else {
            slow = headB;
            fast = headA;
        }
        int count = 0;
        //然后让长度链表先移动k步，随后短链表再开始移动--这样的目的是使两链表遍历的长度一致，方便后面遍历时比较
        while (fast != null && slow != null) {
            if (count >= k) { //TODO: 注意这里，比较一定要放在前面,因为可能两相交链表的长度是一样的！
                if (fast == slow) {
                    return fast;
                }
                slow = slow.next;
            }
            fast = fast.next;
            count++;
        }
        return null;
    }

    /**
     *
     * leetcode官方题解: 不是太理解
     * pA走过的路径为A链+B链
     * pB走过的路径为B链+A链
     * pA和pB走过的长度都相同，都是A链和B链的长度之和，相当于将两条链从尾端对齐，如果相交，则会提前在相交点相遇，如果没有相交点，则会在最后相遇。
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getIntersectionNodeOfficial(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }

    /**
     * 1->2->3->4->5
     * 6->7->8->9->3->4->5
     * 我的错误思路1: 如果存在相交，则2个链表的尾节点应该是相同的，因此我们将2个链表进行反转，如果相交的话，前面一段肯定是相同的，只要遇到不同的，
     *      说明前面就是相交的节点；TODO: 注意两个相交的链表，反转其中一个链表就会影响另一个链表，因此不能用反转来做这道题
     * @param headA
     * @param headB
     * @return
     *
     */
    public static ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        ListNode pA = headA;
        ListNode pB = headB;
        ListNode prevA = null;
        ListNode prevB = null;
        ListNode intersectionNode = null; //用来记录相交节点
        int countA = 0;
        int countB = 0;
        while (pA != null) {
            prevA = pA; //记录尾节点
            countA++;
            pA = pA.next;
        }
        while (pB != null) {
            prevB = pB;
            countB++;
            pB = pB.next;
        }
        if (prevA != prevB) return null; //不相交

        //执行到这里说明有交点，再将A链表进行反转，这样就会影响到B链表
        int k = Math.abs(countA - countB) + 1; //得到相交节点所在倒数第k个位置
        int pos = 0;
        if (countA >= countB) {
            pos = countA - k + 1; //算出相交节点所在位置
        }else {
            pos = countB - k + 1;
        }
        countA = 0;
        pA = headA;
        while (pA != null) {
            countA++;
            if (countA == pos) {
                intersectionNode = pA;
            }
            pA = pA.next;
        }
        return intersectionNode;
    }

    /**
     * 我的错误思路2: 同时遍历2个链表并记录链表的长度，如果到达了尾节点，且尾节点相同，说明存在相交节点，
     *      *       然后用2个链表的长度值互相减去取绝对值+1，就得到相交的点所在链表的倒数第几的位置，再把该节点取出即可！
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        ListNode pA = headA;
        ListNode pB = headB;
        String b = ""; //记录B链表在A链表反转前的数字字符串
        String rb = ""; //记录B链表在A链表反转后的数字字符串
        ListNode intersectionNode = null; //用来记录相交节点
        //遍历链表b
        while (pB != null) {
            b = b + pB.val;
            pB = pB.next;
        }
        //反转A链表
        ListNode reverseA = reverse(headA);
        System.out.println("reverseA: "+ reverseA);
        //再次遍历B链表，如果这次B链表得到的和
        pB = headB;
        while (pB != null) {
            rb = rb + pB.val;
            pB = pB.next;
        }
        if (b.equals(rb)) {
            return null;
        }
        //执行到这里说明A链表反转影响到了B链表,说明两链表之间是有交点的，然后再根据字符串的不同寻找到对应的交点
        char[] cb = b.toCharArray();
        char[] chars = rb.toCharArray();
        int targetIndex = 0; //相交节点在链表B的索引
        for (int i = 1;i<=cb.length;i++) {
            if (cb[i] != chars[i]) {
                targetIndex = i - 1;
                break;
            }
        }
        //再次遍历
        pB = headB;
        int count = 0;
        while (pB != null) {
            count++;
            if (count == targetIndex) {
                intersectionNode = pB;
                return intersectionNode;
            }
            pB = pB.next;
        }
        return null;
    }

    public static ListNode reverse(ListNode head) {
        ListNode current = head;
        ListNode newHead = null;
        while (current != null){
            ListNode tmp = current.next;
            current.next = newHead;
            newHead = current;
            current = tmp;
        }
        return newHead;
    }


}
