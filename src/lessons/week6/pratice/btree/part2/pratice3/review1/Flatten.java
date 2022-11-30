package lessons.week6.pratice.btree.part2.pratice3.review1;

import lessons.common.TreeNode;

/**
 * @version 1.0 二叉树展开为链表 -- 复习
 * @Description: 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 *
 * 示例 1：
 *             1
 *          /    \
 *         2      5     ---->     1->2->3->4->5->6
 *        / \      \
 *       3  4       6
 * 输入：root = [1,2,5,3,4,null,6]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6]
 *
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：root = [0]
 * 输出：[0]
 *
 * 提示：
 * 树中结点数在范围 [0, 2000] 内
 * -100 <= Node.val <= 100
 *
 * @author: bingyu
 * @date: 2022/11/28
 */
public class Flatten {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(5);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(6);

        root.left = node1;
        root.right = node2;

        node1.left = node3;
        node1.right = node4;
        node2.right = node5;
        flatten(root);
        System.out.println(root);
    }

    public static void flatten(TreeNode root) {
        preOrder(root);
    }

    private static TreeNode dummyHead = new TreeNode();//虚拟头节点
    private static TreeNode tail = dummyHead; //保存链表的尾节点;因为需要时刻获取tail，因此要作为共享的成员变量

    /*
     这里重点是如何正确设置链表尾节点，使tail始终保持在末尾
     TODO: 由于该题展开的链表顺序是和前序遍历顺序是一致的，因此需要我们借助前序遍历来解决
    */
    private static void preOrder(TreeNode root) {
        if (root == null) return;
        TreeNode left = root.left;
        TreeNode right = root.right;
        tail.right = root; //尾节点指向下一个尾节点
        tail = root;
        root.left = null;
        preOrder(left);
        preOrder(right);
    }


}
