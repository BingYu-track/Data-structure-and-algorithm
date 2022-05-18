package lessons.week4.pratice.recursion.pratice4;

import lessons.common.ListNode;

import java.util.Arrays;

/**
 * @version 1.0 剑指 Offer 06. 从尾到头打印链表
 * @Description: 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 *
 * 示例 1：
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 *
 * 限制：
 * 0 <= 链表长度 <= 10000
 *
 * @author: bingyu
 * @date: 2022/5/18
 */
public class ReversePrint {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        int[] result = reversePrint(node1);
        System.out.println(Arrays.toString(result));
    }

    /*
     1->3->2
     使用递归的话，就应该分而治之，但是没想到要怎么分， 目前是将链表元素放入数组后，首位交换实现的！
    */
    public static int[] reversePrint(ListNode head) {
        int count = 0; //链表节点数
        ListNode p = head;
        while (p != null) { //计算链表的总节点数
            count++;
            p = p.next;
        }
        int[] result = new int[count];
        p = head;
        count = 0;
        while (p != null) { //将链表每个节点的值放入数组中
            result[count] = p.val;
            count++;
            p = p.next;
        }
        for (int i = 0,j = count - 1;i<=count/2 && j>=count/2 ;i++,j--) {
            int temp = result[i];
            result[i] = result[j];
            result[j] = temp;
        }
        return result;
    }


}
