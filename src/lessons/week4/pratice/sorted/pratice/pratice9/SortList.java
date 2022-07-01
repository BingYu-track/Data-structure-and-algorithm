package lessons.week4.pratice.sorted.pratice.pratice9;

import lessons.common.ListNode;

/**
 * @version 1.0 排序链表
 * @Description: 给你链表的头结点 head ，请将其按升序排列并返回排序后的链表 。
 * 示例 1：
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 *
 * 示例 2：
 * 输入：-1->5->3->4->0
 * 输出：-1->0->3->4->5
 *
 * 示例 3：
 * 输入：head = []
 * 输出：[]
 *
 * 提示：
 * 链表中节点的数目在范围[0, 5 * 10^4]内
 * -10^5<= Node.val <= 10^5
 *
 * 进阶：你可以在O(nlogn) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 * @author: bingyu
 * @date: 2022/6/28
 */
public class SortList {


    public static void main(String[] args) {
        ListNode node1 = new ListNode(5);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(2);
        ListNode node5 = new ListNode(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        ListNode node = sortList(node1);
        System.out.println(node);
    }


    /*
        -1->5-> -2 ->4->0
        我的思路:将链表一分为二，分别排序2子链表，排序完成后，再进行合并,我的这个思路是没错，但是找链表中点时不用遍历，直接使用"快慢指针法"
                来找练表中点
        递推公式:
        终止条件:

           f(n)      3n/2
       f(n/2)  f(n/2)   3n/2
    f(n/4) f(n/4)
    .....
    f(1)
    时间复杂度: n*log2n+3n/2
    "递"时需要遍历一次子链表得到长度，还要再遍历子链表长度的一半得到下半子链表的头节点
    n+n/2=3n/2

    执行用时：10 ms, 在所有 Java 提交中击败了56.89%的用户
    内存消耗：49.6 MB, 在所有 Java 提交中击败了39.03%的用户
    */
    public static ListNode sortList(ListNode head) {
        ListNode p = head;
        return mergeSortList(p,null);
    }

    //快慢指针获取链表中点
    public static ListNode getMiddleNode(ListNode head,ListNode end) {
        ListNode slow = head; //快指针
        ListNode quick = head; //慢指针
        while (quick!=null && quick.next!=end && quick!=end) { //这里加了quick.next!=end是为了防止quick.next.next跳过中间节点
            slow = slow.next;
            quick = quick.next.next;
        }
        return slow;
    }

    public static ListNode mergeSortList(ListNode head, ListNode end) {
        if (head == end) {
            if (head!=null) { //避免空指针
                head.next = null;
            }
            return head;
        }
        ListNode middleNode = getMiddleNode(head,end); //获取链表中点
        ListNode next = middleNode.next; //这里要单独写出来，因为前面终止条件会把next置为null，因此需要显示用变量接收
        ListNode list1 = mergeSortList(head,middleNode); //排序子链表1
        ListNode list2 = mergeSortList(next,end); //排序子链表2
        ListNode list = merge(list1,list2); //合并链表
        return list;
    }

    public static ListNode merge(ListNode list1, ListNode list2) {
        ListNode ordered = new ListNode(Integer.MIN_VALUE);
        ListNode p = ordered;
        while (list1!=null && list2!=null) {
            if (list1.val <= list2.val) {
                ListNode tmp = list1;
                p.next = tmp;
                list1 = list1.next;
                tmp.next = null;
            }else {
                ListNode tmp = list2;
                p.next = tmp;
                list2 = list2.next;
                tmp.next = null;
            }
            p = p.next;
        }
        //执行到这里说明其中一个链表已经遍历完了
        if (list1!=null) {
            p.next = list1;
        }else {
            p.next = list2;
        }
        return ordered.next;
    }


    /*
     我的思路: 跟上一题"对链表进行插入排序"一样的解法，但是由于数据规模大，导致超时
     -1->5->-2->4->0
    */
    public static ListNode sortList2(ListNode head) {
        ListNode newHead = new ListNode(Integer.MIN_VALUE);
        ListNode q = null;
        ListNode p = head;
        while (p != null) {
            ListNode tmp = p.next;
            q = newHead;
            while (q.next!=null && p.val>=q.next.val) { //这里为什么要用p.val>=q.next.val而不是p.val>=q.val?
                                                     //因为当结束循环时，说明p<q.next;这样p直接插入q节点后面就很方便；反之如果是用p.val>=q.val，
                                                    //说明p<q，这样的话p就需要插入到q节点的前面，这样就很麻烦了！
                q = q.next;
            }
            p.next = q.next;
            q.next = p;
            p = tmp;
        }
        return newHead.next;
    }

}
