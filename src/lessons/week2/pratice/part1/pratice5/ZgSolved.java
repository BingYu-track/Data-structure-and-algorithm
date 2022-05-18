package lessons.week2.pratice.part1.pratice5;

import lessons.common.ListNode;

/**
 * @version 1.0
 * @Description: 两数相加
 * @author: bingyu
 * @date: 2022/3/18
 */
public class ZgSolved {

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

    /**
     * 争哥解法思路：
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode dummyHead = new ListNode();//虚拟节点
        ListNode tail = dummyHead;
        int carry = 0; //表示进位
        while (p1 != null || p2 != null) { //只要2个链表都没有遍历完，就需要继续遍历相加，无法是遍历完的加0
            int sum = 0;
            if (p1 != null) { //加上链表l1当前的节点值
                sum += p1.val;
                p1 = p1.next;
            }
            if (p2 != null) { //加上链表l2当前的节点值
                sum += p2.val;
                p2 = p2.next;
            }
            if (carry != 0) { //如果有进位，则还要加上进位值
                sum += carry;
            }
            tail.next = new ListNode(sum%10); //最后对10取余就是当前相加后节点的值
            carry = sum/10; //求进位
            tail = tail.next; //继续下次节点遍历
        }
        if (carry != 0) {
            tail.next = new ListNode(carry);
        }
        return dummyHead.next;
    }
}
