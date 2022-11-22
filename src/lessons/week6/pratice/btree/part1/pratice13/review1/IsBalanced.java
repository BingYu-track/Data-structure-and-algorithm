package lessons.week6.pratice.btree.part1.pratice13.review1;

import lessons.common.TreeNode;

/**
 * @version 1.0 剑指 Offer 55 - II. 平衡二叉树 --复习
 * @Description: 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
 *
 * 示例 1:
 * 给定二叉树 [3,9,20,null,null,15,7]
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回 true 。
 *
 *
 * 示例 2:
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 *
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 * 返回false 。
 *
 * 限制：
 * 0 <= 树的结点个数 <= 10000
 *
 * @author: bingyu
 * @date: 2022/11/22
 */
public class IsBalanced {

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


    private static boolean flag = true;

    /*
     判断树是否是平衡二叉树(任意节点的左右子树深度相差不超过1)
     思路: 不断遍历直到子节点开始返回后累积记录深度，并比较左右深度
    */
    private static boolean isBalanced(TreeNode root) {
        depth(root);
        return flag;
    }

    private static int depth(TreeNode root) {
        if (root == null) return 0;
        int leftDepth = depth(root.left);
        if (!flag) return -1; //提前退出
        int rightDepth = depth(root.right);
        if (!flag) return -1;
        if (Math.abs(leftDepth - rightDepth) > 1) flag = false;
        return Math.max(leftDepth,rightDepth) + 1;
    }


}
