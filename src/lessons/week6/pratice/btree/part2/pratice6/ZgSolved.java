package lessons.week6.pratice.btree.part2.pratice6;

import lessons.common.ListNode;
import lessons.common.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @version 1.0 面试题 04.03. 特定深度节点链表 -- 复习
 * @Description: 给定一棵二叉树，设计一个算法，创建含有某一深度上所有节点的链表（比如，若一棵树的深度为 D，则会创建出 D 个链表）。
 * 返回一个包含所有深度的链表的数组。
 *
 * 示例：
 *
 * 输入：[1,2,3,4,5,null,7,8]
 *
 *          1
 *        /  \
 *       2    3
 *      / \    \
 *     4   5    7
 *    /
 *   8
 *
 * 输出：[[1],[2,3],[4,5,7],[8]]
 *
 * @author: bingyu
 * @date: 2022/9/14
 */
public class ZgSolved {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        root.left = node1;
        root.right = node2;
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        node1.left = node3;
        node1.right = node4;

        TreeNode node5 = new TreeNode(7);
        node2.right = node5;
        TreeNode node6 = new TreeNode(8);
        node3.left = node6;
        ListNode[] listNodes = listOfDepth(root);
        System.out.println(listNodes);
    }


    /*
     我的思路: 就是按层进行遍历，每层元素构造为链表存进数组
    */
    public static ListNode[] listOfDepth(TreeNode tree) {
        ArrayList<ListNode> nodes = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(tree);
        while (!queue.isEmpty()) {
            int size = queue.size();
            ListNode dummyHead = new ListNode(-1); //虚拟头节点
            ListNode tail = dummyHead; //链表尾指针
            for (int i = 0;i<size;i++) {
                TreeNode treeNode = queue.poll();
                if (treeNode!=null) {
                    ListNode node = new ListNode(treeNode.val); //创建当前链表节点
                    tail.next = node; //尾指针指向刚创建的链表节点
                    tail = node;
                    TreeNode left = treeNode.left;
                    if (left!=null) queue.add(left);
                    TreeNode right = treeNode.right;
                    if (right!=null) queue.add(right);

                }
            }
            //执行到这说明一层已经执行完
            if (dummyHead.next!=null) {
                nodes.add(dummyHead.next);
            }
        }
        return nodes.toArray(new ListNode[nodes.size()]);
    }

}
