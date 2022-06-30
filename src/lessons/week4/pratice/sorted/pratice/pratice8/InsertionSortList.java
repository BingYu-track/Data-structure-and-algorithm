package lessons.week4.pratice.sorted.pratice.pratice8;

import lessons.common.ListNode;

/**
 * @version 1.0 对链表进行插入排序
 * @Description: 给定单个链表的头head，使用插入排序,对链表进行排序，并返回排序后链表的头。
 *
 * 插入排序算法的步骤:
 * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 * 重复直到所有输入数据插入完为止。
 * 下面是插入排序算法的一个图形示例。部分排序的列表(黑色)最初只包含列表中的第一个元素。每次迭代时，从输入数据中删除一个元素(红色)，并就地插入已排序的列表中。
 *
 * 对链表进行插入排序。
 *
 * 示例1:
 * 输入: head = [4,2,1,3]
 * 输出: [1,2,3,4]
 *
 * 示例2:
 * 输入: head = [-1,5,3,4,0]
 * 输出: [-1,0,3,4,5]
 *
 * 提示：
 * 列表中的节点数在 [1, 5000]范围内
 * -5000 <= Node.val <= 5000
 *
 * @author: bingyu
 * @date: 2022/6/23
 */
public class InsertionSortList {

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
        ListNode node = insertionSortList(node1);
        System.out.println(node);
    }

    /*
     思路: 新创建一个哨兵节点作为结果链表，遍历目标链表时不断比较大小并插入结果链表即可，参考争哥的解法
         核心难点是，如何确定p节点要插入的位置?
         p.val >= q.next.val这是核心逻辑
        TODO: 需要多多复习
        执行时间比较长
    */
    public static ListNode insertionSortList(ListNode head) {
        ListNode ordered = new ListNode(Integer.MIN_VALUE); //使用哨兵节点作为结果链表，注意这里必须要用最小值作为头，否则后面很难处理
        ListNode q = null; //结果链表指针
        ListNode p = head; //原链表指针
        while (p != null) {
            q = ordered; //TODO 每次比较p节点时，都要从虚拟头节点这个最小节点开始比较,这很重要
            ListNode tmp = p.next;
            while (q.next!=null && p.val >= q.next.val) { //说明当前p节点要在q节点后面,注意，这里必须是p.val >= q.next.val,不能用p.val >= q.val
                q = q.next;
            } //q=-1  p= 5->-2->4->0
            //执行到这里，说明
            p.next = q.next; //将q节点后面的链表放到p节点后面
            q.next = p; //再将q节点指向p节点，就插入成功
            p = tmp; //然后p节点向后移动一位，为后面继续遍历
        }
        return ordered.next;
    }

    /**
     * 链表总结: 插入操作，比如将p插入到q后面
     *  p.next = q.next;
     *  q.next = p;
     */



}
