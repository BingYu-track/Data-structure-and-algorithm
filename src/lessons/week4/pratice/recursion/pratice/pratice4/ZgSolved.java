package lessons.week4.pratice.recursion.pratice.pratice4;

import lessons.common.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0
 * @Description: 剑指 Offer 06. 从尾到头打印链表 --争哥解法
 * @author: bingyu
 * @date: 2022/5/18
 */
public class ZgSolved {

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


    static List<Integer> result = new ArrayList<>(); //用来存储逆序的链表元素
    /*
     争哥解法:
    */
    public static int[] reversePrint(ListNode head) {
        reverseTravel(head);
        int[] resultArr = new int[result.size()];
        int i = 0;
        for (Integer k : result) { //遍历集合，将集合转换为数组
            resultArr[i++] = k;
        }
        return resultArr;
    }

    //这里才是核心的递归解法
    private static void reverseTravel(ListNode head) {
        if (head == null) return;
        reverseTravel(head.next); //不断将next元素放入集合中，最先放入的一定是末尾元素
        result.add(head.val);
    }

}
