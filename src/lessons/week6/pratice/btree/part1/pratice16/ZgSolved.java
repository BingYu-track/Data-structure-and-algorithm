package lessons.week6.pratice.btree.part1.pratice16;

import lessons.common.TreeNode;

/**
 * @version 1.0
 * @Description: 对称二叉树
 * @author: bingyu
 * @date: 2022/9/6
 */
public class ZgSolved {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode leftNode2 = new TreeNode(2);
        TreeNode rightNode2 = new TreeNode(2);
        TreeNode leftNode3 = new TreeNode(3);
        TreeNode rightNode3 = new TreeNode(3);
        TreeNode leftNode4 = new TreeNode(4);
        TreeNode rightNode4 = new TreeNode(4);
        TreeNode leftNode5 = new TreeNode(5);
        TreeNode rightNode5 = new TreeNode(5);
        TreeNode leftNode6 = new TreeNode(6);
        TreeNode rightNode6 = new TreeNode(6);
        root.left = leftNode2;
        root.right = rightNode2;

        leftNode2.left = leftNode3;
        leftNode2.right = rightNode4;

        rightNode2.left = leftNode4;
        rightNode2.right = rightNode3;

//        leftNode3.left = leftNode5;
//        rightNode4.right = leftNode6;
//
//        rightNode3.left = rightNode5;
//        leftNode4.right = rightNode6;
        boolean symmetric = isSymmetric(root);
        System.out.println(symmetric);
    }

    /*
     争哥解法: 思路一样
    */
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSymmetric(root.left, root.right);
    }

    private static boolean isSymmetric(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p != null && q != null && p.val == q.val) {
            return isSymmetric(p.right, q.left) && isSymmetric(p.left, q.right);
        }
        return false;
    }

}
