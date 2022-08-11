package lessons.week2.pratice.array.pratice7;

import lessons.common.ListNode;

/**
 * @version 1.0
 * @Description: 回文链表 争哥解法
 * @author: bingyu
 * @date: 2022/3/22
 */
public class ZgSolved {
    //1->1->2->2->1->1
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(2);
        ListNode node5 = new ListNode(1);
        ListNode node6 = new ListNode(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        boolean palindrome = isPalindrome(node1);
        System.out.println(palindrome);
    }

    //TODO:争哥的解法和我思路一模一样，但是一些是细节确实处理比我好！推荐
    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        ListNode midNode = findMidNode(head); //查找中间节点(使用的快慢指针)
        ListNode rightHalfHead = reverseList(midNode.next); //反转后半部分的链表,这里返回midNode.next是一个小细节，无论是奇数还是偶数都可以统一进行处理
        ListNode p = head;
        ListNode q = rightHalfHead;
        while (q != null) {
            if (p.val != q.val) return false;
            p = p.next;
            q = q.next;
        }
        return true;
    }

    private static ListNode findMidNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) { //快慢指针
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    private static ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode newHead = null;
        ListNode p = head;
        while (p != null) {
            ListNode tmp = p.next;
            p.next = newHead;
            newHead = p;
            p = tmp;
        }
        return newHead;
    }

}
