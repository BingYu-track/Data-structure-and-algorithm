package lessons.week6.pratice.btree.part1.pratice19;

import lessons.common.TreeNode;

/**
 * @version 1.0
 * @Description: 把二叉搜索树转换为累加树--重复练习
 * @author: bingyu
 * @date: 2022/10/25
 */
public class ZgSolved {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(6);
        root.left = node1;
        root.right = node2;
        TreeNode node3 = new TreeNode(0);
        TreeNode node4 = new TreeNode(2);
        node1.left = node3;
        node1.right = node4;
        TreeNode node5 = new TreeNode(3);
        node4.right = node5;
        TreeNode node6 = new TreeNode(5);
        TreeNode node7 = new TreeNode(7);
        node2.left = node6;
        node2.right = node7;
        TreeNode node8 = new TreeNode(8);
        node7.right = node8;
        TreeNode tree = convertBST(root);
        System.out.println();
    }


    private static Integer sum = 0;

    //有图可知需要进行后序遍历，元素进行累加
    public static TreeNode convertBST(TreeNode root) {
        postOrder(root);
        return root;
    }

    private static void postOrder(TreeNode root) {
        if (root == null) return;
        postOrder(root.right);
        sum += root.val;
        root.val = sum;
        postOrder(root.left);
    }


}
