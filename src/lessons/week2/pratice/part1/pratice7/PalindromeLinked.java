package lessons.week2.pratice.part1.pratice7;

import lessons.common.ListNode;

/**
 * @version 1.0 回文链表
 * @Description: 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 *
 * 示例 1：
 * 输入：head = [1,2,2,1]
 * 输出：true
 *
 * 示例 2：
 * 输入：head = [1,2]
 * 输出：false
 *
 *
 * 提示：
 * 链表中节点数目在范围[1, 105] 内
 * 0 <= Node.val <= 9
 *
 * @author: bingyu
 * @date: 2022/3/21
 */
public class PalindromeLinked {

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
    //1 2 3 2 1
    /**
     * 思路: 1->2->3->4->5->6 注意这里的难点是，在进行对整个链表反正时，肯定会修改原链表，因此后面就无法比较了，
     *      我们应该只反转一半的链表，再将其和另一半比较进行比较即可;测试通过，但是感觉会有更好的方法，我的代码笔记繁琐，应该可以改进
     * @param head
     * @return
     */
    public static boolean isPalindrome(ListNode head) {
        boolean flag = false;
        int count = 0; //记录整个链表的长度
        int half = 0;
        ListNode p = head;
        while (p != null) {
            count ++;
            p = p.next;
        }
        if (count % 2!=0) {
            //奇数
            half = count/2 + 1;
        }else {
            //偶数
            flag = true;
            half = count/2;
        }
        count = 0; //重置为0，后面还要继续计数
        p = head; //重新指向头节点
        while (p!=null) { //重新遍历链表到half节点结束
            count++;
            if (count == half) {
                break;
            }
            p = p.next;
        }
        ListNode halfNode = null;
        if (flag) { //是偶数，则p节点就是前半链表的尾节点
            halfNode = reverseList(p.next); //反转后半部分的链表
        }else { //是奇数，则p节点为链表的分界点，
            halfNode = p;
            halfNode = reverseList(p); //反转后半部分的链表

        }
        p.next = null;
        System.out.println(halfNode);
        return compare(head,halfNode); //再和反转的链表进行比较
    }

    public static boolean compare(ListNode node1, ListNode node2) {
        boolean flag = true;
        while (node1 != null) {
            if (node1.val != node2.val) {
                flag = false;
                break;
            }
            node1 = node1.next;
            node2 = node2.next;
        }
        return flag;
    }

    //1->2->3->4->5
    public static ListNode reverseList(ListNode head) {
        ListNode newHead = null;
        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            ListNode tmp = current.next; //临时存储后面的节点
            newHead = current;
            newHead.next = prev; //由于开始newHead和current指向同一引用，因此该代码会导致后current = current.next不正确，因此在前面我们要先把后面的节点用临时变量存储起来
            prev = newHead;  //再结束循环前记录当前反转头节点存到prev，为下一个循环做好准备，这里prev会不断的积累迭代
            current = tmp;
        }
        return newHead;
    }

}
