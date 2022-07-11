package lessons.week4.pratice.sorted.pratice.pratice9;

import lessons.common.ListNode;

/**
 * @version 1.0
 * @Description: 排序链表
 *     进阶：你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 * @author: bingyu
 * @date: 2022/6/28
 */
public class ZgSolved {

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
     争哥思路: 归并排序非递归解法；递归解法是从上到下，不断分成2半处理，适应非递归解法需要我们从小到上，一个个合并处理
     5->4->3->2->1
    */
    public static ListNode sortList(ListNode head) {
        int n = len(head); //获取链表的长度?
        int step = 1; //表示长度为1的链表是有序的，因为每个节点单独来说是有序的
        while (step < n) { //step>=n时说明整个链表都是有序的了
            ListNode newHead = new ListNode(); //初始化结果链表虚节点
            ListNode tail = newHead; //虚节点作为结果链表的尾指针
            ListNode p = head; //原链表指针
            while (p != null) {
                // [p, q]创建p到q且链表长度为step的子链表
                ListNode q = p; //q作为子链表的尾节点
                int count = 1;
                while (q != null && count < step) { //将q移动到长度为step的子链表的末尾
                    q = q.next;
                    count++;
                }
                if (q == null || q.next == null) {//这⼀轮合并结束了
                    tail.next = p;
                    break;
                }
                //[q+1, r]创建q+1到r，且链表长度为step的子链表
                ListNode r = q.next; //初始化r节点
                count = 1;
                while (r != null && count < step) {//将r节点移动到长度为step的子链表的末尾
                    r = r.next;
                    count++;
                }
                // 保存下⼀个step的起点
                ListNode tmp = null;
                if (r != null) {
                    tmp = r.next;
                }
                // merge[p, q][q+1, r] 合并2个子链表
                ListNode[] headAndTail = merge(p, q, r); //争哥这里是成段插的
                tail.next = headAndTail[0];
                tail = headAndTail[1];
                p = tmp; //合并完成后将下一个step的起点赋值给p
            }
            head = newHead.next;
            step *= 2;
        }
        return head;
    }

    //合并[p,q]和[q+1,r]链表
    private static ListNode[] merge(ListNode p, ListNode q, ListNode r) {
        ListNode newHead = new ListNode();
        ListNode tail = newHead; //结果链表的尾指针
        ListNode pa = p; //初始化子链表1的起点
        ListNode pb = q.next; //初始化子链表2的起点
        q.next = null; //删除子链表1的尾节点的后继节点，防止后面到末尾后继续遍历
        if (r != null) {
            r.next = null; //删除子链表2的尾节点的后继节点，防止后面到末尾后继续遍历
        }
        while (pa != null && pb != null) { //这里使用的是双指针，将子链表1和子链表2的起点指针不断比较并放入结果链表中(注意，此时pa、pb都是有序的链表)
            if (pa.val <= pb.val) {
                tail.next = pa;
                tail = tail.next;
                pa = pa.next;
            } else {
                tail.next = pb;
                tail = tail.next;
                pb = pb.next;
            }
        }
        //执行到这里说明pa和pb可能有一个链表没有遍历完成
        if (pa != null) {
            tail.next = pa;
            tail = q; //子链表1的剩余元素在后面，那么合并后链表的尾节点就是子链表1的尾节点
        }
        if (pb != null) {
            tail.next = pb;
            tail = r; //同理子链表2的剩余元素在后面，那么合并后链表的尾节点就是子链表2的尾节点
        }
        return new ListNode[]{newHead.next, tail}; //返回合并后的链表的"起点"和"尾节点"
    }

    private static int len(ListNode head) {
        if (head == null) return 0;
        int n = 1; //这里是从1开始的，获取的长度要比实际链表长度大1，为什么要这么做？
        ListNode p = head;
        while (p != null) {
            n++;
            p = p.next;
        }
        return n;
    }

}
