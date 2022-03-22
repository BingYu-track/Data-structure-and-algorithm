package lessons.week2.pratice.part2.pratice1;

import lessons.week2.ListNode;

/**
 * @version 1.0 奇偶链表
 * @Description: 给定单链表的头节点head，将所有"索引"为奇数的节点和索引为偶数的节点分别组合在一起，然后返回重新排序的列表。
 * 第一个节点的索引被认为是奇数,第二个节点的索引为偶数,以此类推。请注意，(索引偶数组和索引奇数组内部的相对顺序应该与输入时保持一致)。
 * 你必须在O(1)的额外空间复杂度和O(n)的时间复杂度下解决这个问题。
 *
 * 示例 1:
 * 输入: head = [1,2,3,4,5]
 * 输出:[1,3,5,2,4]
 *
 * 示例 2:
 * 输入: head = [2,1,3,5,6,4,7]
 * 输出: [2,3,6,7,1,5,4]
 *
 *
 * 提示:
 * n == 链表中的节点数
 * 0 <= n <= 104
 * -106<= Node.val <= 106
 *
 * @author: bingyu
 * @date: 2022/3/22
 */
public class OddEvenList {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(4);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(6);
        ListNode node5 = new ListNode(3);
        ListNode node6 = new ListNode(7);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        ListNode node = oddEvenList(node1);
        System.out.println(node);
    }

    /**
     * odd奇数的、 even偶数的；
     * 题意: 注意题目是要对"索引"对应的元素进行分组，索引为奇数的为一组，索引为偶数的为一组,且必须满足索引偶数组和索引奇数组内部元素顺序和之前的一样！
     * [4,4,2,6,3,7]
     * [4,2,3,4,6,7]
     * [4,2,3,4,6,7]
     * @param head
     * @return
     */
    public static ListNode oddEvenList(ListNode head) {
        int index = 0; //用来记录链表的索引

        return head;
    }

}
