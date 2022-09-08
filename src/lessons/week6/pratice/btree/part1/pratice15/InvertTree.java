package lessons.week6.pratice.btree.part1.pratice15;

import lessons.common.TreeNode;

/**
 * @version 1.0 翻转二叉树
 * @Description: 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
 *
 * 示例 1：
       4               4
     /   \           /   \
    2     7  ---->  7     2
   / \   / \       / \   / \
  1  3  6  9      9  6  3  1
 * 输入：root = [4,2,7,1,3,6,9]
 * 输出：[4,7,2,9,6,3,1]
 *
 * 示例 2：
 *
 *
 *
 * 输入：root = [2,1,3]
 * 输出：[2,3,1]
 *
 * 示例 3：
 * 输入：root = []
 * 输出：[]
 *
 * 提示：
 * 树中节点数目范围在 [0, 100] 内
 * -100 <= Node.val <= 100
 *
 * @author: bingyu
 * @date: 2022/9/1
 */
public class InvertTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode node2 = new TreeNode(2);
        TreeNode node7 = new TreeNode(7);
        TreeNode node1 = new TreeNode(1);
        TreeNode node3 = new TreeNode(3);
        TreeNode node6 = new TreeNode(6);
        TreeNode node9 = new TreeNode(9);
        root.left = node2;
        root.right = node7;

        node2.left = node1;
        node2.right = node3;

        node7.left = node6;
        node7.right = node9;
        TreeNode tree = invertTree(root);
        System.out.println(tree);
    }

    /*
     我的思路: 二叉树翻转，在递归遍历时进行左右交换;注意二叉树翻转后的树和之前树成镜像；也称作镜像树
     执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     内存消耗：39.2 MB, 在所有 Java 提交中击败了26.68%的用户
     */
    public static TreeNode invertTree(TreeNode root) {
        invert(root);
        return root;
    }

    private static void invert(TreeNode root) {
        if (root == null) return;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invert(root.left);
        invert(root.right);
    }


}
