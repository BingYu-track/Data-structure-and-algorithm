package lessons.week6.pratice.btree.part1.pratice15;

import lessons.common.TreeNode;

/**
 * @version 1.0
 * @Description: 翻转二叉树--复习
 * @author: bingyu
 * @date: 2022/10/13
 */
public class ZgSolved {

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
    二叉树翻转复习--直接递归左右子树交换即可
    */
    private static TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode temp = null;
        temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }


}
