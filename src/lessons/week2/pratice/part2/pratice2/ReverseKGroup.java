package lessons.week2.pratice.part2.pratice2;

import lessons.week2.ListNode;

/**
 * @version 1.0  K个一组翻转链表
 * @Description: 你一个链表，每k个节点一组进行翻转，请你返回翻转后的链表。k是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是k的整数倍，那么请将最后剩余的节点保持原有顺序。
 * 进阶：
 * 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]
 *
 * 示例 2：
 * 输入：head = [1,2,3,4,5], k = 3
 * 输出：[3,2,1,4,5]
 *
 * 示例 3：
 * 输入：head = [1,2,3,4,5], k = 1
 * 输出：[1,2,3,4,5]
 *
 * 示例 4：
 * 输入：head = [1], k = 1
 * 输出：[1]
 *
 * 提示：
 * 列表中节点的数量在范围 sz 内
 * 1 <= sz <= 5000
 * 0 <= Node.val <= 1000
 * 1 <= k <= sz
 *
 *
 * @author: bingyu
 * @date: 2022/3/29
 */
public class ReverseKGroup {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        ListNode node = reverseKGroup2(node1, 4);
        System.out.println(node);
    }

    /**TODO:我的算法
     * 思路: 首先按照k进行反转，当小于等于k时的需要进行反转，翻转一次k组时记录该k组反转后的头节点和尾节点，再继续反转后面的k组并同样记录其头节点和尾节点，
     *     并且将之前反转的链表尾节点标记为上一个链表尾节点
     *  1->2->3->4->5->6
     * @param head
     * @param k
     * @return
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1) return head;
        ListNode result = null; //结果链表的头节点
        ListNode newHead = null; //当前链表的头节点指针
        ListNode preTail = null; //上一个链表的尾节点指针
        ListNode tail = null; //当前链表尾节点的指针
        ListNode currentNode = head;
        int count = 0; //用于记录插入新头节点的次数
        while (currentNode != null) {
            ListNode tmp = currentNode.next;
            if (count < k) {
                if (count == 0) tail = currentNode;
                //进行头插
                currentNode.next = newHead;
                newHead = currentNode;
                //头插完毕后自增，用来限制后面的头插次数
                count++;
            } else {
                //执行到这里说明指定k组头插翻转完毕，开始翻转下一个k组并保留其尾节点和头节点
                if (result == null) { //为空说明这次遍历是遍历的第一个k组链表，直接赋值
                    result = newHead;
                }else {
                    preTail.next = newHead; //不为空，则只需要将上一个k组链表的尾节点指向当前k组链表的头节点即可！
                }
                newHead = null;
                preTail = tail; //因为当前的链表已经遍历完，要开始遍历下一个节点，因此将当前链表尾节点标记为前一个链表尾节点
                tail = null; //当前链表尾节点置为null
                count = 0; //重新将其设置为0
                continue;
            }
            currentNode = tmp;
        }
        //执行到这里说明k组的链表全部遍历完毕
        if (preTail == null) { //preTail为空说明整个链表就是一个k组链表
            result = newHead;
        }else if (count == k){
            //执行到这里，后面是刚好剩余一个k组，
            preTail.next = newHead;
        }else {
            //执行到这说明是不够k组的,如果有剩余会得到一个反转了的不够k组的newHead链表，
            //我们需要重新将剩余不够k组的链表反转复原
            currentNode = newHead;
            newHead = null;
            while (currentNode != null) { //重新反转复原剩余K组链表
                ListNode tmp = currentNode.next;
                currentNode.next = newHead;
                newHead = currentNode;
                currentNode = tmp;
            }
            preTail.next = newHead;
        }
        return result;
    }


    //1->2->3->4->5->6 比较复杂，推荐上面我自己的思路
    public static ListNode reverseKGroup2(ListNode head, int k) {
        ListNode dummyHead = new ListNode();
        ListNode resultTail = dummyHead; //从虚拟节点开始，表示结果链表的尾节点
        ListNode current = head; //
        while (current != null) {
            int count = 0; //用于记录移动的次数，用来控制指定的k次
            ListNode groupTail = current; //遍历每组链表时的尾节点指针(反转前)
            while (groupTail != null) { //遍历k-1次获取当前组的尾节点
                count++;
                if (count == k) {
                    break;
                }
                groupTail = groupTail.next;
            }
            //执行到这里说明当前组链表不够k个，groupTail指针指向的就是当前组链表的尾节点
            if (groupTail == null) {
                //执行到这说明是最后一组了，
                return dummyHead.next;
            } else {
                //不是最后一组
                ListNode tmp = groupTail.next; //先记录后一组链表的头节点，方便当前组链表反转后找不到
                //反转当前组链表，并得到新的头节点和尾节点
                ListNode[] nodes = reverse(current,k); //注意这里不能传tail，因为我们需要原来的头节点
                resultTail.next = nodes[0]; //将上一组链表的尾节点，指向当前组反转后的头节点
                nodes[1].next = tmp; //反转后的尾节点指向下一组链表的头节点
                resultTail = nodes[1]; //更新结果链表的尾节点
                current = tmp; //将下一组链表的头节点赋给当前节点，因为只需要从下一组链表开始遍历
            }
        }
        return dummyHead.next;
    }

    public static ListNode[] reverse(ListNode head, int k) {
        ListNode current = head;
        ListNode newHead = null; //头节点
        ListNode tail = head; //反转后的尾节点就是现在反转前的头节点
        int count = 0;
        while (current != null && count <k) {
            ListNode tmp = current.next;
            current.next = newHead;
            newHead = current;
            count++;
            current = tmp;
        }
        return new ListNode[] {newHead,tail}; //返回头节点，尾节点
    }


}
