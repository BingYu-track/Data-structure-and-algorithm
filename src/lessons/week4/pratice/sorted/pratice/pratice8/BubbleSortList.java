package lessons.week4.pratice.sorted.pratice.pratice8;

import lessons.common.ListNode;

/**
 * @version 1.0
 * @Description: 对链表进行冒泡排序
 * @author: bingyu
 * @date: 2022/7/11
 */
public class BubbleSortList {


    public static void main(String[] args) {
        ListNode node1 = new ListNode(-1);
        ListNode node2 = new ListNode(5);
        ListNode node3 = new ListNode(-2);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(0);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        ListNode node = bubbleSortList(node1);
        System.out.println(node);
    }

    /*
    对链表进行冒泡排序，元素节点不断向后面的进行比较
    TODO 有没有更优雅的处理方式?
    [-1,5,-2,4,0]
    */
    private static ListNode bubbleSortList(ListNode node) {
        ListNode newHead = new ListNode(Integer.MIN_VALUE);
        newHead.next = node;
        ListNode q = newHead;
        ListNode p = node;
        int count = 0;
        int cor = 0;
        int len = getLen(node); //获取链表长度
        //TODO 这个代码只能执行一次冒泡循环
        while (p.next != null) {
            cor = 0;
            while (p.next != null && p.val <= p.next.val) { //p节点小于下一个节点时，p节点指针向后于东
               p = p.next;
               q = q.next; //q节点始终保持在p节点的前面，p向后移动后，q节点也需要向后移动
               cor++;
            }
            if (cor==len-1) break;
            //执行到这里说明p节点大于下一个节点，或者p节点已经是末尾节点了，开始进行节点交换
            if (p.next == null && count<len) { //如果到了末尾。重新从头开始遍历
                p = newHead.next;
                q = newHead;
                count++;
                continue;
            }
            ListNode temp = p.next; //-2
            p.next = p.next.next; //5->4->0 这里报错p.next可能为null
            temp.next = p; //-2->5->4->0
            q.next = temp; //-1->-2->5->4->0
            q = q.next;
            if (p.next == null && count<len) { //如果到了末尾。重新从头开始遍历
                p = newHead.next;
                q = newHead;
                count++;
                continue;
            }
        }
        return newHead.next;
    }

    private static int getLen(ListNode node) {
        int count = 0;
        ListNode p = node;
        while (p != null) {
            p = p.next;
            count++;
        }
        return count;
    }


}
