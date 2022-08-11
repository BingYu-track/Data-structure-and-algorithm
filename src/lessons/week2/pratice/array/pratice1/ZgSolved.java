package lessons.week2.pratice.array.pratice1;

import lessons.common.ListNode;

/**
 * @version 1.0
 * @Description: 移除链表元素
 * @author: bingyu
 * @date: 2022/3/15
 */
public class ZgSolved {



    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(1);
        ListNode node5 = new ListNode(1);
        ListNode node6 = new ListNode(3);
        ListNode node7 = new ListNode(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        ListNode listNode = removeElements3(node1, 1);
        System.out.println(listNode);
    }

    /**
     * 争哥解法--新建一个虚拟节点作为头节点并作为新的链表--结果链表，然后用tail节点始终指向结果链表的尾节点，当遍历原链表时，把符合条件的每个节点插入到
     * 尾部即可！
     * 1->4->2->1->1->3->1
     */
    public static ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;
        ListNode newHead = new ListNode(); //使用虚拟节点
        ListNode tail = newHead;  //
        ListNode p = head;
        while (p != null) {
            ListNode tmp = p.next; //将下一个节点临时存储
            if (p.val != val) { //只有
                p.next = null;
                tail.next = p; //第一次执行，会将虚拟节点作为头节点，然后将当前遍历的节点作为头节点的后继节点
                tail = p;
            }
            p = tmp; //如果当前p节点是要删除的节点，则将临时存储的下一个节点负责给当前节点
        }
        return newHead.next;
    }


    /**
     * 1->4->2->1->1->3->1
     * 链表题是真难！
     * 这题是真难！后面需要重复练习
     */
    public static ListNode removeElements2(ListNode head, int val) {
        ListNode newHead = new ListNode(); //虚拟节点作为头节点
        ListNode tail = newHead; //虽然newHead作为头节点，但刚开始就一个节点，也可以作为尾节点
        ListNode currentNode = head;
        while (currentNode != null) {
            ListNode tmp = currentNode.next; //
            if (currentNode.val != val) {
                currentNode.next = null; //这里是为了保证后面尾节点始终为null
                tail.next = currentNode; //这样做是因为，只要遇到要删除的节点currentNode，就不会指向这条语句，tail.next也就不会指向要删除的节点
                tail = currentNode;
            }
            //执行到这里可能是要删除的节点，如果是要删除的节点，
             //注意是把变量里的地址赋值给tail
            currentNode = tmp; //这里currentNode变量被重新赋值后不会影响到上面的tail变量，但是如果直接修改currentNode是会影响到tail的
        }
        return newHead.next;
    }

    //删除指定的元素 1->2->3->4->5
    //
    public static ListNode removeElements3(ListNode head, int val) {
        ListNode sentinel = new ListNode(); //哨兵节点
        sentinel.next = head; //TODO:注意这行代码不能省
        ListNode current = head;
        ListNode prev = sentinel;
        while (current != null) {
            if (current.val == val) {
                prev.next = current.next;
            }else {
                prev = current; //正常情况下作为下一个循环的前驱节点，如果当前节点刚好是要删除的节点，则该节点不能再作为上个节点了
            }
            current = current.next;
        }
        return sentinel.next;
    }
}
