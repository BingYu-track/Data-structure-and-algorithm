package lessons.week6.pratice.btree.part1.pratice11;

import lessons.common.TreeNode;

/**
 * @version 1.0
 * @Description: 二叉树的最大深度--复习
 * @author: bingyu
 * @date: 2022/10/11
 */
public class ZgSolved {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(7);
        root.left = node1;
        root.right = node2;

        node2.left = node3;
        node2.right = node4;
        int depth = maxDepth(root);
        System.out.println(depth);
    }

    /*
     复习
    */
    private static int maxDepth(TreeNode root) {
        int depth = dfs(root,0);
        return depth;
    }

    /**
     *
     * @param root
     * @param depth 当前root节点所处的深度
     * @return
     */
    private static int dfs(TreeNode root, int depth) {
        if (root == null) return depth;
        int leftDepth = dfs(root.left,depth); //左子树深度
        int rightDepth = dfs(root.right,depth); //右子树深度
        return Math.max(leftDepth,rightDepth) + 1; //比较左子树和右子树的深度，并取最大值，然后加1就是当前root的最大深度
    }


}
