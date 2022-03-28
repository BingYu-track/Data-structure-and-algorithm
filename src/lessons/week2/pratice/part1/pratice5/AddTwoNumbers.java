package lessons.week2.pratice.part1.pratice5;

import lessons.week2.ListNode;

/**
 * @version 1.0 两数相加
 * @Description: 给你两个非空的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。你可以假设除了数字 0 之外，这两个数都不会以 0开头。
 *
 * 示例 1：
 * 输入：l1 = [2,4,3], l2 = [5,6,6]
 * 输出：[7,0,0,1]
 * 解释：342 + 665 = 1007.
 *
 * 示例 2：
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 *
 * 示例 3：
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 *
 *
 * 提示：
 * 每个链表中的节点数在范围 [1, 100] 内
 * 0 <= Node.val <= 9
 * 题目数据保证列表表示的数字不含前导零
 *
 * @author: bingyu
 * @date: 2022/3/17
 */
public class AddTwoNumbers {

    public static void main(String[] args) {
        ListNode nodeA1 = new ListNode(9);
        ListNode nodeA2 = new ListNode(9);
        ListNode nodeA3 = new ListNode(9);
        ListNode nodeA4 = new ListNode(9);
        ListNode nodeA5 = new ListNode(9);
        ListNode nodeA6 = new ListNode(9);
        ListNode nodeA7 = new ListNode(9);

        ListNode nodeB1 = new ListNode(9);
        ListNode nodeB2 = new ListNode(9);
        ListNode nodeB3 = new ListNode(9);
        ListNode nodeB4 = new ListNode(9);
        nodeA1.next = nodeA2;
        nodeA2.next = nodeA3;
        nodeA3.next = nodeA4;
        nodeA4.next = nodeA5;
        nodeA5.next = nodeA6;
        nodeA6.next = nodeA7;

        nodeB1.next = nodeB2;
        nodeB2.next = nodeB3;
        nodeB3.next = nodeB4;
        ListNode listNode = addTwoNumbers(nodeA1, nodeB1);
        System.out.println(listNode);
    }

    //思路: 同时遍历l1和l2并将其相加，大于10，就将其进1位，加到下一个节点里
    // TODO:思路和争哥是一样的，但是实现细节没有争哥好,推荐争哥的解法
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p = new ListNode();
        ListNode tail = p;
        ListNode p1 = l1;
        ListNode p2 = l2;
        int k = 0; //用以记录要向高位进的数字
        while (p1 != null && p2 != null) { //同时遍历l1和l2链表并相加
            int i = p1.val + p2.val + k;
            tail.next = new ListNode(i % 10 );
            if (i >= 10) { //大于等于10，需要进位
                k = 1;
            }else {
                k = 0;
            }
            p1 = p1.next;
            p2 = p2.next;
            tail = tail.next;
        }
        while (p1 != null) { //执行到这里说明l2链表遍历完成
            int i = p1.val + k;
            tail.next = new ListNode(i % 10);
            if (i >= 10) {
                k = 1;
            }else {
                k = 0;
            }
            p1 = p1.next;
            tail = tail.next;
        }
        while (p2 != null) { //执行到这里说明l1链表遍历完成
            int i = p2.val + k;
            tail.next = new ListNode(i % 10);
            if (i >= 10) {
                k = 1;
            }else {
                k = 0;
            }
            p2 = p2.next;
            tail = tail.next;
        }
        if (k!=0) { //当2个链表都遍历完了，但是还有一个需要进位的，则插入进位节点
            tail.next = new ListNode(k);
        }
        return p.next;
    }
}
