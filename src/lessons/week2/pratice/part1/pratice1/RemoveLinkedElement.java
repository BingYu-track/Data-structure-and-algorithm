package lessons.week2.pratice.part1.pratice1;

import lessons.week2.ListNode;

/**
 * @version 1.0 移除链表元素
 * @Description: 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点
 * 、
 * 示例 1：
 * 输入：head = [1,2,6,3,4,5,6], val = 6
 * 输出：[1,2,3,4,5]
 *
 * 示例 2：
 * 输入：head = [], val = 1
 * 输出：[]
 *
 * 示例 3：
 * 输入：head = [7,7,7,7], val = 7
 * 输出：[]
 *
 *
 * 提示：
 * 列表中的节点数目在范围 [0, 104] 内
 * 1 <= Node.val <= 50
 * 0 <= val <= 50
 *
 * @author: bingyu
 * @date: 2022/3/14
 */
public class RemoveLinkedElement {

    //1111111
    public static void main(String[] args) {
        ListNode node1 = new ListNode(6);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(6);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(4);
        ListNode node6 = new ListNode(5);
        ListNode node7 = new ListNode(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        //node6.next = node7;
        ListNode listNode = removeElements2(node1, 6);
        System.out.println(listNode);
    }

    //TODO: 这题要重点多做，多重温复习
    //1->4->2->1->1->3->1 注意2个特殊情况，一个是头结点和目标值一样，一个是空链表
    public static ListNode removeElements(ListNode head, int val) {
        ListNode newHead = new ListNode(); //使用哨兵节点
        newHead.next = head;
        ListNode prevNode = newHead; //用来记录当前节点的上一个结点
//        while (newHead.val == val) { //TODO: 使用了哨兵节点后，我就可以不用对头节点进行特殊处理了
//            head = head.next;
//        }
        ListNode currentNode = head;
        while (currentNode != null) {
            if (currentNode.val == val) {
                prevNode.next = currentNode.next; //重要:如果当前节点等于目标值，此时上一个节点prevNode无需变化，只需要将next指向当前节点的下一个节点即可
            }else {
                prevNode = currentNode;
            }
            currentNode = currentNode.next;
        }
        //return head;
        return newHead.next;
    }









    //1->4->2->1->1->3->1 思路:遍历时记录前驱节点，当遇到删除的节点时，将前驱节点指向当前节点的后继节点
    public static ListNode removeElements2(ListNode head, int val) {
        ListNode p = head;
        ListNode newHead = new ListNode();
        newHead.next = head; //TODO:注意该代码必须加上，因为如果不加上该代码的话，newHead在后面的执行过程中就永远不会联结到我们的链表，
        // 除非第一个元素就是要删除的元素，这就是为什么1->4->2->1->1->3->1链表可以成功执行的原因
        ListNode prev = newHead; //如果这里前驱节点为null，那么如果头节点就是要删除的元素，那么后面prev.next=p.next就会报错
        while (p != null) {
            if (p.val == val) {
                prev.next = p.next; //虽然这里删除了p节点，但是变量p指向的节点的next仍然指向原来后面的节点
            }else {
                prev = p;
            }
            p = p.next;
        }
        return newHead.next;
    }

}
