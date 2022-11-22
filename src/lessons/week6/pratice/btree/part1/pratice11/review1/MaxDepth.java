package lessons.week6.pratice.btree.part1.pratice11.review1;

import lessons.common.TreeNode;

/**
 * @version 1.0 二叉树的最大深度 -- 复习
 * @Description: 给定一个二叉树，找出其最大深度。二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 说明:叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度3 。
 * @author: bingyu
 * @date: 2022/11/22
 */
public class MaxDepth {

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

    //思路是一直递归到子节点，子节点返回时增加1个深度即可，后面每次返回都会加1，直到返回根节点，加的次数就是最大深度
    private static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return Math.max(leftDepth,rightDepth) + 1;
    }


}
