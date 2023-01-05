package lessons.week7.pratice.heap.pratice1;

import lessons.common.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @version 1.0 合并K个升序链表
 * @Description: 给你一个链表数组，每个链表都已经按升序排列。请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 * 示例 1：
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 *
 * 示例 2：
 * 输入：lists = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：lists = [[]]
 * 输出：[]
 *
 * 提示：
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] 按升序排列
 * lists[i].length 的总和不超过 10^4
 *
 * @author: bingyu
 * @date: 2022/12/9
 */
public class MergeKLists {

    public static void main(String[] args) {
        ListNode nodeA1 = new ListNode(1);
        ListNode nodeA2 = new ListNode(4);
        ListNode nodeA3 = new ListNode(5);
        nodeA1.next = nodeA2;
        nodeA2.next = nodeA3;

        ListNode nodeB1 = new ListNode(1);
        ListNode nodeB2 = new ListNode(3);
        ListNode nodeB3 = new ListNode(4);
        nodeB1.next = nodeB2;
        nodeB2.next = nodeB3;

        ListNode nodeC1 = new ListNode(2);
        ListNode nodeC2 = new ListNode(6);
        nodeC1.next = nodeC2;
        ListNode[] lists = new ListNode[] {nodeA1,nodeB1,nodeC1};
        ListNode result = mergeKLists(lists);
        System.out.println(result);
    }

    /*
      解法思路:创建个小顶堆;
      执行用时：4 ms, 在所有 Java 提交中击败了73.87%的用户
      内存消耗：43.8 MB, 在所有 Java 提交中击败了8.49%的用户
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        //创建小顶堆
        PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode p1, ListNode p2) { // 后一个p2大于前面的p1，所以是顺序排列
                return p1.val - p2.val;
            }
        });
        for (ListNode node : lists) {
            if (node!=null) {
                minHeap.offer(node);
            }
        }
        ListNode head = new ListNode();
        ListNode tail = head;
        //此时小顶堆堆顶元素是最小的
        while (!minHeap.isEmpty()) {
            ListNode topNode = minHeap.poll(); //取出堆顶元素的链表头节点
            tail.next = topNode;
            tail = topNode; //尾指针更新
            if (topNode.next!=null) {
                minHeap.offer(topNode.next); //再将之前取出的节点的下一个节点插入小顶堆中,注意offer方法插入null的话会报错
            }
        }
        return head.next;
    }



    /*
     解题思路: 方法二--传统的尾指针方法合并两链表(不太推荐),时间复杂度O(n^2)
     执行用时：97 ms, 在所有 Java 提交中击败了24.93%的用户
     内存消耗：43.7 MB, 在所有 Java 提交中击败了13.72%的用户
    */
    public static ListNode mergeKLists2(ListNode[] lists) {
         //虚拟头节点
        ListNode tail = null;
        for (ListNode list : lists) {
            tail = merge(tail,list);
        }
        return tail;
    }

    //将2个链表节点进行比较，小的先放入tail指针中
    private static ListNode merge(ListNode node1, ListNode node2) {
        ListNode dummyHead = new ListNode(Integer.MIN_VALUE);
        ListNode tail = dummyHead;
        while (node1!=null && node2!=null) {
            if (node1.val < node2.val) {
                ListNode temp1 = node1.next;
                tail.next = node1;
                node1.next = null;
                tail = tail.next;
                node1 = temp1;
            }else {
                ListNode temp2 = node2.next;
                tail.next = node2;
                node2.next = null;
                tail = tail.next;
                node2 = temp2;
            }
        }
        //执行到这里说明其中一个链表执行完毕
        if (node1!=null) {
            tail.next = node1;
        }
        if (node2!=null) {
            tail.next = node2;
        }
        return dummyHead.next;
    }

}
