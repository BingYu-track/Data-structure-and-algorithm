package lessons.week6.pratice.btree.part2.pratice3;

import lessons.common.TreeNode;

/** TODO: 需要多次练习
 * @version 1.0 二叉树展开为链表
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
 * @date: 2022/9/13
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

    /*
     我的思路: 在前序遍历期间进行展开，使用虚拟头节点作为起始节点，然后一个tail随时指向末尾，tail不断指向当前root节点
     TODO： 需要多多练习
     执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     内存消耗：41.3 MB, 在所有 Java 提交中击败了13.14%的用户
    */
    public static void flatten(TreeNode root) {
//        preOrder(root);
        inOrder(root);
    }
    private static TreeNode dummyHead = new TreeNode(); //虚拟头节点
    private static TreeNode tail = dummyHead; //记录链表尾节点

    //在前序遍历的同时进行展开
    private static void preOrder(TreeNode root) {
        if (root == null) return;
        TreeNode left = root.left; //
        TreeNode right = root.right; //这个我怎么存
        tail.right = root; //将当前root作为下一个节点
        tail = root;
        root.left = null;
        preOrder(left);
        preOrder(right);
    }

    //中序遍历 3 2 4 1 5 6
    private static void inOrder(TreeNode root) {
        if (root == null) return;
        TreeNode left = root.left; //
        TreeNode right = root.right;
        inOrder(left);
        tail.right = root;
        tail = root;
        root.left = null;
        inOrder(right);
    }

}
