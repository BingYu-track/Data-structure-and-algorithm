package lessons.week4.pratice.recursion.pratice.pratice4;

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
        int[] result = reversePrint2(node1);
        System.out.println(Arrays.toString(result));
    }

    /*
     非递归解法：
     1->3->2
     使用递归的话，就应该分而治之，但是没想到要怎么分， 目前是将链表元素放入数组后，首尾交换实现的！
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

    //1->2->3->4
    //4->3->2->1
    /*递归解法(还可以用栈，用栈就比较简单)
     TODO 思路:由于单链表只能单向遍历，而且题目要求我们用数组存储，那么就必须遍历一次链表得到节点数来初始化数组，和前面的题解是一样的
          重点是这里的递归也是单向的，第一次递归只能得到头节点的值，再往后遍历就无法得到头节点了，因此我们需要用数组先将头节点的值放到
          数组正确的位置，因此我们直接将其放到数组末尾，递归倒着放到数组就解决了！另外由于递归的是链表，因此节点是线性分布的，并不是和
          斐波那契数列那样是一颗树，因此时间复杂度就是O(n)，所以执行速度还是很快的！

    */
    public static int position = 0; //用来表示尾节点的下标
    public static int[] reversePrint2(ListNode head) {
        int count = 0; //链表节点数
        ListNode p = head;
        while (p != null) { //计算链表的总节点数
            count++;
            p = p.next;
        }
        int[] result = new int[count];
        //
        p = head;
        position = count - 1;
        getValToArr(result,p);
        return result;
    }

    /*
     将链表的节点递归放入数组中
     递推公式: node(n) = node(n-1).next  节点等于上个节点的next
     终止条件: p.next == null
    */
    private static void getValToArr(int[] result, ListNode p) {
        if (p == null) return ;
        result[position] = p.val; //将链表的头节点值放入数组末尾开始，
        position--;
        getValToArr(result, p.next); //递归调用
    }


}
