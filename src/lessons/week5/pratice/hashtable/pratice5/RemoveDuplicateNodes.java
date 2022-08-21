package lessons.week5.pratice.hashtable.pratice5;

import lessons.common.ListNode;

import java.util.HashSet;

/**
 * @version 1.0 移出重复节点
 * @Description: 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
 *
 * 示例1:
 *  输入：[1, 2, 3, 3, 2, 1]
 *  输出：[1, 2, 3]
 *
 * 示例2:
 *  输入：[1, 1, 1, 1, 2]
 *  输出：[1, 2]
 *
 * 提示：
 * 链表长度在[0, 20000]范围内。
 * 链表元素在[0, 20000]范围内。
 * 进阶：
 * 如果不得使用临时缓冲区，该怎么解决？
 *
 * @author: bingyu
 * @date: 2022/8/10
 */
public class RemoveDuplicateNodes {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(5);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(2);
        ListNode node6 = new ListNode(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        ListNode node = removeDuplicateNodes(node1);
        System.out.println(node);
    }

    /*
    1-->2-->3-->3-->2-->1
    我的思路: 解法一不使用额外的存储空间 (要保持原来的顺序不变)
    考虑题目描述中的「进阶」部分，是否存在一种不使用临时缓冲区（也就是方法一中的哈希表）的方法呢？
    不幸的是，在保证方法一时间复杂度 O(N)的前提下，是不存在这样的方法的。因此我们需要增加时间复杂度，使得我们可以仅使用常数的空间来完成本题。
    一种简单的方法是，我们在给定的链表上使用两重循环，其中第一重循环从链表的头节点开始，枚举一个保留的节点，这是因为我们保留的是「最开始出现的节点」。
    第二重循环从枚举的保留节点开始，到链表的末尾结束，将所有与保留节点相同的节点全部移除。方法二的细节部分与方法一类似。第一重循环枚举保留的节点本身，
    而为了编码方便，第二重循环可以枚举待移除节点的前驱节点，方便我们对节点进行移除。这样一来，我们使用「时间换空间」的方法，就可以不使用临时缓冲区解决本题了。
    */
    public static ListNode removeDuplicateNodes(ListNode head) {
        ListNode p = head;
        ListNode newHead = new ListNode(Integer.MIN_VALUE);
        while (p != null) { //第一层遍历链表每个节点
            ListNode temp = p.next;
            p.next = null;
            ListNode tail = newHead;
            while (tail != null) { //TODO 第二层循环是遍历结果链表，看有没有重复的，有重复的就跳过当前循环，没有就将其加入结果链表尾部
                if (tail.val == p.val) { //如果p节点与新的节点重复
                    break;
                }
                //执行到这里说明没有break，也就是说没有遇到与p相同的节点，将其放到尾部
                if (tail.next == null) {
                    tail.next = p;
                    break;
                }
                tail = tail.next;
            }
            //执行到这里说明tail遍历完毕
            p = temp;
        }
        return newHead.next;
    }

    /* 1-->2-->3-->3-->2-->1 TODO:推荐该方法，需要多次练习
       使用哈希表
       执行用时：4 ms, 在所有 Java 提交中击败了96.38%的用户
       内存消耗：42.5 MB, 在所有 Java 提交中击败了75.81%的用户
    */
    public static ListNode removeDuplicateNodes2(ListNode head) {
        HashSet<Integer> set = new HashSet<>();
        ListNode newHead = new ListNode();
        ListNode vm = newHead; //虚拟头节点
        ListNode p = head;
        while (p != null) {
            ListNode temp = p.next; //存储p的后继节点的临时变量
            if (!set.contains(p.val)) { //不包含对应的值
                p.next = null;
                set.add(p.val); //哈希表里添加节点值
                vm.next = p; //将节点加到结果链表末尾
                vm = vm.next;
            }
            p = temp;
        }
        return newHead.next;
    }

    /**
     * leetcode官方题解
     * 执行用时：315 ms, 在所有 Java 提交中击败了6.16%的用户
     * 内存消耗：41.9 MB, 在所有 Java 提交中击败了90.79%的用户
     */
    public static ListNode removeDuplicateNodes3(ListNode head) {
        ListNode p = head;
        while (p != null) {
            ListNode oc = p; //从头节点开始
            while (oc.next != null) { //第二层循环从头节点的后继节点开始，类似i和j=i+1进行比较
                if (oc.next.val == p.val) { //如果遇到相同的，就删除oc的下一个节点，即next指向后继的后继节点
                    oc.next = oc.next.next;
                } else {
                    oc = oc.next; //不相同，正常往后遍历
                }
            }
            p = p.next;
        }
        return head;
    }




}
