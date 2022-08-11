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
        ListNode node2 = new ListNode(-2);
        ListNode node3 = new ListNode(6);
        ListNode node4 = new ListNode(2);
        ListNode node5 = new ListNode(-1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        ListNode node = sortList(node1);
        System.out.println(node);
    }


    /*
     TODO: 很难！需要多次练习和复习
     归并排序非递归解法: 不断合并长度为step的子链表
     参考争哥解法进行实现(需要多次练习)
     5->4->3->2->1
     5和4排序，3和2排序
    */
    public static ListNode sortList(ListNode head) {
        ListNode newHead = new ListNode(Integer.MIN_VALUE);
        newHead.next = head;
        int step = 1;
        int len = getLen(head); //获取链表长度
        while (step < len) { //终止条件为step刚好是链表长度，说明全部处理完毕
            ListNode p = newHead.next;
            ListNode tail = newHead;
            while (p!=null) {
                ListNode q = p; //注意这里必须放到循环内部
                int n = 1; //TODO 注意这里要从1开始，如果从0开始的话，起始点就是2个节点组成子链表了，这样就不对了，应该开始是一个节点构成一个链表然后合并
                //[p,q]区间 相当于[5]
                while (q!=null && n < step) {
                    q = q.next;
                    n++;
                }

                //[q+1,r]区间 相当于[4]区间
                ListNode r = null;
                if (q!=null) {
                    r = q.next;
                }
                n = 1;
                while (r!= null && n < step) {
                    r = r.next;
                    n++;
                }
                //TODO:注意在合并前要保存下⼀个step的起点
                ListNode tmp = null;
                if (r != null) {
                    tmp = r.next;
                }
                //将[p,q]区间和[q+1,r]区间合并----合并[5]和[4]区间，并返回合并链表后的头节点和尾节点
                ListNode[] nodes = merges(p,q,r); //返回[4,5]
                //将排序好的链表插入原链表中
                tail.next = nodes[0];
                //nodes[1].next = tmp;
                tail = nodes[1]; //将指针指向下一个节点，目的是接着这个后面继续将排序的链表插入正确的位置
                p = tmp; //合并完成后，更新开始nodes = {ListNode[2]@507} 起点，继续按照上面的逻辑进行处理
            } //TODO 链表所有节点都执行完后，才能增加子链表长度，否则如果没有这层循环，链表还没按照step=1全部排序完就变成了step=2来处理了
            //step++;
            step*=2; //TODO 改成这样就通过了？这是为什么？为什么step++不能通过？
        }
        return newHead.next;
    }


    //5,4,3,2,1
    private static ListNode[] merges(ListNode p, ListNode q, ListNode r) {
        ListNode q_next = null;
        if (q!=null) {
            q_next = q.next;
            q.next = null; //防止子链表1超出范围
        }
        if (r!=null) {
            r.next = null; //防止子链表2超出范围
        }
        ListNode l1 = p; //[p,q]
        ListNode l2 = q_next; //[q+1,r]
        ListNode newHead = new ListNode(Integer.MIN_VALUE);
        ListNode tail = newHead;
        while (l1!=null && l2!=null) {
            if (l1.val <= l2.val) {
                tail.next = l1;
                l1 = l1.next;
            }else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }

        if (l1 != null) {
            tail.next = l1;
            tail = q; //如果l1链表[p,q]还不为空，说明最后一个节点就是q了
        }
        if (l2 != null) {
            tail.next = l2;
            tail = r; //如果l2链表[q+1,r]还不为空，说明最后一个节点就是r了
        }
        ListNode[] nodes = new ListNode[]{newHead.next,tail};
        return nodes; //返回的是合并后链表的头节点
    }


    private static int getLen(ListNode head) {
        int count = 0;
        ListNode p = head;
        while (p != null) {
            p = p.next;
            count++;
        }
        return count;
    }

    /*
        -1->5-> -2 ->4->0
        我的思路:将链表一分为二，分别排序2子链表，排序完成后，再进行合并,我的这个思路是没错，但是找链表中点时不用遍历，直接使用"快慢指针法"
                来找练表中点；归并排序递归解法，但是这样做的话空间复杂度是O(n)，因此不满足进阶条件
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
    public static ListNode sortList2(ListNode head) {
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
     冒泡排序
     */

    /*
     选择排序
    */

    /*
     我的思路: 跟上一题"对链表进行插入排序"一样的解法，但是由于数据规模大，导致超时
     -1->5->-2->4->0
    */
    public static ListNode sortList3(ListNode head) {
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
