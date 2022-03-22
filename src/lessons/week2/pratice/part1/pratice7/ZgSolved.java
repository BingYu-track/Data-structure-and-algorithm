package lessons.week2.pratice.part1.pratice7;

import lessons.week2.ListNode;

/**
 * @version 1.0
 * @Description: 回文链表 争哥解法
 * @author: bingyu
 * @date: 2022/3/22
 */
public class ZgSolved {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(1);
        ListNode node5 = new ListNode(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        boolean palindrome = isPalindrome(node1);
        System.out.println(palindrome);
    }


    public static boolean isPalindrome(ListNode head) {

        return false;
    }

}
