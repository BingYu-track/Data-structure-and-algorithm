package lessons.week6.pratice.btree.part1.pratice13;

import lessons.common.TreeNode;

/**
 * @version 1.0
 * @Description: 剑指 Offer 55 - II. 平衡二叉树 --复习
 * @author: bingyu
 * @date: 2022/10/12
 */
public class ZgSolved {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(4);
        root.left = node1;
        root.right = node2;
        node2.left = node3;
        node2.right = node4;
        node3.left = node5;
        node3.right = node6;
        boolean balanced = isBalanced(root);
        System.out.println(balanced);
    }

    /*
    复习--判断一棵树是否是平衡二叉树，即树的任意节点，它的左右子树高度是否都相差小于等于1
         在向下遍历的过程中记录节点的左右子树的深度，一旦发现左右子树深度相差大于等于1，就返回false
    */

    private static boolean isBalanced = true;

    public static boolean isBalanced(TreeNode root) {
        dfs(root,0);
        return isBalanced;
    }

    /*

    */
    private static int dfs(TreeNode root, int depth) {
        if (root == null) return 0;
        int leftDepth = dfs(root.left, depth); //左子树深度
        if (!isBalanced) return -1;
        int rightDepth = dfs(root.right, depth); //右子树深度
        if (!isBalanced) return -1;
        if (Math.abs(leftDepth - rightDepth) > 1) { //左右子树深度相差大于1
            isBalanced = false;
        }
        return Math.max(leftDepth,rightDepth) + 1;
    }

}
